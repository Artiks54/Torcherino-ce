package com.artiks.torcherinoCe.Items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class ItemFortuneModule extends ItemBase {

    private static final int[] FORTUNE_LEVELS = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

    public ItemFortuneModule(String name) {
        super(name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public @NotNull String getTranslationKey(@NotNull ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta >= 0 && meta < FORTUNE_LEVELS.length) {
            return super.getTranslationKey() + "_x" + FORTUNE_LEVELS[meta];
        }
        return super.getTranslationKey() + "_x2";
    }

    public static int getFortuneLevel(ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof ItemFortuneModule)) {
            return 1;
        }

        int meta = stack.getMetadata();
        if (meta >= 0 && meta < FORTUNE_LEVELS.length) {
            return FORTUNE_LEVELS[meta];
        }
        return 1;
    }

    @Override
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < FORTUNE_LEVELS.length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < FORTUNE_LEVELS.length; i++) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(Objects.requireNonNull(this.getRegistryName()) + "_x" + FORTUNE_LEVELS[i], "inventory")
            );
        }
    }
}