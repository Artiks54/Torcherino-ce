package com.ariks.torcherino.Register;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.integration.ProjectE.AddonProjectE;
import com.ariks.torcherino.util.Config;
import net.minecraftforge.fml.common.Loader;

public class RegistryIntegration {
    public static void Registry(){
        if (Loader.isModLoaded("projecte") && Config.IntegrationProjectE) {
                AddonProjectE.registerEMC();
                AccelerationRegistry.blacklistString("projecte:dm_pedestal");
                Torcherino.logger.info(Torcherino.MOD_NAME + " Integration projectE : Enabled");
            } else {
                Torcherino.logger.info(Torcherino.MOD_NAME + " Integration projectE : Disabled");
        }
        if(Loader.isModLoaded("topaddons") && Config.IntegrationTheOneProbeTA){
            Torcherino.logger.info(Torcherino.MOD_NAME + " Integration topAddons : Enabled");
        }else {
            Torcherino.logger.info(Torcherino.MOD_NAME + " Integration topAddons : Disabled");
        }
    }
}
