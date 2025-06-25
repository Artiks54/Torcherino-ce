package com.ariks.torcherinoCe.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class RenderHandler {

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) return;

        World world = player.world;
        if (!world.isRemote) return;
        ItemStack mainHand = player.getHeldItemMainhand();
        ItemStack offHand = player.getHeldItemOffhand();
        ItemStack markerStack = null;
        if (mainHand.getItem() instanceof itemMarker && mainHand.hasTagCompound()) {
            markerStack = mainHand;
        } else if (offHand.getItem() instanceof itemMarker && offHand.hasTagCompound()) {
            markerStack = offHand;
        }
        if (markerStack != null) {
            NBTTagCompound nbt = markerStack.getTagCompound();
            if (nbt == null) return;
            int x = nbt.getInteger("x");
            int y = nbt.getInteger("y");
            int z = nbt.getInteger("z");
            BlockPos pos = new BlockPos(x, y, z);
            if (world.isAirBlock(pos)) return;
            AxisAlignedBB bb = world.getBlockState(pos).getBoundingBox(world, pos);
            bb = bb.offset(pos);
            double playerX = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.getPartialTicks();
            double playerY = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.getPartialTicks();
            double playerZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.getPartialTicks();
            GlStateManager.pushMatrix();
            GlStateManager.translate(-playerX, -playerY, -playerZ);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.glLineWidth(4.5F);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.minZ).endVertex();
            buffer.pos(bb.maxX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.maxX, bb.maxY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.minY, bb.maxZ).endVertex();
            buffer.pos(bb.minX, bb.maxY, bb.maxZ).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
}