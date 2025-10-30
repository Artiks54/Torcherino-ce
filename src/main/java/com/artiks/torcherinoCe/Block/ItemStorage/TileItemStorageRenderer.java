package com.artiks.torcherinoCe.Block.ItemStorage;

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
public class TileItemStorageRenderer extends TileEntitySpecialRenderer<TileItemStorage> {

@Override
public void render(@NotNull TileItemStorage tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    if (!tile.isInvalid() && tile.hasWorld() && Config.BooleanRender) {
        ItemStack itemStack;
        if(tile.getStoredItem().isEmpty()){
            itemStack = new ItemStack(Objects.requireNonNull(Blocks.CHEST));
        } else {
            itemStack = tile.getStoredItem().copy();
        }
        pushMatrix();
        enableRescaleNormal();
        enableBlend();
        blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableStandardItemLighting();
        translate(x + 0.5, y + 0.5, z + 0.5);
        scale(1F, 1F, 1F);
        long gameTime = Minecraft.getMinecraft().world.getWorldTime();
        float rotationSpeed = 1.5f;
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