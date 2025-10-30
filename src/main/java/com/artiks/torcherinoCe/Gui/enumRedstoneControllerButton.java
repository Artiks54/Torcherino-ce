package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public enum enumRedstoneControllerButton {

    TRUE(new ItemStack(Blocks.REDSTONE_LAMP), TextFormatting.RED + LocalizedStringKey.StrTextWorking),
    FALSE(new ItemStack(Blocks.REDSTONE_BLOCK), TextFormatting.GREEN + LocalizedStringKey.StrTextAlways),
    ELSE(new ItemStack(Items.REDSTONE), TextFormatting.GREEN + LocalizedStringKey.StrRedstoneMode),
    ELSEIF(new ItemStack(Blocks.REDSTONE_TORCH), TextFormatting.GREEN + LocalizedStringKey.StrRedstoneModeRevers);

    private final ItemStack stack;
    private final String displayText;

    enumRedstoneControllerButton(ItemStack stack, String displayText){
        this.stack = stack;
        this.displayText = displayText;
    }

    public ItemStack getStack() {
        return stack;
    }

    public String getDisplayText() {
        return displayText;
    }

    public static enumRedstoneControllerButton getByIndex(int index) {
        enumRedstoneControllerButton[] values = values();
        if (index >= 0 && index < values.length) {
            return values[index];
        }
        return TRUE;
    }
}