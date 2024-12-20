package com.ariks.torcherino.Block.Time.TimeCollector;

import com.ariks.torcherino.Block.Time.TileTime;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.util.Config;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.NotNull;

public class TileCollectors extends TileTime implements ITickable {
    private int Cooldown;
    public TileCollectors() {
        super(Config.MaxStorageTimeCollector);
    }
    @Override
    public void update() {
        if (!world.isRemote && energyTime.getTimeStored() != energyTime.getMaxTimeStored()) {
            if (++Cooldown >= 20) {
                Cooldown = 0;
                energyTime.producedTime(1);
                this.UpdateTile();
            }
        }
    }
    @Override
    public @NotNull String getGuiID() {
        return String.valueOf(RegistryGui.GUI_COLLECTORS_TIME);
    }
}