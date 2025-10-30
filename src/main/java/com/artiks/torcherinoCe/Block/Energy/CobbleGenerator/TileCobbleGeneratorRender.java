package com.artiks.torcherinoCe.Block.Energy.CobbleGenerator;

import com.artiks.torcherinoCe.utility.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import static net.minecraft.client.renderer.GlStateManager.*;
import static net.minecraft.client.renderer.GlStateManager.blendFunc;
import static net.minecraft.client.renderer.GlStateManager.disableBlend;
import static net.minecraft.client.renderer.GlStateManager.disableRescaleNormal;
import static net.minecraft.client.renderer.GlStateManager.popMatrix;
import static net.minecraft.client.renderer.GlStateManager.rotate;
import static net.minecraft.client.renderer.GlStateManager.scale;
import static net.minecraft.client.renderer.GlStateManager.translate;


@SideOnly(Side.CLIENT)
public class TileCobbleGeneratorRender extends TileEntitySpecialRenderer<TileCobbleGenerator> {

    @Override
    public void render(@NotNull TileCobbleGenerator tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!tile.isInvalid() && tile.hasWorld() && Config.BooleanRender) {
            ItemStack itemStack = tile.syncRender();
            pushMatrix();
            enableRescaleNormal();
            enableBlend();
            blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderHelper.enableStandardItemLighting();
            translate(x + 0.5, y + 0.55, z + 0.5);
            scale(0.45F, 0.45F, 0.45F);
            long gameTime = Minecraft.getMinecraft().world.getWorldTime();
            float rotationSpeed = 2.0f;
            float rotation = (gameTime * rotationSpeed) % 360.0F;
            rotate(rotation, 0.0F, 1.0F, 0.0F);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            disableBlend();
            disableRescaleNormal();
            popMatrix();
        }
    }
}