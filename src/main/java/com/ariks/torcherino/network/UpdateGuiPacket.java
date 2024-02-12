package com.ariks.torcherino.network;

import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateGuiPacket implements IMessage {
    private BlockPos pos;
    private boolean work, render;
    private int radius, speed;
    public UpdateGuiPacket() {}
    public UpdateGuiPacket(BlockPos pos, boolean work, boolean render, int radius, int speed) {
        this.pos = pos;
        this.work = work;
        this.render = render;
        this.radius = radius;
        this.speed = speed;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.work = buf.readBoolean();
        this.render = buf.readBoolean();
        this.radius = buf.readInt();
        this.speed = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeBoolean(this.work);
        buf.writeBoolean(this.render);
        buf.writeInt(this.radius);
        buf.writeInt(this.speed);
    }
    public static class Handler implements IMessageHandler<UpdateGuiPacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateGuiPacket message, MessageContext ctx) {
            final BlockPos pos = message.pos;
            boolean receivedWork = message.work;
            boolean receivedRender = message.render;
            int receivedRadius = message.radius;
            int receivedSpeed = message.speed;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world.isBlockLoaded(pos)) {
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity instanceof TileTorcherinoBase) {
                        TileTorcherinoBase tileCounter = (TileTorcherinoBase) tileEntity;
                        tileCounter.booleanWork = receivedWork;
                        tileCounter.booleanRender = receivedRender;
                        tileCounter.radius = receivedRadius;
                        tileCounter.speed = receivedSpeed;
                    }
                }
            });
            return null;
        }
    }
}