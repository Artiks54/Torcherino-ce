package com.artiks.torcherinoCe.integration.Jei.MolecularFarm;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.MolecularRecipeFarm;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.jetbrains.annotations.NotNull;

public class MolecularFarmWrapper implements IRecipeWrapperFactory<MolecularRecipeFarm> {

    @Override
    public @NotNull IRecipeWrapper getRecipeWrapper(@NotNull MolecularRecipeFarm MolecularRecipeFarm) {
        return new MolecularFarmJei(MolecularRecipeFarm);
    }
}
