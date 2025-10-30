package com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Gui.GuiItemButton;
import com.artiks.torcherinoCe.Gui.GuiSliderInt;
import com.artiks.torcherinoCe.Gui.enumRedstoneControllerButton;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiMarker extends ExampleGuiContainer {
    private final TileMarker tileEntity;
    private  GuiSliderInt sliderSpeed;
    private GuiItemButton buttonWork;
    private String WorkString;
    public GuiMarker(InventoryPlayer inventory, TileMarker tileEntity) {
        super(new ContainerMarker(inventory, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_gps_torch.png", 200, 167);
        BarComponent energy = new BarComponent(this,1,8,7,0,124,137,12,"textures/gui/gui_component.png", BarComponent.SideEnum.LEFT);
        addBarComponent(energy);
    }
    @Override
    public void Tick() {
        SetEnergyBarTooltips(1,tileEntity.getEnergyStored(),tileEntity.getMaxEnergyStorage(),tileEntity.getEnergyPerTick(),true);
        setBarValue(1,tileEntity.getEnergyStored(),tileEntity.getMaxEnergyStorage());
    }
    @Override
    public void TickScreen() {
        UpdateSliderString();
        DrawToolTipInfoButton();
        UpdateButtonStringRender();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        buttonWork = new GuiItemButton(tileEntity,10, ScaledX+149, ScaledY+4,1);
        sliderSpeed = new GuiSliderInt(tileEntity, 1, ScaledX+5, ScaledY+26, 143, 20, 0, tileEntity.getMaxModes(), "mode","",4);
        buttonList.add(sliderSpeed);
        buttonList.add(buttonWork);
    }
    private void UpdateSliderString(){
        int speed = (tileEntity.getMode() * 100 * tileEntity.getMaxSpeed());
        sliderSpeed.displayString = (LocalizedStringKey.StrTextSpeed + ": " + speed + "%");
    }
    private void DrawToolTipInfoButton() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                }
            }
        }
    }
    private void UpdateButtonStringRender() {
        int work = tileEntity.getBooleanMode();
        enumRedstoneControllerButton button = enumRedstoneControllerButton.getByIndex(work);
        WorkString = button.getDisplayText();
        buttonWork.setStackRender(button.getStack());
    }
}