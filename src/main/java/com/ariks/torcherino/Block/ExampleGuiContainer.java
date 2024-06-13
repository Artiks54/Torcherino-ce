package com.ariks.torcherino.Block;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class ExampleGuiContainer extends GuiContainer {
    private ResourceLocation textures;
    private int Width,Height;
    public final LocalizedStringKey LS = new LocalizedStringKey();
    private Boolean booleanBar;
    public int X,Y;
    private String string;
    private int wightTexture, heightTexture,Value,ValueMax,StartX,StartY,CordX,CordY;
    public ExampleGuiContainer(Container container) {
        super(container);
    }
    public void SetTexture(String texturePath) {
        this.textures = new ResourceLocation(Torcherino.MOD_ID, texturePath);
    }
    public void setBooleanBar(Boolean booleanBar) {
        this.booleanBar = booleanBar;
    }
    public void SetWidth(int Width) {
        this.Width = Width;
    }
    public void SetHeight(int Height) {
        this.Height = Height;
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    public void SetBarSettings(int wightTexture,int heightTexture,int StartX,int StartY,int CordX,int CordY,String string){
        this.wightTexture = wightTexture;
        this.heightTexture = heightTexture;
        this.StartX = StartX;
        this.StartY = StartY;
        this.CordX = CordX;
        this.CordY = CordY;
        this.string = string;
    }
    public void SetBarValue(int value, int valueMax){
        this.Value = value;
        this.ValueMax = valueMax;
    }
    private void DrawBar(){
        drawTexturedModalRect(X, Y, 0, 0, Width,Height);
        double draw = (double) (Value * (wightTexture + 1)) / (ValueMax + 1);
        drawTexturedModalRect(X + StartX, Y + StartY, CordX, CordY, (int) draw, heightTexture);
    }
    public void Update(){}
    public void UpdateBar(){}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(textures);
        this.X = (this.width - this.xSize) / 2;
        this.Y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(X, Y, 0, 0, Width,Height);
        this.Update();
        if(booleanBar) {
            this.UpdateBar();
            this.DrawBar();
            if (mouseX >= X + StartX && mouseX <= X + wightTexture+5 && mouseY >= Y + StartY && mouseY <= Y + heightTexture+12) {
                drawHoveringText(string+" "+Value+"/"+ValueMax,mouseX,mouseY);
            }
        }
    }
}