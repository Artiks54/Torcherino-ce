package com.ariks.torcherino.integration.CraftTweaker;

import com.ariks.torcherino.Register.RegistryAcceleration;
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