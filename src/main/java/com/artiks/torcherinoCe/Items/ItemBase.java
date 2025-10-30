package com.artiks.torcherinoCe.Items;

import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.torcherinoCe;
import com.artiks.torcherinoCe.utility.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name) {
        this.setRegistryName(name);
        this.setTranslationKey(name);
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