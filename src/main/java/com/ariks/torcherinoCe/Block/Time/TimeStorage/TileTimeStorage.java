package com.ariks.torcherinoCe.Block.Time.TimeStorage;

import com.ariks.torcherinoCe.Block.Time.TileTime;
import com.ariks.torcherinoCe.Register.RegistryGui;
import com.ariks.torcherinoCe.utility.Config;
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