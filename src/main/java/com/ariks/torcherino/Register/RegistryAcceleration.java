package com.ariks.torcherino.Register;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import java.util.HashSet;
import java.util.Set;


public final class RegistryAcceleration {
    private static final Set<IBlockState> blacklistedBlockStates = new HashSet<>();
    private static final Set<Class<? extends TileEntity>> blacklistedTiles = new HashSet<>();

    public static void blacklistString(String string) {
        if (!string.contains(":")) {
            try {
                Class<?> clazz = Torcherino.instance.getClass().getClassLoader().loadClass(string);
                if (clazz == null) {
                    if (Config.DebugMod) {
                        Torcherino.logger.info("Class null: " + string);
                    }
                    return;
                } else if (!TileEntity.class.isAssignableFrom(clazz)) {
                    if (Config.DebugMod) {
                        Torcherino.logger.info("Class not a TileEntity: " + string);
                    }
                    return;
                }
                blacklistTile(clazz.asSubclass(TileEntity.class));
            } catch (ClassNotFoundException e) {
                if (Config.DebugMod) {
                    Torcherino.logger.info("Class not found: " + string + ", ignoring");
                }
            }
        } else {
            String[] parts = string.split(":");
            if (parts.length < 2 || parts.length > 3) {
                if (Config.DebugMod) {
                    Torcherino.logger.info("Received malformed message: " + string);
                }
                return;
            }
            ResourceLocation location = new ResourceLocation(parts[0], parts[1]);
            Block block = ForgeRegistries.BLOCKS.getValue(location);
            if (block == null) {
                if (Config.DebugMod) {
                    Torcherino.logger.info("Block not found: " + string);
                }
                return;
            }
            if (Config.DebugMod) {
                Torcherino.logger.info(Torcherino.MOD_NAME + ": " + "Block add black list to acceleration: " + string);
            }
            if (parts.length == 3) {
                try {
                    int meta = Integer.parseInt(parts[2]);
                    IBlockState state = block.getStateFromMeta(meta);
                    blacklistBlockState(state);
                } catch (NumberFormatException e) {
                    if (Config.DebugMod) {
                        Torcherino.logger.info("Invalid metadata: " + parts[2]);
                    }
                }
            } else {
                blacklistBlock(block);
            }
        }
    }
    public static void blacklistBlock(Block block) {
        blacklistedBlockStates.addAll(block.getBlockState().getValidStates());
    }
    public static void blacklistBlockState(IBlockState state) {
        blacklistedBlockStates.add(state);
    }
    public static void blacklistTile(Class<? extends TileEntity> tile) {
        blacklistedTiles.add(tile);
    }
    public static boolean isBlockBlacklisted(IBlockState state) {
        return blacklistedBlockStates.contains(state);
    }
    public static boolean isTileBlacklisted(Class<? extends TileEntity> tile) {
        return blacklistedTiles.contains(tile);
    }
}