package com.ariks.torcherino.Block.ParticleCollector;


public enum ParticleCollectorEnumLevel {
    lvl_1(1),
    lvl_2(8),
    lvl_3(64);
    private final int amount;
    ParticleCollectorEnumLevel(int amount) {
        this.amount = amount;
    }
    public int getAmount() {return amount;}

}