package com.artiks.torcherinoCe.Block.Energy.Molecular;

import com.artiks.torcherinoCe.Block.Core.ExampleGuiContainer;
import com.artiks.torcherinoCe.Gui.BarComponent;
import com.artiks.torcherinoCe.Gui.GuiButtonImage;
import com.artiks.torcherinoCe.Gui.GuiItemButton;
import com.artiks.torcherinoCe.Gui.enumRedstoneControllerButton;
import com.artiks.torcherinoCe.utility.EnergyFormat;
import com.artiks.torcherinoCe.utility.LocalizedStringKey;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiRfMolecular extends ExampleGuiContainer {
    private final TileRfMolecular tile;
    private String WorkString,StackMode;
    private final BarComponent barComponentEnergy;
    private Color color;
    private GuiItemButton buttonWork;
    private GuiButtonImage buttonStack,switchTexture;
    String itemOutput;
    String itemInput;
    String energyNeed;
    String collected;
    String rfPerTick;
    String time;

    public GuiRfMolecular(InventoryPlayer inventory, TileRfMolecular tileEntity) {
        super(new ContainerRfMolecular(inventory, tileEntity));
        this.tile = tileEntity;
        barComponentEnergy = new BarComponent(this, 1, 7, 24, 14, 29, "textures/gui/molecular/energy.png", BarComponent.SideEnum.DOWN);
        addBarComponent(barComponentEnergy);
        this.updateTexture();
    }
    @Override
    public void initGui() {
        super.initGui();
        int ScaledX = (this.width - this.xSize) / 2;
        int ScaledY = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        buttonWork = new GuiItemButton(tile, 1, ScaledX + 180, ScaledY + 5, 1);
        switchTexture = new GuiButtonImage(tile, 1, ScaledX + 180, ScaledY + 28, 2);
        switchTexture.setTexture(32, 16);
        buttonStack = new GuiButtonImage(tile,2,ScaledX+180,ScaledY +51,3);
        buttonList.add(switchTexture);
        buttonList.add(buttonWork);
        buttonList.add(buttonStack);
    }
    @Override
    public void TickScreen() {
        this.updateTexture();
        this.UpdateButtonStringRender();
        this.DrawToolTipInfoButton();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.DrawInfo();
    }
    private void updateTexture() {
        int textureId = tile.getTextureId();
        if (textureId >= 0 && textureId < TextureMolecularChanger.TEXTURES.length) {
            TextureMolecularChanger data = TextureMolecularChanger.TEXTURES[textureId];
            setTexture("textures/gui/molecular/" + data.path, 231, 168);
            color = data.color;
            barComponentEnergy.setTextureTexture(data.textureX, 0);
        }
    }
    private void UpdateButtonStringRender() {
        int work = tile.getBooleanMode();
        int stackMode = tile.getStackMode();
        enumRedstoneControllerButton button = enumRedstoneControllerButton.getByIndex(work);
        WorkString = button.getDisplayText();
        buttonWork.setStackRender(button.getStack());
        switch (stackMode) {
            case 0:
                StackMode = TextFormatting.RED + "StackMode false";
                buttonStack.setTexture(48,16);
            break;
            case 1:
                StackMode = TextFormatting.GREEN + "StackMode true";
                buttonStack.setTexture(64,16);
                break;
        }
    }
    private void DrawToolTipInfoButton() {
        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                if (button.equals(buttonWork)) {
                    drawHoveringText(WorkString, getMouseX(), getMouseY());
                }
                if (button.equals(buttonStack)) {
                    drawHoveringText(StackMode, getMouseX(), getMouseY());
                }
            }
        }
    }
    private void DrawInfo() {
        int RecipeID = tile.getCurrentRecipeID();
        int numRecipes = tile.getNumRecipes();
        if (RecipeID != -1) {
            long value = tile.getEnergyCollected();
            long max = (MolecularRecipe.getRecipes().get(RecipeID).getEnergy() * numRecipes);
            int perTick = tile.getEnergyPerTick();
            int ic = MolecularRecipe.getRecipes().get(RecipeID).getInput().getCount() * numRecipes;
            int io = MolecularRecipe.getRecipes().get(RecipeID).getRecipeOutput().getCount() * numRecipes;
            String ni = MolecularRecipe.getRecipes().get(RecipeID).getInput().getDisplayName();
            String no = MolecularRecipe.getRecipes().get(RecipeID).getRecipeOutput().getDisplayName();
            itemInput = (ni + " *" + ic);
            itemOutput = (no + " *" + io);
            energyNeed = (LocalizedStringKey.StrEnergyRecipe + " " + EnergyFormat.formatNumber(MolecularRecipe.getRecipes().get(RecipeID).getEnergy() * numRecipes));
            if(value != 0) {
                collected = (LocalizedStringKey.StrEnergy + " " + EnergyFormat.formatNumber(value));
            }
            if (perTick != 0) {
                setBarValue(1, value, max);
                long ticks = (max - value + perTick - 1) / perTick;
                long totalMilliseconds = ticks * 50L;
                long milliseconds = totalMilliseconds % 1000;
                long totalSeconds = totalMilliseconds / 1000;
                long seconds = totalSeconds % 60;
                long totalMinutes = totalSeconds / 60;
                long minutes = totalMinutes % 60;
                long hours = totalMinutes / 60;
                time = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
                rfPerTick = (LocalizedStringKey.StrRFTick + " " + EnergyFormat.formatNumber(perTick));
                this.fontRenderer.drawStringWithShadow(rfPerTick, 35, 45, color.getRGB());
                this.fontRenderer.drawStringWithShadow(time, 35, 55, color.getRGB());
            }
        } else {
            setBarValue(1, 0, 100);
            itemInput = "";
            itemOutput = "";
            energyNeed = "";
            collected = "";
        }
        this.fontRenderer.drawStringWithShadow(itemInput, 35, 5, color.getRGB());
        this.fontRenderer.drawStringWithShadow(itemOutput, 35, 15, color.getRGB());
        this.fontRenderer.drawStringWithShadow(energyNeed, 35, 25, color.getRGB());
        this.fontRenderer.drawStringWithShadow(collected, 35, 35, color.getRGB());
    }
}