package com.ariks.torcherino.Register;
import com.ariks.torcherino.Tiles.*;
import net.minecraft.init.Blocks;
import static com.ariks.torcherino.Tiles.TileTorch.*;

public class RegisterBlackList {
    public static void preInit() {
        //Minecraft block
        AccelerationRegistry.blacklistBlock(Blocks.COMMAND_BLOCK);
        AccelerationRegistry.blacklistBlock(Blocks.WATER);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_WATER);
        AccelerationRegistry.blacklistBlock(Blocks.LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.REDSTONE_ORE);
        AccelerationRegistry.blacklistBlock(Blocks.GRASS);
        AccelerationRegistry.blacklistBlock(Blocks.GRASS_PATH);
        AccelerationRegistry.blacklistBlock(Blocks.DIRT);
        AccelerationRegistry.blacklistBlock(Blocks.DROPPER);
        AccelerationRegistry.blacklistBlock(Blocks.DISPENSER);
        AccelerationRegistry.blacklistBlock(Blocks.OBSERVER);
        AccelerationRegistry.blacklistBlock(Blocks.DAYLIGHT_DETECTOR);
        AccelerationRegistry.blacklistBlock(Blocks.DAYLIGHT_DETECTOR_INVERTED);
        AccelerationRegistry.blacklistBlock(Blocks.HOPPER);
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
        AccelerationRegistry.blacklistBlock(Blocks.JUKEBOX);
        AccelerationRegistry.blacklistBlock(Blocks.ENCHANTING_TABLE);
        //Torch
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_1);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_2);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_3);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_4);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_5);
        //C-Torch
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl1);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl2);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl3);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl4);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl5);
        //D-Torch
        AccelerationRegistry.blacklistBlock(RegistryArray.D_Compressed_Torch_lvl1);
        AccelerationRegistry.blacklistBlock(RegistryArray.D_Compressed_Torch_lvl2);
        AccelerationRegistry.blacklistBlock(RegistryArray.D_Compressed_Torch_lvl3);
        AccelerationRegistry.blacklistBlock(RegistryArray.D_Compressed_Torch_lvl4);
        AccelerationRegistry.blacklistBlock(RegistryArray.D_Compressed_Torch_lvl5);
        //Tile-Torch
        AccelerationRegistry.blacklistTile(TileBase1.class);
        AccelerationRegistry.blacklistTile(TileBase2.class);
        AccelerationRegistry.blacklistTile(TileBase3.class);
        AccelerationRegistry.blacklistTile(TileBase4.class);
        AccelerationRegistry.blacklistTile(TileBase5.class);
        //Tile-C-Torch
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase1.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase2.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase3.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase4.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase5.class);
        //Tile-D-Torch
        AccelerationRegistry.blacklistTile(TileDCompresedTorch.DCompressedTileBase1.class);
        AccelerationRegistry.blacklistTile(TileDCompresedTorch.DCompressedTileBase2.class);
        AccelerationRegistry.blacklistTile(TileDCompresedTorch.DCompressedTileBase3.class);
        AccelerationRegistry.blacklistTile(TileDCompresedTorch.DCompressedTileBase4.class);
        AccelerationRegistry.blacklistTile(TileDCompresedTorch.DCompressedTileBase5.class);
        //Tile-Acceleration
        AccelerationRegistry.blacklistTile(TileAcceleration.class);
        AccelerationRegistry.blacklistBlock(RegistryArray.Time_Acceleration);
        //Tile-Collectors
        AccelerationRegistry.blacklistTile(TileCollectors.class);
        AccelerationRegistry.blacklistBlock(RegistryArray.Time_collectors);
        //Tile-Storage
        AccelerationRegistry.blacklistTile(TileTimeStorage.class);
        AccelerationRegistry.blacklistBlock(RegistryArray.Time_Storage);
    }
}
