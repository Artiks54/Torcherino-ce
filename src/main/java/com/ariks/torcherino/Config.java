package com.ariks.torcherino;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
public class Config {
    protected static Configuration config;
    public static String[] blacklistedBlocks;
    public static String[] blacklistedTiles;
    public static String TileName;
    public static String TileName2;
    public static String TextS = "Acceleration 1 = 100%";
    public static String TextM = "Number of acceleration modes";
    public static String TextR = "Working radius";
    public static boolean PracTicleSpawn,logPlacement,savedata,defaultModes;
    public static int tickCount;
    public static String colorSettingStop,colorSettingStart;
    public static int
            Torch_lvl1_S,Torch_lvl1_M,Torch_lvl1_R,
            Torch_lvl2_S,Torch_lvl2_M,Torch_lvl2_R,
            Torch_lvl3_S,Torch_lvl3_M,Torch_lvl3_R,
            Torch_lvl4_S, Torch_lvl4_M,Torch_lvl4_R,
            Torch_lvl5_S,Torch_lvl5_M,Torch_lvl5_R;
    public static int
            CTorch_lvl1_S, CTorch_lvl1_M, CTorch_lvl1_R,
            CTorch_lvl2_S, CTorch_lvl2_M, CTorch_lvl2_R,
            CTorch_lvl3_S, CTorch_lvl3_M, CTorch_lvl3_R,
            CTorch_lvl4_S, CTorch_lvl4_M, CTorch_lvl4_R,
            CTorch_lvl5_S, CTorch_lvl5_M, CTorch_lvl5_R;

    public static int minValue = 1;
    public static void init(File file) {
        config = new Configuration(file);
        try {config.load();
            //Category
            String General = "General";
            String Particle = "Particle";
            String ColorText = "ColorText";
            Short maxShort = 32767;

            //Mod
            blacklistedBlocks = config.getStringList("blacklistedBlocks", "blacklist", new String[]{}, "modid:unlocalized");
            blacklistedTiles = config.getStringList("blacklistedTiles", "blacklist", new String[]{}, "Fully qualified class name");
            defaultModes = config.getBoolean("default_Change_Mode",General,false,"Old shift mode change of operation and speed");
            logPlacement = config.getBoolean("logPlacement", General, true, "(For Server Owners) Is it logged when someone places a Torcherino?");
            savedata = config.getBoolean("SaveData",General,true,"Saving data after re-entering the world");
            //Particle
            PracTicleSpawn = config.getBoolean("SpawnParticle", Particle, true, "Spawn of particles in the radius of work");
            tickCount = config.getInt("TickCount",Particle,25,20,maxShort,"The required number of ticks for the spawn of particles");
            //ColorText
            colorSettingStop = config.getString("Color Don'tWork", ColorText, "RED", "Allows you to change the color when switching operating modes." + "All currently available colors can be viewed at this link or on the ForgeAPI-1.12.2 website : https://clck.ru/385Fyh");
            colorSettingStart = config.getString("Color Work", ColorText, "GREEN", "");
            //Torch_lvl_1
            TileName = "Torcherino_lvl_1";
            Torch_lvl1_S = config.getInt("Speed",TileName,1,minValue, maxShort,TextS);
            Torch_lvl1_M = config.getInt("Modes",TileName,5,minValue,maxShort,TextM);
            Torch_lvl1_R = config.getInt("WorkR",TileName,5,minValue, maxShort,TextR);
            //Torch_lvl_2
            TileName = "Torcherino_lvl_2";
            Torch_lvl2_S = config.getInt("Speed",TileName,1,minValue,maxShort,TextS);
            Torch_lvl2_M = config.getInt("Modes",TileName,10,minValue,maxShort,TextM);
            Torch_lvl2_R = config.getInt("WorkR",TileName,10,minValue, maxShort,TextR);
            //Torch_lvl_3
            TileName = "Torcherino_lvl_3";
            Torch_lvl3_S = config.getInt("Speed",TileName,1,minValue,maxShort,TextS);
            Torch_lvl3_M = config.getInt("Modes",TileName,15,minValue,maxShort,TextM);
            Torch_lvl3_R = config.getInt("WorkR",TileName,15,minValue, maxShort,TextR);
            //Torch_lvl_4
            TileName = "Torcherino_lvl_4";
            Torch_lvl4_S = config.getInt("Speed",TileName,1,minValue,maxShort,TextS);
            Torch_lvl4_M = config.getInt("Modes",TileName,20,minValue,maxShort,TextM);
            Torch_lvl4_R = config.getInt("WorkR",TileName,20,minValue, maxShort,TextR);
            //Torch_lvl_5
            TileName = "Torcherino_lvl_5";
            Torch_lvl5_S = config.getInt("Speed",TileName,1,minValue,maxShort,TextS);
            Torch_lvl5_M = config.getInt("Modes",TileName,25,minValue,maxShort,TextM);
            Torch_lvl5_R = config.getInt("WorkR",TileName,25,minValue, maxShort,TextR);

            //Compressed_Torch_lvl_1
            TileName2 = "Compressed_Torch_lvl_1";
            CTorch_lvl1_S = config.getInt("Speed",TileName2,9,minValue,maxShort,TextS);
            CTorch_lvl1_M = config.getInt("Modes",TileName2,5,minValue,maxShort,TextM);
            CTorch_lvl1_R = config.getInt("WorkR",TileName2,5,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_2
            TileName2 = "Compressed_Torch_lvl_2";
            CTorch_lvl2_S = config.getInt("Speed",TileName2,9,minValue,maxShort,TextS);
            CTorch_lvl2_M = config.getInt("Modes",TileName2,10,minValue,maxShort,TextM);
            CTorch_lvl2_R = config.getInt("WorkR",TileName2,10,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_3
            TileName2 = "Compressed_Torch_lvl_3";
            CTorch_lvl3_S = config.getInt("Speed",TileName2,9,minValue,maxShort,TextS);
            CTorch_lvl3_M = config.getInt("Modes",TileName2,15,minValue,maxShort,TextM);
            CTorch_lvl3_R = config.getInt("WorkR",TileName2,15,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_4
            TileName2 = "Compressed_Torch_lvl_4";
            CTorch_lvl4_S = config.getInt("Speed",TileName2,9,minValue,maxShort,TextS);
            CTorch_lvl4_M = config.getInt("Modes",TileName2,20,minValue,maxShort,TextM);
            CTorch_lvl4_R = config.getInt("WorkR",TileName2,20,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_5
            TileName2 = "Compressed_Torch_lvl_5";
            CTorch_lvl5_S = config.getInt("Speed",TileName2,9,minValue,maxShort,TextS);
            CTorch_lvl5_M = config.getInt("Modes",TileName2,25,minValue,maxShort,TextM);
            CTorch_lvl5_R = config.getInt("WorkR",TileName2,25,minValue, maxShort,TextR);



        } finally {if(config.hasChanged()) config.save();}}

    public static void registerConfig(FMLPreInitializationEvent event){
        Torcherino.config = new File(event.getModConfigurationDirectory()+"/"+Torcherino.MOD_ID);
        Torcherino.config.mkdirs();
        init(new File(Torcherino.config.getPath(),Torcherino.MOD_ID+".cfg"));
    }
}
