package com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm;

import com.artiks.torcherinoCe.Tags;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MolecularRecipeFarm extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final long energy;
    private static final List<MolecularRecipeFarm> recipes = new ArrayList<>();

    public MolecularRecipeFarm(ItemStack input, ItemStack output, long energy) {
        this.input = input;
        this.output = output;
        this.energy = energy;
        this.setRegistryName(new ResourceLocation(Tags.MODID,"Molecular_Recipe_Farm"+ UUID.randomUUID()+output.getTranslationKey()));
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

    public boolean matches(ItemStack inputStack) {
        return ItemStack.areItemsEqual(input, inputStack) && input.getCount() <= inputStack.getCount();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 1 && height >= 1;
    }

    public @NotNull ItemStack getRecipeOutput() {
        return output.copy();
    }

    @Override
    public @NotNull ItemStack getCraftingResult(@NotNull InventoryCrafting inv) {
        return output.copy();
    }

    public static void addRecipe(MolecularRecipeFarm recipe) {
        recipes.add(recipe);
    }

    public static List<MolecularRecipeFarm> getRecipes() {
        return new ArrayList<>(recipes);
    }

    public static void postInit() {
        //Other vanilla
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.DEADBUSH,1),new ItemStack(Blocks.DEADBUSH,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.VINE,1),new ItemStack(Blocks.VINE,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.WATERLILY,1),new ItemStack(Blocks.WATERLILY,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.TALLGRASS,1,1),new ItemStack(Blocks.TALLGRASS,1,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.TALLGRASS,1,2),new ItemStack(Blocks.TALLGRASS,1,2),75));
        //Vanilla flowers
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.YELLOW_FLOWER,1),new ItemStack(Blocks.YELLOW_FLOWER,1),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1),new ItemStack(Blocks.RED_FLOWER,1),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,1),new ItemStack(Blocks.RED_FLOWER,1,1),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,2),new ItemStack(Blocks.RED_FLOWER,1,2),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,3),new ItemStack(Blocks.RED_FLOWER,1,3),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,4),new ItemStack(Blocks.RED_FLOWER,1,4),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,5),new ItemStack(Blocks.RED_FLOWER,1,5),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,6),new ItemStack(Blocks.RED_FLOWER,1,6),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,7),new ItemStack(Blocks.RED_FLOWER,1,7),100));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_FLOWER,1,8),new ItemStack(Blocks.RED_FLOWER,1,8),100));
        //Vanilla flowers double
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.DOUBLE_PLANT,1),new ItemStack(Blocks.DOUBLE_PLANT,1),125));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.DOUBLE_PLANT,1,1),new ItemStack(Blocks.DOUBLE_PLANT,1,1),125));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.DOUBLE_PLANT,1,4),new ItemStack(Blocks.DOUBLE_PLANT,1,4),125));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.DOUBLE_PLANT,1,5),new ItemStack(Blocks.DOUBLE_PLANT,1,5),125));
        //Default
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.NETHER_WART,1),new ItemStack(Items.NETHER_WART,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.CHORUS_FRUIT,1),new ItemStack(Items.CHORUS_FRUIT,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.DYE,1,3),new ItemStack(Items.DYE,1,3),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.REEDS,1),new ItemStack(Items.REEDS,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.RED_MUSHROOM,1),new ItemStack(Blocks.RED_MUSHROOM,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.BROWN_MUSHROOM,1),new ItemStack(Blocks.BROWN_MUSHROOM,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.CACTUS,1),new ItemStack(Blocks.CACTUS,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.POTATO,1),new ItemStack(Items.POTATO,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.CARROT,1),new ItemStack(Items.CARROT,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.MELON_SEEDS,1),new ItemStack(Blocks.MELON_BLOCK,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.PUMPKIN_SEEDS,1),new ItemStack(Blocks.PUMPKIN,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.WHEAT_SEEDS,1),new ItemStack(Items.WHEAT,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.WHEAT,1),new ItemStack(Items.WHEAT_SEEDS,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.BEETROOT_SEEDS,1),new ItemStack(Items.BEETROOT,1),150));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Items.BEETROOT,1),new ItemStack(Items.BEETROOT_SEEDS,1),150));
        //Leaves
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES,1),new ItemStack(Blocks.LEAVES,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES2,1),new ItemStack(Blocks.LEAVES2,1),95));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES,1,1),new ItemStack(Blocks.LEAVES,1,1),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES,1,2),new ItemStack(Blocks.LEAVES,1,2),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES,1,3),new ItemStack(Blocks.LEAVES,1,3),75));
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(new ItemStack(Blocks.LEAVES2,1,1),new ItemStack(Blocks.LEAVES2,1,1),95));
    }
}