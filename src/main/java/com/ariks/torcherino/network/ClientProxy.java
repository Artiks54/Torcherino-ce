package com.ariks.torcherino.network;

import com.ariks.torcherino.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherino.Block.RfMolecular.TileRfMolecularRenderer;
import com.ariks.torcherino.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.Time.Aceleration.TileAccelerationBaseRender;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBaseRender;
import com.ariks.torcherino.Register.RegistryBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit() {
		super.preInit();
		ClientRegistry.bindTileEntitySpecialRenderer(TileTorcherinoBase.class, new TileTorcherinoBaseRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileAcceleration.class, new TileAccelerationBaseRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRfMolecular.class, new TileRfMolecularRenderer());
	}
	@Override
	public void Init() {
		super.Init();
		RegistryBlock.registerRender();
	}
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void renderItemOnScreen(ItemStack current, int x, int y) {
		if (current == null) {
			return;
		}
		RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
		GlStateManager.color(1, 1, 1, 1);
		RenderHelper.enableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		itemRender.renderItemAndEffectIntoGUI(current, x, y);
		RenderHelper.disableStandardItemLighting();
	}
}