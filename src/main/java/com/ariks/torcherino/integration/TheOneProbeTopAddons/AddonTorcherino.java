package com.ariks.torcherino.integration.TheOneProbeTopAddons;

import com.ariks.torcherino.Block.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
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
import net.minecraft.world.World;

@TOPAddon(dependency = Torcherino.MOD_ID, order = 0)
public class AddonTorcherino extends AddonBlank {
    @Override
    public void addProbeInfo(final ProbeMode probeMode, final IProbeInfo probeInfo, final EntityPlayer entityPlayer, final World world, final IBlockState iBlockState, final IProbeHitData data) {
        TileEntity tile = world.getTileEntity(data.getPos());
        if(Config.IntegrationTheOneProbeTA){
            if (tile instanceof TileAcceleration) {
                TileAcceleration TileAcceleration = (TileAcceleration) tile;
                probeInfo.text("Time: "+TileAcceleration.getTimeStorage());
            }
            if (tile instanceof TileTimeStorage) {
                TileTimeStorage tileTimeStorage = (TileTimeStorage) tile;
                probeInfo.text("Time: " + tileTimeStorage.getTimeStorage());
            }
            if (tile instanceof TileCollectors) {
                TileCollectors TileCollectors = (TileCollectors) tile;
                probeInfo.text("Time: " + TileCollectors.getTimeStorage());
            }
            if (tile instanceof TileTimeManipulator) {
                TileTimeManipulator TileTimeManipulator = (TileTimeManipulator) tile;
                probeInfo.text("Progress");
                probeInfo.progress(TileTimeManipulator.percent,100);
            }
            if (tile instanceof TileParticleCollector) {
                TileParticleCollector TileParticleCollector = (TileParticleCollector) tile;
                probeInfo.text("Progress");
                probeInfo.progress(TileParticleCollector.percent,100);
            }
            if (tile instanceof TileTorcherinoBase) {
                TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tile;
                if(TileTorcherinoBase.booleanMode == 0){
                    probeInfo.text("Working off");
                }
                else if(TileTorcherinoBase.booleanMode == 1){
                    probeInfo.text( "Always on");
                }
                else if(TileTorcherinoBase.booleanMode == 2){
                    probeInfo.text("Redstone mode");
                }
                else if(TileTorcherinoBase.booleanMode == 3){
                    probeInfo.text("Redstone reverse mode");
                }
                if (TileTorcherinoBase.CurrentRadius >= 1) {
                    probeInfo.text("Radius: " + TileTorcherinoBase.CurrentRadius + "x" + TileTorcherinoBase.CurrentRadius + "x" + TileTorcherinoBase.CurrentRadius);
                } else {
                    probeInfo.text("Radius");
                }
                if (TileTorcherinoBase.CurrentSpeed >= 1) {
                    probeInfo.text("Speed: " + TileTorcherinoBase.CurrentSpeed * TileTorcherinoBase.MaxAcceleration * 100 + "%");
                } else {
                    probeInfo.text("Speed");
                }
                if (TileTorcherinoBase.booleanRender == 0 && Config.BooleanRender) {
                    probeInfo.text("Render off");
                }
                if (TileTorcherinoBase.booleanRender == 1 && Config.BooleanRender) {
                    probeInfo.text("Render line");
                }
                if (TileTorcherinoBase.booleanRender == 2 && Config.BooleanRender) {
                    probeInfo.text("Render box");
                }
                if (TileTorcherinoBase.booleanRender == 3 && Config.BooleanRender) {
                    probeInfo.text("Rend combined");
                }
            }
        }
    }
}