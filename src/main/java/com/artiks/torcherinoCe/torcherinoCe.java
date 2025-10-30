package com.artiks.torcherinoCe;

import java.io.File;
import com.artiks.torcherinoCe.utility.Config;
import com.artiks.torcherinoCe.utility.TorchTab;
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
	//1.12.2
	public static File config;
	public static CreativeTabs torcherinoTab = new TorchTab("torcherinoTab");
	public static Logger logger;
	@Mod.Instance
	public static torcherinoCe instance;
	@SidedProxy(clientSide = "com.artiks.torcherinoCe.network.ClientProxy", serverSide = "com.artiks.torcherinoCe.network.CommonProxy")
	public static com.artiks.torcherinoCe.network.CommonProxy proxy;
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