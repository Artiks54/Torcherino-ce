package com.ariks.torcherino;
import com.ariks.torcherino.Register.RegistrationTiles;
import com.ariks.torcherino.Register.ReciepRegister;
import net.minecraft.item.Item;

public class CommonProxy {

	public void preInit() {
		RegistrationTiles.preInit();
		ReciepRegister.preInit();
	}
	public void registerItemRenderer(Item item, int meta, String id){}
}
