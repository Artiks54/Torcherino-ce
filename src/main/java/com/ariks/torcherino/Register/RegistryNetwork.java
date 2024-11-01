package com.ariks.torcherino.Register;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.Gui.UpdateSlider;
import com.ariks.torcherino.Gui.UpdateTilePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class RegistryNetwork {
    public static SimpleNetworkWrapper network;
    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Torcherino.MOD_ID);
        network.registerMessage(UpdateTilePacket.Handler.class, UpdateTilePacket.class, 1, Side.SERVER);
        network.registerMessage(UpdateSlider.Handler.class, UpdateSlider.class, 2, Side.SERVER);
    }
}