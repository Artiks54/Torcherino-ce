package com.ariks.torcherino.Block.TimeCollector;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTimeCollectors extends ExampleGuiContainer {
    private final TileCollectors tile;
    public GuiTimeCollectors(InventoryPlayer inventory, TileCollectors tileEntity, EntityPlayer player) {
        super(new ContainerTimeCollectors(inventory,tileEntity,player));
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