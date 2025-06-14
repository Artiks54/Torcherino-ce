package com.ariks.torcherinoCe.network;

import com.ariks.torcherinoCe.Block.RfMolecular.MolecularRecipe;
import com.ariks.torcherinoCe.Block.Torcherino.RegistryBlackList;
import com.ariks.torcherinoCe.Register.*;
import com.ariks.torcherinoCe.integration.RegistryIntegration;
import com.ariks.torcherinoCe.torcherinoCe;
import com.ariks.torcherinoCe.utility.JoinDiscord;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import static com.ariks.torcherinoCe.utility.Config.*;

public class CommonProxy {
	public void preInit() {
		RegistryBlock.preInit();
		RegistryTiles.preInit();
		RegistryRecipe.preInit();
		RegistryBlackList.preInit();
		MolecularRecipe.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(torcherinoCe.instance, new RegistryGui());
	}
	public void Init(){
		RegistryNetwork.preInit();
		if(BooleanHelloMsg) {
			MinecraftForge.EVENT_BUS.register(new JoinDiscord());
		}
	}
	public void postInit(){
		RegistryIntegration.Registry();
	}
	public void registerItemRenderer(Item item, int meta, String id) {
	}
	public void renderItemOnScreen(ItemStack current, int x, int y) {}
}