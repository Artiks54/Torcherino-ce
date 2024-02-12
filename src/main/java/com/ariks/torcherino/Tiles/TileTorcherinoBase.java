package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiPacket;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.Register.AccelerationRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TileTorcherinoBase extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    private final Random rand = new Random();
    public int radius, speed, xMin, yMin, zMin, xMax, yMax, zMax;
    public boolean booleanWork, booleanRender;
    private int oldRadius, oldSpeed;
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
        if (booleanRender || booleanWork) {
            UpdateChangeArea();
        }
        if (radius != 0 && speed != 0 && booleanWork) {
            UpdateTickArea();
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiPacket(pos, booleanWork, booleanRender, radius, speed), packetTargetPoint);
            updateOldValues();
            Torcherino.logger.debug("Send Packet update GUI");
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
        double xMinRender = -radius;
        double yMinRender = -radius;
        double zMinRender = -radius;
        double xMaxRender = 1D+radius;
        double yMaxRender = 1D+radius;
        double zMaxRender = 1D+radius;
        return new AxisAlignedBB(xMinRender, yMinRender, zMinRender, xMaxRender, yMaxRender, zMaxRender);
    }
    public void toggleSpeed() {speed = (byte) ((speed + 1) % SpeedModes());}
    public void decreaseSpeed() {speed = (byte) ((speed - 1 + SpeedModes()) % SpeedModes());}
    public void toggleArea() {radius = (radius + 1) % Radius();}
    public void decreaseRadius() {radius = (radius - 1 + Radius()) % Radius();}
    public void toggleWork() {booleanWork = !booleanWork;}
    public void toggleRender() {booleanRender = !booleanRender;}
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setBoolean("work", this.booleanWork);
        tagCompound.setBoolean("render", this.booleanRender);
        tagCompound.setInteger("speed", this.speed);
        tagCompound.setInteger("currentmode", this.radius);
        tagCompound.setBoolean("oldWork", this.oldBooleanWork);
        tagCompound.setBoolean("oldRender", this.oldBooleanRender);
        tagCompound.setInteger("oldSpeed", this.oldSpeed);
        tagCompound.setInteger("oldCurrentmode", this.oldRadius);
        return super.writeToNBT(tagCompound);
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.booleanWork = tagCompound.getBoolean("work");
        this.booleanRender = tagCompound.getBoolean("render");
        this.speed = tagCompound.getInteger("speed");
        this.radius = tagCompound.getInteger("currentmode");
        this.oldBooleanWork = tagCompound.getBoolean("oldWork");
        this.oldBooleanRender = tagCompound.getBoolean("oldRender");
        this.oldSpeed = tagCompound.getInteger("oldSpeed");
        this.oldRadius = tagCompound.getInteger("oldCurrentmode");
        super.readFromNBT(tagCompound);
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
    public void onLoad() {
        this.world.getBlockState(pos);
        if (!this.world.isRemote) {
                this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 64);
        }
    }
}