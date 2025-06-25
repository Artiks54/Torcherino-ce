package com.ariks.torcherinoCe.Block.MarkerAcceleration;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import com.ariks.torcherinoCe.Gui.GuiItemButton;
import com.ariks.torcherinoCe.Gui.GuiSliderInt;
import com.ariks.torcherinoCe.utility.EnergyFormat;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import java.util.Arrays;
import java.util.List;

public class GuiMarker extends ExampleGuiContainer {
    private final TileMarker tileEntity;
    private  GuiSliderInt sliderSpeed;
    private GuiItemButton buttonWork;
    private String WorkString;
    public GuiMarker(InventoryPlayer inventory, TileMarker tileEntity) {
        super(new ContainerMarker(inventory, tileEntity));
        this.tileEntity = tileEntity;
        setTexture("textures/gui/gui_gps_torch.png", 175, 167);
        BarComponent energy = new BarComponent(this,1,8,7,0,124,144,18,"textures/gui/gui_component.png");
        energy.setSideDirection("left");
        addBarComponent(energy);
    }
    @Override
    public void Tick() {
        String formattedValueMin = EnergyFormat.formatNumber(tileEntity.getValue(4));
        String formattedValueMax = EnergyFormat.formatNumber(tileEntity.getMaxEnergyStorage());
        String formattedValueRf = EnergyFormat.formatNumber(tileEntity.getValue(5));
        setBarValue(1,tileEntity.getValue(4),tileEntity.getMaxEnergyStorage());
        List<String> tooltipLines = Arrays.asList(
                formattedValueMin+ " / " + formattedValueMax,
                "RF-Tick: " + formattedValueRf);
        setTooltipBarLines(1,tooltipLines);
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
        sliderSpeed = new GuiSliderInt(tileEntity, 1, ScaledX+5, ScaledY+26, 143, 20, 0, tileEntity.getMaxModes(), 1,"",4);
        buttonList.add(sliderSpeed);
        buttonList.add(buttonWork);
    }
    private void UpdateSliderString(){
        int speed = (tileEntity.getValue(1) * 100 * tileEntity.getValue(3));
        sliderSpeed.displayString = (LS.StrTextSpeed + ": " + speed + "%");
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
        int work = tileEntity.getValue(2);
        String[] workStrings = {
                TextFormatting.RED + LS.StrTextWorking,
                TextFormatting.GREEN + LS.StrTextAlways,
                TextFormatting.GREEN + LS.StrRedstoneMode,
                TextFormatting.GREEN + LS.StrRedstoneModeRevers
        };
        ItemStack[] workIcons = {
                new ItemStack(Blocks.REDSTONE_LAMP),
                new ItemStack(Blocks.REDSTONE_BLOCK),
                new ItemStack(Items.REDSTONE),
                new ItemStack(Blocks.REDSTONE_TORCH)
        };
        WorkString = workStrings[work];
        buttonWork.setStackRender(workIcons[work]);
    }
}