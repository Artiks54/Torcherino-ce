package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.util.GuiButtonNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTimeManipulator extends ExampleGuiContainer {
    private final TileTimeManipulator tile;
    private GuiButtonNetwork buttonSetDay,buttonSetNight;
    private int percent;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity, EntityPlayer player) {
        super(new ContainerTimeManipulator(inventory, tileEntity,player),tileEntity);
        this.tile = tileEntity;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.UpdateButtonEnabled();
        this.percent = tile.getValue(1);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), x+10,y+10, Color.ORANGE.getRGB());
        this.fontRenderer.drawString(LS.StrTextProgress+" "+percent+"%",x+10,y+25, Color.WHITE.getRGB());
    }
    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButtonNetwork(tile,1, x+10, y+95, 110, 20, TextFormatting.YELLOW+ LS.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tile,2, x+135, y+95, 110, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight,2);
        this.UpdateButtonEnabled();
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
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
}