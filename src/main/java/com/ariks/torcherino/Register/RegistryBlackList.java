package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherino.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherino.Block.Time.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.Time.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class RegistryBlackList {
    public static void preInit() {
        //Minecraft block
        RegistryAcceleration.blacklistBlock(Blocks.COMMAND_BLOCK);
        RegistryAcceleration.blacklistBlock(Blocks.WATER);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_WATER);
        RegistryAcceleration.blacklistBlock(Blocks.LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.REDSTONE_ORE);
        RegistryAcceleration.blacklistBlock(Blocks.DAYLIGHT_DETECTOR);
        RegistryAcceleration.blacklistBlock(Blocks.DAYLIGHT_DETECTOR_INVERTED);
        RegistryAcceleration.blacklistBlock(Blocks.PISTON_EXTENSION);
        RegistryAcceleration.blacklistBlock(Blocks.PISTON_HEAD);
        RegistryAcceleration.blacklistBlock(Blocks.STICKY_PISTON);
        RegistryAcceleration.blacklistBlock(Blocks.REDSTONE_LAMP);
        RegistryAcceleration.blacklistBlock(Blocks.REPEATING_COMMAND_BLOCK);
        RegistryAcceleration.blacklistBlock(RegistryBlock.Grow_lvl1);
        RegistryAcceleration.blacklistBlock(RegistryBlock.Grow_lvl2);
        RegistryAcceleration.blacklistBlock(RegistryBlock.Grow_lvl3);
        RegistryAcceleration.blacklistBlock(RegistryBlock.Grow_lvl4);
        RegistryAcceleration.blacklistBlock(RegistryBlock.Grow_lvl5);
        RegistryAcceleration.blacklistBlock(Blocks.POWERED_REPEATER);
        RegistryAcceleration.blacklistBlock(Blocks.UNPOWERED_REPEATER);
        RegistryAcceleration.blacklistBlock(Blocks.POWERED_COMPARATOR);
        RegistryAcceleration.blacklistBlock(Blocks.UNPOWERED_COMPARATOR);
        //All mod block
        for (Block block : RegistryBlock.BLOCKS) {
            RegistryAcceleration.blacklistBlock(block);
            if (Config.DebugMod) {
                Torcherino.logger.info(Torcherino.MOD_NAME + ": " + "Block add black list to acceleration: " + block.getUnlocalizedName());
            }
        }
        //Tile-RF-Molecular
        RegistryAcceleration.blacklistTile(TileRfMolecular.class);
        //Tile-Torcherino
        RegistryAcceleration.blacklistTile(TileTorcherinoBase.class);
        //Tile-Acceleration
        RegistryAcceleration.blacklistTile(TileAcceleration.class);
        //Tile-Collectors
        RegistryAcceleration.blacklistTile(TileCollectors.class);
        //Tile-Storage
        RegistryAcceleration.blacklistTile(TileTimeStorage.class);
        //Tile-Particle-Collector
        RegistryAcceleration.blacklistTile(TileParticleCollector.class);
        //Tile-Time-Manipulator
        RegistryAcceleration.blacklistTile(TileTimeManipulator.class);
        //Tile-Energy-Particle
        RegistryAcceleration.blacklistTile(TileEnergyParticle.class);
    }
}