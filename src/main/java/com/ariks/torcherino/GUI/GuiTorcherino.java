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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends GuiScreen {
    LocalizedStringKey LS = new LocalizedStringKey();
    final ResourceLocation texture = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/gui.png");
    private final TileTorcherinoBase tile;
    private String StrWork,StrRadius,StrSpeed,StrSpawnPrac,StrParc,StrStringConfigArea,StrStringConfigMode;
    private int SpeedModifers;
    public GuiTorcherino(TileTorcherinoBase tile) {
        this.tile = tile;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.updateStringConfig();
        int screenWidthPixels = width;
        int screenHeightPixels = height;
        int textureX = (screenWidthPixels - 256) / 2;
        int textureY = (screenHeightPixels - 256) / 2;
        int stringPositionX = textureX + 15;
        int stringPositionY = textureY + 15;
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, 256, 256, 256, 256);
        FontRenderer fontRenderer = this.fontRenderer;
        fontRenderer.drawSplitString(LS.Info, stringPositionX, stringPositionY, 240, Color.GREEN.getRGB());
        fontRenderer.drawString(StrStringConfigMode, stringPositionX, stringPositionY + 20, Color.GREEN.getRGB());
        fontRenderer.drawString(StrStringConfigArea, stringPositionX, stringPositionY + 40, Color.GREEN.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui() {
        super.initGui();
        this.updateButton();
        this.updatePrac();
        int buttonWidth = 110;
        int buttonHeight = 20;
        int x = (width - buttonWidth) / 2;
        int y = (height - buttonHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1,x-59,y+45,buttonWidth,buttonHeight, StrWork));
        buttonList.add(new GuiButton(2,x-59,y+75,buttonWidth,buttonHeight, StrSpeed));
        buttonList.add(new GuiButton(3,x-59,y+105,buttonWidth,buttonHeight, StrRadius));
        buttonList.add(new GuiButton(4,x+59,y+45,buttonWidth,buttonHeight, StrSpawnPrac));
        buttonList.add(new GuiButton(5,x+59,y+75,buttonWidth,buttonHeight, StrParc));
        buttonList.add(new GuiButton(6,x+59,y+105,buttonWidth,buttonHeight, ""+tile.stepCount));
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        int buttonId = button.id;
        int updatePacketTile;
        switch (buttonId) {
            case 1: updatePacketTile = 1;break;
            case 2: updatePacketTile = isShiftKeyDown() ? 7 : 2;break;
            case 3: updatePacketTile = isShiftKeyDown() ? 8 : 3;break;
            case 4: updatePacketTile = 4;break;
            case 5: updatePacketTile = 5;break;
            case 6: updatePacketTile = 6;break;
            default: return;
        }
        ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), updatePacketTile));
    }
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.initGui();
    }
    @Override
    public boolean doesGuiPauseGame()  {
        return false;
    }
    public void updatePrac() {
        String[] pracArray = {LS.prac0, LS.prac1, LS.prac2, LS.prac3, LS.prac4, LS.prac5, LS.prac6};
        if (tile.modPrac >= 0 && tile.modPrac < pracArray.length) {
            StrParc = pracArray[tile.modPrac];
        }
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
        StrSpawnPrac = tile.booleanSpawnPrac ? LS.ButtonStrSP + " " + LS.ColorGreen + LS.StrOn : LS.ButtonStrSP + " " + LS.ColorRed + LS.StrOff;
        StrSpeed = (tile.speed < 1 ? LS.ColorRed : LS.ColorGreen) + (tile.speed * SpeedModifers * 100) + "%";
        StrRadius = (tile.radius < 1 ? LS.ColorRed : LS.ColorGreen) + (tile.radius + "x" + tile.radius + "x" + tile.radius);
    }
}


