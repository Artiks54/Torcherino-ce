package com.ariks.torcherino.network;
import java.util.HashSet;
import java.util.Set;
import com.ariks.torcherino.Torcherino;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class AccelerationRegistry
{
	public static void blacklistString(String string)
	{
		if (string.indexOf(':') == -1) {
			try
			{
		        Class<?> clazz = Torcherino.instance.getClass().getClassLoader().loadClass(string);
		        if(clazz == null) {
		        	Torcherino.logger.info("Class null: " + string);
		            return;
		        }
		        else if(!TileEntity.class.isAssignableFrom(clazz)) {
		        	Torcherino.logger.info("Class not a TileEntity: " + string);
		            return;
		        }
		        blacklistTile((Class<? extends TileEntity>) clazz);
		    }
			catch(ClassNotFoundException e) {
				Torcherino.logger.info("Class not found: " + string + ", ignoring");
		    }
		} else {
			String[] parts = string.split(":");
		    if(parts.length != 2) {
		    	Torcherino.logger.info("Received malformed message: " + string);
		    	return;
			}
		    Block block = Block.REGISTRY.getObject(new ResourceLocation(parts[0], parts[1]));
		    if(block == null) {
		    	Torcherino.logger.info("Could not find block: " + string + ", ignoring");
		        return;
		    }
		    Torcherino.logger.info("Blacklisting block: " + block.getUnlocalizedName());
		    blacklistBlock(block);
		}
	}
    public static void blacklistBlock(Block block)
    {
        AccelerationRegistry.blacklistedBlocks.add(block);
    }
    public static void blacklistTile(Class<? extends TileEntity> tile) {AccelerationRegistry.blacklistedTiles.add(tile);}
    public static boolean isBlockBlacklisted(Block block) {return AccelerationRegistry.blacklistedBlocks.contains(block);}
    public static boolean isTileBlacklisted(Class<? extends TileEntity> tile) {return AccelerationRegistry.blacklistedTiles.contains(tile);}
    private static Set<Block> blacklistedBlocks = new HashSet<Block>();
    private static Set<Class<? extends TileEntity>> blacklistedTiles = new HashSet<Class<? extends TileEntity>>();
}
