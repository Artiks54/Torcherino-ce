package com.artiks.torcherinoCe.network;

import net.minecraft.entity.player.EntityPlayer;
import java.util.Map;

public interface ISyncableTile {
    void getSyncData(Map<String, Object> data);
    void setSyncData(Map<String, Object> data);
    boolean isUsableByPlayer(EntityPlayer player);
}
