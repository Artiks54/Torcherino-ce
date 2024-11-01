package com.ariks.torcherino.Items;

public class ItemUpgradeCount extends ItemBase{
    private final int value;
    public ItemUpgradeCount(String name, int value) {
        super(name);
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
