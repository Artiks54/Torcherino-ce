package com.artiks.torcherinoCe.Gui;

import com.artiks.torcherinoCe.Block.Core.TileExampleContainer;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.network.Packet.UpdateTilePacketButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.jetbrains.annotations.NotNull;

public class GuiButtonImage extends GuiButtonExt {

    private static final ResourceLocation icon = new ResourceLocation(Tags.MODID, "textures/gui/gui_texture_button.png");
    private final int value;
    private int textureX,textureY;
    private final TileExampleContainer tile;

    public GuiButtonImage(TileExampleContainer tile, int buttonId, int x, int y, int Value) {
        super(buttonId, x, y, 20, 20, null);
        this.value = Value;
        this.tile = tile;
    }
    @Override
    public boolean mousePressed(@NotNull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            if (value != 0) {
                RegistryNetwork.network.sendToServer(new UpdateTilePacketButton(tile.getPos(), value));
                return true;
            }
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }
    public void setTexture(int textureX,int textureY) {
        this.textureX = textureX;
        this.textureY = textureY;
    }
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float p) {
        if (this.visible) {
            super.drawButton(mc, mouseX, mouseY, p);
            mc.getTextureManager().bindTexture(icon);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x + 2, this.y + 2, textureX, textureY, 16, 16);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
