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
    private final Random rand;
    private EnumParticleTypes prac;
    public int modPrac,count,radius, speed, xMin, yMin, zMin, xMax, yMax, zMax;
    public double stepCount = 0.10;
    public boolean booleanWork,booleanSpawnPrac;
    private int oldRadius,oldSpeed,oldModPrac;
    private double oldStepCount;
    private boolean oldBooleanWork,oldBooleanSpawnPrac;
    public TileTorcherinoBase() {
        this.rand = new Random();
    }
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
        if (this.world.isRemote) return;
        this.updateGui();
        if(booleanSpawnPrac || booleanWork ) {
            this.UpdateChangeArea();
        }
        if (Config.PracTicleSpawn && booleanSpawnPrac) {
            this.spawnPrac();
            this.UpdateModePrac();
        }
        if (this.radius != 0 && this.speed != 0 && this.booleanWork) {
            this.UpdateTickArea();
        }
    }
    public void updateGui() {
        if (
                oldBooleanWork != this.booleanWork
                || oldBooleanSpawnPrac != this.booleanSpawnPrac
                || oldRadius != this.radius
                || oldSpeed != this.speed
                || oldModPrac != this.modPrac
                || oldStepCount != this.stepCount
        ){
        ModPacketHandler.network.sendToAllTracking(new UpdateGuiPacket
        (
                this.pos,
                this.booleanWork,
                this.booleanSpawnPrac,
                this.radius,
                this.speed,
                this.modPrac,
                this.stepCount
        ), this.packetTargetPoint);
            oldBooleanWork = this.booleanWork;
            oldBooleanSpawnPrac = this.booleanSpawnPrac;
            oldRadius = this.radius;
            oldSpeed = this.speed;
            oldModPrac = this.modPrac;
            oldStepCount = this.stepCount;
            Torcherino.logger.debug("Send Packet update GUI");
        }
    }
    private void UpdateChangeArea() {
        xMin = pos.getX() - radius;
        yMin = pos.getY() - radius;
        zMin = pos.getZ() - radius;
        xMax = pos.getX() + radius;
        yMax = pos.getY() + radius;
        zMax = pos.getZ() + radius;
    }
    private void UpdateTickArea() {
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    AcelerationTick(new BlockPos(x, y, z));
                }
            }
        }
    }
    private void AcelerationTick(BlockPos pos) {
        IBlockState blockState = this.world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || AccelerationRegistry.isBlockBlacklisted(block)) {
            return;
        }
        if (block.getTickRandomly()) {
            for (int i = 0; i < this.speedBase(this.speed); i++) {
                if (getWorld().getBlockState(pos) != blockState) break;
                block.updateTick(this.world, pos, blockState, this.rand);
            }
        }
        if (block.hasTileEntity(this.world.getBlockState(pos))) {
            TileEntity tile = this.world.getTileEntity(pos);
            if (tile == null || tile.isInvalid()) {
                return;
            }
            if (AccelerationRegistry.isTileBlacklisted(tile.getClass())) return;
            for (int i = 0; i < this.speedBase(this.speed); i++) {
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
                double xMinPrac, yMinPrac, zMinPrac, xMaxPrac, yMaxPrac, zMaxPrac;
                xMinPrac = xMin;
                xMaxPrac = xMax + 1;
                zMinPrac = zMin;
                zMaxPrac = zMax + 1;
                yMinPrac = yMin;
                yMaxPrac = yMax + 1;
                double step = stepCount;
                //Верхния сторона Спавн частиц
                for (double x = xMinPrac; x <= xMaxPrac; x += step) {
                    ((WorldServer) world).spawnParticle(prac, x, yMaxPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, x, yMaxPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double z = zMinPrac; z <= zMaxPrac; z += step) {
                    ((WorldServer) world).spawnParticle(prac, xMinPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, xMaxPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                }
                //Нижния сторона Спан частиц
                for (double x = xMinPrac; x <= xMaxPrac; x += step) {
                    ((WorldServer) world).spawnParticle(prac, x, yMinPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, x, yMinPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double z = zMinPrac; z <= zMaxPrac; z += step) {
                    ((WorldServer) world).spawnParticle(prac, xMinPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, xMaxPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                }
                //Грани Спавн частиц
                for (double y = yMinPrac; y <= yMaxPrac; y += step) {
                    ((WorldServer) world).spawnParticle(prac, xMinPrac, y, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, xMaxPrac, y, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                }
                for (double y = yMinPrac; y <= yMaxPrac; y += step) {
                    ((WorldServer) world).spawnParticle(prac, xMinPrac, y, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                    ((WorldServer) world).spawnParticle(prac, xMaxPrac, y, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                }
            }
        }
    }

    public void toggleSpeed() {speed = (byte) ((speed + 1) % SpeedModes());}
    public void decreaseSpeed() {
        speed = (speed - 1) % SpeedModes();
        if (speed < 0) {
            speed = SpeedModes()-1 ;
        }
    }
    public void toggleArea() {radius = (radius + 1) % Radius();}
    public void decreaseRadius() {
        radius = (radius - 1) % Radius();
        if (radius < 0) {
            radius = Radius() -1;
        }
    }


    public void toggleParticle() {modPrac = (modPrac + 1) % 7;}
    public void UpdateModePrac() {
        switch (modPrac) {
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
            default:
                prac = EnumParticleTypes.FLAME;
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
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setBoolean("boleanwork",this.booleanWork);
        tagCompound.setBoolean("boleanspawn", this.booleanSpawnPrac);
        tagCompound.setInteger("speed",this.speed);
        tagCompound.setInteger("modprac", modPrac);
        tagCompound.setInteger("currentmode",this.radius);
        tagCompound.setInteger("count",this.count);
        tagCompound.setDouble("stepcount",this.stepCount);
        return super.writeToNBT(tagCompound);
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.booleanWork = tagCompound.getBoolean("boleanwork");
        this.booleanSpawnPrac = tagCompound.getBoolean("boleanspawn");
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
    public void handleUpdateTag(@NotNull NBTTagCompound tag) {
        super.handleUpdateTag(tag);
    }
    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity packet) {
        this.handleUpdateTag(packet.getNbtCompound());
        this.getWorld().markBlockRangeForRenderUpdate(this.pos,this.pos);
    }
    @Override
    public void onLoad() {
        IBlockState state = this.world.getBlockState(pos);
        if (!this.world.isRemote) {
                this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 64);
        }
    }
}