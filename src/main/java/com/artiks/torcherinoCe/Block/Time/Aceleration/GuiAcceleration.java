package com.artiks.torcherinoCe.Block.Time.Aceleration;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Gui.GuiButtonImage;
import com.artiks.torcherinoCe.Gui.GuiItemButton;
import com.artiks.torcherinoCe.Gui.enumRedstoneControllerButton;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiAcceleration extends ExampleGuiContainer {
    private final TileAcceleration tile;
    private GuiItemButton buttonWork;
    private GuiButtonImage buttonRender;
    private String WorkString,RenderString;

    public GuiAcceleration(InventoryPlayer inventory, TileAcceleration tileEntity) {
        super(new ContainerAcceleration(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 8, 16, 0, 19, 159, 16, "textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        SetBarTooltips(1,tile.getEnergyTime(),tile.getMaxTimeStored(),true);
        setBarValue(1, tile.getEnergyTime(), tile.getMaxTimeStored());
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
        int render = tile.getBooleanRender();
        int work = tile.getBooleanMode();
        enumRedstoneControllerButton button = enumRedstoneControllerButton.getByIndex(work);
        WorkString = button.getDisplayText();
        buttonWork.setStackRender(button.getStack());
        switch (render) {
            case 0: RenderString = TextFormatting.RED + LocalizedStringKey.StrTextRenderNull;break;
            case 1: RenderString = TextFormatting.GREEN + LocalizedStringKey.StrTextRenderLine;break;
            case 2: RenderString = TextFormatting.GREEN + LocalizedStringKey.StrTextRenderBox;break;
            case 3: RenderString = TextFormatting.GREEN + LocalizedStringKey.StrTextRenderComb;break;
        }
    }
    private void UpdateImageButtonRender(){
        int getRender = tile.getBooleanRender();
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