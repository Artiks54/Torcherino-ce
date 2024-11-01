package com.ariks.torcherino.Gui;

import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateSlider implements IMessage {
    private BlockPos pos;
    private int id;
    private int value;
    public UpdateSlider() {}
    public UpdateSlider(BlockPos pos, int id,int value) {
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
    public static class Handler implements IMessageHandler<UpdateSlider, IMessage> {
        @Override
        public IMessage onMessage(UpdateSlider message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = message.pos;
            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile instanceof TileTorcherinoBase) {
                        TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tile;
                        TileTorcherinoBase.setValue(message.id, message.value);
                        TileTorcherinoBase.UpdateTile();
                    }
                }
            });
            return null;
        }
    }
}