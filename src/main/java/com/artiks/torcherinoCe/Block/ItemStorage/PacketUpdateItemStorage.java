package com.artiks.torcherinoCe.Block.ItemStorage;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketUpdateItemStorage implements IMessage {
    private BlockPos pos;
    private ItemStack storedItem;

    @SuppressWarnings("unused")
    public PacketUpdateItemStorage() {}

    public PacketUpdateItemStorage(BlockPos pos, ItemStack storedItem) {
        this.pos = pos;
        this.storedItem = storedItem;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.storedItem = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        ByteBufUtils.writeItemStack(buf, this.storedItem);
    }

    public static class Handler implements IMessageHandler<PacketUpdateItemStorage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketUpdateItemStorage message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileItemStorage tile = (TileItemStorage) Minecraft.getMinecraft().world.getTileEntity(message.pos);
                if (tile != null) {
                    tile.setStoredItem(message.storedItem);
                    Minecraft.getMinecraft().world.markBlockRangeForRenderUpdate(message.pos, message.pos);
                }
            });
            return null;
        }
    }
}