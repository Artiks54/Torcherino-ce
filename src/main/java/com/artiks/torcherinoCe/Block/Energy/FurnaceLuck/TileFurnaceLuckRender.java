package com.artiks.torcherinoCe.Block.Energy.FurnaceLuck;

import com.artiks.torcherinoCe.utility.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import java.util.Objects;

import static net.minecraft.client.renderer.GlStateManager.*;

@SideOnly(Side.CLIENT)
public class TileFurnaceLuckRender extends TileEntitySpecialRenderer<TileLuck> {

    @Override
    public void render(@NotNull TileLuck tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    if (!tile.isInvalid() && tile.hasWorld() && Config.BooleanRender) {
        ItemStack itemStack = new ItemStack(Objects.requireNonNull(Blocks.FURNACE));
        pushMatrix();
        enableRescaleNormal();
        enableBlend();
        blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableStandardItemLighting();
        translate(x + 0.5, y + 0.7, z + 0.5);
        scale(1F, 1F, 1F);
        long gameTime = Minecraft.getMinecraft().world.getWorldTime();
        float rotation = (gameTime * 1.0F) % 360.0F;
        rotate(rotation, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED);
        RenderHelper.disableStandardItemLighting();
        disableBlend();
        disableRescaleNormal();
        popMatrix();
        }
    }
}