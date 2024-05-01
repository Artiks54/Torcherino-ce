package com.ariks.torcherino.util;

import net.minecraft.entity.player.EntityPlayer;
public interface ITileHas {
    int getValue(int id);
    void setValue(int id, int value);
    boolean isUsableByPlayer(EntityPlayer player);
}