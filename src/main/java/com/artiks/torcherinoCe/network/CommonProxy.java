package com.artiks.torcherinoCe.network;

import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.MolecularRecipeFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularRecipe;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.RegistryBlackList;
import com.artiks.torcherinoCe.Register.*;
import com.artiks.torcherinoCe.integration.ProjectE.ProjectE;
import com.artiks.torcherinoCe.integration.TheOneProbeTopAddons.TheOneProbe;
import com.artiks.torcherinoCe.torcherinoCe;
import com.artiks.torcherinoCe.utility.JoinDiscord;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit() {
		//Block
		RegistryBlock.preInit();
		RegistryBlackList.preInit();
	}

	public void Init(){
		//Tile
		RegistryTiles.Init();
		//Gui
		NetworkRegistry.INSTANCE.registerGuiHandler(torcherinoCe.instance, new RegistryGui());
		//Network
		RegistryNetwork.Init();
	}

	public void postInit(){
		//Discord
		MinecraftForge.EVENT_BUS.register(new JoinDiscord());
		//Recipes
		RegistryRecipe.postInit();
		MolecularRecipe.postInit();
		MolecularRecipeFarm.postInit();
		//Integration
		ProjectE.postInit();
		TheOneProbe.postInit();
		//MolecularFarmReciepIntegration.postInit();
	}

	public void registerItemRenderer(Item item, int meta, String id) {}
	public void renderItemOnScreen(ItemStack current, int x, int y) {}
}