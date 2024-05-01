package com.ariks.torcherino.util;
import com.ariks.torcherino.Register.RegistryArray;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TorchTab extends CreativeTabs {
    public TorchTab(String string) {super(string);}
    @Override
    public @NotNull ItemStack getTabIconItem() {return new ItemStack(RegistryArray.Compressed_Torch_lvl4);}
}
