package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Tags;
import net.minecraft.util.ResourceLocation;
import java.util.List;

public class BarComponent {
    //Текстура
    private final String textures;
    //ID бара
    private final int id;
    //Начала рисовки в гуи
    private final int startX,startY;
    //Ширина и высота бара в текстуре
    private final int widthBar;
    private final int heightBar;
    //Расположение бара в текстуре
    private int TextureX,TextureY;
    //Направление бара
    private final SideEnum side;
    //Значение бара
    private float Value, maxValue;
    private int cordX, cordY;
    private final ExampleGuiContainer exampleGuiContainer;
    private List<String> tooltipLines;

    public enum SideEnum {
        LEFT,UP,DOWN,RIGHT
    }

    public BarComponent(ExampleGuiContainer container, int id, int StartX, int StartY,int TextureX,int TextureY, int widthBar, int heightBar, String textures,SideEnum side) {
        this.exampleGuiContainer = container;
        this.id = id;
        this.startX = StartX;
        this.startY = StartY;
        this.TextureX = TextureX;
        this.TextureY = TextureY;
        this.textures = textures;
        this.widthBar = widthBar + 1;
        this.heightBar = heightBar + 1;
        this.side = side;
    }
    //For-RF-Molecular
    public BarComponent(ExampleGuiContainer container, int id, int StartX, int StartY, int widthBar, int heightBar, String textures,SideEnum side) {
        this.exampleGuiContainer = container;
        this.id = id;
        this.startX = StartX;
        this.startY = StartY;
        this.textures = textures;
        this.widthBar = widthBar + 1;
        this.heightBar = heightBar + 1;
        this.side = side;
    }

    public void setCord(int cordX,int cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public void setTextureTexture(int textureX,int textureY) {
        this.TextureX = textureX;
        this.TextureY = textureY;
    }

    public void setValue(float Value, float maxValue) {
        this.Value = Value;
        this.maxValue = maxValue;
    }

    public int getId() {
        return id;
    }

    public void setTooltipLines(List<String> strings){
        tooltipLines = strings;
    }

    public List<String> getTooltipLines(){
        return tooltipLines;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        int x = cordX + startX;
        int y = cordY + startY;
        return mouseX >= x && mouseX < x + widthBar && mouseY >= y && mouseY < y + heightBar;
    }

    public void draw() {
        if(side == null) return;
        ResourceLocation texture = new ResourceLocation(Tags.MODID, textures);
        exampleGuiContainer.mc.getTextureManager().bindTexture(texture);
        switch (side){
            case LEFT -> {
                int drawWidth = (int) (Value / maxValue * widthBar);
                exampleGuiContainer.drawTexturedModalRect(cordX + startX, cordY + startY, TextureX, TextureY, drawWidth, heightBar);
            }
            case RIGHT -> {
                int drawWidth = (int) (Value / maxValue * widthBar);
                int drawX = cordX + startX + widthBar - drawWidth; // Начинаем с правого края
                exampleGuiContainer.drawTexturedModalRect(drawX, cordY + startY, TextureX + widthBar - drawWidth, TextureY, drawWidth, heightBar);
            }
            case DOWN -> {
                int drawHeight = (int) (Value / maxValue * heightBar);
                int drawY = cordY + startY;
                exampleGuiContainer.drawTexturedModalRect(cordX + startX, drawY, TextureX, TextureY, widthBar, drawHeight);
            }
            case UP -> {
                int drawHeight = (int) (Value / maxValue * heightBar);
                int drawY = cordY + startY + (heightBar - drawHeight);
                exampleGuiContainer.drawTexturedModalRect(cordX + startX, drawY, TextureX, TextureY, widthBar, drawHeight);
            }
        }
    }
}