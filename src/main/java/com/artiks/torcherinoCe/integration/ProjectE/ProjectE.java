package com.artiks.torcherinoCe.integration.ProjectE;

import com.artiks.torcherinoCe.Block.Energy.Torcherino.RegistryAcceleration;
import com.artiks.torcherinoCe.Register.RegistryBlock;
import com.artiks.torcherinoCe.Register.RegistryItems;
import com.artiks.torcherinoCe.Tags;
import com.artiks.torcherinoCe.torcherinoCe;
import com.artiks.torcherinoCe.utility.Config;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class ProjectE {

    public static void postInit() {
        if (Loader.isModLoaded("projecte") && Config.IntegrationProjectE) {
            registerEMC();
            RegistryAcceleration.blacklistString("projecte:dm_pedestal");
            torcherinoCe.logger.info(Tags.MODNAME + " Integration projectE : Enabled");
        } else {
            torcherinoCe.logger.info(Tags.MODNAME + " Integration projectE : Disabled");
        }
    }


    @SuppressWarnings("deprecation")
    private static void registerEMC() {
        IEMCProxy emcProxy = ProjectEAPI.getEMCProxy();
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_particle), 5411);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.raw_time_nugget), 5483);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.DragonEgg),183_541);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_energy_star),10_000_000);
        emcProxy.registerCustomEMC(new ItemStack(RegistryItems.time_plate),8_000_000);
        emcProxy.registerCustomEMC(new ItemStack(Item.getItemFromBlock(RegistryBlock.Block_Red_Star)),10_000_000*9);
    }
}