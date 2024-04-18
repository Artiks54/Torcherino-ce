package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraft.client.resources.I18n;

public class LocalizedStringKey {
     public static final String modid = Torcherino.MOD_ID + "_";
     public String ButtonStrWork = I18n.format(modid+"gui.button.StrWork");
     public String ButtonStrRender = I18n.format(modid+"gui.button.StrRender");
     public String StrOn = I18n.format(modid+"gui.button.StrOn");
     public String StrOff = I18n.format(modid+"gui.button.StrOff");
     public String Info = I18n.format(modid+"gui.info.text");
     public String InfoCollector = I18n.format(modid+"gui.info.text.collector");
     public String TimeCollector = I18n.format(modid+"gui.info.time.collector");
     public String StrModes = I18n.format(modid+"gui.info.modes");
     public String StrArea = I18n.format(modid+"gui.info.area");
     public String StrAceleration = I18n.format(modid+"gui.info.aceleration");
     public String StrCollectorInfoItem = I18n.format(modid+"item.collector.info");
     public String StrWandInfoItem = I18n.format(modid+"item.wand.info");
     public String Str_Time_Storage_Tooltip = I18n.format(modid+"item.tooltip.time_storage");
     public String Str_Time_Wand_Tooltip = I18n.format(modid+"item.tooltip.time_wand");
}
