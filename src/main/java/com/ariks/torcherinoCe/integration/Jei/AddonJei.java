package com.ariks.torcherinoCe.integration.Jei;

import com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration.GuiEnergyParticle;
import com.ariks.torcherinoCe.Block.RfMolecular.GuiRfMolecular;
import com.ariks.torcherinoCe.Block.RfMolecular.MolecularRecipe;
import com.ariks.torcherinoCe.Register.RegistryBlock;
import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.integration.Jei.GrowAccelerator.*;
import com.ariks.torcherinoCe.integration.Jei.Particle.*;
import com.ariks.torcherinoCe.integration.Jei.RfMolecular.*;
import com.ariks.torcherinoCe.utility.Config;
import com.ariks.torcherinoCe.utility.LocalizedStringKey;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import java.util.Collections;

@JEIPlugin
public class AddonJei implements IModPlugin {
    public final LocalizedStringKey LS = new LocalizedStringKey();

    public void register(IModRegistry registry) {
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, LS.jei_info_time_manipulator);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, LS.jei_info_time_manipulator_need_time + " " + Config.RequiredTimeManipulator);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Acceleration), VanillaTypes.ITEM, LS.jei_info_time_accelerator + " " + LS.StrTextRadius + ": " +Config.AccelerationRadius + " " + LS.StrTextSpeed + ": " + (Config.AccelerationSpeed * 100) + "%");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_collectors), VanillaTypes.ITEM, LS.jei_info_time_collector);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Storage), VanillaTypes.ITEM, LS.jei_info_time_storage);
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_speed),VanillaTypes.ITEM, LS.jei_maxItem);
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_count),VanillaTypes.ITEM,LS.jei_maxItem);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl1),VanillaTypes.ITEM,LS.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl2),VanillaTypes.ITEM,LS.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl3),VanillaTypes.ITEM,LS.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl4),VanillaTypes.ITEM,LS.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl5),VanillaTypes.ITEM,LS.jei_info_grow);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_energy_collectors),VanillaTypes.ITEM,LS.jei_info_time_energy_collector+" RF/PerTick: "+Config.ETC_EnergyPerTick);

        String idEnergy = Tags.MODID + "_energy";
        registry.handleRecipes(ParticleRecipeJei.class, recipe -> recipe, idEnergy);
        registry.addRecipes(Collections.singletonList(new ParticleRecipeJei(null, null,new ItemStack(RegistryItems.time_particle))), idEnergy);
        registry.addRecipeClickArea(GuiEnergyParticle.class, 32, 29, 25, 18, idEnergy);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.EnergyParticle), idEnergy);

        String idParticle = Tags.MODID + "_particle";
        registry.handleRecipes(ParticleRecipeJei.class, recipe -> recipe, idParticle);
        registry.addRecipes(Collections.singletonList(new ParticleRecipeJei(new ItemStack(RegistryItems.upgrade_count),new ItemStack(RegistryItems.upgrade_speed), new ItemStack(RegistryItems.time_particle,2))), idParticle);
        registry.addRecipes(Collections.singletonList(new ParticleRecipeJei(null,null, new ItemStack(RegistryItems.time_particle))), idParticle);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Particle_collectors), idParticle);

        String idGrow = Tags.MODID + "_grow";
        registry.handleRecipes(ImageRecipeJei.class, recipe -> recipe, idGrow);
        registry.addRecipes(Collections.singletonList(new ImageRecipeJei()), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl1), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl2), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl3), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl4), idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl5), idGrow);

        String idMolecular = Tags.MODID + "_molecular";
        registry.handleRecipes(MolecularRecipe.class, new MolecularRfWrapper(), idMolecular);
        registry.addRecipes(MolecularRecipe.getRecipes(), idMolecular);
        registry.addRecipeClickArea(GuiRfMolecular.class, 7, 24, 15, 30, idMolecular);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.RF_Molecular), idMolecular);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new EnergyParticleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ParticleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImageRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MolecularRfRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}