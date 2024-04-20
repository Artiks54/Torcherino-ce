package com.ariks.torcherino.Render;

import com.ariks.torcherino.Tiles.TileCompresedTorch;
import com.ariks.torcherino.Tiles.TileDCompresedTorch;
import com.ariks.torcherino.Tiles.TileTorch;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.util.Config;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

@SideOnly(Side.CLIENT)
public class TileTorcherinoBaseRender extends TileEntitySpecialRenderer<TileTorcherinoBase> {
        @Override
        public void render (@NotNull TileTorcherinoBase tile,double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
            if (tile.hasWorld() && tile.booleanRender && Config.BooleanRender && (Config.BooleanRenderLine || Config.BooleanRenderFilledBox)) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(x - 0.0005D, y - 0.0005D, z - 0.0005D);
                GlStateManager.scale(0.999D, 0.999D, 0.999D);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.disableTexture2D();
                GlStateManager.disableLighting();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                GlStateManager.disableCull();
                if (Config.BooleanRenderLine) {
                    int r, g, b;
                    if (tile instanceof TileTorch.TileBase1 || tile instanceof TileCompresedTorch.CompressedTileBase1 || tile instanceof TileDCompresedTorch.DCompressedTileBase1) {
                        r = Config.Torch_Color_lvl1_R;
                        g = Config.Torch_Color_lvl1_G;
                        b = Config.Torch_Color_lvl1_B;
                    } else if (tile instanceof TileTorch.TileBase2 || tile instanceof TileCompresedTorch.CompressedTileBase2 || tile instanceof TileDCompresedTorch.DCompressedTileBase2) {
                        r = Config.Torch_Color_lvl2_R;
                        g = Config.Torch_Color_lvl2_G;
                        b = Config.Torch_Color_lvl2_B;
                    } else if (tile instanceof TileTorch.TileBase3 || tile instanceof TileCompresedTorch.CompressedTileBase3 || tile instanceof TileDCompresedTorch.DCompressedTileBase3) {
                        r = Config.Torch_Color_lvl3_R;
                        g = Config.Torch_Color_lvl3_G;
                        b = Config.Torch_Color_lvl3_B;
                    } else if (tile instanceof TileTorch.TileBase4 || tile instanceof TileCompresedTorch.CompressedTileBase4 || tile instanceof TileDCompresedTorch.DCompressedTileBase4) {
                        r = Config.Torch_Color_lvl4_R;
                        g = Config.Torch_Color_lvl4_G;
                        b = Config.Torch_Color_lvl4_B;
                    } else if (tile instanceof TileTorch.TileBase5 || tile instanceof TileCompresedTorch.CompressedTileBase5 || tile instanceof TileDCompresedTorch.DCompressedTileBase5) {
                        r = Config.Torch_Color_lvl5_R;
                        g = Config.Torch_Color_lvl5_G;
                        b = Config.Torch_Color_lvl5_B;
                    } else {
                        r = 255;
                        g = 255;
                        b = 255;
                    }
                    float red = r / 255f;
                    float green = g / 255f;
                    float blue = b / 255f;
                    RenderGlobal.drawSelectionBoundingBox(tile.getAABBForRender(), red, green, blue, 1F);
                }
                if (Config.BooleanRenderFilledBox) {
                    int i = 61680;
                    int j = i % 65536;
                    int k = 0;
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
                    RenderGlobal.renderFilledBox(tile.getAABBForRender(), 1F, 1F, 1F, 0.50F);
                }
                GlStateManager.enableCull();
                GlStateManager.enableLighting();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.depthMask(true);
                GlStateManager.popMatrix();
            }
        }
}
