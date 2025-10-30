package com.artiks.torcherinoCe.utility;

import com.artiks.torcherinoCe.Register.RegistryBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TorchTab extends CreativeTabs {
    public TorchTab(String string) {
        super(string);
    }
    @Override
    public @NotNull ItemStack createIcon() {
        return new ItemStack(RegistryBlock.Time_Manipulator);
    }
}
