package com.ariks.torcherino.network;

import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateTilePacket implements IMessage {
    private BlockPos pos;
    private int value;
    public UpdateTilePacket() {}
    public UpdateTilePacket(BlockPos pos, int value) {
        this.value = value;
        this.pos = pos;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.value = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.pos.toLong());
        buf.writeInt(value);
    }
    public static class Handler implements IMessageHandler<UpdateTilePacket, IMessage> {
        @Override
        public IMessage onMessage(UpdateTilePacket message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = message.pos;
            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tile = world.getTileEntity(pos);
                    if(tile instanceof TileTorcherinoBase) {
                        TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tile;
                            int receivedValue = message.value;
                            TileTorcherinoBase.UpdateTile();
                            switch (receivedValue) {
                                case 3: TileTorcherinoBase.ToogleWork();break;
                                case 4: TileTorcherinoBase.ToogleRender();break;
                            }
                    }
                    if (tile instanceof TileTimeManipulator) {
                        TileTimeManipulator TileTimeManipulator = (TileTimeManipulator) tile;
                        int receivedValue = message.value;
                        TileTimeManipulator.UpdateTile();
                        switch (receivedValue) {
                            case 1: TileTimeManipulator.SetDay();break;
                            case 2: TileTimeManipulator.SetNight();break;
                        }
                    }
                }
            });
            return null;
        }
    }
}