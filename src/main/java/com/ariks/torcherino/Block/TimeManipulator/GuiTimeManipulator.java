package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.util.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTimeManipulator extends ExampleGuiContainer {
    private final TileTimeManipulator tile;
    private GuiButton buttonSetDay,buttonSetNight;
    private int percent;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity, EntityPlayer player) {
        super(new ContainerTimeManipulator(inventory, tileEntity,player));
        this.tile = tileEntity;
    }
    private void UpdateButtonEnabled(){
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
        percent = tile.getValue(1);
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+4,10, Color.ORANGE.getRGB());
        this.fontRenderer.drawString(LS.StrTextProgress+" "+percent+"%",posX+4,25, Color.WHITE.getRGB());
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButton(tile,1, x+10, y+95, 110, 20, TextFormatting.YELLOW+ LS.StrTextDay,1);
        buttonSetNight = new GuiButton(tile,2, x+135, y+95, 110, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight,2);
        this.UpdateButtonEnabled();
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
}