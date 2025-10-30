package com.artiks.torcherinoCe.network.Packet;

import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.TileMarker;
import com.artiks.torcherinoCe.Block.Energy.Molecular.TileRfMolecular;
import com.artiks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.TileEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.TileTimeManipulator;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TileTorcherinoBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateTilePacketButton implements IMessage {
    private BlockPos pos;
    private int value;
    @SuppressWarnings("unused")
    public UpdateTilePacketButton() {}

    public UpdateTilePacketButton(BlockPos pos, int value) {
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

    public static class Handler implements IMessageHandler<UpdateTilePacketButton, IMessage> {
        @Override
        public IMessage onMessage(UpdateTilePacketButton message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = message.pos;
            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(pos)) {
                    TileEntity tile = world.getTileEntity(pos);
                    if(tile instanceof TileTorcherinoBase TileTorcherinoBase) {
                        switch (message.value) {
                            case 1: TileTorcherinoBase.ToggleWork();break;
                            case 2: TileTorcherinoBase.ToggleRender();break;
                            case 3: TileTorcherinoBase.StartTimer();break;
                            case 4: TileTorcherinoBase.ResetTimer();break;
                            case 5: TileTorcherinoBase.AddTimer(1);break;
                            case 6: TileTorcherinoBase.AddTimer(5);break;
                            case 7: TileTorcherinoBase.AddTimer(10);break;
                            case 8: TileTorcherinoBase.AddTimer(50);break;
                            case 9: TileTorcherinoBase.AddTimer(100);break;
                            case 10: TileTorcherinoBase.AddTimer(500);break;
                        }
                    }
                    if(tile instanceof TileMarker TileMarker) {
                        if (message.value == 1) {
                            TileMarker.ToggleWork();
                        }
                    }
                    if (tile instanceof TileTimeManipulator TileTimeManipulator) {
                        switch (message.value) {
                            case 1: TileTimeManipulator.SetDay();break;
                            case 2: TileTimeManipulator.SetNight();break;
                            case 3: TileTimeManipulator.SetRain();break;
                            case 4: TileTimeManipulator.ClearRain();break;
                        }
                    }
                    if (tile instanceof TileEnergyTimeManipulator TileEnergyTimeManipulator) {
                        switch (message.value) {
                            case 1: TileEnergyTimeManipulator.SetDay();break;
                            case 2: TileEnergyTimeManipulator.SetNight();break;
                            case 3: TileEnergyTimeManipulator.SetRain();break;
                            case 4: TileEnergyTimeManipulator.ClearRain();break;
                        }
                    }
                    if (tile instanceof TileAcceleration TileAcceleration) {
                        switch (message.value) {
                            case 1: TileAcceleration.ToggleWork();break;
                            case 2: TileAcceleration.ToggleRender();break;
                        }
                    }
                    if (tile instanceof TileRfMolecular TileRfMolecular) {
                        switch (message.value) {
                            case 1: TileRfMolecular.ToggleWork(); break;
                            case 2: TileRfMolecular.SwitchTexture(); break;
                            case 3: TileRfMolecular.ToggleStackMode(); break;
                        }
                    }
                }
            });
            return null;
        }
    }
}