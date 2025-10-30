package com.artiks.torcherinoCe.network.Packet;

import com.artiks.torcherinoCe.network.DataSerializer;
import com.artiks.torcherinoCe.network.ISyncableTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import java.util.Map;

public class TileSyncMessageServetSlider implements IMessage {
    private BlockPos pos;
    private Map<String, Object> syncData;

    @SuppressWarnings("unused")
    public TileSyncMessageServetSlider() {}

    public TileSyncMessageServetSlider(BlockPos pos, Map<String, Object> syncData) {
        this.pos = pos;
        this.syncData = syncData;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        int dataLength = buf.readInt();
        byte[] dataBytes = new byte[dataLength];
        buf.readBytes(dataBytes);
        this.syncData = DataSerializer.deserialize(dataBytes);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        byte[] dataBytes = DataSerializer.serialize(this.syncData);
        buf.writeInt(dataBytes.length);
        buf.writeBytes(dataBytes);
    }

    public static class Handler implements IMessageHandler<TileSyncMessageServetSlider, IMessage> {
        @Override
        public IMessage onMessage(TileSyncMessageServetSlider message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = message.pos;
            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile instanceof ISyncableTile syncableTile) {
                        syncableTile.setSyncData(message.syncData);
                    }
                }
            });
            return null;
        }
    }
}
