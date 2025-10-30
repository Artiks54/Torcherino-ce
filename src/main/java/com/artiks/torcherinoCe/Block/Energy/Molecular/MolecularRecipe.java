package com.artiks.torcherinoCe.Block.Energy.Molecular;

import com.artiks.torcherinoCe.Register.RegistryBlock;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.utility.Config;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MolecularRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe{
    private final ItemStack input;
    private final ItemStack output;
    private final long energy;
    private static final List<MolecularRecipe> recipes = new ArrayList<>();
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

    @Override
    public boolean matches(@NotNull InventoryCrafting inv, @NotNull World worldIn) {
        return false;
    }

    @Override
    public @NotNull ItemStack getCraftingResult(@NotNull InventoryCrafting inv) {
        return output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 1 && height >= 1;
    }

    public @NotNull ItemStack getRecipeOutput() {
        return output.copy();
    }

    public static void addRecipe(MolecularRecipe recipe) {
        recipes.add(recipe);
    }

    public static List<MolecularRecipe> getRecipes() {
        return new ArrayList<>(recipes);
    }

    public boolean matches(ItemStack inputStack) {
        return ItemStack.areItemsEqual(input, inputStack) && input.getCount() <= inputStack.getCount();
    }

    public static void postInit() {
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Items.SKULL,1,1), new ItemStack(Items.NETHER_STAR,1), Config.MTR_RecipeStar));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.time_star,1), new ItemStack(RegistryItems.time_energy_star,1), Config.MTR_RecipeEnergyStar));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.time_energy_star,9), new ItemStack(RegistryBlock.Block_Red_Star,1), 500_000_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.time_broken_plate,1), new ItemStack(RegistryItems.time_plate,1), 1_000_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(RegistryItems.DragonEgg,1), new ItemStack(Blocks.DRAGON_EGG,1), 10_000_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.ICE,1), new ItemStack(Blocks.PACKED_ICE,1), 20_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Items.SKULL,1), new ItemStack(Items.SKULL,1,1), 1_000_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.DIRT,1), new ItemStack(Blocks.GRASS,1), 5_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.GRASS,1), new ItemStack(Blocks.MYCELIUM,1), 7_500));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.WOOL,1,14), new ItemStack(Blocks.REDSTONE_BLOCK,1), 80_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.WOOL,1,11), new ItemStack(Blocks.LAPIS_BLOCK,1), 80_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.WOOL,1,4), new ItemStack(Blocks.GLOWSTONE,1), 100_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.NETHERRACK,1), new ItemStack(Blocks.SOUL_SAND,1), 12_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.COBBLESTONE,1), new ItemStack(Blocks.GRAVEL,1), 5_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.GRAVEL,1), new ItemStack(Blocks.SAND,1), 5_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Blocks.COAL_BLOCK,1), new ItemStack(Items.DIAMOND,1), 1_500_000));
        MolecularRecipe.addRecipe(new MolecularRecipe(new ItemStack(Items.COAL,1,1), new ItemStack(Items.COAL,1), 25_000));
    }
}