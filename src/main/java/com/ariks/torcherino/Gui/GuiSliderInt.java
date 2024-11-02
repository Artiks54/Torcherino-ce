package com.ariks.torcherino.Gui;

import com.ariks.torcherino.Block.Core.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.jetbrains.annotations.NotNull;

public class GuiSliderInt extends GuiButtonExt {
    private float sliderPosition = 1.0F;
    public boolean isMouseDown;
    private final int min,max;
    private final String str;
    private final int mode;
    private final TileExampleContainer tile;
    private final int valueId;
    public GuiSliderInt(TileExampleContainer tile, int idIn, int x, int y,
                            int widthIn, int heightIn,
                            final int minIn, final int maxIn, int valueId,String string,int mode) {
        super(idIn, x, y, widthIn, heightIn, string);
        this.updateDisplay();
        this.tile = tile;
        this.min = minIn;
        this.max = maxIn;
        this.str = string;
        this.mode = mode;
        this.valueId = valueId;
        this.setSliderValue(tile.getValue(valueId), false);
    }
    public void setSliderValue(float value, boolean notifyResponder) {
        this.sliderPosition = (value - min) / (max - min);
        if (sliderPosition < 0) {
            sliderPosition = 0;
        }
        if (sliderPosition > max) {
            sliderPosition = max;
        }
        this.updateDisplay();
        if (notifyResponder) {
            notifyResponder();
        }
    }
    private void notifyResponder() {
        int val = (int) this.getSliderValue();
        RegistryNetwork.network.sendToServer(new UpdateSlider(this.tile.getPos(), this.valueId, val));
    }
    public float getSliderValue() {
        float val = min + (max - min) * this.sliderPosition;
        return MathHelper.floor(val);
    }
    private void updateDisplay() {
        if(mode == 1) {
            this.displayString = (str + " " + this.getSliderValue());
        }
        if(mode == 2) {
            this.displayString = str;
        }
        if(mode == 3) {
            int val = (int) this.getSliderValue();
            this.displayString = String.valueOf(val);
        }
    }
    @Override
    public boolean mousePressed(@NotNull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            this.sliderPosition = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
            if (this.sliderPosition < 0.0F) {
                this.sliderPosition = 0.0F;
            }
            if (this.sliderPosition > 1.0F) {
                this.sliderPosition = 1.0F;
            }
            this.updateDisplay();
            this.notifyResponder();
            this.isMouseDown = true;
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    protected void mouseDragged(@NotNull Minecraft mc, int mouseX, int mouseY) {
        super.mouseDragged(mc, mouseX, mouseY);
        if (this.visible) {
            if (this.isMouseDown) {
                this.sliderPosition = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
                if (this.sliderPosition < 0.0F) {
                    this.sliderPosition = 0.0F;
                }
                if (this.sliderPosition > 1.0F) {
                    this.sliderPosition = 1.0F;
                }
                this.updateDisplay();
                this.notifyResponder();
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int) (this.sliderPosition * (this.width - 8)), this.y, 0, 66, 4, height);
            this.drawTexturedModalRect(this.x + (int) (this.sliderPosition * (this.width - 8)) + 4, this.y, 196, 66, 4, height);
        }
    }
    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        super.mouseReleased(mouseX, mouseY);
        this.isMouseDown = false;
    }
    @Override
    protected int getHoverState(boolean mouseOver) {
        return 0;
    }
}