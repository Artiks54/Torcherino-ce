package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherino.Block.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.torcherino.Torcherino.MOD_ID;

public final class RegistryTiles {
    public static void preInit()
    {
        GameRegistry.registerTileEntity(TileEnergyParticle.class,new ResourceLocation(MOD_ID,"Tile_Particle_Energy"));
        GameRegistry.registerTileEntity(TileTorcherinoBase.class,new ResourceLocation(MOD_ID,"Tile_Torcherino"));
        GameRegistry.registerTileEntity(TileAcceleration.class,new ResourceLocation(MOD_ID,"Tile_Acceleration"));
        GameRegistry.registerTileEntity(TileTimeStorage.class,new ResourceLocation(MOD_ID,"Tile_Time_Storage"));
        GameRegistry.registerTileEntity(TileCollectors.class,new ResourceLocation(MOD_ID,"Tile_Collectors"));
        GameRegistry.registerTileEntity(TileParticleCollector.class,new ResourceLocation(MOD_ID,"Tile_Particle_Collector"));
        GameRegistry.registerTileEntity(TileTimeManipulator.class,new ResourceLocation(MOD_ID,"Tile_Time_Manipulator"));
    }
}