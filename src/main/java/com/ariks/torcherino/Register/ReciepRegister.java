package com.ariks.torcherino.Register;

import com.ariks.torcherino.Torcherino;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
                " A ",
                "ABA",
                " A ",
                'A', new ItemStack(Blocks.GOLD_BLOCK),
                'B', new ItemStack(Redstone_Clock));
        //EmeraldClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "EmeraldClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "EmeraldClock"),
                new ItemStack(Emerald_Clock),
                " A ",
                "ABA",
                " A ",
                'A', new ItemStack(Blocks.EMERALD_BLOCK),
                'B', new ItemStack(Lapis_Clock));
        //DiamondClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DiamondClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "DiamondClock"),
                new ItemStack(Diamond_Clock),
                " A ",
                "ABA",
                " A ",
                'A', new ItemStack(Blocks.DIAMOND_BLOCK),
                'B', new ItemStack(Gold_Clock));
        //LapisClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "LapisClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "LapisClock"),
                new ItemStack(Lapis_Clock),
                " A ",
                "ABA",
                " A ",
                'A', new ItemStack(Blocks.LAPIS_BLOCK),
                'B', new ItemStack(Items.CLOCK));
        //RedstoneClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "RedstoneClock"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "RedstoneClock"),
                new ItemStack(Redstone_Clock),
                " A ",
                "ABA",
                " A ",
                'A', new ItemStack(Blocks.REDSTONE_BLOCK),
                'B', new ItemStack(Emerald_Clock));

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
                "ASA",
                "CBC",
                "ASA",
                'S', new ItemStack(Blocks.DRAGON_EGG),
                'A', new ItemStack(Items.NETHER_STAR),
                'C', new ItemStack(Items.END_CRYSTAL),
                'B', new ItemStack(Binding_Element));
        //compressedtorch
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "compressedtorch"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "compressedtorch"),
                new ItemStack(Comp_Torch),
                "AAA",
                "ABA",
                "AAA",
                'A', new ItemStack(Blocks.TORCH),
                'B', new ItemStack(Binding_Element));
        //TimeWand_lvl_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl1"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl1"),
                new ItemStack(Time_Wand_lvl1),
                "  A",
                " B ",
                "B  ",
                'A', new ItemStack(Lapis_Clock),
                'B', new ItemStack(Items.STICK));
        //TimeWand_lvl_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl2"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl2"),
                new ItemStack(Time_Wand_lvl2),
                "  A",
                " B ",
                "B  ",
                'A', new ItemStack(Emerald_Clock),
                'B', new ItemStack(Items.STICK));
        //TimeWand_lvl_3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl3"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl3"),
                new ItemStack(Time_Wand_lvl3),
                "  A",
                " B ",
                "B  ",
                'A', new ItemStack(Redstone_Clock),
                'B', new ItemStack(Items.STICK));
        //TimeWand_lvl_4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl4"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl4"),
                new ItemStack(Time_Wand_lvl4),
                "  A",
                " B ",
                "B  ",
                'A', new ItemStack(Gold_Clock),
                'B', new ItemStack(Items.STICK));
        //TimeWand_lvl_5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl5"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "timewand_lvl5"),
                new ItemStack(Time_Wand_lvl5),
                "  A",
                " B ",
                "B  ",
                'A', new ItemStack(Diamond_Clock),
                'B', new ItemStack(Items.STICK));
        //Time_Storage
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage"),
                new ResourceLocation(Torcherino.MOD_ID + ":" + "time_storage"),
                new ItemStack(Time_Storage),
                "IAI",
                "IBI",
                "ICI",
                'A', new ItemStack(Items.GLASS_BOTTLE),
                'I', new ItemStack(Items.GOLD_INGOT),
                'B', new ItemStack(Blocks.HOPPER),
                'C', new ItemStack(Redstone_Clock));
    }
}