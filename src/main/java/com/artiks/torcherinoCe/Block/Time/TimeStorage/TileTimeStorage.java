package com.artiks.torcherinoCe.Block.Time.TimeStorage;

import com.artiks.torcherinoCe.Block.Time.TileTime;
import com.artiks.torcherinoCe.Register.RegistryGui;
import com.artiks.torcherinoCe.utility.Config;
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