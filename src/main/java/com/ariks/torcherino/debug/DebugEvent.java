package com.ariks.torcherino.debug;
import com.ariks.torcherino.GUI.GuiConfiguration;
import com.ariks.torcherino.GUI.GuiTest;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class DebugEvent {
    @SubscribeEvent
    public void InformationTorch(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        ItemStack heldItem = player.getHeldItemMainhand();
        if (heldItem.getItem() == RegistryArray.Item_Info) {
            if (tile instanceof TileTorcherinoBase){
                GuiTest.lastClickedTileEntity = tile;
                GuiTest.tileName = tile.getClass().getSimpleName();
                Minecraft.getMinecraft().displayGuiScreen(new GuiTest());
            }
        }
    }
    @SubscribeEvent
    public void ConfigurationTorch(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        ItemStack heldItem = player.getHeldItemMainhand();
        if (heldItem.getItem() == RegistryArray.Item_Config) {
            if (tile instanceof TileTorcherinoBase) {
                GuiConfiguration.lastClickedTileEntity = tile;
                GuiConfiguration.tileName = tile.getClass().getSimpleName();
                Minecraft.getMinecraft().displayGuiScreen(new GuiConfiguration());
            }
        }
    }
}