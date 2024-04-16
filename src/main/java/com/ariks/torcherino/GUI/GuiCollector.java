package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Tiles.TileCollector;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateTilePacketCollector;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import java.awt.*;

public class GuiCollector extends GuiScreen {
    LocalizedStringKey LS = new LocalizedStringKey();
    final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui_small.png");
    private final TextFormatting green = TextFormatting.GREEN;
    private final TileCollector tile;
    private String StrWork;
    private final EntityPlayer player;
    public GuiCollector(TileCollector tile, EntityPlayer player) {
        this.tile = tile;
        this.player = player;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int screenWidthPixels = width;
        int screenHeightPixels = height;
        int textureX = (screenWidthPixels - 256) / 2;
        int textureY = (screenHeightPixels - 128) / 2;
        int stringPositionX = textureX + 15;
        int stringPositionY = textureY + 15;
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, 256, 128, 256, 128);
        FontRenderer fontRenderer = this.fontRenderer;
        fontRenderer.drawSplitString(LS.InfoCollector, stringPositionX, stringPositionY, 230, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.TimeCollector + " : "+green+tile.TimeCollect, stringPositionX, stringPositionY + 45, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.StrAceleration+" "+green+(tile.speed*100+"%"),stringPositionX,stringPositionY + 55, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.StrArea+" "+green+(tile.AreaModifier+"x"+ tile.AreaModifier+"x"+tile.AreaModifier),stringPositionX,stringPositionY + 65, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.updateButton();
        ModPacketHandler.network.sendToServer(new UpdateTilePacketCollector(this.tile.getPos(), 2));
        int buttonWidth = 110;
        int buttonHeight = 20;
        int x = (width - buttonWidth) / 2;
        int y = (height - buttonHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1, x - 62, y + 45, buttonWidth, buttonHeight, StrWork));
    }
    @Override
    public void onGuiClosed() {
        ModPacketHandler.network.sendToServer(new UpdateTilePacketCollector(this.tile.getPos(), 3));
    }
    @Override
    protected void actionPerformed(@NotNull GuiButton button) {
        int buttonId = button.id;
        int updatePacketTile;
        if (buttonId == 1) {
            updatePacketTile = 1;
        } else {
            return;
        }
        ModPacketHandler.network.sendToServer(new UpdateTilePacketCollector(this.tile.getPos(), updatePacketTile));
    }
    public void updateScreen() {
        super.updateScreen();
        this.initGui();
        if (tile.getPos().getDistance((int) player.posX, (int) player.posY, (int) player.posZ) > 8.0) {
            player.closeScreen();
        }
    }
    public void updateButton() {
        StrWork = tile.BooleanWork ? LS.ButtonStrWork + " " + TextFormatting.GREEN + LS.StrOn : LS.ButtonStrWork + " " + TextFormatting.RED + LS.StrOff;
    }
        @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if(Keyboard.isKeyDown(Keyboard.KEY_E) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            player.closeScreen();
        }
    }
    @Override
    public boolean doesGuiPauseGame()  {
        return false;
    }
}
