package com.ariks.torcherino.network;

import com.ariks.torcherino.Torcherino;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {
    private static int packetId = 0;

    public static SimpleNetworkWrapper network;
    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Torcherino.MOD_ID);
        network.registerMessage(UpdateTilePacket.Handler.class, UpdateTilePacket.class, packetId++, Side.SERVER);
        network.registerMessage(UpdateGuiPacket.Handler.class, UpdateGuiPacket.class, packetId++, Side.CLIENT);
    }
}
