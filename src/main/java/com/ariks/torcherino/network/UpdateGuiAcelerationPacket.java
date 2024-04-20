package com.ariks.torcherino.network;

import com.ariks.torcherino.Tiles.TileAcceleration;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateGuiAcelerationPacket implements IMessage {
    private BlockPos pos;
    private boolean work;
    private int time;
    public UpdateGuiAcelerationPacket() {}
    public UpdateGuiAcelerationPacket(BlockPos pos, boolean work, int time) {
        this.pos = pos;
        this.work = work;
        this.time = time;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.work = buf.readBoolean();
        this.time = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeBoolean(this.work);
        buf.writeInt(this.time);
    }
    public static class Handler implements IMessageHandler<UpdateGuiAcelerationPacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateGuiAcelerationPacket message, MessageContext ctx) {
            final BlockPos pos = message.pos;
            boolean receivedWork = message.work;
            int receivedTime = message.time;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world.isBlockLoaded(pos)) {
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity instanceof TileAcceleration) {
                        TileAcceleration TileAcceleration = (TileAcceleration) tileEntity;
                        TileAcceleration.TimeCollect = receivedTime;
                        TileAcceleration.BooleanWork = receivedWork;
                    }
                }
            });
            return null;
        }
    }
}