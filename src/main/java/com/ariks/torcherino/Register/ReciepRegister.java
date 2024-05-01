package com.ariks.torcherino.Register;

import com.ariks.torcherino.Torcherino;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.torcherino.Register.RegistryArray.*;

public class ReciepRegister {
    public static void preInit() {
        //GoldClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "GoldClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "GoldClock"),
                new ItemStack(Gold_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.GOLD_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Redstone_Clock));
        //EmeraldClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "EmeraldClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "EmeraldClock"),
                new ItemStack(Emerald_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.EMERALD_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Lapis_Clock));
        //DiamondClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DiamondClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DiamondClock"),
                new ItemStack(Diamond_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.DIAMOND_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Gold_Clock));
        //LapisClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "LapisClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "LapisClock"),
                new ItemStack(Lapis_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.LAPIS_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Items.CLOCK));
        //RedstoneClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "RedstoneClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "RedstoneClock"),
                new ItemStack(Redstone_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.REDSTONE_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Emerald_Clock));
        //DragonClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DragonClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DragonClock"),
                new ItemStack(Dragon_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.DRAGON_EGG),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Diamond_Clock));
        //bindingelement
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement"),
                new ItemStack(Binding_Element),
                "QWE",
                "ASD",
                "ZXC",
                'Q', new ItemStack(Items.SLIME_BALL),
                'W', new ItemStack(Items.ENDER_EYE),
                'E', new ItemStack(Items.NETHER_WART),
                'A', new ItemStack(Items.CHORUS_FRUIT_POPPED),
                'S', new ItemStack(Items.GHAST_TEAR),
                'D', new ItemStack(Items.PRISMARINE_SHARD),
                'Z', new ItemStack(Items.GLOWSTONE_DUST),
                'X', new ItemStack(Items.MAGMA_CREAM),
                'C', new ItemStack(Items.SNOWBALL));
        //bindingelement2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement2"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement2"),
                new ItemStack(Binding_Element2),
                "BTB",
                "TDT",
                "BTB",
                'D', new ItemStack(Blocks.DRAGON_EGG),
                'T', new ItemStack(time_element),
                'B', new ItemStack(Binding_Element));
        //bindingelement2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement3"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "bindingelement3"),
                new ItemStack(Binding_Element3),
                "BTB",
                "TDT",
                "BTB",
                'D', new ItemStack(Blocks.DRAGON_EGG),
                'T', new ItemStack(time_element),
                'B', new ItemStack(Binding_Element2));
        //compressedtorch
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "compressedtorch"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "compressedtorch"),
                new ItemStack(Comp_Torch),
                "AXA",
                "XCX",
                "AXA",
                'A', new ItemStack(Blocks.TORCH),
                'X', new ItemStack(time_ingot),
                'C', new ItemStack(Binding_Element));
        //TimeWand_lvl_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl1"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl1"),
                new ItemStack(Time_Wand_lvl1),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Lapis_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl2"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl2"),
                new ItemStack(Time_Wand_lvl2),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Emerald_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl3"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl3"),
                new ItemStack(Time_Wand_lvl3),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Redstone_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl4"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl4"),
                new ItemStack(Time_Wand_lvl4),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Gold_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl5"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl5"),
                new ItemStack(Time_Wand_lvl5),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Diamond_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_6
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl6"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl6"),
                new ItemStack(Time_Wand_infinite),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(Dragon_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //time_storage_lvl1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl1"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl1"),
                new ItemStack(time_storage_lvl1),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(Blocks.LAPIS_BLOCK),
                'S', new ItemStack(Items.GLASS_BOTTLE),
                'C', new ItemStack(Lapis_Clock));
        //time_storage_lvl_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl2"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl2"),
                new ItemStack(time_storage_lvl2),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl1),
                'S', new ItemStack(Binding_Element),
                'C', new ItemStack(Emerald_Clock));
        //time_storage_lvl3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl3"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl3"),
                new ItemStack(time_storage_lvl3),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl2),
                'S', new ItemStack(Binding_Element),
                'C', new ItemStack(Redstone_Clock));
        //time_storage_lvl4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl4"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl4"),
                new ItemStack(time_storage_lvl4),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl3),
                'S', new ItemStack(Binding_Element2),
                'C', new ItemStack(Gold_Clock));
        //time_storage_lvl5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl5"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_lvl5"),
                new ItemStack(time_storage_lvl5),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl4),
                'S', new ItemStack(Binding_Element2),
                'C', new ItemStack(Diamond_Clock));
        //time_storage_infinite
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_infinite"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage_infinite"),
                new ItemStack(time_storage_infinite),
                "ICI",
                "DBD",
                "ICI",
                'I', new ItemStack(time_storage_lvl5),
                'B', new ItemStack(Dragon_Clock),
                'D', new ItemStack(Binding_Element3),
                'C', new ItemStack(time_element));
        //time_element
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_element"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_element"),
                new ItemStack(time_element),
                "DXG",
                "ZCZ",
                "RNE",
                'R', new ItemStack(Items.REDSTONE),
                'X', new ItemStack(Items.END_CRYSTAL),
                'G', new ItemStack(Items.GOLD_INGOT),
                'Z', new ItemStack(time_particle),
                'C', new ItemStack(Items.CLOCK),
                'E', new ItemStack(Items.EMERALD),
                'N', new ItemStack(Items.NETHER_STAR),
                'D', new ItemStack(Items.DIAMOND));
        //time_nugget
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_nugget"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_nugget"),
                new ItemStack(time_nugget),
                "TIT",
                "GBG",
                "TIT",
                'I', new ItemStack(Items.IRON_NUGGET),
                'T', new ItemStack(time_particle),
                'G', new ItemStack(Items.GOLD_NUGGET),
                'B', new ItemStack(Items.BLAZE_POWDER));
        //time_ingot
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_ingot"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_ingot"),
                new ItemStack(time_ingot),
                "NNN",
                "NNN",
                "NNN",
                'N', new ItemStack(time_nugget));
        //Time_Ingot_To_Nugget
        GameRegistry.addShapelessRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "Time_Ingot_To_Nugget"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "Time_Ingot_To_Nugget"),
                new ItemStack(time_nugget,9), // Результат рецепта
                Ingredient.fromItems(time_ingot) // Входные предметы
        );
        //Time_Stick
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_stick"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_stick"),
                new ItemStack(time_stick),
                " I ",
                " I ",
                " I ",
                'I', new ItemStack(time_ingot));
        //Time_Casing
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_casing"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_casing"),
                new ItemStack(time_casing),
                "SDS",
                "SCS",
                "SDS",
                'S', new ItemStack(time_stick),
                'D', new ItemStack(Items.GLOWSTONE_DUST),
                'C', new ItemStack(Blocks.OBSIDIAN));
    }
}