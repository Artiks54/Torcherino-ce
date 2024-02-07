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
            Item[] itemArray = RegistryArray.ITEMS.toArray(new Item[0]);
            register.getRegistry().registerAll(itemArray);
        }
        @SubscribeEvent
        public static void onBlockRegister(RegistryEvent.Register<Block> register) {
            Block[] blocks = RegistryArray.BLOCKS.toArray(new Block[0]);
            register.getRegistry().registerAll(blocks);
        }
        @SubscribeEvent
        public static void modelRegister(ModelRegistryEvent event) {
            for (Item item : RegistryArray.ITEMS) {
                if (item instanceof IHasModel) {
                    ((IHasModel)item).registerModels();
                }
            }
            for (Block block : RegistryArray.BLOCKS) {
                if (block instanceof IHasModel) {
                    ((IHasModel) block).registerModels();

                }
            }
        }
    }