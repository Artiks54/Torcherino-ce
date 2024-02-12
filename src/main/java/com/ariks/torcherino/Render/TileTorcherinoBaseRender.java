package com.ariks.torcherino.Render;

import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

@SideOnly(Side.CLIENT)
public class TileTorcherinoBaseRender extends TileEntitySpecialRenderer<TileTorcherinoBase> {
    @Override
    public void render(@NotNull TileTorcherinoBase tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tile.hasWorld() || !tile.booleanRender)
            return;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 0.0005D, y - 0.0005D, z - 0.0005D);
        GlStateManager.scale(0.999D, 0.999D, 0.999D);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.disableCull();
        RenderGlobal.drawSelectionBoundingBox(tile.getAABBForRender(), 1F, 1F, 0F, 1F);
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
}
