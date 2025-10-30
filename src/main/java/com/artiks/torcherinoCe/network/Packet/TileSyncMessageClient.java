package com.artiks.torcherinoCe.network.Packet;

import com.artiks.torcherinoCe.network.DataSerializer;
import com.artiks.torcherinoCe.network.ISyncableTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileSyncMessageClient implements IMessage {
    private BlockPos pos;
    private byte[] data;

    @SuppressWarnings("unused")
    public TileSyncMessageClient() {}

    public TileSyncMessageClient(BlockPos pos, byte[] data) {
        this.pos = pos;
        this.data = data;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(data.length);
        buf.writeBytes(data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        data = new byte[buf.readInt()];
        buf.readBytes(data);
    }

    public static class Handler implements IMessageHandler<TileSyncMessageClient, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(final TileSyncMessageClient message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world == null) return;
                TileEntity te = world.getTileEntity(message.pos);
                if (te instanceof ISyncableTile) {
                    ((ISyncableTile) te).setSyncData(DataSerializer.deserialize(message.data));
                }
            });
            return null;
        }
    }
}

