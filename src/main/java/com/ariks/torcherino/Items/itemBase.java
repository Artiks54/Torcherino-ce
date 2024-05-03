package com.ariks.torcherino.Items;

import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class itemBase extends Item implements IHasModel {
    public itemBase(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(64);
        this.setCreativeTab(Torcherino.torcherinoTab);
        RegistryItems.ITEMS.add(this);
    }
    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }
    @Override
    public void registerModels() {
        Torcherino.proxy.registerItemRenderer(this, 0, "inventory");
    }
}