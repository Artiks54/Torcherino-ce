package com.artiks.torcherinoCe.Block.Energy.Torcherino;

import com.artiks.torcherinoCe.utility.Config;

public enum TorcherinoEnumLevel {
    lvl_1(Config.Torch_lvl1_S,Config.Torch_lvl1_R,Config.Torch_lvl1_M,Config.TorcherinoEnergyPerTick,Config.TorcherinoEnergyStorage),
    lvl_2(Config.Torch_lvl2_S,Config.Torch_lvl2_R,Config.Torch_lvl2_M,Config.TorcherinoEnergyPerTick,Config.TorcherinoEnergyStorage ),
    lvl_3(Config.Torch_lvl3_S,Config.Torch_lvl3_R,Config.Torch_lvl3_M,Config.TorcherinoEnergyPerTick,Config.TorcherinoEnergyStorage),
    lvl_4(Config.Torch_lvl4_S,Config.Torch_lvl4_R,Config.Torch_lvl4_M,Config.TorcherinoEnergyPerTick,Config.TorcherinoEnergyStorage),
    lvl_5(Config.Torch_lvl5_S,Config.Torch_lvl5_R,Config.Torch_lvl5_M,Config.TorcherinoEnergyPerTick,Config.TorcherinoEnergyStorage),
    c_lvl_1(Config.CTorch_lvl1_S,Config.CTorch_lvl1_R,Config.CTorch_lvl1_M,Config.TorcherinoEnergyPerTick_c,Config.TorcherinoEnergyStorage_c),
    c_lvl_2(Config.CTorch_lvl2_S,Config.CTorch_lvl2_R,Config.CTorch_lvl2_M,Config.TorcherinoEnergyPerTick_c,Config.TorcherinoEnergyStorage_c),
    c_lvl_3(Config.CTorch_lvl3_S,Config.CTorch_lvl3_R,Config.CTorch_lvl3_M,Config.TorcherinoEnergyPerTick_c,Config.TorcherinoEnergyStorage_c),
    c_lvl_4(Config.CTorch_lvl4_S,Config.CTorch_lvl4_R,Config.CTorch_lvl4_M,Config.TorcherinoEnergyPerTick_c,Config.TorcherinoEnergyStorage_c),
    c_lvl_5(Config.CTorch_lvl5_S,Config.CTorch_lvl5_R,Config.CTorch_lvl5_M,Config.TorcherinoEnergyPerTick_c,Config.TorcherinoEnergyStorage_c),
    d_lvl_1(Config.DTorch_lvl1_S,Config.DTorch_lvl1_R,Config.DTorch_lvl1_M,Config.TorcherinoEnergyPerTick_d,Config.TorcherinoEnergyStorage_d),
    d_lvl_2(Config.DTorch_lvl2_S,Config.DTorch_lvl2_R,Config.DTorch_lvl2_M,Config.TorcherinoEnergyPerTick_d,Config.TorcherinoEnergyStorage_d),
    d_lvl_3(Config.DTorch_lvl3_S,Config.DTorch_lvl3_R,Config.DTorch_lvl3_M,Config.TorcherinoEnergyPerTick_d,Config.TorcherinoEnergyStorage_d),
    d_lvl_4(Config.DTorch_lvl4_S,Config.DTorch_lvl4_R,Config.DTorch_lvl4_M,Config.TorcherinoEnergyPerTick_d,Config.TorcherinoEnergyStorage_d),
    d_lvl_5(Config.DTorch_lvl5_S,Config.DTorch_lvl5_R,Config.DTorch_lvl5_M,Config.TorcherinoEnergyPerTick_d,Config.TorcherinoEnergyStorage_d);
    private final int speed,radius,Modes,EnergyPerTick,MaxEnergy;
    TorcherinoEnumLevel(int speed, int radius, int Modes, int EnergyPerTick, int maxEnergy) {
        this.speed = speed;
        this.radius = radius;
        this.Modes = Modes;
        this.EnergyPerTick = EnergyPerTick;
        this.MaxEnergy = maxEnergy;
    }
    public int getEnergyPerTick(){return EnergyPerTick;}
    public int getSpeed() {return speed;}
    public int getRadius() {return radius;}
    public int getModes() {return Modes;}
    public int getMaxEnergy() {return MaxEnergy;}
}