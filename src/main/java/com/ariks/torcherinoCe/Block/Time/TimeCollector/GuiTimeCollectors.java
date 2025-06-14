package com.ariks.torcherinoCe.Block.Time.TimeCollector;

import com.ariks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.ariks.torcherinoCe.Gui.BarComponent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiTimeCollectors extends ExampleGuiContainer {
    private final TileCollectors tile;

    public GuiTimeCollectors(InventoryPlayer inventory, TileCollectors tileEntity) {
        super(new ContainerTimeCollectors(inventory,tileEntity));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_time.png", 175, 167);
        BarComponent barComponent = new BarComponent(this,1,8,16,0,19,159,16,"textures/gui/gui_component.png");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        String formattedValueMin = numberFormat.format(tile.getValue(1));
        String formattedValueMax = numberFormat.format(tile.getMaxTimeStored());
        setTooltipBar(1,LS.StrTime+ " " +formattedValueMin+ " / " + formattedValueMax);
        setBarValue(1,tile.getValue(1),tile.getMaxTimeStored());
    }
}