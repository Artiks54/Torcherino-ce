package com.ariks.torcherinoCe.Block.RfMolecular;

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

public class PacketMolecular implements IMessage {

    private BlockPos pos;
    private long energyCollected;
    public PacketMolecular() {}

    public PacketMolecular(BlockPos pos, long energyCollected) {
        this.pos = pos;
        this.energyCollected = energyCollected;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.energyCollected = buf.readLong();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeLong(this.energyCollected);
    }

    public static class Handler implements IMessageHandler<PacketMolecular, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(final PacketMolecular message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                WorldClient world = Minecraft.getMinecraft().world;
                if (world == null) {
                    return;
                }
                TileEntity tileEntity = world.getTileEntity(message.pos);
                if (tileEntity instanceof TileRfMolecular TileRfMolecular) {
                    TileRfMolecular.setEnergyCollected(message.energyCollected);
                }
            });
            return null;
        }
    }
}

