package com.ariks.torcherinoCe.integration;

import com.ariks.torcherinoCe.Block.Torcherino.RegistryAcceleration;
import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.torcherinoCe;
import com.ariks.torcherinoCe.integration.ProjectE.AddonProjectE;
import com.ariks.torcherinoCe.utility.Config;
import net.minecraftforge.fml.common.Loader;

public final class RegistryIntegration {
    public static void Registry() {
        if (Loader.isModLoaded("projecte") && Config.IntegrationProjectE) {
            AddonProjectE.registerEMC();
            RegistryAcceleration.blacklistString("projecte:dm_pedestal");
            torcherinoCe.logger.info(Tags.MODNAME + " Integration projectE : Enabled");
            } else {
            torcherinoCe.logger.info(Tags.MODNAME + " Integration projectE : Disabled");
        }
        if(Loader.isModLoaded("topaddons") && Loader.isModLoaded("theoneprobe") && Config.IntegrationTheOneProbeTA){
            torcherinoCe.logger.info(Tags.MODNAME + " Integration topAddons : Enabled");
        }else {
            torcherinoCe.logger.info(Tags.MODNAME + " Integration topAddons : Disabled");
       }
    }
}