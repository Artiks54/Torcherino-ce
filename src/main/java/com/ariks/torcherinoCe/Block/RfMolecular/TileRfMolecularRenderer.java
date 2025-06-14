package com.ariks.torcherinoCe.Block.RfMolecular;

import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.utility.Config;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SideOnly(Side.CLIENT)
public class TileRfMolecularRenderer extends TileEntitySpecialRenderer<TileRfMolecular> {

    private static final TObjectIntMap<List<Serializable>> textureSizeCache = new TObjectIntHashMap<>();
    private static final IResourceManager resources = Minecraft.getMinecraft().getResourceManager();
    private static final ResourceLocation plazmaTextloc = new ResourceLocation(Tags.MODID, "textures/prac/plazma.png");
    private static final ResourceLocation particlesTextloc = new ResourceLocation(Tags.MODID, "textures/prac/particles.png");
    public int ticker;

    public static int getTextureSize(String s, int dv) {
        if (textureSizeCache.containsKey(Arrays.asList(s, dv))) {
            return textureSizeCache.get(Arrays.asList(s, dv));
        } else {
            try {
                InputStream inputstream = resources.getResource(new ResourceLocation(Tags.MODID, s)).getInputStream();
                BufferedImage image = ImageIO.read(inputstream);
                if (image != null) {
                    int size = image.getWidth() / dv;
                    textureSizeCache.put(Arrays.asList(s, dv), size);
                    return size;
                }
            } catch (Exception var4) {
                System.err.println("Failed to load texture: " + s + " - " + var4.getMessage());
            }
            return 16;
        }
    }
    public void renderCore(TileRfMolecular tile, double x, double y, double z, float partialTicks) {
        ++this.ticker;
        if (this.ticker > 160) {
            this.ticker = 0;
        }
        float progressPercentage = (float) (tile.getValue(5) * 100) / tile.getValue(6);
        float maxScale = 0.45F;
        float minScale = 0.01F;
        float getScale = maxScale - (progressPercentage / 100.0F) * (maxScale - minScale);
        int plazmaSize = getTextureSize(plazmaTextloc.getPath(), 64);
        int particleSize = getTextureSize(particlesTextloc.getPath(), 32);
        float rotationX = ActiveRenderInfo.getRotationX();
        float rotationXZ = ActiveRenderInfo.getRotationXZ();
        float rotationZ = ActiveRenderInfo.getRotationZ();
        float rotationYZ = ActiveRenderInfo.getRotationYZ();
        float rotationXY = ActiveRenderInfo.getRotationXY();
        float scaleCore = getScale;
        float posX = (float) x + 0.5F;
        float posY = (float) y + 0.5F;
        float posZ = (float) z + 0.5F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Color colour = new Color(12648447);
        // Рендер плазмы
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        this.bindTexture(plazmaTextloc);
        int phase = this.ticker % 16;
        float quadPlazmaSize = (float) (plazmaSize * 4);
        float plasmaEdge = (float) plazmaSize - 0.01F;
        float xBottom = ((float) (phase % 4 * plazmaSize) + 0.0F) / quadPlazmaSize;
        float xTop = ((float) (phase % 4 * plazmaSize) + plasmaEdge) / quadPlazmaSize;
        float yBottom = ((float) (phase / 4 * plazmaSize) + 0.0F) / quadPlazmaSize;
        float yTop = ((float) (phase / 4 * plazmaSize) + plasmaEdge) / quadPlazmaSize;
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(posX - rotationX * scaleCore - rotationYZ * scaleCore,
                        posY - rotationXZ * scaleCore,
                        posZ - rotationZ * scaleCore - rotationXY * scaleCore)
                .tex(xTop, yTop)
                .color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255)
                .endVertex();
        buffer.pos(posX - rotationX * scaleCore + rotationYZ * scaleCore,
                        posY + rotationXZ * scaleCore,
                        posZ - rotationZ * scaleCore + rotationXY * scaleCore)
                .tex(xTop, yBottom)
                .color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255)
                .endVertex();
        buffer.pos(posX + rotationX * scaleCore + rotationYZ * scaleCore,
                        posY + rotationXZ * scaleCore,
                        posZ + rotationZ * scaleCore + rotationXY * scaleCore)
                .tex(xBottom, yBottom)
                .color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255)
                .endVertex();
        buffer.pos(posX + rotationX * scaleCore - rotationYZ * scaleCore,
                        posY - rotationXZ * scaleCore,
                        posZ + rotationZ * scaleCore - rotationXY * scaleCore)
                .tex(xBottom, yTop)
                .color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255)
                .endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
        // Рендер частиц
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        this.bindTexture(particlesTextloc);
        phase += 24;
        float octParticleSize = (float) (particleSize * 8);
        plasmaEdge = (float) particleSize - 0.01F;
        xBottom = ((float) (phase % 8 * particleSize) + 0.0F) / octParticleSize;
        xTop = ((float) (phase % 8 * particleSize) + plasmaEdge) / octParticleSize;
        yBottom = ((float) (phase / 8 * particleSize) + 0.0F) / octParticleSize;
        yTop = ((float) (phase / 8 * particleSize) + plasmaEdge) / octParticleSize;
        scaleCore = getScale + MathHelper.sin((float) this.ticker / 10.0F) * 0.1F;
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        GlStateManager.disableLighting();
        buffer.pos(posX - rotationX * scaleCore - rotationYZ * scaleCore,
                        posY - rotationXZ * scaleCore,
                        posZ - rotationZ * scaleCore - rotationXY * scaleCore)
                .tex(xTop, yTop)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX - rotationX * scaleCore + rotationYZ * scaleCore,
                        posY + rotationXZ * scaleCore,
                        posZ - rotationZ * scaleCore + rotationXY * scaleCore)
                .tex(xTop, yBottom)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX + rotationX * scaleCore + rotationYZ * scaleCore,
                        posY + rotationXZ * scaleCore,
                        posZ + rotationZ * scaleCore + rotationXY * scaleCore)
                .tex(xBottom, yBottom)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX + rotationX * scaleCore - rotationYZ * scaleCore,
                        posY - rotationXZ * scaleCore,
                        posZ + rotationZ * scaleCore - rotationXY * scaleCore)
                .tex(xBottom, yTop)
                .color(255, 255, 255, 255)
                .endVertex();
        GlStateManager.enableLighting();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
    @Override
    public void render(TileRfMolecular tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tile.isInvalid() && tile.hasWorld() && tile.getValue(1) >= 0 && Config.BooleanRender) {
            renderCore(tile, x, y, z, partialTicks);
        }
    }
}