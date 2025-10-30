package com.artiks.torcherinoCe.Block.Energy.CobbleGenerator;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventoryEnergy;
import com.artiks.torcherinoCe.Register.RegistryGui;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class TileCobbleGenerator extends TileExampleInventoryEnergy implements ITickable {

    private int EnergyPerTick = 4500;
    private ItemStack generation = new ItemStack(Blocks.COBBLESTONE,1920);

    public TileCobbleGenerator() {
        super(32,100_000,Integer.MAX_VALUE,0);
        setSlotsForExtract(2, 31);
    }

    @Override
    public void update() {
        super.update();
        if (!world.isRemote) {
            if(getEnergyStored() < getEnergyPerTick())return;
            if (hasEnoughOutputSpace(generation)) {
                consumeEnergy(EnergyPerTick);
                addInventoryItemOutput(generation);
            }
        }
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("ept",EnergyPerTick);
        NBTTagCompound itemTag = new NBTTagCompound();
        generation.writeToNBT(itemTag);
        nbt.setTag("generation", itemTag);
        return nbt;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        EnergyPerTick = nbt.getInteger("ept");
        if (nbt.hasKey("generation")) {
            NBTTagCompound itemTag = nbt.getCompoundTag("generation");
            generation = new ItemStack(itemTag);
        }
    }

    @Override
    public void getSyncData(Map<String, Object> data) {
        super.getSyncData(data);
        data.put("ept",getEnergyPerTick());
    }

    @Override
    public void setSyncData(Map<String, Object> data) {
        super.setSyncData(data);
        if(data.containsKey("ept")){
            setEnergyPerTick((int)data.get("ept"));
        }
    }

    public void setEnergyPerTick(int energyPerTick) {
        EnergyPerTick = energyPerTick;
    }

    @SideOnly(Side.CLIENT)
    public ItemStack syncRender(){
      return generation;
    }

    public void setGeneration(ItemStack generation) {
        this.generation = generation;
        world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),3);
    }

    public int getEnergyPerTick() {
        return EnergyPerTick;
    }

    @Override
    public @NotNull AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_COBBLE_GENERATOR);
    }
}