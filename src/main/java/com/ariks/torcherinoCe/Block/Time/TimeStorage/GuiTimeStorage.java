package com.ariks.torcherinoCe.Block.Time.TimeStorage;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiTimeStorage extends ExampleGuiContainer {

    private final TileTimeStorage tile;

    public GuiTimeStorage(InventoryPlayer inventory, TileTimeStorage tileEntity) {
        super(new ContainerTimeStorage(inventory, tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 8, 16, 0, 19, 159, 16, "textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }

    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getMaxTimeStored());
        setTooltipBar(1, LS.StrTime + " " + formattedValueMin + " / " + formattedValueMax);
        setBarValue(1, tile.getValue(1), tile.getMaxTimeStored());
    }
}