package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Tiles.TileAcceleration;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import java.awt.*;

public class GuiAceleration extends ExampleGui {
    private final TileAcceleration tile;
    private String StrWork;
    public GuiAceleration(TileAcceleration tile, EntityPlayer player) {
        super(player,tile);
        this.tile = tile;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, 256, 128, 256, 128);
        fontRenderer.drawSplitString(LS.StrGuiAceleration, stringPositionX, stringPositionY, 230, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.TimeCollected + " : "+green+tile.TimeCollect, stringPositionX, stringPositionY + 45, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.StrAceleration+" "+green+(tile.speed*100+"%"),stringPositionX,stringPositionY + 55, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.StrArea+" "+green+(tile.AreaModifier+"x"+ tile.AreaModifier+"x"+tile.AreaModifier),stringPositionX,stringPositionY + 65, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.updateButton();
        int buttonWidth = 110;
        int buttonHeight = 20;
        int x = (width - buttonWidth) / 2;
        int y = (height - buttonHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1, x - 62, y + 45, buttonWidth, buttonHeight, StrWork));
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 2));
    }
    @Override
    protected void actionPerformed(@NotNull GuiButton button) {
        int buttonId = button.id;
        if (buttonId == 1) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 1));
        }
    }
    @Override
    public void onGuiClosed() {
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 3));
    }
    public void updateButton() {
        StrWork = tile.BooleanWork ? LS.ButtonStrWork + " " + TextFormatting.GREEN + LS.StrOn : LS.ButtonStrWork + " " + TextFormatting.RED + LS.StrOff;
    }
}
