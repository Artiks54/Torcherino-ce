package com.ariks.torcherino.network;

import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
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
    public UpdateTilePacket(BlockPos pos,int value) {
        this.value = value;
        this.pos = pos;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = CordUtils.readBlockPos(buf);
        this.value = buf.readInt();
    }
    @Override
    public void toBytes(ByteBuf buf) {
        CordUtils.writeBlockPos(buf, this.pos);
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
                        if (receivedValue == 1) {
                            ((TileTorcherinoBase) tile).toggleWork();
                            Torcherino.logger.debug("Send packet update tile Work");
                        }
                        if (receivedValue == 2) {
                            ((TileTorcherinoBase) tile).toggleSpeed();
                            Torcherino.logger.debug("Send packet update tile Speed");
                        }
                        if (receivedValue == 3) {
                            ((TileTorcherinoBase) tile).toggleArea();
                            Torcherino.logger.debug("Send packet update tile Area");
                        }
                        if (receivedValue == 4) {
                            ((TileTorcherinoBase) tile).toggleSpawnPrac();
                            Torcherino.logger.debug("Send packet update tile Spawn Particle");
                        }
                        if (receivedValue == 5) {
                            ((TileTorcherinoBase) tile).toggleParticle();
                            Torcherino.logger.debug("Send packet update tile Particle");
                        }
                        if (receivedValue == 6) {
                            ((TileTorcherinoBase) tile).toggleStepCount();
                            Torcherino.logger.debug("Send packet update tile Steep");
                        }
                    }
                }
            }
            );
            return null;
        }
    }
}
