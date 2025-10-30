package com.artiks.torcherinoCe.network;

import com.artiks.torcherinoCe.Block.ItemStorage.PacketUpdateItemStorage;
import com.artiks.torcherinoCe.network.Packet.TileSyncMessageClient;
import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.network.Packet.UpdateTilePacketButton;
import com.artiks.torcherinoCe.network.Packet.TileSyncMessageServetSlider;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class RegistryNetwork {

    public static int id;
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void Init() {
        network.registerMessage(UpdateTilePacketButton.Handler.class, UpdateTilePacketButton.class, id++, Side.SERVER);
        network.registerMessage(TileSyncMessageServetSlider.Handler.class, TileSyncMessageServetSlider.class, id++, Side.SERVER);
        network.registerMessage(PacketUpdateItemStorage.Handler.class, PacketUpdateItemStorage.class, id++, Side.CLIENT);
        network.registerMessage(TileSyncMessageClient.Handler.class, TileSyncMessageClient.class, id++, Side.CLIENT);
    }
}