package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Gui.*;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends GuiContainer {
    private final TileTorcherinoBase tile;
    private final LocalizedStringKey LS = new LocalizedStringKey();
    private final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_t2.png");
    private GuiSliderInt sliderSpeed,sliderR,sliderG,sliderB,sliderX,sliderY,sliderZ;
    private GuiItemButton buttonInfo,buttonWork;
    GuiColorCube colorCube = new GuiColorCube();
    GuiButtonImage buttonRender;
    private String WorkString,RenderString;
    private int ScaledX,ScaledY,MouseX,MouseY;
    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity, player));
        this.tile = tileEntity;
        this.xSize = 281;
        this.ySize = 174;
    }
    @Override
    public void initGui() {
        super.initGui();
        this.ScaledX = (this.width - xSize) / 2;
        this.ScaledY = (this.height - ySize) / 2;
        this.buttonList.clear();
        //Radius
        sliderX = new GuiSliderInt(tile, 1, ScaledX+40, ScaledY+5, 180, 20, 0, tile.getValue(5), 15,"",4);
        sliderY = new GuiSliderInt(tile, 2, ScaledX+40, ScaledY+28, 180, 20, 0, tile.getValue(5), 16,"",4);
        sliderZ = new GuiSliderInt(tile, 3, ScaledX+40, ScaledY+51, 180, 20, 0, tile.getValue(5), 17,"",4);
        //Speed
        sliderSpeed = new GuiSliderInt(tile, 7, ScaledX+40, ScaledY+74, 180, 20, 0, tile.getValue(6), 2,"",4);
        //Render
        sliderR = new GuiSliderInt(tile, 4, ScaledX+40, ScaledY+104, 180, 20, 0, 255, 8,"",4);
        sliderG = new GuiSliderInt(tile, 5, ScaledX+40, ScaledY+127 , 180, 20, 0, 255, 9,"",4);
        sliderB = new GuiSliderInt(tile, 6, ScaledX+40, ScaledY+150, 180, 20, 0, 255, 10,"",4);
        //ItemButton-NetworkButton
        buttonInfo = new GuiItemButton(tile,8, ScaledX+230, ScaledY+5,0);
        buttonWork = new GuiItemButton(tile,9, ScaledX+256, ScaledY+5,1);
        //ImageButton-NetworkButton
        buttonRender = new GuiButtonImage(tile,12,ScaledX+230,ScaledY+28,2);
        //OTHER
        buttonInfo.setStackRender(new ItemStack(Items.PAPER));
        this.CreateButton();
        this.SettingOff();
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(ScaledX, ScaledY, 0, 0, xSize,ySize,xSize,ySize);
        this.cordScaled(mouseX,mouseY);
        this.DrawCube();
        this.UpdateButtonTooltip();
        this.UpdateSliderString();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
        this.DrawToolTipInfoButton();
    }
    private void CreateButton(){
        buttonList.add(buttonRender);
        buttonList.add(buttonInfo);
        buttonList.add(buttonWork);
    }
    private void SettingOff(){
        buttonList.add(sliderX);
        buttonList.add(sliderY);
        buttonList.add(sliderZ);
        buttonList.add(sliderSpeed);
        buttonList.remove(sliderR);
        buttonList.remove(sliderG);
        buttonList.remove(sliderB);
    }
    private void SettingOn(){
        buttonList.add(sliderR);
        buttonList.add(sliderG);
        buttonList.add(sliderB);
        buttonList.remove(sliderX);
        buttonList.remove(sliderY);
        buttonList.remove(sliderZ);
        buttonList.remove(sliderSpeed);
    }
    private void DrawToolTipInfoButton(){
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button == buttonInfo) {
                    int MaxRadius = tile.getValue(5);
                    int MaxSpeed = (tile.getValue(7) * 100 * tile.getValue(6));
                    drawHoveringText(TextFormatting.GREEN+"Info",MouseX,MouseY-16);
                    drawHoveringText("Max " + LS.StrTextRadius + ": " + MaxRadius+"x"+MaxRadius+"x"+MaxRadius, MouseX, MouseY+16);
                    drawHoveringText("Max " + LS.StrTextSpeed + ": " + MaxSpeed + "%", MouseX, MouseY);
                }
            }
            if (button.isMouseOver()) {
                if (button == buttonWork) {
                    drawHoveringText(WorkString,MouseX,MouseY);
                }
            }
            if (button.isMouseOver()) {
                if (button == buttonRender) {
                    drawHoveringText(RenderString,MouseX,MouseY);
                }
            }
        }
    }
    private void UpdateSliderString(){
        int speed = (tile.getValue(2) * 100 * tile.getValue(7));
        String speedString = (LS.StrTextSpeed + ": " + speed + "%");
        String stringR = ("Red: " + tile.getValue(8));
        String stringG = ("Green: " + tile.getValue(9));
        String stringB = ("Blue: " + tile.getValue(10));
        String radiusX = ("X Range: "+tile.getValue(15));
        String radiusY = ("Y Range: "+tile.getValue(16));
        String radiusZ = ("Z Range: "+tile.getValue(17));
        sliderX.displayString = radiusX;
        sliderY.displayString = radiusY;
        sliderZ.displayString = radiusZ;
        sliderR.displayString = stringR;
        sliderG.displayString = stringG;
        sliderB.displayString = stringB;
        sliderSpeed.displayString = speedString;
    }
    private void UpdateImageButton(){
        if(tile.getValue(4) == 0){
            buttonRender.setTexture(0,0);
        }
        if(tile.getValue(4) == 1){
            buttonRender.setTexture(16,0);
        }
    }
    private void UpdateButtonTooltip() {
        int renderGet = tile.getValue(4);
        int workGet = tile.getValue(3);
        this.UpdateImageButton();
        switch (renderGet) {
            case 0: RenderString = LS.StrTextRenderNull;break;
            case 1: RenderString = LS.StrTextRenderLine;break;
            case 2: RenderString = LS.StrTextRenderBox;break;
            case 3: RenderString = LS.StrTextRenderComb;break;
        }
        switch (workGet) {
            case 0: WorkString = LS.StrTextWorking;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_LAMP));break;
            case 1: WorkString = LS.StrTextAlways;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_BLOCK));break;
            case 2: WorkString = LS.StrRedstoneMode;buttonWork.setStackRender(new ItemStack(Items.REDSTONE));break;
            case 3: WorkString = LS.StrRedstoneModeRevers;buttonWork.setStackRender(new ItemStack(Blocks.REDSTONE_TORCH));break;
        }
    }
    private void cordScaled(int mouseX,int mouseY){
        this.ScaledX = (this.width - xSize) / 2;
        this.ScaledY = (this.height - ySize) / 2;
        this.MouseX = mouseX;
        this.MouseY = mouseY;
    }
    private void DrawCube(){
        float red = sliderR.getSliderValue() / 255f;
        float green = sliderG.getSliderValue() / 255f;
        float blue = sliderB.getSliderValue() / 255f;
        colorCube.setCube(46, 65, ScaledX + 230, ScaledY + 104, red, green, blue);
        colorCube.drawCube();
    }
}