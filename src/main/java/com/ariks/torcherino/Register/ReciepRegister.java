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
                "SBS",
                "ASA",
                'S', new ItemStack(Blocks.DRAGON_EGG),
                'A', new ItemStack(Items.NETHER_STAR),
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
    }
}