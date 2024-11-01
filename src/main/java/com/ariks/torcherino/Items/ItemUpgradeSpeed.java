package com.ariks.torcherino.Items;

public class ItemUpgradeSpeed extends ItemBase{
    private final int value;
    public ItemUpgradeSpeed(String name, int value) {
        super(name);
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
