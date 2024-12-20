package com.ariks.torcherino.Block.Time.TimeStorage;

import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import com.ariks.torcherino.Gui.BarComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiTimeStorage extends ExampleGuiContainer {

    private final TileTimeStorage tile;

    public GuiTimeStorage(InventoryPlayer inventory, TileTimeStorage tileEntity, EntityPlayer player) {
        super(new ContainerTimeStorage(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this, 1, 8, 16, 0, 19, 159, 16, "textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }

    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getValue(2));
        setTooltipBar(1, LS.StrTime + " " + formattedValueMin + " / " + formattedValueMax);
        setBarValue(1, tile.getValue(1), tile.getValue(2));
    }
}