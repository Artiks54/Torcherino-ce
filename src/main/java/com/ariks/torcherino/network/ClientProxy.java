package com.ariks.torcherino.network;

import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBaseRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
	}
}