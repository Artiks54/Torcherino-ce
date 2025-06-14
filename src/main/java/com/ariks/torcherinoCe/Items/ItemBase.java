package com.ariks.torcherinoCe.Items;

import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.torcherinoCe;
import com.ariks.torcherinoCe.utility.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name) {
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setMaxStackSize(64);
        this.setCreativeTab(torcherinoCe.torcherinoTab);
        this.setNoRepair();
        RegistryItems.ITEMS.add(this);
    }
    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }
    @Override
    public void registerModels() {
        torcherinoCe.proxy.registerItemRenderer(this, 0, "inventory");
    }
}