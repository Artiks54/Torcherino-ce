package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.util.Config;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileTorcherinoBaseRender extends TileEntitySpecialRenderer<TileTorcherinoBase> {
    @Override
    public void render( TileTorcherinoBase tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (tile.hasWorld() && tile.getValue(4) > 0 && Config.BooleanRender) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x - 0.0005D, y - 0.0005D, z - 0.0005D);
            GlStateManager.scale(0.999D, 0.999D, 0.999D);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.disableCull();
            if (tile.getValue(4) == 1 || tile.getValue(4) == 3) {
                float red = tile.getValue(8)/ 255f;
                float green = tile.getValue(9)/ 255f;
                float blue = tile.getValue(10) / 255f;
                RenderGlobal.drawSelectionBoundingBox(tile.getAABBForRender(), red, green, blue, 1F);
            }
            if (tile.getValue(4) == 2 || tile.getValue(4) == 3) {
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