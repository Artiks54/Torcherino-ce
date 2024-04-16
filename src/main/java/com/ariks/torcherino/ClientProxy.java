package com.ariks.torcherino;

import com.ariks.torcherino.GUI.GuiCollector;
import com.ariks.torcherino.GUI.GuiTorcherino;
import com.ariks.torcherino.Render.TileTorcherinoBaseRender;
import com.ariks.torcherino.Tiles.TileCollector;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import java.util.Objects;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
		ClientRegistry.bindTileEntitySpecialRenderer(TileTorcherinoBase.class, new TileTorcherinoBaseRender());
	}
	@Override
	public boolean openGui(World world, BlockPos pos, EntityPlayer player) {
		if (!world.isRemote) {
			return true;
		}
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileTorcherinoBase) {
			GuiTorcherino gui = new GuiTorcherino((TileTorcherinoBase) tile,player);
			Minecraft.getMinecraft().displayGuiScreen(gui);
			return true;
		}
		if (tile instanceof TileCollector) {
			GuiCollector gui = new GuiCollector((TileCollector) tile,player);
			Minecraft.getMinecraft().displayGuiScreen(gui);
			return true;
		}
		return false;
	}
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
	}
}