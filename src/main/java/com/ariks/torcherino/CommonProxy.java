package com.ariks.torcherino;
import com.ariks.torcherino.Register.RegistrationTiles;
import com.ariks.torcherino.Register.ReciepRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommonProxy {
	public void preInit() {
		RegistrationTiles.preInit();
		ReciepRegister.preInit();
	}
	public boolean openGui(World world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	public void registerItemRenderer(Item item, int meta, String id){}
}
