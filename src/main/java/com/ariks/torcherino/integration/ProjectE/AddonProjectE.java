package com.ariks.torcherino.integration.ProjectE;

import com.ariks.torcherino.Register.RegistryItems;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.ItemStack;

public class AddonProjectE  {
    public static void registerEMC() {
        IEMCProxy emcProxy = ProjectEAPI.getEMCProxy();
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_particle),5411);
    }
}