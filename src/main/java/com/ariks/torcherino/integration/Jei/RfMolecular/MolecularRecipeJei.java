package com.ariks.torcherino.integration.Jei.RfMolecular;

import com.ariks.torcherino.Block.RfMolecular.MolecularRecipe;
import com.ariks.torcherino.util.EnergyFormat;
import com.ariks.torcherino.util.LocalizedStringKey;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class MolecularRecipeJei implements IRecipeWrapper {
    MolecularRecipe recipe;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    public MolecularRecipeJei(MolecularRecipe recipes) {
        this.recipe = recipes;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutput(ItemStack.class, getOutputs());
    }
    public List<ItemStack> getInputs() {
        return Collections.singletonList(recipe.getInput());
    }
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(recipe.getRecipeOutput());
    }
    public Long getEnergy() {
        return recipe.getEnergy();
    }
    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        IRecipeWrapper.super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        String energyText =  LS.StrEnergyRecipe +" "+ EnergyFormat.formatNumber(recipe.getEnergy());
        minecraft.fontRenderer.drawString(energyText, 35, 5, Color.WHITE.getRGB());
    }
}