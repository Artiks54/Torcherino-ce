package com.ariks.torcherino.network;
import com.ariks.torcherino.Register.RegistryArray;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
public class TorchTab extends CreativeTabs {
    public TorchTab(String string) {super(string);}
    @Override
    public ItemStack getTabIconItem() {return new ItemStack(RegistryArray.Diamond_Clock);}
}
