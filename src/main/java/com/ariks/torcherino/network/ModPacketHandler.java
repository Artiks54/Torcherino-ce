package com.ariks.torcherino.network;

import com.ariks.torcherino.Torcherino;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {
    public static SimpleNetworkWrapper network;
    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Torcherino.MOD_ID);
        //Server
        network.registerMessage(UpdateTilePacket.Handler.class, UpdateTilePacket.class, 1, Side.SERVER);
    }
}