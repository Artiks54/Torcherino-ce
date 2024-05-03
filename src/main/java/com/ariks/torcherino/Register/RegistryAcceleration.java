package com.ariks.torcherino.Register;

import java.util.HashSet;
import java.util.Set;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RegistryAcceleration {
	private static final Set<Block> blacklistedBlocks = new HashSet<>();
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
			if (parts.length != 2) {
				if (Config.DebugMod) {
					Torcherino.logger.info("Received malformed message: " + string);
				}
				return;
			}
			ResourceLocation location = new ResourceLocation(parts[0], parts[1]);
			Block block = Block.REGISTRY.getObject(location);
			if (Config.DebugMod) {
				Torcherino.logger.info("Blacklisting block: " + block.getUnlocalizedName());
			}
			blacklistBlock(block);
		}
	}
	public static void blacklistBlock(Block block) {
		blacklistedBlocks.add(block);
	}
	public static void blacklistTile(Class<? extends TileEntity> tile) {
		blacklistedTiles.add(tile);
	}
	public static boolean isBlockBlacklisted(Block block) {
		return blacklistedBlocks.contains(block);
	}
	public static boolean isTileBlacklisted(Class<? extends TileEntity> tile) {
		return blacklistedTiles.contains(tile);
	}
}