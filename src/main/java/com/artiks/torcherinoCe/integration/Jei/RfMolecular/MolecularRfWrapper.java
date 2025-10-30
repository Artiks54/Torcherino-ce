package com.artiks.torcherinoCe.integration.Jei.RfMolecular;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import org.jetbrains.annotations.NotNull;

public class MolecularRfWrapper implements IRecipeWrapperFactory<MolecularRecipe> {

    @Override
    public @NotNull IRecipeWrapper getRecipeWrapper(@NotNull MolecularRecipe MolecularRecipe) {
        return new MolecularRecipeJei(MolecularRecipe);
    }
}
