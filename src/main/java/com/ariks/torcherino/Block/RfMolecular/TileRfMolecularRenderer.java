package com.ariks.torcherino.Block.RfMolecular;

import com.ariks.torcherino.util.Config;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileRfMolecularRenderer extends TileEntitySpecialRenderer<TileRfMolecular> {

    @Override
    public void render(@NotNull TileRfMolecular tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (tile.hasWorld() && tile.getValue(1) >= 0 && Config.BooleanRender) {
            GlStateManager.pushMatrix();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            float progressPercentage = (int) ((tile.energyCollected * 100) / tile.energyRequired);
            float currentValue = 0.15F + (0.02F - 0.15F) * (progressPercentage / 100.0F);
            GlStateManager.translate(x + 0.5, y + 0.52, z + 0.5);
            GlStateManager.scale(currentValue,currentValue,currentValue);
            GlStateManager.color(0.8F, 0.0F, 0.0F, 0.5F);
            drawSphere();
            GlStateManager.enableCull();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            GlStateManager.popMatrix();
        }
    }
    private void drawSphere() {
        for (int i = 0; i < 30; i++) {
            double phi0 = Math.PI * ((i) * 1.0 / 30 - 0.5);
            double phi1 = Math.PI * ((i + 1) * 1.0 / 30 - 0.5);
            GL11.glBegin(GL11.GL_QUAD_STRIP);
            for (int j = 0; j <= 30; j++) {
                double theta = 2 * Math.PI * j / 30;
                double x = Math.cos(theta);
                double y = Math.sin(theta);
                GL11.glNormal3d(x * Math.cos(phi0), y * Math.cos(phi0), Math.sin(phi0));
                GL11.glVertex3d(1.0 * x * Math.cos(phi0), 1.0 * y * Math.cos(phi0), Math.sin(phi0));
                GL11.glNormal3d(x * Math.cos(phi1), y * Math.cos(phi1), Math.sin(phi1));
                GL11.glVertex3d(1.0 * x * Math.cos(phi1), 1.0 * y * Math.cos(phi1), Math.sin(phi1));
            }
            GL11.glEnd();
        }
    }
}