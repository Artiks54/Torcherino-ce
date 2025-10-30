package com.artiks.torcherinoCe.integration.Jei.GrowAccelerator;

import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ImageRecipeCategory implements IRecipeCategory<ImageRecipeJei> {

    private final IDrawable background;
    private final String localizedName;
    public ImageRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/gui/gui_jei_grow.png");
        background = guiHelper.createDrawable(location, 0, 0, 101, 101);
        localizedName = LocalizedStringKey.Jei_grow_tooltip;
    }

    @Override
    public @NotNull String getUid() {
        return Tags.MODID + "_grow";
    }

    @Override
    public @NotNull String getTitle() {
        return localizedName;
    }

    @Override
    public @NotNull String getModName() {
        return Tags.MODID;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout iRecipeLayout, @NotNull ImageRecipeJei imageRecipe, @NotNull IIngredients iIngredients) {
    }
}
