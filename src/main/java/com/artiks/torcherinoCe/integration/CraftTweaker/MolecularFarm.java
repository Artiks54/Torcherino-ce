package com.artiks.torcherinoCe.integration.CraftTweaker;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.MolecularRecipeFarm;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.tce.MolecularFarm")

public class MolecularFarm {
    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output, long energy) {
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        inputStack.setItemDamage(input.getMetadata());
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        outputStack.setItemDamage(output.getMetadata());
        MolecularRecipeFarm.addRecipe(new MolecularRecipeFarm(inputStack, outputStack, energy));
    }
}