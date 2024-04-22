package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiPacket;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.Register.AccelerationRegistry;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public abstract class TileTorcherinoBase extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    private final Random rand = new Random();
    public int radius, speed,booleanMode, xMin, yMin, zMin, xMax, yMax, zMax;
    public boolean booleanRender;
    public boolean redstoneSignal;
    private boolean redstoneMode;
    private int oldRadius, oldSpeed,cooldown,oldBooleanMode;
    private boolean oldBooleanRender;
    protected int Radius() {
        return 1 ;
    }
    protected int speedBase(int base) {
        return base;
    }
    protected int SpeedModes() {
        return 1;
    }
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    @Override
    public void update() {
        if (world.isRemote) return;
        updateGui();
        CheckRedstoneSignal();
        if (redstoneMode || (booleanMode == 1 && Config.BooleanVisualWork && radius != 0 && speed != 0)) {
            WorkVisual();
        }
        if (booleanRender || booleanMode == 1 || redstoneMode) {
            UpdateChangeArea();
        }
        if (radius != 0 && speed != 0 && (booleanMode == 1 || redstoneMode)) {
            UpdateTickArea();
        }
    }
    public void CheckRedstoneSignal() {
        redstoneMode = redstoneSignal && booleanMode == 2;
    }
    public void WorkVisual() {
        cooldown++;
        if (cooldown >= 15) {
            cooldown = 0;
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
                EnumParticleTypes parc = EnumParticleTypes.FLAME;
                ((WorldServer) world).spawnParticle(parc, x + 0.5, y + 1.2, z + 0.5, 1, 0, 0, 0, 0, new int[0]);
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            updateOldValues();
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiPacket(pos, booleanRender, radius, speed, booleanMode), packetTargetPoint);
            if (Config.DebugMod) {
                Torcherino.logger.debug("Send Packet update GUI Tile Torcherino");
            }
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return oldBooleanRender != booleanRender || oldRadius != radius || oldSpeed != speed || oldBooleanMode != booleanMode;
    }
    private void updateOldValues() {
        oldBooleanRender = booleanRender;
        oldRadius = radius;
        oldSpeed = speed;
        oldBooleanMode = booleanMode;
    }
    private void UpdateChangeArea() {
        BlockPos minPos = pos.add(-radius, -radius, -radius);
        BlockPos maxPos = pos.add(radius, radius, radius);
        xMin = minPos.getX();
        yMin = minPos.getY();
        zMin = minPos.getZ();
        xMax = maxPos.getX();
        yMax = maxPos.getY();
        zMax = maxPos.getZ();
    }
    private void UpdateTickArea() {
        for (BlockPos pos : BlockPos.getAllInBox(xMin, yMin, zMin, xMax, yMax, zMax)) {
            AcelerationTick(pos);
        }
    }
    private void AcelerationTick(BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || AccelerationRegistry.isBlockBlacklisted(block)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = speedBase(speed);
            for (int i = 0; i < speedBase; i++) {
                if (world.getBlockState(pos) != blockState) {
                    break;
                }
                block.updateTick(world, pos, blockState, rand);
            }
        }
        if (block.hasTileEntity(world.getBlockState(pos))) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile == null || tile.isInvalid()) {
                return;
            }
            if (AccelerationRegistry.isTileBlacklisted(tile.getClass())) {
                return;
            }
            int speedBase = speedBase(speed);
            for (int i = 0; i < speedBase; i++) {
                if (tile.isInvalid()) {
                    break;
                }
                if (tile instanceof ITickable) {
                    ((ITickable) tile).update();
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getAABBForRender() {
        return new AxisAlignedBB(-radius, -radius, -radius, radius+1, radius+1, radius+1);
    }
    public void toggleWorking(boolean increase){booleanMode = (byte) ((booleanMode + (increase ? 1 : -1) + 3) % 3);}
    public void toggleSpeed(boolean increase){speed = (byte) ((speed + (increase ? 1 : -1) + SpeedModes()) % SpeedModes());}
    public void toggleArea(boolean increase){radius = (byte) ((radius + (increase ? 1 : -1) + Radius()) % Radius());}
    public void toggleRender() {booleanRender = !booleanRender;}
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setBoolean("render", this.booleanRender);
        nbt.setBoolean("oldRender", this.oldBooleanRender);
        nbt.setInteger("speed", this.speed);
        nbt.setInteger("currentmode", this.radius);
        nbt.setInteger("BooleanMode",this.booleanMode);
        nbt.setInteger("oldSpeed", this.oldSpeed);
        nbt.setInteger("oldCurrentmode", this.oldRadius);
        nbt.setInteger("OldBooleanMode",this.oldBooleanMode);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.booleanRender = nbt.getBoolean("render");
        this.oldBooleanRender = nbt.getBoolean("oldRender");
        this.speed = nbt.getInteger("speed");
        this.radius = nbt.getInteger("currentmode");
        this.booleanMode = nbt.getInteger("BooleanMode");
        this.oldSpeed = nbt.getInteger("oldSpeed");
        this.oldRadius = nbt.getInteger("oldCurrentmode");
        this.oldBooleanMode = nbt.getInteger("OldBooleanMode");
        super.readFromNBT(nbt);
    }
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }
    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }
    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
        this.getWorld().markBlockRangeForRenderUpdate(this.pos, this.pos);
    }
    @Override
    public boolean shouldRefresh(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState oldState, @NotNull IBlockState newSate) {
        return super.shouldRefresh(world, pos, oldState, newSate);
    }
    @Override
    public void onLoad() {
        this.world.getBlockState(pos);
        if (!this.world.isRemote) {
                this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 64);
        }
    }
}