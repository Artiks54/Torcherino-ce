package com.ariks.torcherino.Block.Time.TimeStorage;

import com.ariks.torcherino.Block.Time.TileTime;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import org.jetbrains.annotations.NotNull;

public class TileTimeStorage extends TileTime {

    public TileTimeStorage() {
        super(Config.MaxStorageTimeStorage);
    }

    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_TIME_STORAGE);
    }
}