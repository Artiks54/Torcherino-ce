//package com.ariks.torcherino.network;
//
//import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
//import io.netty.buffer.ByteBuf;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.WorldClient;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//
//public class UpdateGuiTorcherinoPacket implements IMessage {
//    private BlockPos pos;
//    private int radius, speed, booleanMode,booleanRender;
//    public UpdateGuiTorcherinoPacket() {}
//    public UpdateGuiTorcherinoPacket(BlockPos pos,int booleanMode,int booleanRender,int radius,int speed) {
//        this.pos = pos;
//        this.booleanMode = booleanMode;
//        this.booleanRender = booleanRender;
//        this.radius = radius;
//        this.speed = speed;
//    }
//    @Override
//    public void fromBytes(ByteBuf buf) {
//        this.pos = BlockPos.fromLong(buf.readLong());
//        this.booleanMode = buf.readInt();
//        this.booleanRender = buf.readInt();
//        this.radius = buf.readInt();
//        this.speed = buf.readInt();
//    }
//    @Override
//    public void toBytes(ByteBuf buf) {
//        buf.writeLong(this.pos.toLong());
//        buf.writeInt(this.booleanMode);
//        buf.writeInt(this.booleanRender);
//        buf.writeInt(this.radius);
//        buf.writeInt(this.speed);
//    }
//    public static class Handler implements IMessageHandler<UpdateGuiTorcherinoPacket, IMessage> {
//        @Override
//        public IMessage onMessage(UpdateGuiTorcherinoPacket message, MessageContext ctx) {
//            final BlockPos pos = message.pos;
//            int receivedBooleanMode = message.booleanMode;
//            int receivedRender = message.booleanRender;
//            int receivedRadius = message.radius;
//            int receivedSpeed = message.speed;
//            Minecraft.getMinecraft().addScheduledTask(() -> {
//                WorldClient world = Minecraft.getMinecraft().world;
//                if (world.isBlockLoaded(pos)) {
//                    TileEntity tileEntity = world.getTileEntity(pos);
//                   if (tileEntity instanceof TileTorcherinoBase) {
//                       TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tileEntity;
//                       TileTorcherinoBase.booleanMode = receivedBooleanMode;
//                       TileTorcherinoBase.booleanRender = receivedRender;
//                       TileTorcherinoBase.radius = receivedRadius;
//                       TileTorcherinoBase.speed = receivedSpeed;
//                   }
//               }
//            });
//            return null;
//        }
//    }
//}