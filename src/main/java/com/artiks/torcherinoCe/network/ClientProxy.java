package com.artiks.torcherinoCe.network;

import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.TileCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.TileCobbleGeneratorRender;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.TileFurnaceLuckRender;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.TileLuck;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.TileRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.TileRfMolecularRendererFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.TileRfMolecularLegendary;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.TileRfMolecularRendererLeg;
import com.artiks.torcherinoCe.Block.ItemStorage.TileItemStorage;
import com.artiks.torcherinoCe.Block.ItemStorage.TileItemStorageRenderer;
import com.artiks.torcherinoCe.Block.Energy.Molecular.TileRfMolecular;
import com.artiks.torcherinoCe.Block.Energy.Molecular.TileRfMolecularRenderer;
import com.artiks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.artiks.torcherinoCe.Block.Time.Aceleration.TileAccelerationBaseRender;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TileTorcherinoBase;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TileTorcherinoBaseRender;
import com.artiks.torcherinoCe.Items.RenderHandler;
import com.artiks.torcherinoCe.Register.RegistryBlock;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileItemStorage.class, new TileItemStorageRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileLuck.class, new TileFurnaceLuckRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRfMolecularLegendary.class, new TileRfMolecularRendererLeg());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRfMolecularFarm.class, new TileRfMolecularRendererFarm());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCobbleGenerator.class, new TileCobbleGeneratorRender());
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