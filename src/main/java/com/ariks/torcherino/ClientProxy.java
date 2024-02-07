package com.ariks.torcherino;

import com.ariks.torcherino.GUI.GuiTorcherino;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import java.util.Objects;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
	}
	@Override
	public boolean openGui(World world, BlockPos pos, EntityPlayer player) {
		if (!world.isRemote) {
			return true;
		}
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileTorcherinoBase) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiTorcherino((TileTorcherinoBase) tile));
			return true;
		}
		return false;
	}
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation((Objects.requireNonNull(item.getRegistryName())),id));}
}
