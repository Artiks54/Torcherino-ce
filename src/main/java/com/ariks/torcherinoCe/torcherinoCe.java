package com.ariks.torcherinoCe;

import java.io.File;
import com.ariks.torcherinoCe.network.CommonProxy;
import com.ariks.torcherinoCe.utility.TorchTab;
import com.ariks.torcherinoCe.utility.Config;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tags.MODID, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12]", version = Tags.VERSION)
public class torcherinoCe {
	public static File config;
	public static CreativeTabs torcherinoTab = new TorchTab("torcherinoTab");
	public static Logger logger;
	@Mod.Instance
	public static torcherinoCe instance;
	@SidedProxy(clientSide = "com.ariks.torcherinoCe.network.ClientProxy", serverSide = "com.ariks.torcherinoCe.network.CommonProxy")
	public static CommonProxy proxy;
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.registerConfig(event);
		logger = event.getModLog();
		proxy.preInit();
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.Init();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	@Mod.EventHandler
	public void imcMessage(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()) {
			if (!message.isStringMessage()) {
				if (Config.DebugMod) {
					logger.info("Received non-string message! Ignoring");
				}
			}
		}
	}
}