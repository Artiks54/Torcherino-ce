package com.ariks.torcherino.network;

import com.ariks.torcherino.Register.*;
import com.ariks.torcherino.Register.RegistryBlock;
import com.ariks.torcherino.Torcherino;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import static com.ariks.torcherino.util.Config.*;

public class CommonProxy {
	public void preInit() {
		RegistryBlock.preInit();
		RegistryTiles.preInit();
		RegistryReciep.preInit();
		RegistryBlackList.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(Torcherino.instance, new RegistryGui());
	}
	public void Init(){
		RegistryNetwork.init();
	}
	public void postInit(){
		RegistryIntegration.Registry();
		for (String block : blacklistedBlocks) {
			RegistryAcceleration.blacklistString(block);
		}
		for (String tile : blacklistedTiles) {
			RegistryAcceleration.blacklistString(tile);
		}
	}
	public void registerItemRenderer(Item item, int meta, String id) {
	}
}