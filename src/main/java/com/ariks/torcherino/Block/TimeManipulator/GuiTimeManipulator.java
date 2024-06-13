package com.ariks.torcherino.Block.TimeManipulator;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.util.GuiButtonNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTimeManipulator extends ExampleGuiContainer {
    private final TileTimeManipulator tile;
    private GuiButtonNetwork buttonSetDay,buttonSetNight;
    public GuiTimeManipulator(InventoryPlayer inventory, TileTimeManipulator tileEntity, EntityPlayer player) {
        super(new ContainerTimeManipulator(inventory,tileEntity,player));
        this.tile = tileEntity;
        SetTexture("textures/gui/gui2.png");
        SetWidth(175);
        SetHeight(167);
        setBooleanBar(true);
        SetBarSettings(165,25,5,13,1,170,"Time:");
    }
    @Override
    public void UpdateBar() {
        SetBarValue(tile.getValue(1),tile.getValue(2));
    }
    @Override
    public void Update() {
        if(tile.getValue(1) < tile.getValue(2)) {
            buttonSetDay.enabled = false;
            buttonSetNight.enabled = false;
        }if(tile.getValue(1) >= tile.getValue(2)){
            buttonSetDay.enabled = true;
            buttonSetNight.enabled = true;
        }
    }
    @Override
    public void initGui() {
        super.initGui();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonSetDay = new GuiButtonNetwork(tile,1, x+5, y+50, 70, 20, TextFormatting.YELLOW+ LS.StrTextDay,1);
        buttonSetNight = new GuiButtonNetwork(tile,2, x+101, y+50, 70, 20, TextFormatting.DARK_PURPLE+ LS.StrTextNight,2);
        buttonList.add(buttonSetDay);
        buttonList.add(buttonSetNight);
    }
}