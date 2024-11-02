package com.ariks.torcherino.Block.Core;

import com.ariks.torcherino.Gui.BarComponent;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ExampleGuiContainer extends GuiContainer {

    private final List<BarComponent> barComponents = new ArrayList<>();
    public final LocalizedStringKey LS = new LocalizedStringKey();
    private ResourceLocation GuiTexture;
    private int MouseX;
    private int MouseY;
    private int ScaledX;
    private int ScaledY;
    private int WidthTexture,HeightTexture;
    public NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public ExampleGuiContainer(Container container) {
        super(container);
    }

    //Обновление
    private void UpdateScaled(int mouseX,int mouseY){
        ScaledX = (this.width - this.xSize) / 2;
        ScaledY = (this.height - this.ySize) / 2;
        MouseX = mouseX;
        MouseY = mouseY;
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.UpdateScaled(mouseX,mouseY);
        this.Tick();
        this.mc.getTextureManager().bindTexture(GuiTexture);
        this.drawTexturedModalRect(ScaledX, ScaledY, 0, 0, WidthTexture, HeightTexture);
        if(!barComponents.isEmpty()){
            for (BarComponent barComponent : barComponents) {
                barComponent.setCord(ScaledX, ScaledY);
                barComponent.draw();
            }
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        if(!barComponents.isEmpty()){
            for (BarComponent barComponent : barComponents){
                if (barComponent.isMouseOver(MouseX, MouseY) && barComponent.getTooltip() != null) {
                    drawHoveringText(barComponent.getTooltip(),MouseX,MouseY);
                }
            }
        }
    }
    //Бар
    public void addBarComponent(BarComponent barComponent) {
        barComponents.add(barComponent);
    }
    public void setTooltipBar(int id,String tooltip){
        for (BarComponent barComponent : barComponents) {
            if (barComponent.getId() == id) {
                barComponent.setTooltip(tooltip);
                break;
            }
        }
    }
    public void setBarValue(int id, int currentValue, int maxValue) {
        for (BarComponent barComponent : barComponents) {
            if (barComponent.getId() == id) {
                barComponent.setValue(currentValue, maxValue);
                break;
            }
        }
    }
    //Утсановка текстуры
    public void setTexture(String GuiTexture,int WidthTexture, int HeightTexture){
        this.GuiTexture = new ResourceLocation(Torcherino.MOD_ID,GuiTexture);
        this.WidthTexture = WidthTexture + 1;
        this.HeightTexture = HeightTexture + 1;
    }
    public void Tick(){
    }
}
