package com.artiks.torcherinoCe.Items;

import com.artiks.torcherinoCe.utility.Config;

public enum EnumWands {
    lvl_1(Config.SpeedWand_lvl1),
    lvl_2(Config.SpeedWand_lvl2),
    lvl_3(Config.SpeedWand_lvl3),
    lvl_4(Config.SpeedWand_lvl4),
    lvl_5(Config.SpeedWand_lvl5),
    infinite(Config.SpeedWand_infinite);
    private final int speed;
    EnumWands(int speed){
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
}