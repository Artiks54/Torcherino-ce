package com.ariks.torcherinoCe.integration.CraftTweaker;

import com.ariks.torcherinoCe.Block.RfMolecular.MolecularRecipe;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.tce.TileRfMolecular")

public class TileRfMolecular {
    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output, long energy) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        inputStack.setItemDamage(input.getMetadata());
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        outputStack.setItemDamage(output.getMetadata());
        MolecularRecipe.addRecipe(new MolecularRecipe(inputStack, outputStack, energy));
    }
}