package com.artiks.torcherinoCe.Register;

import com.artiks.torcherinoCe.utility.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class RegistryHandlers {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> register) {
        register.getRegistry().registerAll(RegistryItems.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void modelRegister(ModelRegistryEvent event) {
        RegistryItems.ITEMS.stream()
                .filter(item -> item instanceof IHasModel)
                .forEach(item -> ((IHasModel) item).registerModels());
    }
}