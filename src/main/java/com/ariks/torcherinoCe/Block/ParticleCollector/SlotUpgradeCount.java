package com.ariks.torcherinoCe.Block.ParticleCollector;

import com.ariks.torcherinoCe.Items.ItemUpgradeCount;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jetbrains.annotations.NotNull;

public class SlotUpgradeCount extends Slot {
    private final TileEntity tileEntity;
    public SlotUpgradeCount(IInventory inventoryIn, int index, int xPosition, int yPosition, TileEntity tileEntity) {
        super(inventoryIn, index, xPosition, yPosition);
        this.tileEntity = tileEntity;
    }
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemUpgradeCount;
    }
    public int getUpgrade() {
        ItemStack stack = this.getStack();
        if (stack.getItem() instanceof ItemUpgradeCount) {
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
        ((TileParticleCollector) tileEntity).updateAmount(getUpgrade());
    }
    @Override
    public @NotNull ItemStack onTake(@NotNull EntityPlayer thePlayer, @NotNull ItemStack stack) {
        ((TileParticleCollector) tileEntity).updateAmount(getUpgrade());
        return super.onTake(thePlayer, stack);
    }
}