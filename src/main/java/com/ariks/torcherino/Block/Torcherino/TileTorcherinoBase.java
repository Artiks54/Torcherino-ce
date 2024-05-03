package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryAcceleration;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class TileTorcherinoBase extends TileExampleContainer implements ITickable {
    private final Random rand = new Random();
    public int CurrentRadius,CurrentSpeed,booleanMode,booleanRender,cooldown;
    public int MaxRadius,MaxModes,MaxAcceleration;
    private int xMin,xMax,yMin,yMax,zMin,zMax;
    private boolean redstoneSignal,redstoneMode;
    protected int Radius() {
        return 1+MaxRadius;
    }
    protected int speedBase(int base) {
        return base * MaxAcceleration;
    }
    protected int SpeedModes() {return 1+MaxModes;}
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    @Override
    public void update() {
        if (world.isRemote) return;
        CheckRedstoneSignal();
        if (redstoneMode || (booleanMode == 1 && Config.BooleanVisualWork && CurrentRadius != 0 && CurrentSpeed != 0)) {
            WorkVisual();
        }
        if (booleanRender == 1 || booleanMode == 1 || redstoneMode) {
            UpdateChangeArea();
        }
        if (CurrentRadius != 0 && CurrentSpeed != 0 && (booleanMode == 1 || redstoneMode)) {
            UpdateTickArea();
        }
    }
    public void CheckRedstoneSignal() {redstoneMode = (booleanMode == 2 && redstoneSignal) || (booleanMode == 3 && !redstoneSignal);}
    public void WorkVisual() {
        if (++cooldown >= 20) {
            cooldown = 0;
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            EnumParticleTypes parc = EnumParticleTypes.FLAME;
            ((WorldServer) world).spawnParticle(parc, x + 0.5, y + 1.2, z + 0.5, 1, 0, 0, 0, 0, new int[0]);
        }
    }
    private void UpdateChangeArea() {
        BlockPos minPos = pos.add(-CurrentRadius, -CurrentRadius, -CurrentRadius);
        BlockPos maxPos = pos.add(CurrentRadius, CurrentRadius, CurrentRadius);
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
        if (block instanceof BlockFluidBase || RegistryAcceleration.isBlockBlacklisted(block)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = speedBase(CurrentSpeed);
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
            int speedBase = speedBase(CurrentSpeed);
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
    public AxisAlignedBB getAABBForRender() {return new AxisAlignedBB(-CurrentRadius, -CurrentRadius, -CurrentRadius, CurrentRadius+1, CurrentRadius+1, CurrentRadius+1);}
    public void toggleWorking(boolean increase){
        booleanMode = (byte) ((booleanMode + (increase ? 1 : -1) + 4) % 4);
        markDirty();
    }
    public void toggleSpeed(boolean increase){
        CurrentSpeed = (byte) ((CurrentSpeed + (increase ? 1 : -1) + SpeedModes()) % SpeedModes());
        markDirty();
    }
    public void toggleArea(boolean increase){
        CurrentRadius = (byte) ((CurrentRadius + (increase ? 1 : -1) + Radius()) % Radius());
        markDirty();
    }
    public void toggleRender(boolean increase){
        booleanRender = (byte) ((booleanRender + (increase ? 1 : -1) + 4) % 4);
        markDirty();
    }
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("MaxAcceleration",this.MaxAcceleration);
        nbt.setInteger("MaxModes",this.MaxModes);
        nbt.setInteger("MaxRadius",this.MaxRadius);
        nbt.setInteger("CurrentSpeed", this.CurrentSpeed);
        nbt.setInteger("CurrentRadius", this.CurrentRadius);
        nbt.setInteger("mode",this.booleanMode);
        nbt.setInteger("render", this.booleanRender);
        nbt.setBoolean("Red",this.redstoneSignal);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.MaxAcceleration = nbt.getInteger("MaxAcceleration");
        this.MaxModes = nbt.getInteger("MaxModes");
        this.MaxRadius = nbt.getInteger("MaxRadius");
        this.CurrentSpeed = nbt.getInteger("CurrentSpeed");
        this.CurrentRadius = nbt.getInteger("CurrentRadius");
        this.booleanMode = nbt.getInteger("mode");
        this.booleanRender = nbt.getInteger("render");
        this.redstoneSignal = nbt.getBoolean("Red");
        super.readFromNBT(nbt);
    }
    @Override
    public int getValue(int id) {
        switch (id){
            case 1:return this.booleanMode;
            case 2:return this.CurrentRadius;
            case 3:return this.CurrentSpeed;
            case 4:return this.booleanRender;
            case 5:return this.MaxAcceleration;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        switch (id) {
            case 1: this.booleanMode = value;
            case 2: this.CurrentRadius = value;
            case 3: this.CurrentSpeed = value;
            case 4: this.booleanRender = value;
            case 5: this.MaxAcceleration = value;
        }
    }
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {return new ContainerTorcherino(inventoryPlayer,this,entityPlayer);}
    @Override
    public String getGuiID() {return String.valueOf(RegistryGui.GUI_TORCHERINO);}
    @Override
    public String getName() {return "TileTorcherinoBase";}
    @Override
    public boolean hasCustomName() {return false;}
}