package com.ariks.torcherino;

import java.io.File;
import com.ariks.torcherino.Register.RegistryIntegration;
import com.ariks.torcherino.network.CommonProxy;
import com.ariks.torcherino.Register.RegisterBlackList;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.Register.AccelerationRegistry;
import com.ariks.torcherino.util.TorchTab;
import com.ariks.torcherino.util.Config;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import static com.ariks.torcherino.util.Config.*;

@Mod(modid = Torcherino.MOD_ID, name = Torcherino.MOD_NAME, useMetadata = true, acceptedMinecraftVersions = "[1.12]", version = "7.7.6")
public class Torcherino {
	public static File config;
	public static CreativeTabs torcherinoTab = new TorchTab("torcherinoTab");
	public static Logger logger;
	public static final String MOD_ID = "tce", MOD_NAME = "TorcherinoCE";
	@Mod.Instance(Torcherino.MOD_ID)
	public static Torcherino instance;
	@SidedProxy(clientSide = "com.ariks.torcherino.network.ClientProxy", serverSide = "com.ariks.torcherino.network.CommonProxy")
	public static CommonProxy proxy;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.registerConfig(event);
		logger = event.getModLog();
		proxy.preInit();
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
			ModPacketHandler.init();
			RegisterBlackList.preInit();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RegistryIntegration.Registry();
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