package com.ariks.torcherino.Register;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.network.AccelerationRegistry;
import net.minecraft.init.Blocks;
import static com.ariks.torcherino.Tiles.TileTorch.*;

public class RegisterBlackList {
    public static void preInit() {
        //Main Block
        AccelerationRegistry.blacklistBlock(Blocks.WATER);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_WATER);
        AccelerationRegistry.blacklistBlock(Blocks.LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.FLOWING_LAVA);
        AccelerationRegistry.blacklistBlock(Blocks.SAND);
        AccelerationRegistry.blacklistBlock(Blocks.COMMAND_BLOCK);
        AccelerationRegistry.blacklistBlock(Blocks.GRAVEL);
        AccelerationRegistry.blacklistBlock(Blocks.GRASS_PATH);
        AccelerationRegistry.blacklistBlock(Blocks.GRASS);
        AccelerationRegistry.blacklistBlock(Blocks.DIRT);
        //Block Mods
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_1);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_2);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_3);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_4);
        AccelerationRegistry.blacklistBlock(RegistryArray.Torch_lvl_5);

        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl1);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl2);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl3);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl4);
        AccelerationRegistry.blacklistBlock(RegistryArray.Compressed_Torch_lvl5);

        //Tile Mode
        AccelerationRegistry.blacklistTile(TileBase1.class);
        AccelerationRegistry.blacklistTile(TileBase2.class);
        AccelerationRegistry.blacklistTile(TileBase3.class);
        AccelerationRegistry.blacklistTile(TileBase4.class);
        AccelerationRegistry.blacklistTile(TileBase5.class);

        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase1.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase2.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase3.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase4.class);
        AccelerationRegistry.blacklistTile(TileCompresedTorch.CompressedTileBase5.class);

    }
}
