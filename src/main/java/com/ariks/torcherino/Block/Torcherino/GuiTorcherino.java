package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.util.CuboidGui;
import com.ariks.torcherino.util.GuiSliderInt;
import com.ariks.torcherino.Register.RegistryNetwork;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGuiContainer {
    private final TileTorcherinoBase tile;
    private GuiSliderInt sliderRadius,sliderSpeed,sliderR,sliderG,sliderB;
    private GuiButton buttonWork,buttonRender,SettingsOpen;
    CuboidGui cuboidGui = new CuboidGui();
    private boolean drawCube;

    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
    }
    @Override
    public void initGui() {
        super.initGui();
        DelCube();
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        sliderRadius = new GuiSliderInt(tile, 0, x + 10, y + 70, 115, 20, 0, tile.getValue(5), 1);
        sliderSpeed = new GuiSliderInt(tile, 0, x + 10, y + 95, 115, 20, 0, tile.getValue(6), 2);
        sliderR = new GuiSliderInt(tile, 0, x + 10, y + 45, 115, 20, 0, 255, 8);
        sliderG = new GuiSliderInt(tile, 0, x + 10, y + 70, 115, 20, 0, 255, 9);
        sliderB = new GuiSliderInt(tile, 0, x + 10, y + 95, 115, 20, 0, 255, 10);
        buttonWork = new GuiButton(1, x + 130, y + 70, 115, 20, "");
        buttonRender = new GuiButton(2, x + 130, y + 95, 115, 20, "");
        SettingsOpen = new GuiButton(3, x + 130, y + 95, 115, 20, "[ " + LS.StrTextRenderOff + " ]");
        buttonList.add(buttonWork);
        buttonList.add(buttonRender);
        buttonList.add(SettingsOpen);
        buttonList.add(sliderRadius);
        buttonList.add(sliderSpeed);
        buttonList.add(sliderR);
        buttonList.add(sliderG);
        buttonList.add(sliderB);
        offStings();
        hideStings();
    }
    public void hideMain() {
        buttonWork.visible = false;
        sliderRadius.visible = false;
        sliderSpeed.visible = false;
        SettingsOpen.visible = false;
        buttonRender.visible = true;
        sliderR.visible = true;
        sliderG.visible = true;
        sliderB.visible = true;
    }
    public void offMain() {
        buttonWork.enabled = false;
        sliderRadius.enabled = false;
        sliderSpeed.enabled = false;
        SettingsOpen.enabled = false;
        buttonRender.enabled = true;
        sliderR.enabled = true;
        sliderG.enabled = true;
        sliderB.enabled = true;
    }
    public void hideStings() {
        sliderR.visible = false;
        sliderG.visible = false;
        sliderB.visible = false;
        buttonRender.visible = false;
        buttonWork.visible = true;
        sliderRadius.visible = true;
        sliderSpeed.visible = true;
        SettingsOpen.visible = true;
    }
    public void offStings() {
        sliderR.enabled = false;
        sliderG.enabled = false;
        sliderB.enabled = false;
        buttonRender.enabled = false;
        buttonWork.enabled = true;
        sliderRadius.enabled = true;
        sliderSpeed.enabled = true;
        SettingsOpen.enabled = true;
    }
    public void DrawCube(){
        if(drawCube) {
            int x = (this.width - xSize) / 2;
            int y = (this.height - ySize) / 2;
            float red = sliderR.getSliderValue() / 255f;
            float green = sliderG.getSliderValue() / 255f;
            float blue = sliderB.getSliderValue() / 255f;
            cuboidGui.setCube(60, x + 185, y+55, red, green, blue);
            cuboidGui.drawCube();
        }
    }
    public void DelCube(){
        drawCube = false;
        cuboidGui.clearCube();
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            RegistryNetwork.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 3));
        }
        if (button.id == 2) {
            RegistryNetwork.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 4));
        }
        if (button.id == 3) {
            hideMain();
            offMain();
            drawCube = true;
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        DrawCube();
        for (GuiButton button : buttonList) {
            if (button == sliderRadius && button.isMouseOver()) {
                drawHoveringText(LS.StrTextRadius, mouseX, mouseY);
                break;
            }
            if (button == sliderSpeed && button.isMouseOver()) {
                drawHoveringText(LS.StrTextSpeed+" "+tile.getValue(7) * 100 * sliderSpeed.getSliderValue() +"%",mouseX,mouseY);
                break;
            }
            if (button == sliderR && button.isMouseOver()) {
                drawHoveringText("R", mouseX, mouseY);
                break;
            }
            if (button == sliderG && button.isMouseOver()) {
                drawHoveringText("G", mouseX, mouseY);
                break;
            }
            if (button == sliderB && button.isMouseOver()) {
                drawHoveringText("B", mouseX, mouseY);
                break;
            }
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int posX = (this.xSize / 2) -121;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+5,10, Color.WHITE.getRGB());
    }
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.updateButton();
    }
    public void updateButton() {
        if(tile.getValue(3) == 0) {buttonWork.displayString = LS.StrTextWorking;}
        else if (tile.getValue(3) == 1) {buttonWork.displayString = LS.StrTextAlways;}
        else if (tile.getValue(3) == 2) {buttonWork.displayString = LS.StrRedstoneMode;}
        else if (tile.getValue(3) == 3) {buttonWork.displayString = LS.StrRedstoneModeRevers;}
        if(tile.getValue(4) == 0){buttonRender.displayString = LS.StrTextRenderNull;}
        else if(tile.getValue(4) == 1){buttonRender.displayString = LS.StrTextRenderLine;}
        else if(tile.getValue(4) == 2){buttonRender.displayString = LS.StrTextRenderBox;}
        else if(tile.getValue(4) == 3){buttonRender.displayString = LS.StrTextRenderComb;}
    }
}