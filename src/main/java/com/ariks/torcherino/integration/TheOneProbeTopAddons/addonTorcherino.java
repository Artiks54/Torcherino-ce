package com.ariks.torcherino.integration.TheOneProbeTopAddons;

import com.ariks.torcherino.Tiles.*;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import io.github.drmanganese.topaddons.addons.AddonBlank;
import io.github.drmanganese.topaddons.api.TOPAddon;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

@TOPAddon(dependency = Torcherino.MOD_ID, order = 0)
public class addonTorcherino extends AddonBlank {
    private int SpeedModifers;
    @Override
    public void addProbeInfo(final ProbeMode probeMode, final IProbeInfo probeInfo, final EntityPlayer entityPlayer, final World world, final IBlockState iBlockState, final IProbeHitData data) {
        TileEntity tile = world.getTileEntity(data.getPos());
        if(Config.BooleanTOP) {
            if (tile instanceof TileAcceleration) {
                TileAcceleration TileAcceleration = (TileAcceleration) tile;
                if (TileAcceleration.BooleanWork) {
                    probeInfo.text(TextFormatting.GREEN + "Work: " + "true");
                } else {
                    probeInfo.text(TextFormatting.RED + "Work: " + "false");
                }
                probeInfo.text(TextFormatting.YELLOW + "Time: "+TileAcceleration.TimeCollect);
            }
        }
        if(Config.BooleanTOP) {
            if (tile instanceof TileTimeStorage) {
                TileTimeStorage tileTimeStorage = (TileTimeStorage) tile;
                probeInfo.text(TextFormatting.GREEN + "Time: " + tileTimeStorage.TimeStorage);
            }
        }
        if(Config.BooleanTOP) {
            if (tile instanceof TileCollectors) {
                TileCollectors TileCollectors = (TileCollectors) tile;
                probeInfo.text(TextFormatting.GREEN + "Time: " + TileCollectors.TimeCollect);
            }
        }
        if (Config.BooleanTOP) {
            if (tile instanceof TileTorcherinoBase) {
                TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tile;
                if (TileTorcherinoBase.booleanWork) {
                    probeInfo.text(TextFormatting.GREEN + "Work: " + "true");
                } else {
                    probeInfo.text(TextFormatting.RED + "Work: " + "false");
                }
                if (TileTorcherinoBase.radius >= 1) {
                    probeInfo.text(TextFormatting.GREEN + "Radius: " + TileTorcherinoBase.radius + "x" + TileTorcherinoBase.radius + "x" + TileTorcherinoBase.radius);
                } else {
                    probeInfo.text(TextFormatting.RED + "Radius: " + TileTorcherinoBase.radius + "x" + TileTorcherinoBase.radius + "x" + TileTorcherinoBase.radius);
                }
                if (TileTorcherinoBase.speed >= 1) {
                    probeInfo.text(TextFormatting.GREEN + "Speed: " + TileTorcherinoBase.speed * SpeedModifers * 100 + "%");
                } else {
                    probeInfo.text(TextFormatting.RED + "Speed: " + "0%");
                }
                if (TileTorcherinoBase.booleanRender && Config.BooleanRender) {
                    probeInfo.text(TextFormatting.GREEN + "Visualization: " + "true");
                }
                if (!TileTorcherinoBase.booleanRender && Config.BooleanRender) {
                    probeInfo.text(TextFormatting.RED + "Visualization: " + "false");
                }
                }
                if (tile instanceof TileTorch.TileBase1) {
                    SpeedModifers = Config.Torch_lvl1_S;
                } else if (tile instanceof TileTorch.TileBase2) {
                    SpeedModifers = Config.Torch_lvl2_S;
                } else if (tile instanceof TileTorch.TileBase3) {
                    SpeedModifers = Config.Torch_lvl3_S;
                } else if (tile instanceof TileTorch.TileBase4) {
                    SpeedModifers = Config.Torch_lvl4_S;
                } else if (tile instanceof TileTorch.TileBase5) {
                    SpeedModifers = Config.Torch_lvl5_S;
                } else if (tile instanceof TileCompresedTorch.CompressedTileBase1) {
                    SpeedModifers = Config.CTorch_lvl1_S;
                } else if (tile instanceof TileCompresedTorch.CompressedTileBase2) {
                    SpeedModifers = Config.CTorch_lvl2_S;
                } else if (tile instanceof TileCompresedTorch.CompressedTileBase3) {
                    SpeedModifers = Config.CTorch_lvl3_S;
                } else if (tile instanceof TileCompresedTorch.CompressedTileBase4) {
                    SpeedModifers = Config.CTorch_lvl4_S;
                } else if (tile instanceof TileCompresedTorch.CompressedTileBase5) {
                    SpeedModifers = Config.CTorch_lvl5_S;
                } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase1) {
                    SpeedModifers = Config.DTorch_lvl1_S;
                } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase2) {
                    SpeedModifers = Config.DTorch_lvl2_S;
                } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase3) {
                    SpeedModifers = Config.DTorch_lvl3_S;
                } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase4) {
                    SpeedModifers = Config.DTorch_lvl4_S;
                } else if (tile instanceof TileDCompresedTorch.DCompressedTileBase5) {
                    SpeedModifers = Config.DTorch_lvl5_S;
                }
            }
    }
}