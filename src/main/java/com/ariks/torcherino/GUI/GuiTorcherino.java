package com.ariks.torcherino.GUI;

import com.ariks.torcherino.Tiles.TileDCompresedTorch;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.network.UpdateTilePacket;
import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiTorcherino extends ExampleGui {
    private String StrRadius,StrSpeed,StrRender,StrWork,StrStringConfigArea,StrStringConfigMode,StrStringConfigAceleration;
    private int SpeedModifers;
    private final TileTorcherinoBase tile;
    public GuiTorcherino(TileTorcherinoBase tile, EntityPlayer player) {
        super(player,tile);
        this.tile = tile;
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
        fontRenderer.drawSplitString(LS.Info, stringPositionX, stringPositionY, 230, Color.WHITE.getRGB());
        fontRenderer.drawString(StrStringConfigMode, stringPositionX, stringPositionY + 27 , Color.WHITE.getRGB());
        fontRenderer.drawString(StrStringConfigArea, stringPositionX, stringPositionY + 37, Color.WHITE.getRGB());
        fontRenderer.drawString(StrStringConfigAceleration, stringPositionX, stringPositionY + 47, Color.WHITE.getRGB());
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
        buttonList.add(new GuiButton(1, x - 59, y + 20, buttonWidth, buttonHeight, StrWork));
        buttonList.add(new GuiButton(2, x + 59, y + 20, buttonWidth, buttonHeight, StrSpeed));
        buttonList.add(new GuiButton(3, x + 59, y + 45, buttonWidth, buttonHeight, StrRadius));
        if (Config.BooleanRender) {
            buttonList.add(new GuiButton(4, x - 59, y + 45, buttonWidth, buttonHeight, StrRender));
        }
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
        } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase1) {
            updateStringConfigForTile(Config.DTorch_lvl1_M, Config.DTorch_lvl1_R, Config.DTorch_lvl1_S);
        } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase2) {
            updateStringConfigForTile(Config.DTorch_lvl2_M, Config.DTorch_lvl2_R, Config.DTorch_lvl2_S);
        } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase3) {
            updateStringConfigForTile(Config.DTorch_lvl3_M, Config.DTorch_lvl3_R, Config.DTorch_lvl3_S);
        } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase4) {
            updateStringConfigForTile(Config.DTorch_lvl4_M, Config.DTorch_lvl4_R, Config.DTorch_lvl4_S);
        } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase5) {
            updateStringConfigForTile(Config.DTorch_lvl5_M, Config.DTorch_lvl5_R, Config.DTorch_lvl5_S);
        }
    }
    private void updateStringConfigForTile(int mode, int area, int speedModifier) {
        StrStringConfigMode = LS.StrModes + " " + green+mode;
        StrStringConfigArea = LS.StrArea + " " + green+(area+"x"+area+"x"+area);
        StrStringConfigAceleration = LS.StrAceleration + " "+green+(speedModifier * 100 + "%");
        SpeedModifers = speedModifier;
    }
    public void updateButton() {
        StrWork = tile.booleanWork ? LS.ButtonStrWork + " " + TextFormatting.GREEN + LS.StrOn : LS.ButtonStrWork + " " + TextFormatting.RED + LS.StrOff;
        StrRender = tile.booleanRender ? LS.ButtonStrRender + " " + TextFormatting.GREEN + LS.StrOn : LS.ButtonStrRender + " " + TextFormatting.RED + LS.StrOff;
        StrSpeed = (tile.speed < 1 ? TextFormatting.RED : TextFormatting.GREEN) + (tile.speed * SpeedModifers * 100 + "%");
        StrRadius = (tile.radius < 1 ? TextFormatting.RED : TextFormatting.GREEN) + (tile.radius + "x" + tile.radius + "x" + tile.radius);
    }
}


