package com.artiks.torcherinoCe.integration.Jei.MolecularFarm;

import com.artiks.torcherinoCe.Register.RegistryBlock;
import com.artiks.torcherinoCe.Tags;
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

public class MolecularFarmRecipeCategory implements IRecipeCategory<MolecularFarmJei> {
    private final IDrawable background;
    private final String localizedName;
    private final IDrawableAnimated progressBar;

    public MolecularFarmRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/gui/molecular/farm_gui_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 122, 40);
        localizedName = RegistryBlock.RF_Molecular_Farm.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(Tags.MODID, "textures/gui/molecular/farm_gui_jei.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 0, 41, 60, 5);
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 40, IDrawableAnimated.StartDirection.LEFT, false);
    }
    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        progressBar.draw(minecraft, 31, 13);
    }
    @Override
    public @NotNull String getUid() {
        return Tags.MODID + "_molecular_farm";
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
    public void setRecipe(IRecipeLayout recipeLayout, MolecularFarmJei recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 7, 7);
        recipeLayout.getItemStacks().init(1, false, 97, 7);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getInputs());
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());
    }
}