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
    private boolean render;
    private int radius, speed, booleanMode;
    public UpdateGuiPacket() {}
    public UpdateGuiPacket(BlockPos pos, boolean render, int radius, int speed , int booleanMode) {
        this.pos = pos;
        this.render = render;
        this.radius = radius;
        this.speed = speed;
        this.booleanMode = booleanMode;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.render = buf.readBoolean();
        this.radius = buf.readInt();
        this.speed = buf.readInt();
        this.booleanMode = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeBoolean(this.render);
        buf.writeInt(this.radius);
        buf.writeInt(this.speed);
        buf.writeInt(this.booleanMode);
    }
    public static class Handler implements IMessageHandler<UpdateGuiPacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateGuiPacket message, MessageContext ctx) {
            final BlockPos pos = message.pos;
            boolean receivedRender = message.render;
            int receivedRadius = message.radius;
            int receivedSpeed = message.speed;
            int receivedBooleanMode = message.booleanMode;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world.isBlockLoaded(pos)) {
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity instanceof TileTorcherinoBase) {
                        TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tileEntity;
                        TileTorcherinoBase.booleanRender = receivedRender;
                        TileTorcherinoBase.radius = receivedRadius;
                        TileTorcherinoBase.speed = receivedSpeed;
                        TileTorcherinoBase.booleanMode = receivedBooleanMode;
                    }
                }
            });
            return null;
        }
    }
}