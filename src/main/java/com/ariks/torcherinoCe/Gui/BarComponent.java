package com.ariks.torcherinoCe.Gui;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Tags;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import java.util.Objects;

public class BarComponent {
    //Текстура
    private final String textures;
    //ID бара
    private final int id;
    //Начала рисовки в гуи
    private final int startX,startY;
    //Ширина и высота бара в текстуре
    private final int widthBar,heightBar;
    //Расположение бара в текстуре
    private int TextureX,TextureY;
    //Подсказка бара
    private String tooltip;
    //Направление бара
    private String SideDirection;
    //Значение бара
    private float Value, maxValue;
    private int cordX, cordY;
    private final ExampleGuiContainer container;

    public BarComponent(ExampleGuiContainer container, int id, int StartX, int StartY,int TextureX,int TextureY, int widthBar, int heightBar, String textures) {
        this.container = container;
        this.id = id;
        this.startX = StartX;
        this.startY = StartY;
        this.TextureX = TextureX;
        this.TextureY = TextureY;
        this.textures = textures;
        this.widthBar = widthBar + 1;
        this.heightBar = heightBar + 1;
    }
    public BarComponent(ExampleGuiContainer container, int id, int StartX, int StartY, int widthBar, int heightBar, String textures) {
        this.container = container;
        this.id = id;
        this.startX = StartX;
        this.startY = StartY;
        this.textures = textures;
        this.widthBar = widthBar + 1;
        this.heightBar = heightBar + 1;
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
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
    public String getTooltip() {
        return tooltip;
    }
    public void setSideDirection(String sideDirection) {
        SideDirection = sideDirection;
    }
    public boolean isMouseOver(int mouseX, int mouseY) {
        int x = cordX + startX;
        int y = cordY + startY;
        return mouseX >= x && mouseX < x + widthBar && mouseY >= y && mouseY < y + heightBar;
    }
    public void draw() {
        ResourceLocation texture = new ResourceLocation(Tags.MODID, textures);
        container.mc.getTextureManager().bindTexture(texture);
        //С право на лево
        if (Objects.equals(SideDirection, "right")) {
            int drawWidth = (int) (Value / maxValue * widthBar);
            int drawX = cordX + startX + widthBar - drawWidth; // Начинаем с правого края
            Gui.drawModalRectWithCustomSizedTexture(drawX, cordY + startY, TextureX + widthBar - drawWidth, TextureY, drawWidth, heightBar, 256, 256);
        }
        //С лево на право
        if (Objects.equals(SideDirection, "left")) {
            int drawWidth = (int) (Value / maxValue * widthBar);
            Gui.drawModalRectWithCustomSizedTexture(cordX + startX, cordY + startY, TextureX, TextureY, drawWidth, heightBar, 256, 256);
        }
        //Снизу в верх
        if (Objects.equals(SideDirection, "up")) {
            int drawHeight = (int) (Value / maxValue * heightBar);
            int drawY = cordY + startY + (heightBar - drawHeight);
            Gui.drawModalRectWithCustomSizedTexture(cordX + startX, drawY, TextureX, TextureY, widthBar, drawHeight, 256, 256);
        }
        //Сверху в низ
        if (Objects.equals(SideDirection, "down")) {
            int drawHeight = (int) (Value / maxValue * heightBar);
            int drawY = cordY + startY;
            Gui.drawModalRectWithCustomSizedTexture(cordX + startX, drawY, TextureX, TextureY, widthBar, drawHeight, 256, 256);
        }
        //Default с лево на право
        if (SideDirection == null) {
            int drawWidth = (int) (Value / maxValue * widthBar);
            Gui.drawModalRectWithCustomSizedTexture(cordX + startX, cordY + startY, TextureX, TextureY, drawWidth, heightBar, 256, 256);
        }
    }
}