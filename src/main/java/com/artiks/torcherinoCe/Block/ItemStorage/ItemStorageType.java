package com.artiks.torcherinoCe.Block.ItemStorage;

public enum ItemStorageType {
    lvl1(1_000_000),
    lvl2(64_000_000),
    lvl3(256_000_000),
    lvl4(512_000_000),
    lvl5(Integer.MAX_VALUE);

    private final int capacity;

    ItemStorageType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
