package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherino.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.Time.TimeCollector.TileCollectors;
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
        RegistryAcceleration.blacklistBlock(Blocks.AIR);
        RegistryAcceleration.blacklistBlock(Blocks.WATER);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_WATER);
        RegistryAcceleration.blacklistBlock(Blocks.LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.FURNACE);
        //All mod blocks
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
        for (Block block : RegistryBlock.BLOCKS) {
            RegistryAcceleration.blacklistBlock(block);
            if (Config.DebugMod) {
                Torcherino.logger.info(Torcherino.MOD_NAME + ": " + "Block add black list to acceleration: " + block.getUnlocalizedName());
            }
        }
    }
}