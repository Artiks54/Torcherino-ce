package com.ariks.torcherinoCe.integration.CraftTweaker;

import com.ariks.torcherinoCe.Block.Torcherino.RegistryAcceleration;
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