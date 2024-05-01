package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.util.Config;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.jetbrains.annotations.NotNull;

public class TileTorcherinoBaseRender extends TileEntitySpecialRenderer<TileTorcherinoBase> {
    @Override
    public void render(@NotNull TileTorcherinoBase tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (tile.hasWorld() && tile.booleanRender > 0 && Config.BooleanRender) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x - 0.0005D, y - 0.0005D, z - 0.0005D);
            GlStateManager.scale(0.999D, 0.999D, 0.999D);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.disableCull();
            if (tile.booleanRender == 1 || tile.booleanRender == 3) {
                float red = Config.Render_Color_R / 255f;
                float green = Config.Render_Color_G / 255f;
                float blue = Config.Render_Color_B / 255f;
                RenderGlobal.drawSelectionBoundingBox(tile.getAABBForRender(), red, green, blue, 1F);
            }
            if (tile.booleanRender == 2 || tile.booleanRender == 3) {
                int i = 61680;
                int j = i % 65536;
                int k = 0;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
                RenderGlobal.renderFilledBox(tile.getAABBForRender(), 1F, 1F, 1F, 0.50F);
            }
            GlStateManager.enableCull();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.popMatrix();
        }
    }
}