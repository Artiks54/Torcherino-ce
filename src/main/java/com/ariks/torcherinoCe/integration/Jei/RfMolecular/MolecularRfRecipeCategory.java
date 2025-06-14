package com.ariks.torcherinoCe.integration.Jei.RfMolecular;

import com.ariks.torcherinoCe.Register.RegistryBlock;
import com.ariks.torcherinoCe.Tags;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MolecularRfRecipeCategory implements IRecipeCategory<MolecularRecipeJei> {
    private final IDrawable background;
    private final String localizedName;
    private final IDrawableAnimated progressBar;

    public MolecularRfRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/gui/gui_molecular_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 77);
        localizedName = RegistryBlock.RF_Molecular.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(Tags.MODID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 181, 0, 14, 29);
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.TOP, false);
    }
    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        progressBar.draw(minecraft, 7, 24);
    }
    @Override
    public @NotNull String getUid() {
        return Tags.MODID + "_molecular";
    }
    @Override
    public @NotNull String getTitle() {
        return localizedName;
    }
    @Override
    public @NotNull String getModName() {
        return Tags.MODNAME;
    }
    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MolecularRecipeJei recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 5, 5);
        recipeLayout.getItemStacks().init(1, false, 5, 54);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getInputs());
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());
    }
}