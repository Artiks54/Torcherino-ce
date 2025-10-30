package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Tags;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class TextureComponent {
    // Текстура
    private final String textures;
    // ID бара
    private final int id;
    // Начала рисовки в GUI
    private final int startX, startY;
    // Ширина и высота бара в текстуре
    private final int widthBar, heightBar;
    // Расположение бара в текстуре
    private final int textureX, textureY;
    private int cordX, cordY;
    private boolean drawing;
    private final ExampleGuiContainer container;

    public TextureComponent(ExampleGuiContainer container, int id, int startX, int startY, int textureX, int textureY, int widthBar, int heightBar, String textures) {
        this.container = container;
        this.id = id;
        this.startX = startX;
        this.startY = startY;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textures = textures;
        this.widthBar = widthBar;
        this.heightBar = heightBar;
    }

    public void setCord(int cordX, int cordY) {
        this.cordX = cordX;
        this.cordY = cordY;
    }


    public void setDrawing(boolean drawing){
        this.drawing = drawing;
    }

    public int getId() {
        return id;
    }

    public void draw() {
        if(drawing) {
            ResourceLocation texture = new ResourceLocation(Tags.MODID, textures);
            container.mc.getTextureManager().bindTexture(texture);
            Gui.drawModalRectWithCustomSizedTexture(cordX + startX, cordY + startY, textureX, textureY, widthBar, heightBar, 256, 256);
        }
    }
}
