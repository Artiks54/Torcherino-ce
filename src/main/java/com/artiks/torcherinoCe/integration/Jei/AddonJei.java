package com.artiks.torcherinoCe.integration.Jei;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.GuiRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.MolecularRecipeFarm;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.GuiEnergyParticle;
import com.artiks.torcherinoCe.Block.Energy.Molecular.GuiRfMolecular;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularRecipe;
import com.artiks.torcherinoCe.Register.RegistryBlock;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.integration.Jei.GrowAccelerator.*;
import com.artiks.torcherinoCe.integration.Jei.MolecularFarm.MolecularFarmRecipeCategory;
import com.artiks.torcherinoCe.integration.Jei.MolecularFarm.MolecularFarmWrapper;
import com.artiks.torcherinoCe.integration.Jei.Particle.*;
import com.artiks.torcherinoCe.integration.Jei.RfMolecular.*;
import com.artiks.torcherinoCe.utility.Config;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;
import java.util.Collections;

@JEIPlugin
public class AddonJei implements IModPlugin {

    public void register(IModRegistry registry) {
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, LocalizedStringKey.jei_info_time_manipulator);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, LocalizedStringKey.jei_info_time_manipulator_need_time + " " + Config.RequiredTimeManipulator);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Acceleration), VanillaTypes.ITEM, LocalizedStringKey.jei_info_time_accelerator + " " + LocalizedStringKey.StrTextRadius + ": " +Config.AccelerationRadius + " " + LocalizedStringKey.StrTextSpeed + ": " + (Config.AccelerationSpeed * 100) + "%");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_collectors), VanillaTypes.ITEM, LocalizedStringKey.jei_info_time_collector);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Storage), VanillaTypes.ITEM, LocalizedStringKey.jei_info_time_storage);
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_speed),VanillaTypes.ITEM, LocalizedStringKey.jei_maxItem);
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_count),VanillaTypes.ITEM,LocalizedStringKey.jei_maxItem);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl1),VanillaTypes.ITEM,LocalizedStringKey.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl2),VanillaTypes.ITEM,LocalizedStringKey.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl3),VanillaTypes.ITEM,LocalizedStringKey.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl4),VanillaTypes.ITEM,LocalizedStringKey.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl5),VanillaTypes.ITEM,LocalizedStringKey.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_energy_collectors),VanillaTypes.ITEM,LocalizedStringKey.jei_info_time_energy_collector+" RF/PerTick: "+Config.ETC_EnergyPerTick);

        String idEnergy = Tags.MODID + "_energy";
        registry.handleRecipes(ParticleRecipeJei.class, recipe -> recipe, idEnergy);
        registry.addRecipes(Collections.singletonList(new ParticleRecipeJei(null, null,new ItemStack(RegistryItems.time_particle))), idEnergy);
        registry.addRecipeClickArea(GuiEnergyParticle.class, 32, 29, 25, 18, idEnergy);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.EnergyParticle), idEnergy);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Particle_collectors), idEnergy);

        String idGrow = Tags.MODID + "_grow";
        registry.handleRecipes(ImageRecipeJei.class, recipe -> recipe, idGrow);
        registry.addRecipes(Collections.singletonList(new ImageRecipeJei()), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl1), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl2), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl3), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl4), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl5), idGrow);

        String furnaceCategoryUid = VanillaRecipeCategoryUid.SMELTING;
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Block_Luck), furnaceCategoryUid);

        String idMolecular = Tags.MODID + "_molecular";
        registry.handleRecipes(MolecularRecipe.class, new MolecularRfWrapper(), idMolecular);
        registry.addRecipes(MolecularRecipe.getRecipes(), idMolecular);
        registry.addRecipeClickArea(GuiRfMolecular.class, 7, 24, 15, 30, idMolecular);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular), idMolecular);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular_Legendary), idMolecular);

        String idMolecularFarm = Tags.MODID + "_molecular_farm";
        registry.handleRecipes(MolecularRecipeFarm.class, new MolecularFarmWrapper(), idMolecularFarm);
        registry.addRecipes(MolecularRecipeFarm.getRecipes(), idMolecularFarm);
        registry.addRecipeClickArea(GuiRfMolecularFarm.class, 91, 25, 26, 26, idMolecularFarm);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular_Farm), idMolecularFarm);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new EnergyParticleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImageRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularRfRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularFarmRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}