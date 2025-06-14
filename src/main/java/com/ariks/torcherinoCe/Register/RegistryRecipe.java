package com.ariks.torcherinoCe.Register;

import com.ariks.torcherinoCe.Tags;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.torcherinoCe.Register.RegistryItems.*;
import static com.ariks.torcherinoCe.Register.RegistryBlock.*;

public final class RegistryRecipe {
    public static void preInit() {
        String[] Level = {"lvl1", "lvl2", "lvl3", "lvl4", "lvl5"};
        Block[] torchBlocks = {Torch_lvl_1, Torch_lvl_2, Torch_lvl_3, Torch_lvl_4, Torch_lvl_5};
        Block[] CTorchBlocks = {Compressed_Torch_lvl1, Compressed_Torch_lvl2, Compressed_Torch_lvl3, Compressed_Torch_lvl4, Compressed_Torch_lvl5};
        Block[] DTorchBlocks = {D_Compressed_Torch_lvl1, D_Compressed_Torch_lvl2, D_Compressed_Torch_lvl3, D_Compressed_Torch_lvl4, D_Compressed_Torch_lvl5};
        //CTorch
        for (int i = 0; i < Level.length; i++) {
            String level = Level[i];
            Block input = torchBlocks[i];
            Block output = CTorchBlocks[i];
            String recipeID = "compressed_torch_" + level;
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Tags.MODID, recipeID),
                    null,
                    new ItemStack(output),
                    "TTT",
                    "TBT",
                    "TTT",
                    'T', new ItemStack(input),
                    'B', new ItemStack(binding_Element2)
            );
        }
        //DTorch
        for (int i = 0; i < Level.length; i++) {
            String level = Level[i];
            Block input = CTorchBlocks[i];
            Block output = DTorchBlocks[i];
            String recipeID = "d_compressed_torch_" + level;
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(Tags.MODID, recipeID),
                    null,
                    new ItemStack(output),
                    "TTT",
                    "TBT",
                    "TTT",
                    'T', new ItemStack(input),
                    'B', new ItemStack(binding_Element3)
            );
        }
        //Torch_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Torch_1"), null,
                new ItemStack(Torch_lvl_1),
                "LFL",
                "CBC",
                "TIT",
                'C', new ItemStack(Blocks.OBSIDIAN),
                'T', new ItemStack(time_stick),
                'I', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'B', new ItemStack(time_core),
                'L', new ItemStack(lapis_Clock));
        //Torch_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Torch_2"), null,
                new ItemStack(Torch_lvl_2),
                "LFL",
                "CBC",
                "TIT",
                'C', new ItemStack(Torch_lvl_1),
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'B', new ItemStack(binding_Element),
                'L', new ItemStack(emerald_Clock));
        //Torch_3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Torch_3"), null,
                new ItemStack(Torch_lvl_3),
                "LFL",
                "CBC",
                "TIT",
                'C', new ItemStack(Torch_lvl_2),
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'B', new ItemStack(binding_Element),
                'L', new ItemStack(redstone_Clock));
        //Torch_4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Torch_4"), null,
                new ItemStack(Torch_lvl_4),
                "LFL",
                "CBC",
                "TIT",
                'C', new ItemStack(Torch_lvl_3),
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'B', new ItemStack(binding_Element),
                'L', new ItemStack(gold_Clock));
        //Torch_5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Torch_5"), null,
                new ItemStack(Torch_lvl_5),
                "LFL",
                "CBC",
                "TIT",
                'C', new ItemStack(Torch_lvl_4),
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'B', new ItemStack(binding_Element),
                'L', new ItemStack(diamond_Clock));
        //TimeCore
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "TimeCore"), null,
                new ItemStack(time_core),
                "BSB",
                "CEC",
                "LDA",
                'B', new ItemStack(binding_Element),
                'S', new ItemStack(Time_Storage),
                'C', new ItemStack(time_casing),
                'E', new ItemStack(time_element),
                'L', new ItemStack(Time_collectors),
                'D', new ItemStack(lapis_Clock),
                'A', new ItemStack(Time_Acceleration));
        //TimeStorage
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "TimeStorage"), null,
                new ItemStack(Time_Storage),
                "CIC",
                "DTD",
                "CIC",
                'C', new ItemStack(time_casing),
                'T', new ItemStack(time_storage_lvl1),
                'I', new ItemStack(time_ingot),
                'D', new ItemStack(Items.DIAMOND));
        //TimeManipulator
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "TimeManipulator"), null,
                new ItemStack(Time_Manipulator),
                "FDF",
                "SBS",
                "CTC",
                'D', new ItemStack(dragon_Clock),
                'S', new ItemStack(time_stick),
                'B', new ItemStack(binding_Element3),
                'C', new ItemStack(time_casing),
                'F', new ItemStack(comp_Torch),
                'T', new ItemStack(time_core));
        //EnergyTimeManipulator
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "EnergyTimeManipulator"), null,
                new ItemStack(Time_Energy_Manipulator),
                "FDF",
                "SBS",
                "CTC",
                'D', new ItemStack(time_plate),
                'S', new ItemStack(time_storage_lvl3),
                'B', new ItemStack(Time_Manipulator),
                'C', new ItemStack(time_energy_star),
                'F', new ItemStack(redstone_Clock),
                'T', new ItemStack(upgrade_speed));
        //TimeCollectors
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "TimeCollectors"), null,
                new ItemStack(Time_collectors),
                "CFC",
                "ITI",
                "CEC",
                'C', new ItemStack(time_casing),
                'T', new ItemStack(Time_Storage),
                'I', new ItemStack(time_ingot),
                'F', new ItemStack(comp_Torch),
                'E', new ItemStack(time_element));
        //TimeAceleration
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "TimeAceleration"), null,
                new ItemStack(Time_Acceleration),
                "CFC",
                "ITI",
                "CEC",
                'C', new ItemStack(time_casing),
                'F', new ItemStack(binding_Element),
                'T', new ItemStack(comp_Torch),
                'I', new ItemStack(emerald_Clock),
                'E', new ItemStack(Time_Storage));
        //ParticleCollector_lvl_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "ParticleCollector"), null,
                new ItemStack(Particle_collectors),
                "DME",
                "GBG",
                "RHL",
                'R', new ItemStack(Blocks.REDSTONE_BLOCK),
                'H', new ItemStack(Blocks.HOPPER),
                'L', new ItemStack(Blocks.LAPIS_BLOCK),
                'G', new ItemStack(Items.CLOCK),
                'M', new ItemStack(Blocks.BEACON),
                'D', new ItemStack(Blocks.DIAMOND_BLOCK),
                'E', new ItemStack(Blocks.EMERALD_BLOCK),
                'B', new ItemStack(binding_Element));
        //RF Particle Collector
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "EnergyParticleCollector"), null,
                new ItemStack(EnergyParticle),
                "PCP",
                "TMT",
                "RSR",
                'P', new ItemStack(time_casing),
                'C', new ItemStack(Particle_collectors),
                'T', new ItemStack(time_core),
                'M', new ItemStack(binding_Element2),
                'R', new ItemStack(redstone_Clock),
                'S', new ItemStack(time_energy_star));
        //UpgradeModule
        //upgrade_speed
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "upgrade_speed"), null,
                new ItemStack(upgrade_speed),
                "STS",
                "RTR",
                "ITI",
                'I', new ItemStack(time_ingot),
                'S', new ItemStack(Blocks.STICKY_PISTON),
                'R', new ItemStack(Blocks.REDSTONE_BLOCK),
                'T', new ItemStack(time_particle_redstone));
        //upgrade_count
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "upgrade_count"), null,
                new ItemStack(upgrade_count),
                "STS",
                "RTR",
                "ITI",
                'I', new ItemStack(time_ingot),
                'S', new ItemStack(Blocks.PISTON),
                'R', new ItemStack(Blocks.LAPIS_BLOCK),
                'T', new ItemStack(time_particle_gold));
        //GoldClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "GoldClock"), null,
                new ItemStack(gold_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.GOLD_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(redstone_Clock));
        //EmeraldClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "EmeraldClock"), null,
                new ItemStack(emerald_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.EMERALD_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(lapis_Clock));
        //DiamondClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "DiamondClock"), null,
                new ItemStack(diamond_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.DIAMOND_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(gold_Clock));
        //LapisClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "LapisClock"), null,
                new ItemStack(lapis_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.LAPIS_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(Items.CLOCK));
        //RedstoneClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "RedstoneClock"), null,
                new ItemStack(redstone_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.REDSTONE_BLOCK),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(emerald_Clock));
        //DragonClock
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "DragonClock"), null,
                new ItemStack(dragon_Clock),
                "NAN",
                "ABA",
                "NAN",
                'A', new ItemStack(Blocks.DRAGON_EGG),
                'N', new ItemStack(time_ingot),
                'B', new ItemStack(diamond_Clock));
        //bindingelement
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "bindingelement"), null,
                new ItemStack(binding_Element),
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
                new ResourceLocation(Tags.MODID + ":" + "bindingelement2"), null,
                new ItemStack(binding_Element2),
                "BTB",
                "TDT",
                "BTB",
                'D', new ItemStack(time_energy_star),
                'T', new ItemStack(time_element),
                'B', new ItemStack(binding_Element));
        //bindingelement3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "bindingelement3"), null,
                new ItemStack(binding_Element3),
                "BTB",
                "TDT",
                "BTB",
                'D', new ItemStack(Blocks.DRAGON_EGG),
                'T', new ItemStack(time_element),
                'B', new ItemStack(binding_Element2));
        //compressedtorch
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "compressedtorch"), null,
                new ItemStack(comp_Torch),
                "AAA",
                "ACA",
                "AAA",
                'A', new ItemStack(Blocks.TORCH),
                'C', new ItemStack(binding_Element));
        //TimeWand_lvl_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl1"), null,
                new ItemStack(time_Wand_lvl1),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(lapis_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl2"), null,
                new ItemStack(time_Wand_lvl2),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(emerald_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl3"), null,
                new ItemStack(time_Wand_lvl3),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(redstone_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl4"), null,
                new ItemStack(time_Wand_lvl4),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(gold_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl5"), null,
                new ItemStack(time_Wand_lvl5),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(diamond_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //TimeWand_lvl_6
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "timewand_lvl6"), null,
                new ItemStack(time_Wand_infinite),
                " AT",
                " BA",
                "B  ",
                'A', new ItemStack(dragon_Clock),
                'T', new ItemStack(time_element),
                'B', new ItemStack(time_stick));
        //time_storage_lvl1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_lvl1"), null,
                new ItemStack(time_storage_lvl1),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(Blocks.LAPIS_BLOCK),
                'S', new ItemStack(Items.GLASS_BOTTLE),
                'C', new ItemStack(lapis_Clock));
        //time_storage_lvl_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_lvl2"), null,
                new ItemStack(time_storage_lvl2),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl1),
                'S', new ItemStack(binding_Element),
                'C', new ItemStack(emerald_Clock));
        //time_storage_lvl3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_lvl3"), null,
                new ItemStack(time_storage_lvl3),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl2),
                'S', new ItemStack(binding_Element),
                'C', new ItemStack(redstone_Clock));
        //time_storage_lvl4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_lvl4"), null,
                new ItemStack(time_storage_lvl4),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl3),
                'S', new ItemStack(binding_Element2),
                'C', new ItemStack(gold_Clock));
        //time_storage_lvl5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_lvl5"), null,
                new ItemStack(time_storage_lvl5),
                "ITI",
                "BSB",
                "ICI",
                'T', new ItemStack(time_element),
                'I', new ItemStack(time_ingot),
                'B', new ItemStack(time_storage_lvl4),
                'S', new ItemStack(binding_Element2),
                'C', new ItemStack(diamond_Clock));
        //time_storage_infinite
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_storage_infinite"), null,
                new ItemStack(time_storage_infinite),
                "ICI",
                "DBD",
                "ICI",
                'I', new ItemStack(time_storage_lvl5),
                'B', new ItemStack(dragon_Clock),
                'D', new ItemStack(binding_Element3),
                'C', new ItemStack(time_element));
        //time_element
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_element"), null,
                new ItemStack(time_element),
                "DNG",
                "CBC",
                "RIE",
                'R', new ItemStack(time_particle_redstone),
                'G', new ItemStack(time_particle_gold),
                'B', new ItemStack(binding_Element),
                'C', new ItemStack(Items.CLOCK),
                'E', new ItemStack(time_particle_emerald),
                'N', new ItemStack(time_energy_star),
                'I', new ItemStack(time_ingot),
                'D', new ItemStack(time_particle_diamond));
        //time_particle_redstone
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_particle_redstone"), null,
                new ItemStack(time_particle_redstone),
                "PIP",
                "PBP",
                "PIP",
                'I', new ItemStack(Items.REDSTONE),
                'B', new ItemStack(binding_Element),
                'P', new ItemStack(time_particle));
        //time_particle_gold
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_particle_gold"), null,
                new ItemStack(time_particle_gold),
                "PIP",
                "PBP",
                "PIP",
                'I', new ItemStack(Items.GOLD_INGOT),
                'B', new ItemStack(binding_Element),
                'P', new ItemStack(time_particle));
        //time_particle_diamond
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_particle_diamond"), null,
                new ItemStack(time_particle_diamond),
                "PIP",
                "PBP",
                "PIP",
                'I', new ItemStack(Items.DIAMOND),
                'B', new ItemStack(binding_Element),
                'P', new ItemStack(time_particle));
        //time_particle_emerald
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_particle_emerald"), null,
                new ItemStack(time_particle_emerald),
                "PIP",
                "PBP",
                "PIP",
                'I', new ItemStack(Items.EMERALD),
                'B', new ItemStack(binding_Element),
                'P', new ItemStack(time_particle));
        //time_nugget
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_nugget"), null,
                new ItemStack(time_nugget),
                "BDB",
                "GPR",
                "BEB",
                'B', new ItemStack(Items.BLAZE_POWDER),
                'D', new ItemStack(time_particle_diamond),
                'G', new ItemStack(time_particle_gold),
                'P', new ItemStack(time_particle),
                'R', new ItemStack(time_particle_redstone),
                'E', new ItemStack(time_particle_emerald));
        //Time_Stick
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_stick"), null,
                new ItemStack(time_stick),
                " I ",
                " I ",
                " I ",
                'I', new ItemStack(time_ingot));
        //Time_Casing
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_casing"), null,
                new ItemStack(time_casing),
                "SGS",
                "POP",
                "SGS",
                'S', new ItemStack(time_stick),
                'G', new ItemStack(Items.GLOWSTONE_DUST),
                'P', new ItemStack(time_plate),
                'O', new ItemStack(Blocks.OBSIDIAN));
        //Time_Plate
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_plate"), null,
                new ItemStack(time_plate),
                "TFT",
                "FIF",
                "TFT",
                'T', new ItemStack(time_ingot),
                'I', new ItemStack(Items.IRON_INGOT),
                'F', new ItemStack(Items.FIRE_CHARGE));
        //Time_Star
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "time_star"), null,
                new ItemStack(time_star),
                "DSD",
                "TBT",
                "DSD",
                'T', new ItemStack(time_plate),
                'B', new ItemStack(binding_Element),
                'S', new ItemStack(Items.NETHER_STAR),
                'D', new ItemStack(Blocks.DIAMOND_BLOCK));
        //Grow_level_1
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "grow_level_1"), null,
                new ItemStack(Grow_lvl1),
                "DBD",
                "BTB",
                "DBD",
                'D', new ItemStack(Blocks.DIRT),
                'B', new ItemStack(Items.DYE,1,15),
                'T', new ItemStack(time_particle));
        //Grow_level_2
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "grow_level_2"), null,
                new ItemStack(Grow_lvl2),
                " B ",
                "BGB",
                " B ",
                'B', new ItemStack(Items.DYE,1,15),
                'G', new ItemStack(Grow_lvl1));
        //Grow_level_3
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "grow_level_3"), null,
                new ItemStack(Grow_lvl3),
                "DBD",
                "BGB",
                "DBD",
                'D', new ItemStack(Blocks.DIRT),
                'B', new ItemStack(Items.DYE,1,15),
                'G', new ItemStack(Grow_lvl2));
        //Grow_level_4
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "grow_level_4"), null,
                new ItemStack(Grow_lvl4),
                " B ",
                "BGB",
                " B ",
                'B', new ItemStack(Items.DYE,1,15),
                'G', new ItemStack(Grow_lvl3));
        //Grow_level_5
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "grow_level_5"), null,
                new ItemStack(Grow_lvl5),
                "DBD",
                "BGB",
                "DBD",
                'D', new ItemStack(Blocks.DIRT),
                'B', new ItemStack(Items.DYE,1,15),
                'G', new ItemStack(Grow_lvl4));
        //RF_Molecular
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "RF_Molecular"), null,
                new ItemStack(RF_Molecular),
                "BSB",
                "NPN",
                "PCP",
                'B', new ItemStack(binding_Element),
                'S', new ItemStack(Items.NETHER_STAR),
                'C', new ItemStack(Particle_collectors),
                'P', new ItemStack(time_particle_redstone),
                'N', new ItemStack(time_nugget));
        //Time_Energy_Collector
        GameRegistry.addShapedRecipe(
                new ResourceLocation(Tags.MODID + ":" + "Time_Energy_Collector"), null,
                new ItemStack(Time_energy_collectors),
                "YTY",
                "SCS",
                "MRM",
                'T', new ItemStack(time_element),
                'C', new ItemStack(Time_collectors),
                'S', new ItemStack(time_casing),
                'R', new ItemStack(upgrade_count),
                'M', new ItemStack(upgrade_speed),
                'Y', new ItemStack(time_energy_star));
    }
}