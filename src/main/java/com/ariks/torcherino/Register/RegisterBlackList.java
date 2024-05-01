package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class RegisterBlackList {
    public static void preInit() {
        //Minecraft block
        AccelerationRegistry.blacklistBlock(Blocks.COMMAND_BLOCK);
        AccelerationRegistry.blacklistBlock(Blocks.WATER);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_WATER);
        AccelerationRegistry.blacklistBlock(Blocks.LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.REDSTONE_ORE);
        AccelerationRegistry.blacklistBlock(Blocks.DAYLIGHT_DETECTOR);
        AccelerationRegistry.blacklistBlock(Blocks.DAYLIGHT_DETECTOR_INVERTED);
        AccelerationRegistry.blacklistBlock(Blocks.PISTON);
        AccelerationRegistry.blacklistBlock(Blocks.PISTON_EXTENSION);
        AccelerationRegistry.blacklistBlock(Blocks.PISTON_HEAD);
        AccelerationRegistry.blacklistBlock(Blocks.STICKY_PISTON);
        AccelerationRegistry.blacklistBlock(Blocks.REDSTONE_LAMP);
        AccelerationRegistry.blacklistBlock(Blocks.REPEATING_COMMAND_BLOCK);
        AccelerationRegistry.blacklistBlock(Blocks.POWERED_REPEATER);
        AccelerationRegistry.blacklistBlock(Blocks.UNPOWERED_REPEATER);
        AccelerationRegistry.blacklistBlock(Blocks.POWERED_COMPARATOR);
        AccelerationRegistry.blacklistBlock(Blocks.UNPOWERED_COMPARATOR);
        //All block
        for (Block block : RegistryArray.BLOCKS) {
            AccelerationRegistry.blacklistBlock(block);
            if (Config.DebugMod) {
                Torcherino.logger.info(Torcherino.MOD_NAME + ": " + "Block add black list to aceleration: " + block);
            }
        }
        //Tile-Torcherino
        AccelerationRegistry.blacklistTile(TileTorcherinoBase.class);
        //Tile-Acceleration
        AccelerationRegistry.blacklistTile(TileAcceleration.class);
        //Tile-Collectors
        AccelerationRegistry.blacklistTile(TileCollectors.class);
        //Tile-Storage
        AccelerationRegistry.blacklistTile(TileTimeStorage.class);
        //Tile-Particle-Collector
        AccelerationRegistry.blacklistTile(TileParticleCollector.class);
        //Tile-Time-Manipulator
        AccelerationRegistry.blacklistTile(TileTimeManipulator.class);
    }
}