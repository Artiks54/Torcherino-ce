package com.ariks.torcherinoCe.Items;

import com.ariks.torcherinoCe.utility.Config;

public enum EnumStorage {
        lvl_1(Config.Stored_Time_Bottle_Lvl_1),
        lvl_2(Config.Stored_Time_Bottle_Lvl_2),
        lvl_3(Config.Stored_Time_Bottle_Lvl_3),
        lvl_4(Config.Stored_Time_Bottle_Lvl_4),
        lvl_5(Config.Stored_Time_Bottle_Lvl_5),
        infinite(Integer.MAX_VALUE);
        private final int storage;
    EnumStorage(int storage){
        this.storage = storage;
    }
    public int getStorage() {
        return storage;
    }
}