package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Items.ItemFortuneModule;
import com.artiks.torcherinoCe.utility.IFortuneModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotFortuneModule extends Slot {

    public SlotFortuneModule(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemFortuneModule;
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
        if (this.inventory instanceof IFortuneModule fortuneModule) {
            ItemStack stack = this.getStack();
            if (!stack.isEmpty() && stack.getItem() instanceof ItemFortuneModule) {
                int fortune = ItemFortuneModule.getFortuneLevel(stack);
                fortuneModule.setFortune(fortune);
            } else {
                fortuneModule.setFortune(1);
            }
        }
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        if (this.inventory instanceof IFortuneModule fortuneModule) {
            if (!stack.isEmpty() && stack.getItem() instanceof ItemFortuneModule) {
                int fortune = ItemFortuneModule.getFortuneLevel(stack);
                fortuneModule.setFortune(fortune);
            } else {
                fortuneModule.setFortune(1);
            }
        }
        return super.onTake(thePlayer, stack);
    }
}