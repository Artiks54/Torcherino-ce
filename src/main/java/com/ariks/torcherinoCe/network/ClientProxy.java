package com.ariks.torcherinoCe.network;

import com.ariks.torcherinoCe.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherinoCe.Block.RfMolecular.TileRfMolecularRenderer;
import com.ariks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherinoCe.Block.Time.Aceleration.TileAccelerationBaseRender;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBaseRender;
import com.ariks.torcherinoCe.Items.RenderHandler;
import com.ariks.torcherinoCe.Register.RegistryBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.Objects;

@SideOnly(Side.CLIENT)
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
		MinecraftForge.EVENT_BUS.register(new RenderHandler());
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