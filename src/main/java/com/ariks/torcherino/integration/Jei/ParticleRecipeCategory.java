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
import java.util.Collections;
import java.util.List;

public class ParticleRecipeCategory implements IRecipeCategory<ParticleRecipe> {
    private final IDrawable background;
    private final String localizedName;
    private final String uid = Torcherino.MOD_ID + "_particle";
    private IDrawableAnimated progressBar;

    public ParticleRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_particle_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 79, 67);
        localizedName = RegistryBlock.Particle_collectors.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 0, 38, 40, 42); // Размеры текстуры анимации
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.LEFT, false);

    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        // Отрисовываем анимацию
        progressBar.draw(minecraft, 0, 26);
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX >= 0 && mouseX <= 39 && mouseY >= 26 && mouseY <= 67) {
            return Collections.singletonList(Config.RequiredGeneratorParticle + " Need tick");
        }
        return Collections.emptyList();
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
    public void setRecipe(IRecipeLayout recipeLayout, ParticleRecipe recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(1, false, 42, 29);
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());

        recipeLayout.getItemStacks().init(2,false,51,0);
        recipeLayout.getItemStacks().set(2,recipeWrapper.getInputs());
    }
}