package com.ariks.torcherino;

import java.io.File;
import com.ariks.torcherino.Register.RegisterBlackList;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.Tiles.TileCompresedTorch.*;
import com.ariks.torcherino.Tiles.TileTorch.*;
import com.ariks.torcherino.Register.AccelerationRegistry;
import com.ariks.torcherino.util.TorchTab;
import com.ariks.torcherino.util.Config;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import static com.ariks.torcherino.util.Config.*;

@Mod(modid = Torcherino.MOD_ID, name = Torcherino.MOD_NAME, useMetadata = true, acceptedMinecraftVersions = "[1.12]", version = "7.7.4")
public class Torcherino {
	public static File config;
	public static CreativeTabs torcherinoTab = new TorchTab("torcherinoTab");
	public static Logger logger;
	public static final String MOD_ID = "tce", MOD_NAME = "TorcherinoCE";
	@Mod.Instance(Torcherino.MOD_ID)
	public static Torcherino instance;
	@SidedProxy(clientSide = "com.ariks.torcherino.ClientProxy", serverSide = "com.ariks.torcherino.CommonProxy")
	public static CommonProxy proxy;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit();
		Config.registerConfig(event);
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
			ModPacketHandler.init();
			logger.info(TileBase1.class);
			logger.info(TileBase2.class);
			logger.info(TileBase3.class);
			logger.info(TileBase4.class);
			logger.info(TileBase5.class);
			logger.info(CompressedTileBase1.class);
			logger.info(CompressedTileBase2.class);
			logger.info(CompressedTileBase3.class);
			logger.info(CompressedTileBase4.class);
			logger.info(CompressedTileBase5.class);
			RegisterBlackList.preInit();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("projecte")) {
			AccelerationRegistry.blacklistString("projecte:dm_pedestal");
		}
		for (String block : blacklistedBlocks) {
			AccelerationRegistry.blacklistString(block);
		}
		for (String tile : blacklistedTiles) {
			AccelerationRegistry.blacklistString(tile);
		}
	}
	@Mod.EventHandler
	public void imcMessage(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()) {
			if (!message.isStringMessage()) {
				if (Config.DebugMod) {
					logger.info("Received non-string message! Ignoring");
				}
				continue;
			}
			String s = message.getStringValue();
			AccelerationRegistry.blacklistString(s);
		}
	}
}
