package com.ariks.torcherino.Items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ItemInfo extends itemBase {
    public ItemInfo(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.torcherino.descriptionInfo"));
    }
}
