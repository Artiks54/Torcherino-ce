package com.ariks.torcherinoCe.Register;

import com.ariks.torcherinoCe.Block.RfMolecular.PacketMolecular;
import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.network.UpdateTileValue;
import com.ariks.torcherinoCe.network.UpdateTilePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class RegistryNetwork {

    public static short id;
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void preInit() {
        network.registerMessage(UpdateTilePacket.Handler.class, UpdateTilePacket.class, id++, Side.SERVER);
        network.registerMessage(UpdateTileValue.Handler.class, UpdateTileValue.class, id++, Side.SERVER);
        network.registerMessage(PacketMolecular.Handler.class, PacketMolecular.class, id++, Side.CLIENT);
    }
}