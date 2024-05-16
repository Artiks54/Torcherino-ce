package com.ariks.torcherino.util;

import com.ariks.torcherino.Block.TileExampleContainer;
import com.ariks.torcherino.Register.RegistryNetwork;
import com.ariks.torcherino.network.UpdateSlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiSliderInt extends GuiButtonExt {
    private float sliderPosition = 1.0F;
    public boolean isMouseDown;
    private final int min;
    private final int max;
    private final String str;
    private final TileExampleContainer responder;
    private final int responderField;
    private boolean SetString = false;
    public GuiSliderInt(TileExampleContainer guiResponder, int idIn, int x, int y,
                            int widthIn, int heightIn,
                            final int minIn, final int maxIn, int fieldId,String string,boolean booleanString) {
        super(idIn, x, y, widthIn, heightIn, string);
        this.updateDisplay();
        responder = guiResponder;
        this.min = minIn;
        this.max = maxIn;
        this.str = string;
        this.SetString = booleanString;
        this.responderField = fieldId;
        this.setSliderValue(responder.getValue(responderField), false);
    }
    public void setSliderValue(float value, boolean notifyResponder) {
        this.sliderPosition = (value - this.getMin()) / (this.getMax() - this.getMin());
        if (sliderPosition < 0) {
            sliderPosition = 0;
        }
        if (sliderPosition > this.getMax()) {
            sliderPosition = this.getMax();
        }
        this.updateDisplay();
        if (notifyResponder) {
            notifyResponder();
        }
    }
    private void notifyResponder() {
        int val = (int) this.getSliderValue();
        RegistryNetwork.network.sendToServer(new UpdateSlider(this.responder.getPos(), this.responderField, val));
    }
    public float getSliderValue() {
        float val = this.getMin() + (this.getMax() - this.getMin()) * this.sliderPosition;
        return MathHelper.floor(val);
    }
    private void updateDisplay() {
        if(SetString){
            this.displayString = str;
        } else {
            int val = (int) this.getSliderValue();
            this.displayString = String.valueOf(val);
        }
    }
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
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
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
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
    public int getMax() {
        return max;
    }
    public int getMin() {
        return min;
    }
}