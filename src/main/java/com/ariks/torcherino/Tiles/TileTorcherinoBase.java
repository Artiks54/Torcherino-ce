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
    public int radius, speed, xMin, yMin, zMin, xMax, yMax, zMax;
    public boolean booleanWork, booleanRender;
    private int oldRadius, oldSpeed,cooldown;
    private boolean oldBooleanWork, oldBooleanRender;
    protected int Radius() {
        return 1 ;
    }
    protected int speedBase(int base) {
        return base;
    }
    protected int SpeedModes() {
        return 1;
    }
    @Override
    public void update() {
        if (world.isRemote)return;
        updateGui();
        if(booleanWork && Config.BooleanVisualWork && radius != 0 && speed != 0) {
            WorkVisual();
        }
        if (booleanRender || booleanWork) {
            UpdateChangeArea();
        }
        if (radius != 0 && speed != 0 && booleanWork) {
            UpdateTickArea();
        }
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
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiPacket(pos, booleanWork, booleanRender, radius, speed), packetTargetPoint);
            if (Config.DebugMod) {
                Torcherino.logger.debug("Send Packet update GUI Tile Torcherino");
            }
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return oldBooleanWork != booleanWork || oldBooleanRender != booleanRender || oldRadius != radius || oldSpeed != speed;
    }
    private void updateOldValues() {
        oldBooleanWork = booleanWork;
        oldBooleanRender = booleanRender;
        oldRadius = radius;
        oldSpeed = speed;
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
    public void toggleSpeed(boolean increase){speed = (byte) ((speed + (increase ? 1 : -1) + SpeedModes()) % SpeedModes());}
    public void toggleArea(boolean increase){radius = (byte) ((radius + (increase ? 1 : -1) + Radius()) % Radius());}
    public void toggleWork() {booleanWork = !booleanWork;}
    public void toggleRender() {booleanRender = !booleanRender;}
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setBoolean("work", this.booleanWork);
        nbt.setBoolean("render", this.booleanRender);
        nbt.setInteger("speed", this.speed);
        nbt.setInteger("currentmode", this.radius);
        nbt.setBoolean("oldWork", this.oldBooleanWork);
        nbt.setBoolean("oldRender", this.oldBooleanRender);
        nbt.setInteger("oldSpeed", this.oldSpeed);
        nbt.setInteger("oldCurrentmode", this.oldRadius);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.booleanWork = nbt.getBoolean("work");
        this.booleanRender = nbt.getBoolean("render");
        this.speed = nbt.getInteger("speed");
        this.radius = nbt.getInteger("currentmode");
        this.oldBooleanWork = nbt.getBoolean("oldWork");
        this.oldBooleanRender = nbt.getBoolean("oldRender");
        this.oldSpeed = nbt.getInteger("oldSpeed");
        this.oldRadius = nbt.getInteger("oldCurrentmode");
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