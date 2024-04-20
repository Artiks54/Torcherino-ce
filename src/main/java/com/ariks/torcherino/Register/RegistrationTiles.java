package com.ariks.torcherino.Register;

import com.ariks.torcherino.Tiles.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.ariks.torcherino.Torcherino.MOD_ID;
public final class RegistrationTiles{
    public static void preInit()
    {
        GameRegistry.registerTileEntity(TileTorch.TileBase1.class, new ResourceLocation(MOD_ID,"Tile_torch_lvl1"));
        GameRegistry.registerTileEntity(TileTorch.TileBase2.class, new ResourceLocation(MOD_ID,"Tile_torch_lvl2"));
        GameRegistry.registerTileEntity(TileTorch.TileBase3.class,new ResourceLocation(MOD_ID,"Tile_torch_lvl3"));
        GameRegistry.registerTileEntity(TileTorch.TileBase4.class,new ResourceLocation(MOD_ID,"Tile_torch_lvl4"));
        GameRegistry.registerTileEntity(TileTorch.TileBase5.class,new ResourceLocation(MOD_ID,"Tile_torch_lvl5"));
        GameRegistry.registerTileEntity(TileCompresedTorch.CompressedTileBase1.class, new ResourceLocation(MOD_ID,"Tile_Compressed_lvl1"));
        GameRegistry.registerTileEntity(TileCompresedTorch.CompressedTileBase2.class, new ResourceLocation(MOD_ID,"Tile_Compressed_lvl2"));
        GameRegistry.registerTileEntity(TileCompresedTorch.CompressedTileBase3.class,new ResourceLocation(MOD_ID,"Tile_Compressed_lvl3"));
        GameRegistry.registerTileEntity(TileCompresedTorch.CompressedTileBase4.class,new ResourceLocation(MOD_ID,"Tile_Compressed_lvl4"));
        GameRegistry.registerTileEntity(TileCompresedTorch.CompressedTileBase5.class,new ResourceLocation(MOD_ID,"Tile_Compressed_lvl5"));
        GameRegistry.registerTileEntity(TileDCompresedTorch.DCompressedTileBase1.class, new ResourceLocation(MOD_ID,"Tile_D_Compressed_lvl1"));
        GameRegistry.registerTileEntity(TileDCompresedTorch.DCompressedTileBase2.class, new ResourceLocation(MOD_ID,"Tile_D_Compressed_lvl2"));
        GameRegistry.registerTileEntity(TileDCompresedTorch.DCompressedTileBase3.class,new ResourceLocation(MOD_ID,"Tile_D_Compressed_lvl3"));
        GameRegistry.registerTileEntity(TileDCompresedTorch.DCompressedTileBase4.class,new ResourceLocation(MOD_ID,"Tile_D_Compressed_lvl4"));
        GameRegistry.registerTileEntity(TileDCompresedTorch.DCompressedTileBase5.class,new ResourceLocation(MOD_ID,"Tile_D_Compressed_lvl5"));
        GameRegistry.registerTileEntity(TileAcceleration.class,new ResourceLocation(MOD_ID,"Tile_Acceleration"));
        GameRegistry.registerTileEntity(TileTimeStorage.class,new ResourceLocation(MOD_ID,"Tile_Time_Storage"));
        GameRegistry.registerTileEntity(TileCollectors.class,new ResourceLocation(MOD_ID,"Tile_Collectors"));
    }
}
