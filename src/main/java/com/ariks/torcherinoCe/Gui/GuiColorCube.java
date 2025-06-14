package com.ariks.torcherinoCe.Gui;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GuiColorCube {
    private float width, height;
    private float x, y;
    private float red, green, blue;

    public void setCube(float width, float height, float x, float y, float red, float green, float blue) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public void drawCube() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.color(red, green, blue, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(0, 0, 0).endVertex();
        buffer.pos(width, 0, 0).endVertex();
        buffer.pos(width, height, 0).endVertex();
        buffer.pos(0, height, 0).endVertex();
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
    public void clearCube() {
        Tessellator.getInstance().getBuffer().reset();
    }
}