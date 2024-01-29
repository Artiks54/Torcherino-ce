package com.ariks.torcherino.Tiles;
import com.ariks.torcherino.Config;
import com.ariks.torcherino.network.AccelerationRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;



import java.util.Random;

public class TileTorcherinoBase extends TileEntity implements ITickable {
    private final Random rand;
    public boolean bolaenWork;
    public boolean boleanSpawnPrac;
    public double stepCount = 0.25;
    public EnumParticleTypes prac;
    public int count, currentMode,modPrac, speed, xMin, yMin, zMin, xMax, yMax, zMax;
    public double xMinPrac, yMinPrac, zMinPrac, xMaxPrac, yMaxPrac, zMaxPrac;

    public TileTorcherinoBase() {
        this.rand = new Random();
    }

    protected int Radius(int area) {
        return area;
    }

    protected int speedBase(int base) {
        return base;
    }

    protected int SpeedModes(int newSpeed) {
        return newSpeed;
    }

    @Override
    public void update() {
        if (this.world.isRemote) return;
        if (Config.PracTicleSpawn) {
            this.spawnPrac();
            this.UpdateModePrac();
        }
        if (this.currentMode == 0 || this.speed == 0 || this.bolaenWork) {
            this.UpdateChangeArea();
            this.UpdateTickArea();
        }
    }

    private void UpdateChangeArea() {
        int radius = currentMode;
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

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setBoolean("boleanwork",this.bolaenWork);
        tagCompound.setBoolean("boleanspawn", this.boleanSpawnPrac);
        tagCompound.setInteger("speed",this.speed);
        tagCompound.setInteger("modprac",this.modPrac);
        tagCompound.setInteger("currentmode",this.currentMode);
        tagCompound.setInteger("count",this.count);
        tagCompound.setDouble("stepcount",this.stepCount);
        return tagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.bolaenWork = tagCompound.getBoolean("boleanwork");
        this.boleanSpawnPrac = tagCompound.getBoolean("boleanspawn");
        this.speed = tagCompound.getInteger("speed");
        this.modPrac = tagCompound.getInteger("modprac");
        this.currentMode = tagCompound.getInteger("currentmode");
        this.count = tagCompound.getInteger("count");
        this.stepCount = tagCompound.getDouble("stepcount");
    }
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new SPacketUpdateTileEntity(getPos(), -999, nbt);
    }
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readFromNBT(pkt.getNbtCompound());
    }
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
    public void spawnPrac() {
        if(boleanSpawnPrac){
            this.count++;
            if (count >= Config.tickCount) {
                this.count = 0;
                if (world instanceof WorldServer) {
                    xMinPrac = xMin;
                    xMaxPrac = xMax + 1;
                    zMinPrac = zMin;
                    zMaxPrac = zMax + 1;
                    yMinPrac = yMin;
                    yMaxPrac = yMax + 1;
                    double step = stepCount;
                    //Верхния сторона
                    for (double x = xMinPrac; x <= xMaxPrac; x += step) {
                        ((WorldServer) world).spawnParticle(prac, x, yMaxPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                        ((WorldServer) world).spawnParticle(prac, x, yMaxPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                    }
                    for (double z = zMinPrac; z <= zMaxPrac; z += step) {
                        ((WorldServer) world).spawnParticle(prac, xMinPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                        ((WorldServer) world).spawnParticle(prac, xMaxPrac, yMaxPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    }
                    //Нижния сторона
                    for (double x = xMinPrac; x <= xMaxPrac; x += step) {
                        ((WorldServer) world).spawnParticle(prac, x, yMinPrac, zMinPrac, 1, 0, 0, 0, 0, new int[0]);
                        ((WorldServer) world).spawnParticle(prac, x, yMinPrac, zMaxPrac, 1, 0, 0, 0, 0, new int[0]);
                    }
                    for (double z = zMinPrac; z <= zMaxPrac; z += step) {
                        ((WorldServer) world).spawnParticle(prac, xMinPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                        ((WorldServer) world).spawnParticle(prac, xMaxPrac, yMinPrac, z, 1, 0, 0, 0, 0, new int[0]);
                    }
                    //Грани
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
    }
    public void increaseArea() {currentMode = (currentMode + 1) % Radius(1);}
    public void increaseSpeed() {speed = (byte) ((speed + 1) % SpeedModes(1));}
    public void toggleParticle() {
        modPrac +=1;
        if(modPrac >2){
            modPrac = 0;
        }
    }
    public void UpdateModePrac() {
        if (modPrac == 0) {
            prac = EnumParticleTypes.FLAME;
        }
        if (modPrac == 1) {
            prac = EnumParticleTypes.DRAGON_BREATH;
        }
        if (modPrac == 2) {
            prac = EnumParticleTypes.REDSTONE;
        }
    }
    public void toggleWork() {
        bolaenWork = !bolaenWork;
    }
    public void toggleSpawnPrac() {boleanSpawnPrac = !boleanSpawnPrac;}
    public void toggleStepCount(){
        stepCount +=0.05;
        if(stepCount > 1.01){
            stepCount = 0.1;
        }
    }
    public int debugGetCount(){return count;}
    public int debugGetSpeed(){return speed;}
    public int debugGetRadius(){return currentMode;}
    public TextComponentString getDiscription() {
        TextComponentString message;
        TextFormatting textFormattingStop = TextFormatting.valueOf(Config.colorSettingStop.toUpperCase());
        TextFormatting textFormattingStart = TextFormatting.valueOf(Config.colorSettingStart.toUpperCase());
            if (currentMode == 0) {
                message = new TextComponentString(textFormattingStop + "Mode: " + currentMode + "x" + currentMode + "x" + currentMode);
            } else {
                message = new TextComponentString(textFormattingStart + "Mode: " + currentMode + "x" + currentMode + "x" + currentMode);
            }
            if (speed == 0) {
                message.appendText(textFormattingStop + "Speed: " + this.speedBase(this.speed) * 100 + "%");
            } else {
                message.appendText(textFormattingStart + "Speed: " + this.speedBase(this.speed) * 100 + "%");
            }
            return message;
        }

}