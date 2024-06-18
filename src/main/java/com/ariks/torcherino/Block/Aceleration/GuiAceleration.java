package com.ariks.torcherino.Block.Aceleration;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiAceleration extends ExampleGuiContainer {
    private final TileAcceleration tile;
    public GuiAceleration(InventoryPlayer inventory, TileAcceleration tileEntity, EntityPlayer player) {
        super(new ContainerAceleration(inventory,tileEntity,player));
        this.tile = tileEntity;
        SetTexture("textures/gui/gui2.png");
        SetWidth(175);
        SetHeight(167);
        setBooleanBar(true);
        setBooleanTooltip(true);
        SetBarSettings(165,25,5,13,1,170);
    }
    @Override
    public void UpdateBar() {
        SetBarValue(tile.getValue(1),tile.getValue(2));
    }
}