package com.ariks.torcherino.network;

import com.ariks.torcherino.Tiles.TileAcceleration;
import com.ariks.torcherino.Tiles.TileCollectors;
import com.ariks.torcherino.Tiles.TileTimeStorage;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
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
                    if (tile instanceof TileTorcherinoBase) {
                        int receivedValue = message.value;
                        TileTorcherinoBase torcherinoTile = (TileTorcherinoBase) tile;
                        switch (receivedValue) {
                            case 1: torcherinoTile.toggleWork();break;
                            case 2: torcherinoTile.toggleSpeed(true);break;
                            case 3: torcherinoTile.toggleSpeed(false);break;
                            case 4: torcherinoTile.toggleArea(true);break;
                            case 5: torcherinoTile.toggleArea(false);break;
                            case 6: torcherinoTile.toggleRender();break;
                        }
                    }
                    if (tile instanceof TileAcceleration) {
                        int receivedValue = message.value;
                        TileAcceleration TileAcceleration = (TileAcceleration) tile;
                        switch (receivedValue) {
                            case 1: TileAcceleration.toggleWork();break;
                            case 2: TileAcceleration.OpenGuiAceleration = true;break;
                            case 3: TileAcceleration.OpenGuiAceleration = false;break;
                        }
                    }
                    if (tile instanceof TileTimeStorage) {
                        int receivedValue = message.value;
                        TileTimeStorage TileTimeStorage = (TileTimeStorage) tile;
                        switch (receivedValue) {
                            case 1: TileTimeStorage.OpenGuiStorage = true;break;
                            case 2: TileTimeStorage.OpenGuiStorage = false;break;
                        }
                    }
                    if (tile instanceof TileCollectors) {
                        int receivedValue = message.value;
                        TileCollectors TileCollectors = (TileCollectors) tile;
                        switch (receivedValue) {
                            case 1: TileCollectors.OpenGuiCollectors = true;break;
                            case 2: TileCollectors.OpenGuiCollectors = false;break;
                        }
                    }
                }
            });
            return null;
        }
    }
}