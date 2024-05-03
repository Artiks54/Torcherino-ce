package com.ariks.torcherino.integration.Jei;

import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.Register.RegistryBlock;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class AddonJei implements IModPlugin {
    public void register(IModRegistry registry) {
        registry.addIngredientInfo(new ItemStack(RegistryItems.time_particle),ItemStack.class,"Generated in a particle collector.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Particle_collectors_lvl1),ItemStack.class,"Generates time particles * 1");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Particle_collectors_lvl2),ItemStack.class,"Generates time particles * 8");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Particle_collectors_lvl3),ItemStack.class,"Generates time particles * 64");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Manipulator),ItemStack.class,"Allows you to change the time in the world.");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Acceleration),ItemStack.class,"Uses time to accelerate by 500% within a 3x3x3 radius");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_collectors),ItemStack.class,"Passively accumulates time");
        registry.addIngredientInfo(new ItemStack(RegistryBlock.Time_Storage),ItemStack.class,"It is used to store time");
    }
}