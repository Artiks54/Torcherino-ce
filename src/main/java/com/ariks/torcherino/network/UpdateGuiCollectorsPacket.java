package com.ariks.torcherino.network;

import com.ariks.torcherino.Tiles.TileAcceleration;
import com.ariks.torcherino.Tiles.TileCollectors;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateGuiCollectorsPacket implements IMessage {
    private BlockPos pos;
    private int time;
    public UpdateGuiCollectorsPacket() {}
    public UpdateGuiCollectorsPacket(BlockPos pos, int time) {
        this.pos = pos;
        this.time = time;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.time = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeInt(this.time);
    }
    public static class Handler implements IMessageHandler<UpdateGuiCollectorsPacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateGuiCollectorsPacket message, MessageContext ctx) {
            final BlockPos pos = message.pos;
            int receivedTime = message.time;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world.isBlockLoaded(pos)) {
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity instanceof TileCollectors) {
                        TileCollectors TileCollectors = (TileCollectors) tileEntity;
                        TileCollectors.TimeCollect = receivedTime;
                    }
                }
            });
            return null;
        }
    }
}