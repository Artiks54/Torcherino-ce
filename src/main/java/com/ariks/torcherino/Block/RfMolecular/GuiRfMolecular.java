package com.ariks.torcherino.Block.RfMolecular;

import com.ariks.torcherino.Block.Core.ExampleGuiContainer;
import com.ariks.torcherino.Gui.BarComponent;
import com.ariks.torcherino.util.EnergyFormat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.awt.*;
import java.text.NumberFormat;

@SideOnly(Side.CLIENT)
public class GuiRfMolecular extends ExampleGuiContainer {
    private final TileRfMolecular tile;
    public NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private String ItemInput;
    private String ItemOutput;
    private String EnergyNeed;
    private String Collected;
    private String RfPerTick;
    private String Time;

    public GuiRfMolecular(InventoryPlayer inventory, TileRfMolecular tileEntity, EntityPlayer player) {
        super(new ContainerRfMolecular(inventory, tileEntity, player));
        this.tile = tileEntity;
        setTexture("textures/gui/gui_molecular.png", 205, 170);
        BarComponent barComponent = new BarComponent(this, 1, 7, 24, 181, 0, 14, 29, "textures/gui/gui_component.png");
        barComponent.setSideDirection("down");
        addBarComponent(barComponent);
    }
    @Override
    public void Tick() {
        this.getRecipe();
    }
    private void getRecipe() {
        int RecipeID = tile.getValue(1);
        if (RecipeID >= 0) {
            int ic = MolecularRecipe.getRecipes().get(RecipeID).getInput().getCount();
            int io = MolecularRecipe.getRecipes().get(RecipeID).getRecipeOutput().getCount();
            String ni = MolecularRecipe.getRecipes().get(RecipeID).getInput().getDisplayName();
            String no = MolecularRecipe.getRecipes().get(RecipeID).getRecipeOutput().getDisplayName();
            ItemInput = (ni + " *" + ic);
            ItemOutput = (no + " *" + io);
            EnergyNeed = (LS.StrEnergyRecipe + " " + EnergyFormat.formatNumber(tile.energyRequired));
            Collected = (LS.StrEnergy + " " + EnergyFormat.formatNumber(tile.energyCollected));
            RfPerTick = (LS.StrRFTick + " " + numberFormat.format(tile.energyReceived));
            if (tile.energyReceived != 0) {
                long ticks = (tile.energyRequired - tile.energyCollected) / tile.energyReceived;
                long seconds = ticks / 20;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                seconds = seconds % 60;
                minutes = minutes % 60;
                Time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }
            int progressPercentage = (int) ((tile.energyCollected * 100) / tile.energyRequired);
            setBarValue(1, progressPercentage, 100);
        } else {
            ItemInput = "";
            ItemOutput = "";
            EnergyNeed = "";
            Collected = "";
            RfPerTick = "";
            Time = "";
            setBarValue(1, 0, 100);
        }
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        int xOffset = 35;
        int yOffset = 5;
        this.fontRenderer.drawStringWithShadow(ItemInput, xOffset, yOffset, Color.RED.getRGB());
        this.fontRenderer.drawStringWithShadow(ItemOutput, xOffset, yOffset + 10, Color.RED.getRGB());
        this.fontRenderer.drawStringWithShadow(EnergyNeed, xOffset, yOffset + 20, Color.RED.getRGB());
        this.fontRenderer.drawStringWithShadow(Collected, xOffset, yOffset + 30, Color.RED.getRGB());
        this.fontRenderer.drawStringWithShadow(RfPerTick, xOffset, yOffset + 40, Color.RED.getRGB());
        this.fontRenderer.drawStringWithShadow(Time, xOffset, yOffset + 50, Color.RED.getRGB());
    }
}