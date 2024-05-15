package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiUtils;

public class GuiTextureButton extends GuiButtonExt {
    private ItemStack stack = ItemStack.EMPTY;
    public GuiTextureButton(int buttonId, int x, int y) {
        super(buttonId, x, y, 20, 20, "");
    }
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float p) {
        if (this.visible) {
            super.drawButton(mc, mouseX, mouseY, p);
//            ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Torcherino.MOD_ID, "textures/gui/button_info.png");
//            GuiUtils.drawContinuousTexturedBox(BUTTON_TEXTURES, this.x, this.y, 0, 0, this.width, this.height, 200, 20, 0, 0, 0, 0, this.zLevel);
//            if (!stack.isEmpty())
                Torcherino.proxy.renderItemOnScreen(this.stack, this.x+2, this.y+2);
            }
        }
    public ItemStack getStackRender() {
        return stack;
    }
    public void setStackRender(ItemStack stackRender) {
        this.stack = stackRender;
    }
}
