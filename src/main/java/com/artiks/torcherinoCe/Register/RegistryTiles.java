package com.artiks.torcherinoCe.Register;

import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.TileCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.TileLuck;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.TileRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.TileRfMolecularLegendary;
import com.artiks.torcherinoCe.Block.ItemStorage.TileItemStorage;
import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.TileMarker;
import com.artiks.torcherinoCe.Block.Energy.Molecular.TileRfMolecular;
import com.artiks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.TileEnergyParticle;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.TileEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeCollector.TileCollectors;
import com.artiks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector.TileEnergyCollectors;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.TileTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeStorage.TileTimeStorage;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TileTorcherinoBase;
import com.artiks.torcherinoCe.Tags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RegistryTiles {

    public static void Init()
    {
        GameRegistry.registerTileEntity(TileEnergyCollectors.class,new ResourceLocation(Tags.MODID,"Tile_Energy_Collectors"));
        GameRegistry.registerTileEntity(TileRfMolecular.class,new ResourceLocation(Tags.MODID,"Tile_Rf_Molecular"));
        GameRegistry.registerTileEntity(TileRfMolecularLegendary.class,new ResourceLocation(Tags.MODID,"Tile_Rf_Molecular_Legendary"));
        GameRegistry.registerTileEntity(TileRfMolecularFarm.class,new ResourceLocation(Tags.MODID,"Tile_Rf_Molecular_Farm"));
        GameRegistry.registerTileEntity(TileEnergyParticle.class,new ResourceLocation(Tags.MODID,"Tile_Particle_Energy"));
        GameRegistry.registerTileEntity(TileTorcherinoBase.class,new ResourceLocation(Tags.MODID,"Tile_Torcherino"));
        GameRegistry.registerTileEntity(TileAcceleration.class,new ResourceLocation(Tags.MODID,"Tile_Acceleration"));
        GameRegistry.registerTileEntity(TileTimeStorage.class,new ResourceLocation(Tags.MODID,"Tile_Time_Storage"));
        GameRegistry.registerTileEntity(TileCollectors.class,new ResourceLocation(Tags.MODID,"Tile_Collectors"));
        GameRegistry.registerTileEntity(TileParticleCollector.class,new ResourceLocation(Tags.MODID,"Tile_Particle_Collector"));
        GameRegistry.registerTileEntity(TileTimeManipulator.class,new ResourceLocation(Tags.MODID,"Tile_Time_Manipulator"));
        GameRegistry.registerTileEntity(TileEnergyTimeManipulator.class,new ResourceLocation(Tags.MODID,"Tile_Energy_Time_Manipulator"));
        GameRegistry.registerTileEntity(TileMarker.class,new ResourceLocation(Tags.MODID,"Tile_Marker"));
        GameRegistry.registerTileEntity(TileLuck.class,new ResourceLocation(Tags.MODID,"Tile_Luck"));
        GameRegistry.registerTileEntity(TileItemStorage.class,new ResourceLocation(Tags.MODID,"Tile_item_storage"));
        GameRegistry.registerTileEntity(TileCobbleGenerator.class,new ResourceLocation(Tags.MODID,"Tile_Cobble_Generator"));
    }
}