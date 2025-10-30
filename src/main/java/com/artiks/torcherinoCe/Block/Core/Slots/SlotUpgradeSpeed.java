package com.artiks.torcherinoCe.Block.Core.Slots;

import com.artiks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.artiks.torcherinoCe.Items.ItemUpgradeSpeed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SlotUpgradeSpeed extends Slot {

    public SlotUpgradeSpeed(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemUpgradeSpeed;
    }
    public int getUpgrade() {
        ItemStack stack = this.getStack();
        if (stack.getItem() instanceof ItemUpgradeSpeed) {
            return 1 + stack.getCount();
        }
        return 1;
    }
    @Override
    public int getItemStackLimit(@NotNull ItemStack itemStack) {
        return 64;
    }
    @Override
    public int getSlotStackLimit() {
        return 64;
    }
    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ((TileParticleCollector) inventory).updateSpeed(getUpgrade());
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        ((TileParticleCollector) inventory).updateSpeed(getUpgrade());
        return super.onTake(thePlayer, stack);
    }
}