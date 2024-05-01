package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.util.Config;

public enum TorcherinoEnumLevel {
    lvl_1(Config.Torch_lvl1_S,Config.Torch_lvl1_R,Config.Torch_lvl1_M),
    lvl_2(Config.Torch_lvl2_S,Config.Torch_lvl2_R,Config.Torch_lvl2_M),
    lvl_3(Config.Torch_lvl3_S,Config.Torch_lvl3_R,Config.Torch_lvl3_M),
    lvl_4(Config.Torch_lvl4_S,Config.Torch_lvl4_R,Config.Torch_lvl4_M),
    lvl_5(Config.Torch_lvl5_S,Config.Torch_lvl5_R,Config.Torch_lvl5_M),
    c_lvl_1(Config.CTorch_lvl1_S,Config.CTorch_lvl1_R,Config.CTorch_lvl1_M),
    c_lvl_2(Config.CTorch_lvl2_S,Config.CTorch_lvl2_R,Config.CTorch_lvl2_M),
    c_lvl_3(Config.CTorch_lvl3_S,Config.CTorch_lvl3_R,Config.CTorch_lvl3_M),
    c_lvl_4(Config.CTorch_lvl4_S,Config.CTorch_lvl4_R,Config.CTorch_lvl4_M),
    c_lvl_5(Config.CTorch_lvl5_S,Config.CTorch_lvl5_R,Config.CTorch_lvl5_M),
    d_lvl_1(Config.DTorch_lvl1_S,Config.DTorch_lvl1_R,Config.DTorch_lvl1_M),
    d_lvl_2(Config.DTorch_lvl2_S,Config.DTorch_lvl2_R,Config.DTorch_lvl2_M),
    d_lvl_3(Config.DTorch_lvl3_S,Config.DTorch_lvl3_R,Config.DTorch_lvl3_M),
    d_lvl_4(Config.DTorch_lvl4_S,Config.DTorch_lvl4_R,Config.DTorch_lvl4_M),
    d_lvl_5(Config.DTorch_lvl5_S,Config.DTorch_lvl5_R,Config.DTorch_lvl5_M);
    private final int speed,radius,speedModes;
    TorcherinoEnumLevel(int speed, int radius, int speedModes) {
        this.speed = speed;
        this.radius = radius;
        this.speedModes = speedModes;
    }
    public int getSpeed() {return speed;}
    public int getRadius() {return radius;}
    public int getSpeedModes() {return speedModes;}
}