package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTimeManipulator extends ExampleGuiContainer {
    private final TileTimeManipulator tile;
    private GuiButton buttonSetDay;
    private GuiButton buttonSetNight;
    private int percent;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity, EntityPlayer player) {
        super(new ContainerTimeManipulator(inventory, tileEntity,player));
        this.tile = tileEntity;
    }
    public void UpdateButtonEnabled(){
        if(percent < 100) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
        }if(percent >= 100){
            buttonSetDay.enabled = true;
            buttonSetNight.enabled = true;
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = (this.xSize / 2) -121;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+4,10, Color.ORANGE.getRGB());
        this.fontRenderer.drawString(LS.StrTextProgress+" "+percent+"%",posX+4,25, Color.WHITE.getRGB());
        percent = tile.getValue(1);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButton(1, x+10, y+95, 110, 20, TextFormatting.YELLOW+ LS.StrTextDay);
        buttonSetNight = new GuiButton(2, x+135, y+95, 110, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight);
        this.UpdateButtonEnabled();
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
    @Override
    protected void actionPerformed(GuiButton button){
        int buttonId = button.id;
        if (buttonId == 1) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 1));
        }
        if (buttonId == 2) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 2));
        }
    }
}