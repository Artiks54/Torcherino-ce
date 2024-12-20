package com.ariks.torcherino.Block.Time.Aceleration;

import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import com.ariks.torcherino.Gui.BarComponent;
import com.ariks.torcherino.Gui.GuiButtonImage;
import com.ariks.torcherino.Gui.GuiItemButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GuiAcceleration extends ExampleGuiContainer {
    private final TileAcceleration tile;
    private GuiItemButton buttonWork;
    private GuiButtonImage buttonRender;
    private String WorkString,RenderString;

    public GuiAcceleration(InventoryPlayer inventory, TileAcceleration tileEntity, EntityPlayer player) {
        super(new ContainerAcceleration(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 8, 16, 0, 19, 159, 16, "textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getValue(2));
        setTooltipBar(1, LS.StrTime + " " + formattedValueMin + " / " + formattedValueMax);
        setBarValue(1, tile.getValue(1), tile.getValue(2));
    }
    @Override
    public void TickScreen() {
        DrawToolTipInfoButton();
        UpdateButtonTooltip();
        UpdateImageButtonRender();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonWork = new GuiItemButton(tile, 1, ScaledX + 5, ScaledY + 40, 1);
        buttonRender = new GuiButtonImage(tile, 2, ScaledX + 30, ScaledY + 40, 2);
        buttonList.add(buttonRender);
        buttonList.add(buttonWork);
    }
    private void UpdateButtonTooltip() {
        int render = tile.getValue(4);
        int work = tile.getValue(3);
        switch (render) {
            case 0: RenderString = LS.StrTextRenderNull;break;
            case 1: RenderString = LS.StrTextRenderLine;break;
            case 2: RenderString = LS.StrTextRenderBox;break;
            case 3: RenderString = LS.StrTextRenderComb;break;
        }
        switch (work) {
            case 0: WorkString = LS.StrTextWorking;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_LAMP));break;
            case 1: WorkString = LS.StrTextAlways;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_BLOCK));break;
            case 2: WorkString = LS.StrRedstoneMode;buttonWork.setStackRender(new ItemStack(Items.REDSTONE));break;
            case 3: WorkString = LS.StrRedstoneModeRevers;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_TORCH));break;
        }
    }
    private void UpdateImageButtonRender(){
        int getRender = tile.getValue(4);
        switch (getRender){
            case 0: buttonRender.setTexture(0,16);break;
            case 1: buttonRender.setTexture(0,0);break;
            case 2: buttonRender.setTexture(16,0);break;
            case 3: buttonRender.setTexture(32,0);break;
        }
    }
    private void DrawToolTipInfoButton() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                }
                if (button.equals(buttonRender)) {
                    drawHoveringText(RenderString, getMouseX(), getMouseY());
                }
            }
        }
    }
}