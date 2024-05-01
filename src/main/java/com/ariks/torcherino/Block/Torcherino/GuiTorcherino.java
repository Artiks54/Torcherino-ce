package com.ariks.torcherino.Block.Torcherino;

import com.ariks.torcherino.Block.ExampleGuiContainer;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGuiContainer {
    private final TileTorcherinoBase tile;
    private String StrWork,StrRadius,StrSpeed,StrRender;
    public GuiTorcherino(InventoryPlayer inventory, TileTorcherinoBase tileEntity, EntityPlayer player) {
        super(new ContainerTorcherino(inventory, tileEntity,player));
        this.tile = tileEntity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.updateButton();
        int posX = (this.xSize / 2) -121;
        this.fontRenderer.drawString(tile.getBlockType().getLocalizedName(), posX+5,10, Color.WHITE.getRGB());
        this.fontRenderer.drawString(LS.StrTextIncrease, posX+5,20, Color.WHITE.getRGB());
        this.fontRenderer.drawString(LS.StrTextDecrease, posX+5,30, Color.WHITE.getRGB());
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1, x+10, y+70, 115, 20,StrWork));
        buttonList.add(new GuiButton(4, x+10, y+95, 115, 20,StrRender));
        buttonList.add(new GuiButton(2, x+130, y+70, 115, 20,StrSpeed));
        buttonList.add(new GuiButton(3, x+130, y+95, 115, 20,StrRadius));
    }
    @Override
    protected void actionPerformed( GuiButton button) {
        int updatePacketTile;
        switch (button.id) {
            case 1: updatePacketTile = isShiftKeyDown() ? 3 : 4;break;
            case 2: updatePacketTile = isShiftKeyDown() ? 5 : 6;break;
            case 3: updatePacketTile = isShiftKeyDown() ? 7 : 8;break;
            case 4: updatePacketTile = isShiftKeyDown() ? 9 : 10;break;
            default: return;
        }
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), updatePacketTile));
    }
    public void updateButton() {
        if(tile.getValue(1) == 0) {StrWork = LS.StrTextWorking;}
        else if (tile.getValue(1) == 1) {StrWork = LS.StrTextAlways;}
        else if (tile.getValue(1) == 2) {StrWork = LS.StrRedstoneMode;}
        else if (tile.getValue(1) == 3) {StrWork = LS.StrRedstoneModeRevers;}
        if(tile.getValue(4) == 0){StrRender = LS.StrTextRenderOff;}
        else if(tile.getValue(4) == 1){StrRender = LS.StrTextRenderLine;}
        else if(tile.getValue(4) == 2){StrRender = LS.StrTextRenderBox;}
        else if(tile.getValue(4) == 3){StrRender = LS.StrTextRenderComb;}
        if(tile.getValue(3) < 1){StrSpeed = LS.StrTextSpeed;}
        else{StrSpeed = (tile.getValue(3) * tile.getValue(5)) * 100 + "%";}
        if(tile.getValue(2) < 1){StrRadius = LS.StrTextRadius;}
        else{StrRadius = tile.getValue(2) + "x" + tile.getValue(2) + "x" + tile.getValue(2);}
    }
}