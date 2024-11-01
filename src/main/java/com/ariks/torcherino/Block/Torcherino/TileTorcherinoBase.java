package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryAcceleration;
import com.ariks.torcherino.Register.RegistryGui;
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
    private int RadiusX,RadiusY,RadiusZ;
    private int Speed,booleanMode,booleanRender,xMin,xMax,yMin,yMax,zMin,zMax,cooldown;
    private int R = 255;
    private int G = 0;
    private int B = 0;
    public int MaxRadius,MaxModes,MaxAcceleration;
    private boolean redstoneSignal,redstoneMode;
    protected int speedBase(int base) {
        return base * MaxAcceleration;
    }
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    @Override
    public void update() {
        if (world.isRemote) return;
        CheckRedstoneSignal();
        if (booleanRender != 0 || booleanMode != 0) {
            UpdateChangeArea();
        }
        if ((RadiusX != 0 || RadiusY !=0 || RadiusZ !=0) && Speed != 0 && (booleanMode == 1 || redstoneMode)) {
            UpdateTickArea();
            WorkVisual();
        }
    }
    private void CheckRedstoneSignal() {
        redstoneMode = (booleanMode == 2 && redstoneSignal) || (booleanMode == 3 && !redstoneSignal);
    }
    private void WorkVisual() {
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
        BlockPos minPos = pos.add(-RadiusX,-RadiusY,-RadiusZ);
        BlockPos maxPos = pos.add(+RadiusX,+RadiusY,+RadiusZ);
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
        if (block instanceof BlockFluidBase || RegistryAcceleration.isBlockBlacklisted(block)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = speedBase(Speed);
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
            int speedBase = speedBase(Speed);
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
        return new AxisAlignedBB(-RadiusX,-RadiusY,-RadiusZ,RadiusX+1,RadiusY+1,RadiusZ+1);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        final int X = getPos().getX();
        final int Y = getPos().getY();
        final int Z = getPos().getZ();
        return new AxisAlignedBB(X-RadiusX,Y-RadiusY,Z-RadiusZ,X+RadiusX+1,Y+RadiusY+1,Z+RadiusZ+1);
    }
    public void ToogleWork(){
        UpdateTile();
        booleanMode++;
        if(booleanMode > 3){
            booleanMode = 0;
        }
    }
    public void ToogleRender(){
        UpdateTile();
        booleanRender++;
        if(booleanRender > 3){
            booleanRender = 0;
        }
    }
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("MaxAcceleration",this.MaxAcceleration);
        nbt.setInteger("MaxModes",this.MaxModes);
        nbt.setInteger("MaxRadius",this.MaxRadius);
        nbt.setInteger("Speed", this.Speed);
        nbt.setInteger("mode",this.booleanMode);
        nbt.setInteger("render", this.booleanRender);
        nbt.setInteger("R", this.R);
        nbt.setInteger("G", this.G);
        nbt.setInteger("B", this.B);
        nbt.setInteger("RadiusX", this.RadiusX);
        nbt.setInteger("RadiusY", this.RadiusY);
        nbt.setInteger("RadiusZ", this.RadiusZ);
        nbt.setBoolean("Red",this.redstoneSignal);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.MaxAcceleration = nbt.getInteger("MaxAcceleration");
        this.MaxModes = nbt.getInteger("MaxModes");
        this.MaxRadius = nbt.getInteger("MaxRadius");
        this.Speed = nbt.getInteger("Speed");
        this.booleanMode = nbt.getInteger("mode");
        this.booleanRender = nbt.getInteger("render");
        this.R = nbt.getInteger("R");
        this.G = nbt.getInteger("G");
        this.B = nbt.getInteger("B");
        this.RadiusX = nbt.getInteger("RadiusX");
        this.RadiusY = nbt.getInteger("RadiusY");
        this.RadiusZ = nbt.getInteger("RadiusZ");
        this.redstoneSignal = nbt.getBoolean("Red");
        super.readFromNBT(nbt);
    }
    @Override
    public int getValue(int id) {
        if (id == 2) {
            return this.Speed;
        }
        if (id == 3) {
            return this.booleanMode;
        }
        if (id == 4) {
            return this.booleanRender;
        }
        if (id == 5) {
            return this.MaxRadius;
        }
        if (id == 6) {
            return this.MaxModes;
        }
        if (id == 7) {
            return this.MaxAcceleration;
        }
        if (id == 8) {
            return this.R;
        }
        if (id == 9) {
            return this.G;
        }
        if (id == 10) {
            return this.B;
        }
        if (id == 15) {
            return this.RadiusX;
        }
        if (id == 16) {
            return this.RadiusY;
        }
        if (id == 17) {
            return this.RadiusZ;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value)
    {
        if (id == 2) {
            this.Speed = value;
        }
        if(id == 3){
            this.booleanMode = value;
        }
        if(id == 4){
            this.booleanRender = value;
        }
        if(id == 8){
            this.R = value;
        }
        if(id == 9){
            this.G = value;
        }
        if(id == 10){
            this.B = value;
        }
        if(id == 15){
            this.RadiusX = value;
        }
        if(id == 16){
            this.RadiusY = value;
        }
        if(id == 17){
            this.RadiusZ = value;
        }
    }
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        return new ContainerTorcherino(inventoryPlayer,this,entityPlayer);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_TORCHERINO);
    }
}