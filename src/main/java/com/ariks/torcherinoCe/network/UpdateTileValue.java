package com.ariks.torcherinoCe.network;

import com.ariks.torcherinoCe.Block.MarkerAcceleration.TileMarker;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateTileValue implements IMessage {
    private BlockPos pos;
    private int id;
    private int value;
    public UpdateTileValue() {}
    public UpdateTileValue(BlockPos pos, int id, int value) {
        this.pos = pos;
        this.id = id;
        this.value = value;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.id = buf.readInt();
        this.value = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeInt(id);
        buf.writeInt(value);
    }
    public static class Handler implements IMessageHandler<UpdateTileValue, IMessage> {
        @Override
        public IMessage onMessage(UpdateTileValue message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = message.pos;
            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile instanceof TileMarker TileMarker) {
                        TileMarker.setValue(message.id, message.value);
                    }
                    if (tile instanceof TileTorcherinoBase TileTorcherinoBase) {
                        TileTorcherinoBase.setValue(message.id, message.value);
                        if(message.id == 50){
                            TileTorcherinoBase.AddTimer(message.value);
                        }
                    }
                }
            });
            return null;
        }
    }
}