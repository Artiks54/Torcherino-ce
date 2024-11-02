package com.ariks.torcherino.integration.Jei;

import com.ariks.torcherino.Register.RegistryBlock;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

@JEIPlugin
public class AddonJei implements IModPlugin {
    private final String idEnergy = Torcherino.MOD_ID + "_energy";
    private final String idParticle = Torcherino.MOD_ID + "_particle";
    private final String idGrow = Torcherino.MOD_ID + "_grow";
    private final String idCharger = Torcherino.MOD_ID + "_charger";
    private final String formattedValue = NumberFormat.getNumberInstance().format(Config.RFPerTickEnergyParticle);

    public void register(IModRegistry registry) {

        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, "Allows you to change the time in the world.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator), VanillaTypes.ITEM, "Need time: "+Config.RequiredTimeManipulator);
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Acceleration), VanillaTypes.ITEM, "Uses time to accelerate by 500% within a 3x3x3 radius");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_collectors), VanillaTypes.ITEM, "Passively accumulates time");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Storage), VanillaTypes.ITEM, "It is used to store time");
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_speed),VanillaTypes.ITEM,"Maximum 64");
        registry.addIngredientInfo(new ItemStack(RegistryItems.upgrade_count),VanillaTypes.ITEM,"Maximum 64");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl1),VanillaTypes.ITEM,"Placed a block under the block on which something is growing and this will accelerate growth.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl2),VanillaTypes.ITEM,"Placed a block under the block on which something is growing and this will accelerate growth.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl3),VanillaTypes.ITEM,"Placed a block under the block on which something is growing and this will accelerate growth.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl4),VanillaTypes.ITEM,"Placed a block under the block on which something is growing and this will accelerate growth.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Grow_lvl5),VanillaTypes.ITEM,"Placed a block under the block on which something is growing and this will accelerate growth.");
        registry.handleRecipes(ParticleRecipe.class, recipe -> recipe, idEnergy);
        registry.addRecipes(Collections.singletonList(new ParticleRecipe(null, null,new ItemStack(RegistryItems.time_particle))),idEnergy);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.EnergyParticle),idEnergy);
        registry.handleRecipes(ParticleRecipe.class, recipe -> recipe, idParticle);
        registry.addRecipes(Collections.singletonList(new ParticleRecipe(new ItemStack(RegistryItems.upgrade_count),new ItemStack(RegistryItems.upgrade_speed), new ItemStack(RegistryItems.time_particle,2))),idParticle);
        registry.addRecipes(Collections.singletonList(new ParticleRecipe(null,null, new ItemStack(RegistryItems.time_particle))),idParticle);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Particle_collectors),idParticle);
        registry.handleRecipes(ImageRecipe.class, recipe -> recipe, idGrow);
        registry.addRecipes(Collections.singletonList(new ImageRecipe()),idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl1),idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl2),idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl3),idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl4),idGrow);
        registry.addRecipeCatalyst(new ItemStack(RegistryBlock.Grow_lvl5),idGrow);
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new EnergyParticleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ParticleRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImageRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}