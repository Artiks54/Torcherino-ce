package com.ariks.torcherinoCe.Register;

import com.ariks.torcherinoCe.Block.MarkerAcceleration.TileMarker;
import com.ariks.torcherinoCe.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator.TileEnergyTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeCollector.TileCollectors;
import com.ariks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector.TileEnergyCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeStorage.TileTimeStorage;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherinoCe.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RegistryTiles {

    public static void preInit()
    {
        GameRegistry.registerTileEntity(TileEnergyCollectors.class,new ResourceLocation(Tags.MODID,"Tile_Energy_Collectors"));
        GameRegistry.registerTileEntity(TileRfMolecular.class,new ResourceLocation(Tags.MODID,"Tile_Rf_Molecular"));
        GameRegistry.registerTileEntity(TileEnergyParticle.class,new ResourceLocation(Tags.MODID,"Tile_Particle_Energy"));
        GameRegistry.registerTileEntity(TileTorcherinoBase.class,new ResourceLocation(Tags.MODID,"Tile_Torcherino"));
        GameRegistry.registerTileEntity(TileAcceleration.class,new ResourceLocation(Tags.MODID,"Tile_Acceleration"));
        GameRegistry.registerTileEntity(TileTimeStorage.class,new ResourceLocation(Tags.MODID,"Tile_Time_Storage"));
        GameRegistry.registerTileEntity(TileCollectors.class,new ResourceLocation(Tags.MODID,"Tile_Collectors"));
        GameRegistry.registerTileEntity(TileParticleCollector.class,new ResourceLocation(Tags.MODID,"Tile_Particle_Collector"));
        GameRegistry.registerTileEntity(TileTimeManipulator.class,new ResourceLocation(Tags.MODID,"Tile_Time_Manipulator"));
        GameRegistry.registerTileEntity(TileEnergyTimeManipulator.class,new ResourceLocation(Tags.MODID,"Tile_Energy_Time_Manipulator"));
        GameRegistry.registerTileEntity(TileMarker.class,new ResourceLocation(Tags.MODID,"Tile_Marker"));
    }
}