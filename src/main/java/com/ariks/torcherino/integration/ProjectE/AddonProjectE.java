package com.ariks.torcherino.integration.ProjectE;

import com.ariks.torcherino.Register.RegistryItems;
import com.ariks.torcherino.util.Config;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.ItemStack;

public class AddonProjectE  {
    public static void registerEMC() {
        IEMCProxy emcProxy = ProjectEAPI.getEMCProxy();
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_particle), Config.EMCParticle);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_ingot),Config.EMCIngot);
    }
}