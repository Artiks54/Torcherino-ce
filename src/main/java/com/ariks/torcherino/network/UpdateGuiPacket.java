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
    private boolean work, spawnPrac;
    private int radius, speed, modprac;
    private double stepcount;

    public UpdateGuiPacket() {
    }

    public UpdateGuiPacket(BlockPos pos, boolean work, boolean spawnPrac, int radius, int speed, int modprac, double stepcount) {
        this.pos = pos;
        this.work = work;
        this.spawnPrac = spawnPrac;
        this.radius = radius;
        this.speed = speed;
        this.modprac = modprac;
        this.stepcount = stepcount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = CordUtils.readBlockPos(buf);
        this.work = buf.readBoolean();
        this.spawnPrac = buf.readBoolean();
        this.radius = buf.readInt();
        this.speed = buf.readInt();
        this.modprac = buf.readInt();
        this.stepcount = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        CordUtils.writeBlockPos(buf, this.pos);
        buf.writeBoolean(work);
        buf.writeBoolean(spawnPrac);
        buf.writeInt(radius);
        buf.writeInt(speed);
        buf.writeInt(modprac);
        buf.writeDouble(stepcount);
    }

    public static class Handler implements IMessageHandler<UpdateGuiPacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateGuiPacket message, MessageContext ctx) {
            final BlockPos pos = message.pos;
            boolean receivedWork = message.work;
            boolean receivedSpawnPrac = message.spawnPrac;
            int receivedRadius = message.radius;
            int receivedSpeed = message.speed;
            int receivedModPrac = message.modprac;
            double receivedStepCount = message.stepcount;
            WorldClient world = Minecraft.getMinecraft().world;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tileEntity = world.getTileEntity(pos);
                    if (tileEntity instanceof TileTorcherinoBase) {
                        TileTorcherinoBase tileCounter = (TileTorcherinoBase) tileEntity;
                        tileCounter.booleanWork = receivedWork;
                        tileCounter.booleanSpawnPrac = receivedSpawnPrac;
                        tileCounter.radius = receivedRadius;
                        tileCounter.speed = receivedSpeed;
                        tileCounter.modPrac = receivedModPrac;
                        tileCounter.stepCount = receivedStepCount;
                    }
                }
            });
            return null;
        }
    }
}