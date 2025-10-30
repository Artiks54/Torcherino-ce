package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Block.Core.TileExampleInventory;
import com.artiks.torcherinoCe.Items.ItemExport;
import com.artiks.torcherinoCe.Register.RegistryItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotItemExport extends Slot {
    public SlotItemExport(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemExport;
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
        if(getStack().getItem() == RegistryItems.ExportModule){
            ((TileExampleInventory)inventory).setExportSide((ItemExport.getExportSide(getStack())));
        }
        if(getStack().getItem() == Items.AIR){
            ((TileExampleInventory)inventory).setExportSide(null);
        }
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        if(getStack().getItem() == RegistryItems.ExportModule){
            ((TileExampleInventory)inventory).setExportSide((ItemExport.getExportSide(getStack())));
        }
        if(getStack().getItem() == Items.AIR){
            ((TileExampleInventory)inventory).setExportSide(null);
        }
        return super.onTake(thePlayer, stack);
    }
}