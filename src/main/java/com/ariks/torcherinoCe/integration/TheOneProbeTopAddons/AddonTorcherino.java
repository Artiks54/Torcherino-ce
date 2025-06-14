package com.ariks.torcherinoCe.integration.TheOneProbeTopAddons;

import com.ariks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherinoCe.Block.Time.TileTime;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.utility.Config;
import io.github.drmanganese.topaddons.addons.AddonBlank;
import io.github.drmanganese.topaddons.api.TOPAddon;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import java.awt.*;

@TOPAddon(dependency = Tags.MODID, order = 0)
public class AddonTorcherino extends AddonBlank {
    @Override
    public void addProbeInfo(final ProbeMode probeMode, final IProbeInfo probeInfo, final EntityPlayer entityPlayer, final World world, final IBlockState iBlockState, final IProbeHitData data) {
        if(Config.IntegrationTheOneProbeTA){
            TileEntity tile = world.getTileEntity(data.getPos());
            if (tile instanceof TileTime TileTime) {
                IProgressStyle progressStyle = probeInfo.defaultProgressStyle()
                        .suffix("Time")
                        .borderColor(Color.DARK_GRAY.getRGB())
                        .filledColor(Color.gray.getRGB()).alternateFilledColor(Color.darkGray.getRGB())
                        .numberFormat(NumberFormat.COMPACT);
                probeInfo.progress(TileTime.energyTime.getTimeStored(),TileTime.energyTime.getMaxTimeStored(),progressStyle);
            }
            if (tile instanceof TileParticleCollector TileParticleCollector) {
                probeInfo.text("Progress:");
                probeInfo.progress(TileParticleCollector.percent, 100);
            }
            if (tile instanceof TileTorcherinoBase TileTorcherinoBase) {
                if(TileTorcherinoBase.getValue(3) == 0){
                    probeInfo.text("Working: not selected");
                }
                else if(TileTorcherinoBase.getValue(3) == 1){
                    probeInfo.text( "Always on");
                }
                else if(TileTorcherinoBase.getValue(3) == 2){
                    probeInfo.text("Redstone mode");
                }
                else if(TileTorcherinoBase.getValue(3) == 3){
                    probeInfo.text("Redstone reverse mode");
                }
                if (TileTorcherinoBase.getValue(15) >= 1) {
                    probeInfo.text("X: " + TileTorcherinoBase.getValue(15));
                }
                if (TileTorcherinoBase.getValue(16) >= 1) {
                    probeInfo.text("Y: " + TileTorcherinoBase.getValue(16));
                }
                if (TileTorcherinoBase.getValue(17) >= 1) {
                    probeInfo.text("Z: " + TileTorcherinoBase.getValue(17));
                }
                if (TileTorcherinoBase.getValue(19) >= 1) {
                    probeInfo.text("Speed: " + TileTorcherinoBase.getValue(19) * TileTorcherinoBase.getValue(5) * 100 + "%");
                } else {
                    probeInfo.text("Speed: not selected");
                }
                if (TileTorcherinoBase.getValue(11) == 0 && Config.BooleanRender) {
                    probeInfo.text("Render: not selected");
                }
                if (TileTorcherinoBase.getValue(11) == 1 && Config.BooleanRender) {
                    probeInfo.text("Render line");
                }
                if (TileTorcherinoBase.getValue(11) == 2 && Config.BooleanRender) {
                    probeInfo.text("Render box");
                }
                if (TileTorcherinoBase.getValue(11) == 3 && Config.BooleanRender) {
                    probeInfo.text("Rend combined");
                }
                if (TileTorcherinoBase.getValue(11) == 4 && Config.BooleanRender) {
                    probeInfo.text("Rend wave");
                }
                if (TileTorcherinoBase.getValue(11) == 5 && Config.BooleanRender) {
                    probeInfo.text("Rend cube");
                }
                if (TileTorcherinoBase.getValue(2) == 1) {
                    probeInfo.text("Timer enabled");
                } else {
                    probeInfo.text("Timer disabled");
                }
                if (TileTorcherinoBase.getValue(1) > 0) {
                    probeInfo.text("Timer: " + TileTorcherinoBase.getValue(1));
                }
            }
        }
    }
}