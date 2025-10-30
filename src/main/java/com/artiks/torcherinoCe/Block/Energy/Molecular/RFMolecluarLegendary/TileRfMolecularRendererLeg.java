package com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary;

import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.utility.Config;
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
public class TileRfMolecularRendererLeg extends TileEntitySpecialRenderer<TileRfMolecularLegendary> {

    private static final TObjectIntMap<List<Serializable>> textureSizeCache = new TObjectIntHashMap<>();
    private static final IResourceManager resources = Minecraft.getMinecraft().getResourceManager();
    private static final ResourceLocation PlazmaTextureLocation = new ResourceLocation(Tags.MODID, "textures/prac/plazma_leg.png");
    private static final ResourceLocation ParticlesTextureLocation = new ResourceLocation(Tags.MODID, "textures/prac/particles_leg.png");
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

    public void renderCore(double x, double y, double z) {
        ++this.ticker;
        if (this.ticker > 200) {
            this.ticker = 0;
        }

        int plazmaSize = getTextureSize(PlazmaTextureLocation.getPath(), 64);
        int particleSize = getTextureSize(ParticlesTextureLocation.getPath(), 32);
        float rotationX = ActiveRenderInfo.getRotationX();
        float rotationXZ = ActiveRenderInfo.getRotationXZ();
        float rotationZ = ActiveRenderInfo.getRotationZ();
        float rotationYZ = ActiveRenderInfo.getRotationYZ();
        float rotationXY = ActiveRenderInfo.getRotationXY();
        float scaleCore = 0.45F; // Фиксированный размер сферы
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
        this.bindTexture(PlazmaTextureLocation);
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
        this.bindTexture(ParticlesTextureLocation);
        phase += 24;
        float octParticleSize = (float) (particleSize * 8);
        plasmaEdge = (float) particleSize - 0.01F;
        xBottom = ((float) (phase % 8 * particleSize) + 0.0F) / octParticleSize;
        xTop = ((float) (phase % 8 * particleSize) + plasmaEdge) / octParticleSize;
        yBottom = ((float) (phase / 8 * particleSize) + 0.0F) / octParticleSize;
        yTop = ((float) (phase / 8 * particleSize) + plasmaEdge) / octParticleSize;
        float particleScale = scaleCore + MathHelper.sin((float) this.ticker / 10.0F) * 0.1F; // Сохраняем пульсацию для частиц
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        GlStateManager.disableLighting();
        buffer.pos(posX - rotationX * particleScale - rotationYZ * particleScale,
                        posY - rotationXZ * particleScale,
                        posZ - rotationZ * particleScale - rotationXY * particleScale)
                .tex(xTop, yTop)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX - rotationX * particleScale + rotationYZ * particleScale,
                        posY + rotationXZ * particleScale,
                        posZ - rotationZ * particleScale + rotationXY * particleScale)
                .tex(xTop, yBottom)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX + rotationX * particleScale + rotationYZ * particleScale,
                        posY + rotationXZ * particleScale,
                        posZ + rotationZ * particleScale + rotationXY * particleScale)
                .tex(xBottom, yBottom)
                .color(255, 255, 255, 255)
                .endVertex();
        buffer.pos(posX + rotationX * particleScale - rotationYZ * particleScale,
                        posY - rotationXZ * particleScale,
                        posZ + rotationZ * particleScale - rotationXY * particleScale)
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
    public void render(TileRfMolecularLegendary tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tile.isInvalid() && tile.hasWorld() && Config.BooleanRender) {
            if(tile.isWorking()){
                renderCore(x,y,z);
            }
        }
    }
}