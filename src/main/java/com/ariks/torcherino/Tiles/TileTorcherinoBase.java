package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiPacket;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.Register.AccelerationRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TileTorcherinoBase extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    private final Random rand = new Random();
    private EnumParticleTypes prac;
    public int modPrac, count, radius, speed, xMin, yMin, zMin, xMax, yMax, zMax;
    public double stepCount = 0.10;
    public boolean booleanWork, booleanSpawnPrac;
    private int oldRadius, oldSpeed, oldModPrac;
    private double oldStepCount;
    private boolean oldBooleanWork, oldBooleanSpawnPrac;
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
        if (booleanSpawnPrac || booleanWork) {
            UpdateChangeArea();
        }
        if (Config.PracTicleSpawn && booleanSpawnPrac) {
            spawnPrac();
            UpdateModePrac();
        }
        if (radius != 0 && speed != 0 && booleanWork) {
            UpdateTickArea();
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiPacket(pos, booleanWork, booleanSpawnPrac, radius, speed, modPrac, stepCount), packetTargetPoint);
            updateOldValues();
            Torcherino.logger.debug("Send Packet update GUI");
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return oldBooleanWork != booleanWork || oldBooleanSpawnPrac != booleanSpawnPrac || oldRadius != radius || oldSpeed != speed || oldModPrac != modPrac || oldStepCount != stepCount;
    }
    private void updateOldValues() {
        oldBooleanWork = booleanWork;
        oldBooleanSpawnPrac = booleanSpawnPrac;
        oldRadius = radius;
        oldSpeed = speed;
        oldModPrac = modPrac;
        oldStepCount = stepCount;
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
    public void spawnPrac() {
        this.count++;
        if (count >= Config.tickCount) {
            this.count = 0;
            if (world instanceof WorldServer) {
                double xMinPrac = xMin;
                double xMaxPrac = xMax + 1;
                double zMinPrac = zMin;
                double zMaxPrac = zMax + 1;
                double yMinPrac = yMin;
                double yMaxPrac = yMax + 1;
                WorldServer worldServer = (WorldServer) world;
                for (double x = xMinPrac; x <= xMaxPrac; x += stepCount) {
                    worldServer.spawnParticle(prac, x, yMaxPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, x, yMaxPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double z = zMinPrac; z <= zMaxPrac; z += stepCount) {
                    worldServer.spawnParticle(prac, xMinPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, xMaxPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double x = xMinPrac; x <= xMaxPrac; x += stepCount) {
                    worldServer.spawnParticle(prac, x, yMinPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, x, yMinPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double z = zMinPrac; z <= zMaxPrac; z += stepCount) {
                    worldServer.spawnParticle(prac, xMinPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, xMaxPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double y = yMinPrac; y <= yMaxPrac; y += stepCount) {
                    worldServer.spawnParticle(prac, xMinPrac, y, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, xMaxPrac, y, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double y = yMinPrac; y <= yMaxPrac; y += stepCount) {
                    worldServer.spawnParticle(prac, xMinPrac, y, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                    worldServer.spawnParticle(prac, xMaxPrac, y, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                }
            }
        }
    }
    public void toggleSpeed() {speed = (byte) ((speed + 1) % SpeedModes());}
    public void decreaseSpeed() {speed = (byte) ((speed - 1 + SpeedModes()) % SpeedModes());}
    public void toggleArea() {radius = (radius + 1) % Radius();}
    public void decreaseRadius() {radius = (radius - 1 + Radius()) % Radius();}
    public void toggleParticle() {modPrac = (modPrac + 1) % 7;}
    public void UpdateModePrac() {
        switch (modPrac) {
            case 0:
                prac = EnumParticleTypes.FLAME;
                break;
            case 1:
                prac = EnumParticleTypes.DRAGON_BREATH;
                break;
            case 2:
                prac = EnumParticleTypes.REDSTONE;
                break;
            case 3:
                prac = EnumParticleTypes.END_ROD;
                break;
            case 4:
                prac = EnumParticleTypes.SPELL;
                break;
            case 5:
                prac = EnumParticleTypes.VILLAGER_HAPPY;
                break;
            case 6:
                prac = EnumParticleTypes.SMOKE_NORMAL;
                break;
        }
    }
    public void toggleWork() {booleanWork = !booleanWork;}
    public void toggleSpawnPrac() {booleanSpawnPrac = !booleanSpawnPrac;}
    public void toggleStepCount(){
        stepCount +=0.05;
        if(stepCount > 5.00){
            stepCount = 0.10;
        }
    }
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setBoolean("work", this.booleanWork);
        tagCompound.setBoolean("spawn", this.booleanSpawnPrac);
        tagCompound.setInteger("speed", this.speed);
        tagCompound.setInteger("modprac", this.modPrac);
        tagCompound.setInteger("currentmode", this.radius);
        tagCompound.setInteger("count", this.count);
        tagCompound.setDouble("stepcount", this.stepCount);
        return super.writeToNBT(tagCompound);
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.booleanWork = tagCompound.getBoolean("work");
        this.booleanSpawnPrac = tagCompound.getBoolean("spawn");
        this.speed = tagCompound.getInteger("speed");
        this.modPrac = tagCompound.getInteger("modprac");
        this.radius = tagCompound.getInteger("currentmode");
        this.count = tagCompound.getInteger("count");
        this.stepCount = tagCompound.getDouble("stepcount");
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