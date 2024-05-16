package com.ariks.torcherino.Block.Aceleration;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Items.ITileTimeStorage;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class TileAcceleration extends TileExampleContainer implements ITickable, ITileTimeStorage {
    private final int speed = Config.AccelerationSpeed;
    private final int AreaModifier = Config.AccelerationRadius;
    private int TimeStorage,Cooldown;
    private final Random rand = new Random();
    protected int speedBase(int base) {
        return base;
    }
    @Override
    public void update() {
        if (world.isRemote) return;
        this.timeDecrees();
    }
    private void timeDecrees(){
        if(TimeStorage > 0){
            UpdateTickArea();
            Cooldown++;
            if(Cooldown >= 19){
                Cooldown = 0;
                TimeStorage--;
                WorkVisual();
            }
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
        nbt.setInteger("TimeStorage", TimeStorage);
        nbt.setInteger("Cooldown", Cooldown);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeStorage = nbt.getInteger("TimeStorage");
        this.Cooldown = nbt.getInteger("Cooldown");
        super.readFromNBT(nbt);
    }
    @Override
    public int getValue(int id) {
        if (id == 1) {
            return this.TimeStorage;
        }
        return id;
    }
    @Override
    public void setValue(int id, int value) {
        if (id == 1) {
            this.TimeStorage = value;
        }
    }
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        return new ContainerAceleration(inventoryPlayer,this,entityPlayer);
    }
    @Override
    public String getGuiID() {
        return String.valueOf(RegistryGui.GUI_ACELERATION);
    }
    @Override
    public void addTimeStorage(int time) {
        this.TimeStorage += time;
    }
    @Override
    public void removeTimeStorage(int time) {
        this.TimeStorage -= time;
    }
    @Override
    public int getTimeStorage() {
        return this.TimeStorage;
    }
    @Override
    public int getMaxStorage() {
        return Integer.MAX_VALUE;
    }
}