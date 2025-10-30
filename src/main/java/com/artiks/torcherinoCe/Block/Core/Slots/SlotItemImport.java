package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Items.ItemImport;
import com.artiks.torcherinoCe.Register.RegistryItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotItemImport extends Slot {
    public SlotItemImport(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemImport;
    }

    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 1;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        if(getStack().getItem() == RegistryItems.ImportModule){
            ((TileExampleInventory)inventory).setImportSide((ItemImport.getImportSide(getStack())));
        }
        if(getStack().getItem() == Items.AIR){
            ((TileExampleInventory)inventory).setImportSide(null);
        }
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        if(getStack().getItem() == RegistryItems.ImportModule){
            ((TileExampleInventory)inventory).setImportSide((ItemImport.getImportSide(getStack())));
        }
        if(getStack().getItem() == Items.AIR){
            ((TileExampleInventory)inventory).setImportSide(null);
        }
        return super.onTake(thePlayer, stack);
    }
}