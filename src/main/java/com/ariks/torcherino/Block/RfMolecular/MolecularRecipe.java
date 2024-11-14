package com.ariks.torcherino.Block.RfMolecular;

import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MolecularRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final long energy;
    public static ArrayList<MolecularRecipe> recipes = new ArrayList<>();
    public MolecularRecipe(ItemStack input, ItemStack output, long energy) {
        this.input = input;
        this.output = output;
        this.energy = energy;
    }
    public ItemStack getInput() {
        return input;
    }
    public long getEnergy() {
        return energy;
    }
    public @NotNull ItemStack getRecipeOutput() {
        return output.copy();
    }
    public static void addRecipe(MolecularRecipe recipe) {
        recipes.add(recipe);
    }
    public static ArrayList<MolecularRecipe> getRecipes() {
        return recipes;
    }
    public static void preInit() {
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.time_nugget,9), new ItemStack(RegistryItems.time_ingot,1), Config.MTR_RecipeIngot));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.time_ingot,1), new ItemStack(RegistryItems.time_nugget,9), Config.MTR_RecipeNugget));
    }
}