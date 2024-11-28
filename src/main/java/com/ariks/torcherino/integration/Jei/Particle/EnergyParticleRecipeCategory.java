package com.ariks.torcherino.integration.Jei.Particle;

import com.ariks.torcherino.Register.RegistryBlock;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.LocalizedStringKey;
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
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

public class EnergyParticleRecipeCategory implements IRecipeCategory<ParticleRecipeJei> {
    private final IDrawable background;
    private final String localizedName;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    private final IDrawableAnimated progressBar;
    public EnergyParticleRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_energy_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 76);
        localizedName = RegistryBlock.EnergyParticle.getLocalizedName();

        ResourceLocation progressLocation = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_component.png");
        IDrawableStatic progressDrawable = guiHelper.createDrawable(progressLocation, 43, 53, 24, 16);
        progressBar = guiHelper.createAnimatedDrawable(progressDrawable, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }
    @Override
    public @NotNull List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX >= 4 && mouseX <= 30 && mouseY >= 4 && mouseY <= 73) {
            String formattedValue = NumberFormat.getNumberInstance().format((long) Config.RFPerTickEnergyParticle * Config.MaxEnergyParticleProgress);
            return Collections.singletonList(LS.jei_rf_particle_collector + " " + formattedValue);
        }
        return Collections.emptyList();
    }
    @Override
    public @NotNull String getUid() {
        return Torcherino.MOD_ID + "_energy";
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
    public void drawExtras(@NotNull Minecraft minecraft) {
        progressBar.draw(minecraft, 32, 29);
    }
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ParticleRecipeJei recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(1, false, 60, 12);
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());
    }
}