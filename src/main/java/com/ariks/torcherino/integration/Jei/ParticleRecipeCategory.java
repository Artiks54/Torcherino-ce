package com.ariks.torcherino.integration.Jei;

import com.ariks.torcherino.Register.RegistryBlock;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
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
import java.util.Collections;
import java.util.List;

public class ParticleRecipeCategory implements IRecipeCategory<ParticleRecipe> {
    private final IDrawable background;
    private final String localizedName;
    private final String uid = Torcherino.MOD_ID + "_particle";
    private final IDrawableAnimated progressBar;
    private final IDrawableAnimated progressBar2;

    public ParticleRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_particle_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 119, 67);
        localizedName = RegistryBlock.Particle_collectors.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 0, 38, 40, 42); // Размеры текстуры анимации
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.LEFT, false);

        IDrawableStatic progressDrawable2 = guiHelper.createDrawable(progressLocation, 0, 82, 40, 42); // Размеры текстуры анимации
        progressBar2 = guiHelper.createAnimatedDrawable(progressDrawable2, 100, IDrawableAnimated.StartDirection.RIGHT, false);
    }

    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        // Отрисовываем анимацию
        progressBar.draw(minecraft, 0, 26);
        progressBar2.draw(minecraft,80,26);
    }

    @Override
    public @NotNull List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX >= 0 && mouseX <= 39 && mouseY >= 26 && mouseY <= 67) {
            return Collections.singletonList(Config.RequiredGeneratorParticle + " Need tick");
        }
        if (mouseX >= 79 && mouseX <= 119 && mouseY >= 26 && mouseY <= 67) {
            return Collections.singletonList(Config.RequiredGeneratorParticle + " Need tick");
        }
        return Collections.emptyList();
    }
    @Override
    public @NotNull String getUid() {
        return uid;
    }
    @Override
    public @NotNull String getTitle() {
        return localizedName;
    }
    @Override
    public @NotNull String getModName() {
        return Torcherino.MOD_NAME;
    }
    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ParticleRecipe recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, false, 42, 29);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getOutputs());
        recipeLayout.getItemStacks().init(1, true, 41, 0);
        recipeLayout.getItemStacks().set(1, recipeWrapper.getInputs().get(0));
        recipeLayout.getItemStacks().init(2, true, 61, 0);
        recipeLayout.getItemStacks().set(2, recipeWrapper.getInputs().get(1));
    }
}