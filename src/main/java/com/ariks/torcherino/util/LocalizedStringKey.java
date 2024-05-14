package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraft.client.resources.I18n;

public class LocalizedStringKey {
     public static final String modid = Torcherino.MOD_ID + "_";
     //TileTorcherino
     public String StrTextSpeed = I18n.format(modid+"gui.text.speed");
     public String StrRedstoneMode = I18n.format(modid+"gui.button.StrRedstone");
     public String StrRedstoneModeRevers = I18n.format(modid+"gui.button.StrRedstoneRevers");
     public String StrTextRadius = I18n.format(modid+"gui.text.radius");
     public String StrTextWorking = I18n.format(modid+"gui.text.working");
     public String StrTextAlways = I18n.format(modid+"gui.text.always");
     public String StrTextRenderLine = I18n.format(modid+"gui.text.render.line");
     public String StrTextRenderBox = I18n.format(modid+"gui.text.render.box");
     public String StrTextRenderComb = I18n.format(modid+"gui.text.render.comb");
     public String StrTextRenderOff = I18n.format(modid+"gui.text.render.off");
     public String StrTextRenderNull = I18n.format(modid+"gui.text.render.null");
     //Time Manipulator tile
     public String StrTextDay = I18n.format(modid+"gui.text.tile_time_manipulator.day");
     public String StrTextNight = I18n.format(modid+"gui.text.tile_time_manipulator.night");
     public String StrTextProgress = I18n.format(modid+"gui.text.tile_time_manipulator.progress");
     //Time Storage Tile
     public String TimeCollected = I18n.format(modid+"gui.text.time");
     //Item storage
     public String Str_Time_Storage_Tooltip = I18n.format(modid+"item.tooltip.time_storage");
     //Item Wand
     public String StrWandInfoItem = I18n.format(modid+"item.wand.info");
     public String Str_Time_Wand_Tooltip = I18n.format(modid+"item.tooltip.time_wand");
}