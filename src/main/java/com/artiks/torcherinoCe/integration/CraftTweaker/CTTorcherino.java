package com.artiks.torcherinoCe.integration.CraftTweaker;

import com.artiks.torcherinoCe.Block.Energy.Torcherino.RegistryAcceleration;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.tce.Torcherino")
public class CTTorcherino {

    @ZenMethod
    public static void blacklist(String string) {
        RegistryAcceleration.blacklistString(string);
    }
}