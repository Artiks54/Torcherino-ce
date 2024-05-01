package com.ariks.torcherino.network;

import com.ariks.torcherino.Register.GuiHandler;
import com.ariks.torcherino.Register.RegistrationTiles;
import com.ariks.torcherino.Register.ReciepRegister;
import com.ariks.torcherino.Torcherino;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit() {
		RegistrationTiles.preInit();
		ReciepRegister.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(Torcherino.instance, new GuiHandler());
	}
	public void registerItemRenderer(Item item, int meta, String id) {
	}
}