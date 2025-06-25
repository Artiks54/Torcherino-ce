package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.utility.Config;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class TileTorcherinoBaseRender extends TileEntitySpecialRenderer<TileTorcherinoBase> {
    private static final class RenderSettings {
        // Основные настройки
        public static final float LINE_WIDTH = 4.5F;       // 1.0-10.0 толщина линий
        // Анимационные настройки
        public static final float ANIMATION_SPEED = 5.0F;   // 0.1-5.0 скорость анимации
        // Настройки эффектов
        public static final float CORNER_SIZE = 0.25F;       // 0.1-0.5 размер углов
        public static final float WAVE_AMPLITUDE = 0.6F;    // 0.1-1.0 амплитуда волн
    }
    @Override
    public void render(TileTorcherinoBase tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tile.isInvalid() && tile.hasWorld() && tile.getValue(11) > 0 && Config.BooleanRender) {
            //Init
            setupRenderState(x, y, z);
            //Render-mode
            int renderMode = tile.getValue(11);
            long time = tile.getWorld().getTotalWorldTime();
            //Color
            float hueLines = (float) tile.getValue(8) / 800  * 0.9f;
            float hueCube = (float) tile.getValue(9) / 800  * 0.9f;
            Color colorLines = Color.getHSBColor(hueLines, 1.0f, 1.0f);
            Color colorCube = Color.getHSBColor(hueCube, 1.0f, 1.0f);
            //Alpha
            int as = tile.getValue(10);
            float Alpha = 1.0F - (as / 100.0F);
            // Рендер в зависимости от режима
            switch (renderMode) {
                case 1:
                    renderLines(tile,colorLines,Alpha);
                    break;
                case 2:
                    renderFill(tile,colorCube,Alpha);
                    break;
                case 3:
                    renderFill(tile,colorCube,Alpha);
                    renderLines(tile,colorLines,Alpha);
                    break;
                case 4:
                    renderWaveEffect(tile, time,colorLines,Alpha);
                    break;
                case 5:
                    renderCornerMarkers(tile,colorCube,Alpha);
                    break;
            }
            restoreRenderState();
        }
    }
    private void setupRenderState(double x, double y, double z) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x - 0.0005D, y - 0.0005D, z - 0.0005D);
        GlStateManager.scale(0.999D, 0.999D, 0.999D);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
    }
    private void restoreRenderState() {
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
    // 1. Обычные линии
    private void renderLines(TileTorcherinoBase tile,Color color, float alpha) {
        GL11.glLineWidth(RenderSettings.LINE_WIDTH);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;
        RenderGlobal.drawSelectionBoundingBox(tile.getAABBForRender(), red, green, blue, alpha);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    // 2. Заливка
    private void renderFill(TileTorcherinoBase tile,Color color, float alpha) {
        int i = 61680;
        int j = i % 65536;
        int k = 0;
        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
        RenderGlobal.renderFilledBox(tile.getAABBForRender(), red, green, blue, alpha);
    }
    // 4. Волновой эффект
    private void renderWaveEffect(TileTorcherinoBase tile, long time, Color color, float alpha) {
        AxisAlignedBB aabb = tile.getAABBForRender();
        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;
        double centerX = (aabb.minX + aabb.maxX) / 2;
        double centerY = (aabb.minY + aabb.maxY) / 2;
        double centerZ = (aabb.minZ + aabb.maxZ) / 2;
        float waveTime = time * RenderSettings.ANIMATION_SPEED * 0.005F;
        int waveCount = 5;
        GL11.glLineWidth(2.0F);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        for (int wave = 0; wave < waveCount; wave++) {
            float waveRadius = (wave + (waveTime % 1.0F)) * RenderSettings.WAVE_AMPLITUDE;
            float waveAlpha = alpha * (1.0F - (wave / (float) waveCount));
            GL11.glColor4f(red, green, blue, waveAlpha);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            int segments = 32;
            for (int i = 0; i < segments; i++) {
                double angle = 2 * Math.PI * i / segments;
                double x = centerX + Math.cos(angle) * waveRadius;
                double z = centerZ + Math.sin(angle) * waveRadius;
                GL11.glVertex3d(x, centerY, z);
            }
            GL11.glEnd();
        }
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    // 5. Только углы
    private void renderCornerMarkers(TileTorcherinoBase tile, Color color, float alpha) {
        AxisAlignedBB aabb = tile.getAABBForRender();
        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;
        double[][] corners = {
                {aabb.minX, aabb.minY, aabb.minZ},
                {aabb.maxX, aabb.minY, aabb.minZ},
                {aabb.minX, aabb.maxY, aabb.minZ},
                {aabb.maxX, aabb.maxY, aabb.minZ},
                {aabb.minX, aabb.minY, aabb.maxZ},
                {aabb.maxX, aabb.minY, aabb.maxZ},
                {aabb.minX, aabb.maxY, aabb.maxZ},
                {aabb.maxX, aabb.maxY, aabb.maxZ}
        };
        for (double[] corner : corners) {
            AxisAlignedBB cornerBox = new AxisAlignedBB(
                    corner[0] - RenderSettings.CORNER_SIZE, corner[1] - RenderSettings.CORNER_SIZE, corner[2] - RenderSettings.CORNER_SIZE,
                    corner[0] + RenderSettings.CORNER_SIZE, corner[1] + RenderSettings.CORNER_SIZE, corner[2] + RenderSettings.CORNER_SIZE
            );
            RenderGlobal.renderFilledBox(cornerBox, red, green, blue, alpha);
        }
    }
}