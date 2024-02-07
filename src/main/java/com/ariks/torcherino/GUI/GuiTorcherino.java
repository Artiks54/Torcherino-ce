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
import java.io.IOException;

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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.updateStringConfig();
        int screenWidth = this.width;
        int screenHeight = this.height;
        int GuiWidths = 256;
        int GuiHeights = 256;
        int textureX = (screenWidth - GuiWidths) / 2;
        int textureY = (screenHeight - GuiHeights) / 2;
        int StringPositionX = textureX + 15;
        int StringPositionY = textureY + 15;
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(textureX, textureY, 0, 0, GuiWidths, GuiHeights, GuiWidths, GuiHeights);
        FontRenderer fr = fontRenderer;
        fr.drawSplitString(LS.Info, StringPositionX, StringPositionY,240 ,0x55FF55);
        fr.drawString(StrStringConfigMode,StringPositionX,StringPositionY+20,0x55FF55);
        fr.drawString(StrStringConfigArea,StringPositionX,StringPositionY+40,0x55FF55);
        super.drawScreen(mouseX,mouseY,partialTicks);
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
        //General
        buttonList.add(new GuiButton(1,x-59,y+45,buttonWidth,buttonHeight, StrWork));
        buttonList.add(new GuiButton(2,x-59,y+75,buttonWidth,buttonHeight, StrSpeed));
        buttonList.add(new GuiButton(3,x-59,y+105,buttonWidth,buttonHeight, StrRadius));
        //Particle
        buttonList.add(new GuiButton(4,x+59,y+45,buttonWidth,buttonHeight, StrSpawnPrac));
        buttonList.add(new GuiButton(5,x+59,y+75,buttonWidth,buttonHeight, StrParc));
        buttonList.add(new GuiButton(6,x+59,y+105,buttonWidth,buttonHeight, ""+tile.stepCount));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 1));
        }
        if (button.id == 2) {
            if(!isShiftKeyDown()) {
                ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 2));
            }else{
                ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 7));
            }
        }
        if (button.id == 3) {
            if (!isShiftKeyDown()) {
                ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 3));
            } else {
                ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 8));
            }
        }
        if (button.id == 4) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 4));
        }
        if (button.id == 5) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 5));
        }
        if (button.id == 6) {
            ModPacketHandler.network.sendToServer(new UpdateTilePacket(this.tile.getPos(), 6));
        }
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
    public void updatePrac(){
        if(tile.modPrac == 0){
            StrParc = LS.prac0;
        }
        if(tile.modPrac == 1){
            StrParc = LS.prac1;
        }
        if(tile.modPrac == 2){
            StrParc = LS.prac2;
        }
        if(tile.modPrac == 3){
            StrParc = LS.prac3;
        }
        if(tile.modPrac == 4){
            StrParc = LS.prac4;
        }
        if(tile.modPrac == 5){
            StrParc = LS.prac5;
        }
        if(tile.modPrac == 6){
            StrParc = LS.prac6;
        }
    }

    public void updateStringConfig(){
        if (tile instanceof TileTorch.TileBase1) {
            StrStringConfigMode = LS.StrModes +" "+ Config.Torch_lvl1_M;
            StrStringConfigArea = LS.StrArea +" "+ Config.Torch_lvl1_R;
            SpeedModifers = Config.Torch_lvl1_S;
        }
        if (tile instanceof TileTorch.TileBase2) {
            StrStringConfigMode = LS.StrModes +" "+ Config.Torch_lvl2_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.Torch_lvl2_R;
            SpeedModifers = Config.Torch_lvl2_S;
        }
        if (tile instanceof TileTorch.TileBase3) {
            StrStringConfigMode = LS.StrModes +" "+ Config.Torch_lvl3_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.Torch_lvl3_R;
            SpeedModifers = Config.Torch_lvl3_S;
        }
        if (tile instanceof TileTorch.TileBase4) {
            StrStringConfigMode = LS.StrModes +" "+ Config.Torch_lvl4_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.Torch_lvl4_R;
            SpeedModifers = Config.Torch_lvl4_S;
        }
        if (tile instanceof TileTorch.TileBase5) {
            StrStringConfigMode = LS.StrModes +" "+ Config.Torch_lvl5_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.Torch_lvl5_R;
            SpeedModifers = Config.Torch_lvl5_S;
        }
        if (tile instanceof TileCompresedTorch.CompressedTileBase1) {
            StrStringConfigMode = LS.StrModes +" "+ Config.CTorch_lvl1_M;
            StrStringConfigArea = LS.StrArea +" "+ Config.CTorch_lvl1_R;
            SpeedModifers = Config.CTorch_lvl1_S;
        }
        if (tile instanceof TileCompresedTorch.CompressedTileBase2) {
            StrStringConfigMode = LS.StrModes +" "+ Config.CTorch_lvl2_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.CTorch_lvl2_R;
            SpeedModifers = Config.CTorch_lvl2_S;
        }
        if (tile instanceof TileCompresedTorch.CompressedTileBase3) {
            StrStringConfigMode = LS.StrModes +" "+ Config.CTorch_lvl3_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.CTorch_lvl3_R;
            SpeedModifers = Config.CTorch_lvl3_S;
        }
        if (tile instanceof TileCompresedTorch.CompressedTileBase4) {
            StrStringConfigMode = LS.StrModes +" "+ Config.CTorch_lvl4_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.CTorch_lvl4_R;
            SpeedModifers = Config.CTorch_lvl4_S;
        }
        if (tile instanceof TileCompresedTorch.CompressedTileBase5) {
            StrStringConfigMode = LS.StrModes +" "+ Config.CTorch_lvl5_M;
            StrStringConfigArea = LS.StrArea  +" "+ Config.CTorch_lvl5_R;
            SpeedModifers = Config.CTorch_lvl5_S;
        }
    }
    public void updateButton(){
       StrWork = tile.booleanWork ? LS.ButtonStrWork+" "+LS.ColorGreen+LS.StrOn : LS.ButtonStrWork+" "+LS.ColorRed+LS.StrOff;
       StrSpawnPrac = tile.booleanSpawnPrac ? LS.ButtonStrSP+" "+LS.ColorGreen+LS.StrOn : LS.ButtonStrSP+" "+LS.ColorRed+LS.StrOff;
       StrSpeed = tile.speed < 1 ? LS.ColorRed+LS.ButtonStrSpeed : LS.ColorGreen+tile.speed * SpeedModifers* 100 +"%";
       StrRadius = tile.radius < 1 ? LS.ColorRed+LS.ButtonStrArea : LS.ColorGreen+(tile.radius+"x"+tile.radius+"x"+tile.radius);
    }
}


