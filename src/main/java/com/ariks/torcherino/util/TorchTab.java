package com.ariks.torcherino.util;

import com.ariks.torcherino.Register.RegistryBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TorchTab extends CreativeTabs {
    public TorchTab(String string) {super(string);}
    @Override
    public @NotNull ItemStack getTabIconItem() {return new ItemStack(RegistryBlock.Time_Manipulator);}
}
