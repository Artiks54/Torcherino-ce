package com.ariks.torcherino.util;

public interface ITileTimeStorage {
    void addTimeStorage(int time);
    void removeTimeStorage(int time);
    int getTimeStorage();
    int getMaxStorage();
}
