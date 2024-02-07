package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.io.File;

public class Config {
    protected static Configuration config;
    public static String[] blacklistedBlocks;
    public static String[] blacklistedTiles;
    public static String TileNameTorch;
    public static String TileNameCompressed;
    private final static String TextS = "Acceleration 1 = 100%";
    private final static String TextM = "Number of acceleration modes";
    private final static String TextR = "Working radius";
    public static boolean PracTicleSpawn,logPlacement;
    public static int tickCount;
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
            short maxShort = 32767;
            //Mod
            blacklistedBlocks = config.getStringList("blacklistedBlocks", "blacklist", new String[]{}, "modid:unlocalized");
            blacklistedTiles = config.getStringList("blacklistedTiles", "blacklist", new String[]{}, "Fully qualified class name");
            logPlacement = config.getBoolean("logPlacement", General, true, "(For Server Owners) Is it logged when someone places a Torcherino?");
            //Particle
            PracTicleSpawn = config.getBoolean("SpawnParticle", Particle, true, "Spawn of particles in the radius of work");
            tickCount = config.getInt("TickCount",Particle,25,20,maxShort,"The required number of ticks for the spawn of particles");
            //Torch_lvl_1
            TileNameTorch = "Torcherino_lvl_1";
            Torch_lvl1_S = config.getInt("Speed",TileNameTorch,1,minValue, maxShort,TextS);
            Torch_lvl1_M = config.getInt("Modes",TileNameTorch,5,minValue,maxShort,TextM);
            Torch_lvl1_R = config.getInt("WorkR",TileNameTorch,5,minValue, maxShort,TextR);
            //Torch_lvl_2
            TileNameTorch = "Torcherino_lvl_2";
            Torch_lvl2_S = config.getInt("Speed",TileNameTorch,1,minValue,maxShort,TextS);
            Torch_lvl2_M = config.getInt("Modes",TileNameTorch,10,minValue,maxShort,TextM);
            Torch_lvl2_R = config.getInt("WorkR",TileNameTorch,10,minValue, maxShort,TextR);
            //Torch_lvl_3
            TileNameTorch = "Torcherino_lvl_3";
            Torch_lvl3_S = config.getInt("Speed",TileNameTorch,1,minValue,maxShort,TextS);
            Torch_lvl3_M = config.getInt("Modes",TileNameTorch,15,minValue,maxShort,TextM);
            Torch_lvl3_R = config.getInt("WorkR",TileNameTorch,15,minValue, maxShort,TextR);
            //Torch_lvl_4
            TileNameTorch = "Torcherino_lvl_4";
            Torch_lvl4_S = config.getInt("Speed",TileNameTorch,1,minValue,maxShort,TextS);
            Torch_lvl4_M = config.getInt("Modes",TileNameTorch,20,minValue,maxShort,TextM);
            Torch_lvl4_R = config.getInt("WorkR",TileNameTorch,20,minValue, maxShort,TextR);
            //Torch_lvl_5
            TileNameTorch = "Torcherino_lvl_5";
            Torch_lvl5_S = config.getInt("Speed",TileNameTorch,1,minValue,maxShort,TextS);
            Torch_lvl5_M = config.getInt("Modes",TileNameTorch,25,minValue,maxShort,TextM);
            Torch_lvl5_R = config.getInt("WorkR",TileNameTorch,25,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_1
            TileNameCompressed = "Torcherino_lvl_1_Compressed";
            CTorch_lvl1_S = config.getInt("Speed",TileNameCompressed,9,minValue,maxShort,TextS);
            CTorch_lvl1_M = config.getInt("Modes",TileNameCompressed,5,minValue,maxShort,TextM);
            CTorch_lvl1_R = config.getInt("WorkR",TileNameCompressed,5,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_2
            TileNameCompressed = "Torcherino_lvl_2_Compressed";
            CTorch_lvl2_S = config.getInt("Speed",TileNameCompressed,9,minValue,maxShort,TextS);
            CTorch_lvl2_M = config.getInt("Modes",TileNameCompressed,10,minValue,maxShort,TextM);
            CTorch_lvl2_R = config.getInt("WorkR",TileNameCompressed,10,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_3
            TileNameCompressed = "Torcherino_lvl_3_Compressed";
            CTorch_lvl3_S = config.getInt("Speed",TileNameCompressed,9,minValue,maxShort,TextS);
            CTorch_lvl3_M = config.getInt("Modes",TileNameCompressed,15,minValue,maxShort,TextM);
            CTorch_lvl3_R = config.getInt("WorkR",TileNameCompressed,15,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_4
            TileNameCompressed = "Torcherino_lvl_4_Compressed";
            CTorch_lvl4_S = config.getInt("Speed",TileNameCompressed,9,minValue,maxShort,TextS);
            CTorch_lvl4_M = config.getInt("Modes",TileNameCompressed,20,minValue,maxShort,TextM);
            CTorch_lvl4_R = config.getInt("WorkR",TileNameCompressed,20,minValue, maxShort,TextR);
            //Compressed_Torch_lvl_5
            TileNameCompressed = "Torcherino_lvl_5_Compressed";
            CTorch_lvl5_S = config.getInt("Speed",TileNameCompressed,9,minValue,maxShort,TextS);
            CTorch_lvl5_M = config.getInt("Modes",TileNameCompressed,25,minValue,maxShort,TextM);
            CTorch_lvl5_R = config.getInt("WorkR",TileNameCompressed,25,minValue, maxShort,TextR);
        } finally {if(config.hasChanged()) config.save();}}
    public static void registerConfig(FMLPreInitializationEvent event){
        Torcherino.config = new File(event.getModConfigurationDirectory()+"/"+Torcherino.MOD_ID);
        Torcherino.config.mkdirs();
        init(new File(Torcherino.config.getPath(),Torcherino.MOD_ID+".cfg"));
    }
}
