package com.artiks.torcherinoCe.integration.Jei.MolecularFarm;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.MolecularRecipeFarm;
import com.artiks.torcherinoCe.utility.EnergyFormat;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class MolecularFarmJei implements IRecipeWrapper {
    MolecularRecipeFarm recipe;
    public MolecularFarmJei(MolecularRecipeFarm recipes) {
        this.recipe = recipes;
    }
    @SuppressWarnings("deprecation")
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
        String energyText = EnergyFormat.formatNumber(recipe.getEnergy());
        minecraft.fontRenderer.drawString(energyText, 30, 3, Color.WHITE.getRGB());
        minecraft.fontRenderer.drawString("Not consumed",7,27,Color.WHITE.getRGB());
    }
}