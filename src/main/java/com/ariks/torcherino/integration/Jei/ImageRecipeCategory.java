package com.ariks.torcherino.integration.Jei;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class ImageRecipeCategory implements IRecipeCategory<ImageRecipe> {
    private final IDrawable background;
    private final String localizedName;
    private final String uid = Torcherino.MOD_ID + "_grow";
    private final LocalizedStringKey LS = new LocalizedStringKey();

    public ImageRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_jei_grow.png");
        background = guiHelper.createDrawable(location, 0, 0, 101, 101);
        localizedName = LS.Jei_grow_tooltip;
    }
    @Override
    public String getUid() {
        return uid;
    }
    @Override
    public String getTitle() {
        return localizedName;
    }
    @Override
    public String getModName() {
        return Torcherino.MOD_NAME;
    }
    @Override
    public IDrawable getBackground() {
        return background;
    }
    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, ImageRecipe imageRecipe, IIngredients iIngredients) {
    }
}
