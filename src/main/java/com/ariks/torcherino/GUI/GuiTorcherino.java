package com.ariks.torcherino.GUI;

import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.network.UpdateTilePacket;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.LocalizedStringKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends GuiScreen {
    LocalizedStringKey LS = new LocalizedStringKey();
    final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui.png");
    private final TileTorcherinoBase tile;
    private final EntityPlayer player;
    private String StrRadius,StrSpeed,StrRender,StrWork,StrStringConfigArea,StrStringConfigMode;
    private int SpeedModifers;
    public GuiTorcherino(TileTorcherinoBase tile, EntityPlayer player) {
        this.tile = tile;
        this.player = player;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int screenWidthPixels = width;
        int screenHeightPixels = height;
        int textureX = (screenWidthPixels - 256) / 2;
        int textureY = (screenHeightPixels - 256) / 2;
        int stringPositionX = textureX + 15;
        int stringPositionY = textureY + 15;
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, 256, 256, 256, 256);
        FontRenderer fontRenderer = this.fontRenderer;
        fontRenderer.drawSplitString(LS.Info, stringPositionX, stringPositionY, 230, Color.WHITE.getRGB());
        fontRenderer.drawString(LS.Pos + " X: "+tile.getPos().getX()+" Y: "+tile.getPos().getY()+" Z: "+tile.getPos().getZ(), stringPositionX, stringPositionY + 45, Color.WHITE.getRGB());
        fontRenderer.drawString(StrStringConfigMode, stringPositionX, stringPositionY + 60, Color.WHITE.getRGB());
        fontRenderer.drawString(StrStringConfigArea, stringPositionX, stringPositionY + 75, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.updateStringConfig();
        this.updateButton();
        int buttonWidth = 110;
        int buttonHeight = 20;
        int x = (width - buttonWidth) / 2;
        int y = (height - buttonHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1,x-59,y+75,buttonWidth,buttonHeight, StrWork));
        buttonList.add(new GuiButton(2,x+59,y+75,buttonWidth,buttonHeight, StrSpeed));
        buttonList.add(new GuiButton(3,x+59,y+105,buttonWidth,buttonHeight, StrRadius));
        buttonList.add(new GuiButton(4,x-59,y+105,buttonWidth,buttonHeight, StrRender));
    }
    @Override
    protected void actionPerformed(@NotNull GuiButton button) {
        int buttonId = button.id;
        int updatePacketTile;
        switch (buttonId) {
            case 1: updatePacketTile = 1;break;
            case 2: updatePacketTile = isShiftKeyDown() ? 3 : 2;break;
            case 3: updatePacketTile = isShiftKeyDown() ? 5 : 4;break;
            case 4: updatePacketTile = 6;break;
            default: return;
        }
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), updatePacketTile));
    }
    public void updateScreen() {
        super.updateScreen();
        this.initGui();
        if (tile.getPos().getDistance((int) player.posX, (int) player.posY, (int) player.posZ) > 8.0) {
            player.closeScreen();
        }
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
    public void updateStringConfig() {
        if (tile instanceof TileTorch.TileBase1) {
            updateStringConfigForTile(Config.Torch_lvl1_M, Config.Torch_lvl1_R, Config.Torch_lvl1_S);
        } else if (tile instanceof TileTorch.TileBase2) {
            updateStringConfigForTile(Config.Torch_lvl2_M, Config.Torch_lvl2_R, Config.Torch_lvl2_S);
        } else if (tile instanceof TileTorch.TileBase3) {
            updateStringConfigForTile(Config.Torch_lvl3_M, Config.Torch_lvl3_R, Config.Torch_lvl3_S);
        } else if (tile instanceof TileTorch.TileBase4) {
            updateStringConfigForTile(Config.Torch_lvl4_M, Config.Torch_lvl4_R, Config.Torch_lvl4_S);
        } else if (tile instanceof TileTorch.TileBase5) {
            updateStringConfigForTile(Config.Torch_lvl5_M, Config.Torch_lvl5_R, Config.Torch_lvl5_S);
        } else if (tile instanceof TileCompresedTorch.CompressedTileBase1) {
            updateStringConfigForTile(Config.CTorch_lvl1_M, Config.CTorch_lvl1_R, Config.CTorch_lvl1_S);
        } else if (tile instanceof TileCompresedTorch.CompressedTileBase2) {
            updateStringConfigForTile(Config.CTorch_lvl2_M, Config.CTorch_lvl2_R, Config.CTorch_lvl2_S);
        } else if (tile instanceof TileCompresedTorch.CompressedTileBase3) {
            updateStringConfigForTile(Config.CTorch_lvl3_M, Config.CTorch_lvl3_R, Config.CTorch_lvl3_S);
        } else if (tile instanceof TileCompresedTorch.CompressedTileBase4) {
            updateStringConfigForTile(Config.CTorch_lvl4_M, Config.CTorch_lvl4_R, Config.CTorch_lvl4_S);
        } else if (tile instanceof TileCompresedTorch.CompressedTileBase5) {
            updateStringConfigForTile(Config.CTorch_lvl5_M, Config.CTorch_lvl5_R, Config.CTorch_lvl5_S);
        }
    }
    private void updateStringConfigForTile(int mode, int area, int speedModifier) {
        StrStringConfigMode = LS.StrModes + " " + mode;
        StrStringConfigArea = LS.StrArea + " " + area;
        SpeedModifers = speedModifier;
    }
    public void updateButton() {
        StrWork = tile.booleanWork ? LS.ButtonStrWork + " " + LS.ColorGreen + LS.StrOn : LS.ButtonStrWork + " " + LS.ColorRed + LS.StrOff;
        StrRender = tile.booleanRender ? LS.ButtonStrRender + " " + LS.ColorGreen + LS.StrOn : LS.ButtonStrRender + " " + LS.ColorRed + LS.StrOff;
        StrSpeed = (tile.speed < 1 ? LS.ColorRed : LS.ColorGreen) + (tile.speed * SpeedModifers * 100) + "%";
        StrRadius = (tile.radius < 1 ? LS.ColorRed : LS.ColorGreen) + (tile.radius + "x" + tile.radius + "x" + tile.radius);
    }
}


