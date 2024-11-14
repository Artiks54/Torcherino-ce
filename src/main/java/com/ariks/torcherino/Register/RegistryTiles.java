package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherino.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherino.Block.Time.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.Time.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.torcherino.Torcherino.MOD_ID;

public final class RegistryTiles {
    public static void preInit()
    {
        GameRegistry.registerTileEntity(TileRfMolecular.class,new ResourceLocation(MOD_ID,"Tile_Rf_Molecular"));
        GameRegistry.registerTileEntity(TileEnergyParticle.class,new ResourceLocation(MOD_ID,"Tile_Particle_Energy"));
        GameRegistry.registerTileEntity(TileTorcherinoBase.class,new ResourceLocation(MOD_ID,"Tile_Torcherino"));
        GameRegistry.registerTileEntity(TileAcceleration.class,new ResourceLocation(MOD_ID,"Tile_Acceleration"));
        GameRegistry.registerTileEntity(TileTimeStorage.class,new ResourceLocation(MOD_ID,"Tile_Time_Storage"));
        GameRegistry.registerTileEntity(TileCollectors.class,new ResourceLocation(MOD_ID,"Tile_Collectors"));
        GameRegistry.registerTileEntity(TileParticleCollector.class,new ResourceLocation(MOD_ID,"Tile_Particle_Collector"));
        GameRegistry.registerTileEntity(TileTimeManipulator.class,new ResourceLocation(MOD_ID,"Tile_Time_Manipulator"));
    }
}