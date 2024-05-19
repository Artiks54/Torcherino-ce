package com.ariks.torcherino.util;

public interface ITileTimeStorage {
    void AddTimeStorage(int time);
    void RemoveTimeStorage(int time);
    int GetTimeStorage();
    int GetMaxStorage();
}
