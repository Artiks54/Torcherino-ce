package com.ariks.torcherinoCe.integration.ProjectE;

import com.ariks.torcherinoCe.Register.RegistryItems;
import com.ariks.torcherinoCe.utility.Config;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.ItemStack;

public class AddonProjectE  {
    public static void registerEMC() {
        IEMCProxy emcProxy = ProjectEAPI.getEMCProxy();
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_particle), Config.EMCParticle);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_ingot),Config.EMCIngot);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_energy_star),Config.EMCStar);
    }
}