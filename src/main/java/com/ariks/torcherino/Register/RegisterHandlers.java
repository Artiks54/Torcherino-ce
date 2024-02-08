package com.ariks.torcherino.Register;

import com.ariks.torcherino.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterHandlers {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> register) {
        register.getRegistry().registerAll(RegistryArray.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> register) {
        register.getRegistry().registerAll(RegistryArray.BLOCKS.toArray(new Block[0]));
    }
    @SubscribeEvent
    public static void modelRegister(ModelRegistryEvent event) {
        RegistryArray.ITEMS.stream()
                .filter(item -> item instanceof IHasModel)
                .forEach(item -> ((IHasModel) item).registerModels());
        RegistryArray.BLOCKS.stream()
                .filter(block -> block instanceof IHasModel)
                .forEach(block -> ((IHasModel) block).registerModels());
    }
}