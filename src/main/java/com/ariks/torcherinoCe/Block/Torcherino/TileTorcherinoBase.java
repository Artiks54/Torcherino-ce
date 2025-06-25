package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.Block.Core.TileExampleContainer;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.utility.EnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class TileTorcherinoBase extends TileExampleContainer implements ITickable {
    //general
    private final Random rand = new Random();
    private final EnergyStorage storage;
    private int EnergyPerTick;
    private int EnergyMaxStorage;
    private int MaxRadius, MaxModes, MaxSpeed;
    private TorcherinoEnumLevel level;
    //Render
    private int rgbLine;
    private int rgbCube;
    private int booleanRender;
    private int alpha = 50;
    //Radius
    private int RadiusX, RadiusY, RadiusZ;
    private int xMin, xMax, yMin, yMax, zMin, zMax;
    //Work
    private int currentMode, booleanMode;
    private boolean redstoneSignal, redstoneMode;
    //Timer
    private int booleanTimer;
    private int timer;
    //Default !!! if null is not working NBT saves
    public TileTorcherinoBase() {
        this(TorcherinoEnumLevel.c_lvl_1);
    }
    public TileTorcherinoBase(TorcherinoEnumLevel tileType) {
        level = tileType;
        EnergyPerTick = level.getEnergyPerTick();
        EnergyMaxStorage = level.getMaxEnergy();
        MaxRadius = level.getRadius();
        MaxSpeed = level.getSpeed();
        MaxModes = level.getModes();
        storage = new EnergyStorage(0,Integer.MAX_VALUE,0,this);
        storage.setCapacity(EnergyMaxStorage);
    }
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    @Override
    public void update() {
        if (world.isRemote) return;
        UpdateRedstoneMode();
        if (booleanRender != 0 || booleanMode != 0) {
            UpdateChangeArea();
        }
        if ((RadiusX != 0 || RadiusY != 0 || RadiusZ != 0) && currentMode != 0 && (booleanMode == 1 || redstoneMode) && storage.getEnergyStored() >= EnergyPerTick) {
            UpdateTickArea();
            storage.consumeEnergy(EnergyPerTick);
        }
        HandleTimer();
    }
    private void UpdateRedstoneMode() {
        redstoneMode = (booleanMode == 2 && redstoneSignal) || (booleanMode == 3 && !redstoneSignal);
    }
    private void UpdateChangeArea() {
        BlockPos minPos = pos.add(-RadiusX, -RadiusY, -RadiusZ);
        BlockPos maxPos = pos.add(+RadiusX, +RadiusY, +RadiusZ);
        xMin = minPos.getX();
        yMin = minPos.getY();
        zMin = minPos.getZ();
        xMax = maxPos.getX();
        yMax = maxPos.getY();
        zMax = maxPos.getZ();
    }
    private void UpdateTickArea() {
        for (BlockPos pos : BlockPos.getAllInBox(xMin, yMin, zMin, xMax, yMax, zMax)) {
            AccelerationTick(pos);
        }
    }
    private void AccelerationTick(BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || RegistryAcceleration.isBlockBlacklisted(blockState)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = currentMode * MaxSpeed;
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
            if (RegistryAcceleration.isTileBlacklisted(tile.getClass())) {
                return;
            }
            int speedBase = currentMode * MaxSpeed;
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
        return new AxisAlignedBB(-RadiusX, -RadiusY, -RadiusZ, RadiusX + 1, RadiusY + 1, RadiusZ + 1);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        final int X = getPos().getX();
        final int Y = getPos().getY();
        final int Z = getPos().getZ();
        return new AxisAlignedBB(X - RadiusX, Y - RadiusY, Z - RadiusZ, X + RadiusX + 1, Y + RadiusY + 1, Z + RadiusZ + 1);
    }
    public void ToggleWork() {
        booleanMode++;
        if (booleanMode > 3) booleanMode = 0;
    }
    public void ToggleRender() {
        booleanRender++;
        if (booleanRender > 5) booleanRender = 0;
    }
    private int sec;
    private void HandleTimer() {
        if (booleanTimer == 1){
            sec++;
            if (sec >= 20) {
                timer--;
                sec = 0;
                if(timer <= 0){
                    EndTimer();
                }
            }
        }
    }
    public void AddTimer(int value){
        timer += value;
    }
    public void StartTimer(){
        booleanTimer = 1;
    }
    public void ResetTimer(){
        booleanTimer = 0;
        timer = 0;
        sec = 0;
    }
    private void EndTimer(){
        ResetTimer();
        booleanMode = 0;
    }
    @Override
    public int[] getValueList() {
        return new int[]{1,2,8,9,10,11,15,16,17,18,3,19,4,5,6,20,21};
    }
    @Override
    public int getValue(int id) {
        if(id == 1) return this.timer;
        if (id == 2) return this.booleanTimer;
        if (id == 3) return this.booleanMode;
        if (id == 4) return this.MaxRadius;
        if (id == 5) return this.MaxSpeed;
        if (id == 6) return this.MaxModes;
        if (id == 8) return this.rgbLine;
        if (id == 9) return this.rgbCube;
        if (id == 10) return this.alpha;
        if (id == 11) return this.booleanRender;
        if (id == 15) return this.RadiusX;
        if (id == 16) return this.RadiusY;
        if (id == 17) return this.RadiusZ;
        if (id == 18) return this.storage.getEnergyStored();
        if (id == 19) return this.currentMode;
        if (id == 20) return this.EnergyPerTick;
        if (id == 21) return this.EnergyMaxStorage;
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) this.timer = value;
        if (id == 2) this.booleanTimer = value;
        if (id == 3) this.booleanMode = value;
        if (id == 4) this.MaxRadius = value;
        if (id == 5) this.MaxSpeed = value;
        if (id == 6) this.MaxModes = value;
        if (id == 8) this.rgbLine = value;
        if (id == 9) this.rgbCube = value;
        if (id == 10) this.alpha = value;
        if (id == 11) this.booleanRender = value;
        if (id == 15) this.RadiusX = value;
        if (id == 16) this.RadiusY = value;
        if (id == 17) this.RadiusZ = value;
        if (id == 18) this.storage.setEnergy(value);
        if (id == 19) this.currentMode = value;
        if (id == 20) this.EnergyPerTick = value;
        if (id == 21) this.EnergyMaxStorage = value;
    }
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        nbt.setString("tileType", level.toString());
        nbt.setInteger("booleanTimer", this.booleanTimer);
        nbt.setInteger("timer", this.timer);
        nbt.setInteger("currentMode", this.currentMode);
        nbt.setInteger("RadiusX", this.RadiusX);
        nbt.setInteger("RadiusY", this.RadiusY);
        nbt.setInteger("RadiusZ", this.RadiusZ);
        nbt.setInteger("mode", this.booleanMode);
        nbt.setInteger("render", this.booleanRender);
        nbt.setInteger("rgbLine", this.rgbLine);
        nbt.setInteger("rgbCube", this.rgbCube);
        nbt.setInteger("alpha", this.alpha);
        nbt.setBoolean("Red", this.redstoneSignal);
        nbt.setInteger("Stored",this.storage.getEnergyStored());
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        //Default
        this.level = nbt.hasKey("tileType") ? TorcherinoEnumLevel.valueOf(nbt.getString("tileType")) : TorcherinoEnumLevel.c_lvl_1;
        this.MaxRadius = this.level.getRadius();
        this.MaxSpeed = this.level.getSpeed();
        this.MaxModes = this.level.getModes();
        this.EnergyPerTick = this.level.getEnergyPerTick();
        this.EnergyMaxStorage = this.level.getMaxEnergy();
        this.storage.setCapacity(EnergyMaxStorage);
        //Other
        this.currentMode = Math.min(nbt.getInteger("currentMode"),MaxModes);
        this.RadiusX = Math.min(nbt.getInteger("RadiusX"),MaxRadius);
        this.RadiusY = Math.min(nbt.getInteger("RadiusY"),MaxRadius);
        this.RadiusZ = Math.min(nbt.getInteger("RadiusZ"),MaxRadius);
        this.booleanTimer = nbt.getInteger("booleanTimer");
        this.timer = nbt.getInteger("timer");
        this.booleanMode = nbt.getInteger("mode");
        this.booleanRender = nbt.getInteger("render");
        this.rgbLine = nbt.getInteger("rgbLine");
        this.rgbCube = nbt.getInteger("rgbCube");
        this.alpha = nbt.getInteger("alpha");
        this.redstoneSignal = nbt.getBoolean("Red");
        this.storage.setEnergy(nbt.getInteger("Stored"));
        super.readFromNBT(nbt);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_TORCHERINO);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) storage;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}