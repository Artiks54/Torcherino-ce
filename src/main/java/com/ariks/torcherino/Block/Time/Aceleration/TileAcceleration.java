package com.ariks.torcherino.Block.Time.Aceleration;

import com.ariks.torcherino.Block.Time.TileTime;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Register.RegistryAcceleration;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

public class TileAcceleration extends TileTime implements ITickable {
    private final int speed = Config.AccelerationSpeed;
    private final int AreaModifier = Config.AccelerationRadius;
    private boolean redstoneSignal, redstoneMode;
    private int booleanMode,booleanRender;
    private int Cooldown;
    private final Random rand = new Random();
    protected int speedBase(int base) {
        return base;
    }
    public TileAcceleration(){
        super(Config.MaxStorageTimeAcceleration);
    }
    private void CheckRedstoneSignal() {
        redstoneMode = (booleanMode == 2 && redstoneSignal) || (booleanMode == 3 && !redstoneSignal);
    }
    public void setRedstoneSignal(boolean redstoneSignal) {
        this.redstoneSignal = redstoneSignal;
    }
    public void ToogleWork() {
        booleanMode++;
        if (booleanMode > 3) {
            booleanMode = 0;
        }
        this.UpdateTile();
    }
    public void ToogleRender() {
        booleanRender++;
        if (booleanRender > 3) {
            booleanRender = 0;
        }
        this.UpdateTile();
    }
    @Override
    public void update() {
        if (!world.isRemote) {
            this.CheckRedstoneSignal();
            if(booleanMode != 0) {
                this.TimeDecrees();
            }
        }
    }
    private void TimeDecrees(){
        if(energyTime.getTimeStored() > 0  && (booleanMode == 1 || redstoneMode)){
            this.UpdateTickArea();
            Cooldown++;
            if(Cooldown >= 19){
                Cooldown = 0;
                this.energyTime.consumeTime(1);
                this.WorkVisual();
            }
            this.UpdateTile();
        }
    }
    private void WorkVisual() {
        if (Config.BooleanVisualWork) {
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            EnumParticleTypes parc = EnumParticleTypes.FLAME;
            ((WorldServer) world).spawnParticle(parc, x + 0.5, y + 1.15, z + 0.5, 1, 0, 0, 0, 0, new int[0]);
        }
    }
    private void UpdateTickArea() {
        int AreaDecrX = pos.getX() - AreaModifier;
        int AreaDecrY = pos.getY() - AreaModifier;
        int AreaDecrZ = pos.getZ() - AreaModifier;
        int AreaIncrX = pos.getX() + AreaModifier;
        int AreaIncrY = pos.getY() + AreaModifier;
        int AreaIncrZ = pos.getZ() + AreaModifier;
        for (BlockPos pos : BlockPos.getAllInBox(AreaDecrX, AreaDecrY, AreaDecrZ, AreaIncrX, AreaIncrY, AreaIncrZ)) {
            AccelerationTick(pos);
        }
    }
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getAABBForRender() {
        return new AxisAlignedBB(-AreaModifier, -AreaModifier, -AreaModifier, AreaModifier + 1, AreaModifier + 1, AreaModifier + 1);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        final int X = getPos().getX();
        final int Y = getPos().getY();
        final int Z = getPos().getZ();
        return new AxisAlignedBB(X - AreaModifier, Y - AreaModifier, Z - AreaModifier, X + AreaModifier + 1, Y + AreaModifier + 1, Z + AreaModifier + 1);
    }
    private void AccelerationTick(BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || RegistryAcceleration.isBlockBlacklisted(blockState)) {
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
            if (RegistryAcceleration.isTileBlacklisted(tile.getClass())) {
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
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Cooldown", Cooldown);
        nbt.setInteger("mode", this.booleanMode);
        nbt.setBoolean("Red", this.redstoneSignal);
        nbt.setInteger("render", this.booleanRender);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        this.Cooldown = nbt.getInteger("Cooldown");
        this.booleanMode = nbt.getInteger("mode");
        this.redstoneSignal = nbt.getBoolean("Red");
        this.booleanRender = nbt.getInteger("render");
        super.readFromNBT(nbt);
    }
    @Override
    public int getValue(int id) {
        if(id == 3){
            return booleanMode;
        }
        if (id == 4) {
            return this.booleanRender;
        }
        return super.getValue(id);
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ACCELERATION);
    }
}