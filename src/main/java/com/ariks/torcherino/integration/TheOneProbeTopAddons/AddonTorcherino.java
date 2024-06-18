package com.ariks.torcherino.integration.TheOneProbeTopAddons;

import com.ariks.torcherino.Block.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Block.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.TileTorcherinoBase;
import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import io.github.drmanganese.topaddons.addons.AddonBlank;
import io.github.drmanganese.topaddons.api.TOPAddon;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
                probeInfo.text("Time: "+TileAcceleration.GetTimeStorage());
            }
            if (tile instanceof TileTimeStorage) {
                TileTimeStorage tileTimeStorage = (TileTimeStorage) tile;
                probeInfo.text("Time: " + tileTimeStorage.GetTimeStorage());
            }
            if (tile instanceof TileCollectors) {
                TileCollectors TileCollectors = (TileCollectors) tile;
                probeInfo.text("Time: " + TileCollectors.GetTimeStorage());
            }
            if (tile instanceof TileTimeManipulator) {
                TileTimeManipulator TileTimeManipulator = (TileTimeManipulator) tile;
                probeInfo.text("Time: " + TileTimeManipulator.GetTimeStorage());
            }
            if (tile instanceof TileParticleCollector) {
                TileParticleCollector TileParticleCollector = (TileParticleCollector) tile;
                probeInfo.text("Progress:");
                probeInfo.progress(TileParticleCollector.percent, 100);
                probeInfo.text("Level: " + ((TileParticleCollector) tile).level);
                if (!((TileParticleCollector) tile).getStackInSlot(0).isEmpty()) {
                    probeInfo.item(new ItemStack(RegistryItems.time_particle, ((TileParticleCollector) tile).getStackInSlot(0).getCount()));
                }
            }
            if (tile instanceof TileTorcherinoBase) {
                TileTorcherinoBase TileTorcherinoBase = (TileTorcherinoBase) tile;
                if(TileTorcherinoBase.getValue(3) == 0){
                    probeInfo.text("Working off");
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
                if (TileTorcherinoBase.getValue(2) >= 1) {
                    probeInfo.text("Speed: " + TileTorcherinoBase.getValue(2) * TileTorcherinoBase.MaxAcceleration * 100 + "%");
                } else {
                    probeInfo.text("Speed");
                }
                if (TileTorcherinoBase.getValue(4) == 0 && Config.BooleanRender) {
                    probeInfo.text("Render off");
                }
                if (TileTorcherinoBase.getValue(4) == 1 && Config.BooleanRender) {
                    probeInfo.text("Render line");
                }
                if (TileTorcherinoBase.getValue(4) == 2 && Config.BooleanRender) {
                    probeInfo.text("Render box");
                }
                if (TileTorcherinoBase.getValue(4) == 3 && Config.BooleanRender) {
                    probeInfo.text("Rend combined");
                }
            }
        }
    }
}