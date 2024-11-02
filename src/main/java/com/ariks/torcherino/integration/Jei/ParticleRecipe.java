package com.ariks.torcherino.integration.Jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParticleRecipe implements IRecipeWrapper {
    private final ItemStack input1;
    private final ItemStack input2;
    private final ItemStack output;

    public ParticleRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutput(ItemStack.class, getOutputs());
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input1, input2);
    }

    public List<ItemStack> getOutputs() {
        return Collections.singletonList(output);
    }
}