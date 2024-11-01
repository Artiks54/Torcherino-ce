package com.ariks.torcherino.integration.Jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import java.util.Collections;
import java.util.List;

public class ParticleRecipe implements IRecipeWrapper {
    private final ItemStack input;
    private final ItemStack output;

    public ParticleRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }
    public List<ItemStack> getInputs() {
        return Collections.singletonList(input);
    }
    public List<ItemStack> getOutputs() {
        return Collections.singletonList(output);
    }
}