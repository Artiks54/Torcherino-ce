package com.ariks.torcherino.integration.Jei.Particle;

import com.ariks.torcherino.Register.RegistryBlock;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.LocalizedStringKey;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

public class EnergyParticleRecipeCategory implements IRecipeCategory<ParticleRecipeJei> {
    private final IDrawable background;
    private final String localizedName;
    private final String uid = Torcherino.MOD_ID + "_energy";
    public final LocalizedStringKey LS = new LocalizedStringKey();

    public EnergyParticleRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_energy_jei.png");
        background = guiHelper.createDrawable(location, 0, 0, 176, 83);
        localizedName = RegistryBlock.EnergyParticle.getLocalizedName();
    }
    @Override
    public @NotNull List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (mouseX >= 5 && mouseX <= 170 && mouseY >= 13 && mouseY <= 37) {
            String formattedValue = NumberFormat.getNumberInstance().format(Config.RFPerTickEnergyParticle);
            return Collections.singletonList(LS.jei_rf_particle_collector + " " + formattedValue);
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
    public void setRecipe(IRecipeLayout recipeLayout, ParticleRecipeJei recipeWrapper, @NotNull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(1, false, 7, 40);
        recipeLayout.getItemStacks().set(1, recipeWrapper.getOutputs());
    }
}