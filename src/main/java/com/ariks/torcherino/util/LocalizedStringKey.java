package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraft.client.resources.I18n;

public class LocalizedStringKey {
     public static final String modid = Torcherino.MOD_ID + "_";
     public String ButtonStrWork = I18n.format(modid+"gui.button.StrWork");
     public String ButtonStrRender = I18n.format(modid+"gui.button.StrRender");
     public String StrOn = I18n.format(modid+"gui.button.StrOn");
     public String StrOff = I18n.format(modid+"gui.button.StrOff");
     public String StrNotSelected = I18n.format(modid+"gui.button.NotSelected");
     public String StrRedstoneMode = I18n.format(modid+"gui.button.StrRedstone");
     public String StrModes = I18n.format(modid+"gui.info.modes");
     public String StrArea = I18n.format(modid+"gui.info.area");
     public String Info = I18n.format(modid+"gui.info.text");
     //Time Storage Tile
     public String TimeCollected = I18n.format(modid+"gui.info.time.collected");
     public String InfoTimeStorage = I18n.format(modid+"gui.info.text.storage");
     //Aceleration Tile
     public String StrAceleration = I18n.format(modid+"gui.info.aceleration");
     public String StrAcelerationInfoItem = I18n.format(modid+"item.aceleration.info");
     public String StrGuiAceleration = I18n.format(modid+"gui.info.text.aceleration");
     //Collector Tile
     public String StrCollectorsInfoItem = I18n.format(modid+"item.collectors.info");
     public String StrCollectorsGuiInfo = I18n.format(modid+"gui.info.text.collectors");
     //Item storage
     public String Str_Time_Storage_Tooltip = I18n.format(modid+"item.tooltip.time_storage");
     public String StrStorageInfoItem = I18n.format(modid+"item.storage.info");
     //Item Wand
     public String StrWandInfoItem = I18n.format(modid+"item.wand.info");
     public String Str_Time_Wand_Tooltip = I18n.format(modid+"item.tooltip.time_wand");
}
