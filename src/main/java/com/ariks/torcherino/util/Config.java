package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class Config {
    protected static Configuration config;
    public static String[] blacklistedBlocks;
    public static String[] blacklistedTiles;
    public static String TileName;
    private final static String TextS = "Speed 1 = 100%";
    private final static String TextM = "Number of speed modes";
    private final static String TextR = "Maximum Radius";
    private final static String radius = "_radius";
    private final static String speed = "_speed";
    private final static String modes = "_number_modes";
    public static boolean DebugMod;
    public static boolean BooleanParcWand,BooleanRender,BooleanVisualWork;
    public static boolean IntegrationProjectE,IntegrationTheOneProbeTA;
    public static int
            Torch_lvl1_S,Torch_lvl1_M,Torch_lvl1_R, Torch_lvl2_S,Torch_lvl2_M,Torch_lvl2_R,
            Torch_lvl3_S,Torch_lvl3_M,Torch_lvl3_R, Torch_lvl4_S, Torch_lvl4_M,Torch_lvl4_R,
            Torch_lvl5_S,Torch_lvl5_M,Torch_lvl5_R;
    public static int
            CTorch_lvl1_S, CTorch_lvl1_M, CTorch_lvl1_R, CTorch_lvl2_S, CTorch_lvl2_M, CTorch_lvl2_R,
            CTorch_lvl3_S, CTorch_lvl3_M, CTorch_lvl3_R, CTorch_lvl4_S, CTorch_lvl4_M, CTorch_lvl4_R,
            CTorch_lvl5_S, CTorch_lvl5_M, CTorch_lvl5_R;
    public static int
            DTorch_lvl1_S, DTorch_lvl1_M, DTorch_lvl1_R, DTorch_lvl2_S, DTorch_lvl2_M, DTorch_lvl2_R,
            DTorch_lvl3_S, DTorch_lvl3_M, DTorch_lvl3_R, DTorch_lvl4_S, DTorch_lvl4_M, DTorch_lvl4_R,
            DTorch_lvl5_S, DTorch_lvl5_M, DTorch_lvl5_R;
    public static int AccelerationSpeed,AccelerationRadius;
    public static int TimeStorageMaxTime;
    public static int RequiredGeneratorParticle;
    public static int RequiredTimeManipulator;
    public static int MaxStorageTimeCollectors;
    public static int Stored_Time_Bottle_Lvl_1,Stored_Time_Bottle_Lvl_2,Stored_Time_Bottle_Lvl_3,Stored_Time_Bottle_Lvl_4,Stored_Time_Bottle_Lvl_5,Stored_Time_Bottle_infinite;
    public static int SpeedWand_lvl1,SpeedWand_lvl2,SpeedWand_lvl3,SpeedWand_lvl4,SpeedWand_lvl5,SpeedWand_infinite;
    public static void init(File file) {
        config = new Configuration(file);
        try {config.load();
            String General = "General";
            String Render = "Render";
            String Item = "Item";
            String Tile = "Tile";
            String TileTorcherino = "Tile_Torcherino_lvl1";
            String TileTorcherino2 = "Tile_Torcherino_lvl2";
            String TileTorcherino3 = "Tile_Torcherino_lvl3";
            String TileTorcherino4 = "Tile_Torcherino_lvl4";
            String TileTorcherino5 = "Tile_Torcherino_lvl5";
            config.setCategoryComment(General,"General settings");
            config.setCategoryComment(Item,"Item settings");
            config.setCategoryComment(Render,"Render settings");
            config.setCategoryComment(Tile,"TileEntity settings .Before changing these values, it is best to destroy the already installed TileEntity \n" +
                    "If this is not possible, then in order for the value in TileEntity to change, it will need to be set again.");
            config.setCategoryComment(TileTorcherino,"TileEntity settings .Before changing these values, it is best to destroy the already installed TileEntity \n" +
                    "If this is not possible, then in order for the value in TileEntity to change, it will need to be set again.");
//BlackList
            blacklistedBlocks = config.getStringList("blacklistedBlocks", "blacklist", new String[]{}, "modid:unlocalized");
            blacklistedTiles = config.getStringList("blacklistedTiles", "blacklist", new String[]{}, "Fully qualified class name");
//General
            DebugMod = config.getBoolean("Debug_Mode",General,false,"Debug modes");
            IntegrationProjectE = config.getBoolean("integration_top_addons",General,true,"Integration TheOneProbe-TopAddons");
            IntegrationTheOneProbeTA = config.getBoolean("integration_ProjectE",General,true,"Integration ProjectE");
//Storage_Time_In_Bottle
            Stored_Time_Bottle_Lvl_1 = config.getInt("Time_Storage_lvl_1",Item,500,1,Integer.MAX_VALUE,"Storage time bottle lvl 1");
            Stored_Time_Bottle_Lvl_2 = config.getInt("Time_Storage_lvl_2",Item,1500,1,Integer.MAX_VALUE,"Storage time bottle lvl 2");
            Stored_Time_Bottle_Lvl_3 = config.getInt("Time_Storage_lvl_3",Item,4500,1,Integer.MAX_VALUE,"Storage time bottle lvl 3");
            Stored_Time_Bottle_Lvl_4 = config.getInt("Time_Storage_lvl_4",Item,13500,1,Integer.MAX_VALUE,"Storage time bottle lvl 4");
            Stored_Time_Bottle_Lvl_5 = config.getInt("Time_Storage_lvl_5",Item,45000,1,Integer.MAX_VALUE,"Storage time bottle lvl 5");
            Stored_Time_Bottle_infinite = config.getInt("Time_Storage_infinite",Item,Integer.MAX_VALUE,1,Integer.MAX_VALUE,"Storage time infinite");
//wand
            SpeedWand_lvl1 = config.getInt("Time_Wand_Speed_lvl1",Item,3,1,Byte.MAX_VALUE,"Speed Time Wand lvl 1. 1 = 100%.....");
            SpeedWand_lvl2 = config.getInt("Time_Wand_Speed_lvl2",Item,6,1,Byte.MAX_VALUE,"Speed Time Wand lvl 2. 1 = 100%.....");
            SpeedWand_lvl3 = config.getInt("Time_Wand_Speed_lvl3",Item,9,1,Byte.MAX_VALUE,"Speed Time Wand lvl 3. 1 = 100%.....");
            SpeedWand_lvl4 = config.getInt("Time_Wand_Speed_lvl4",Item,12,1,Byte.MAX_VALUE,"Speed Time Wand lvl 4. 1 = 100%.....");
            SpeedWand_lvl5 = config.getInt("Time_Wand_Speed_lvl5",Item,15,1,Byte.MAX_VALUE,"Speed Time Wand lvl 5. 1 = 100%.....");
            SpeedWand_infinite = config.getInt("Time_Wand_Speed_lvl6",Item,20,1,Byte.MAX_VALUE,"Speed Time Wand infinite. 1 = 100%.....");
//Render
            BooleanParcWand = config.getBoolean("Time_Wand_Parc",Render,true,"Spawn Particle Wand");
            BooleanVisualWork = config.getBoolean("Tile_Visual_Work",Render,true,"Tile spawn Particle flame");
            BooleanRender = config.getBoolean("Tile_Render",Render,true,"Tile render true/false");
//Time manipulator tile
            RequiredTimeManipulator = config.getInt("Tile_Time_Manipulator",Tile,54110,1,Short.MAX_VALUE,"Need ticks to generate 100%");
//Generator particle tile
            RequiredGeneratorParticle = config.getInt("Tile_Generator_Particle",Tile,1154,1,Short.MAX_VALUE,"Need ticks to generate 100%");
//Storage tile
            TimeStorageMaxTime = config.getInt("Tile_Time_Storage",Tile,1000000,1,Integer.MAX_VALUE,"Time max time Storage");
//Collectors tile
            MaxStorageTimeCollectors = config.getInt("Tile_Time_Collectors_MaxStorage",Tile,1500,1,Integer.MAX_VALUE,"Maximum storage size");
//Acceleration tile
            AccelerationSpeed = config.getInt("Tile_Time_Acceleration_Speed",Tile,3,1, Byte.MAX_VALUE,TextS);
            AccelerationRadius = config.getInt("Tile_Time_Acceleration_Radius",Tile,3,1,Byte.MAX_VALUE,TextR);
//Torch_lvl_1
            TileName = "Torcherino_lvl_1";
            Torch_lvl1_S = config.getInt(TileName+speed,TileTorcherino,1,1,Byte.MAX_VALUE,TextS);
            Torch_lvl1_M = config.getInt(TileName+modes,TileTorcherino,3,1,Byte.MAX_VALUE,TextM);
            Torch_lvl1_R = config.getInt(TileName+radius,TileTorcherino,3,1,Byte.MAX_VALUE,TextR);
//Torch_lvl_2
            TileName = "Torcherino_lvl_2";
            Torch_lvl2_S = config.getInt(TileName+speed,TileTorcherino2,1,1,Byte.MAX_VALUE,TextS);
            Torch_lvl2_M = config.getInt(TileName+modes,TileTorcherino2,6,1,Byte.MAX_VALUE,TextM);
            Torch_lvl2_R = config.getInt(TileName+radius,TileTorcherino2,6,1,Byte.MAX_VALUE,TextR);
//Torch_lvl_3
            TileName = "Torcherino_lvl_3";
            Torch_lvl3_S = config.getInt(TileName+speed,TileTorcherino3,1,1,Byte.MAX_VALUE,TextS);
            Torch_lvl3_M = config.getInt(TileName+modes,TileTorcherino3,9,1,Byte.MAX_VALUE,TextM);
            Torch_lvl3_R = config.getInt(TileName+radius,TileTorcherino3,9,1,Byte.MAX_VALUE,TextR);
//Torch_lvl_4
            TileName = "Torcherino_lvl_4";
            Torch_lvl4_S = config.getInt(TileName+speed,TileTorcherino4,1,1,Byte.MAX_VALUE,TextS);
            Torch_lvl4_M = config.getInt(TileName+modes,TileTorcherino4,12,1,Byte.MAX_VALUE,TextM);
            Torch_lvl4_R = config.getInt(TileName+radius,TileTorcherino4,12,1,Byte.MAX_VALUE,TextR);
//Torch_lvl_5
            TileName = "Torcherino_lvl_5";
            Torch_lvl5_S = config.getInt(TileName+speed,TileTorcherino5,1,1,Byte.MAX_VALUE,TextS);
            Torch_lvl5_M = config.getInt(TileName+modes,TileTorcherino5,15,1,Byte.MAX_VALUE,TextM);
            Torch_lvl5_R = config.getInt(TileName+radius,TileTorcherino5,15,1,Byte.MAX_VALUE,TextR);
//Compressed_Torch_lvl_1
            TileName = "Torcherino_lvl_1_Compressed";
            CTorch_lvl1_S = config.getInt(TileName+speed,TileTorcherino,9,1,Byte.MAX_VALUE,TextS);
            CTorch_lvl1_M = config.getInt(TileName+modes,TileTorcherino,3,1,Byte.MAX_VALUE,TextM);
            CTorch_lvl1_R = config.getInt(TileName+radius,TileTorcherino,3,1,Byte.MAX_VALUE,TextR);
//Compressed_Torch_lvl_2
            TileName = "Torcherino_lvl_2_Compressed";
            CTorch_lvl2_S = config.getInt(TileName+speed,TileTorcherino2,9,1,Byte.MAX_VALUE,TextS);
            CTorch_lvl2_M = config.getInt(TileName+modes,TileTorcherino2,6,1,Byte.MAX_VALUE,TextM);
            CTorch_lvl2_R = config.getInt(TileName+radius,TileTorcherino2,6,1,Byte.MAX_VALUE,TextR);
//Compressed_Torch_lvl_3
            TileName = "Torcherino_lvl_3_Compressed";
            CTorch_lvl3_S = config.getInt(TileName+speed,TileTorcherino3,9,1,Byte.MAX_VALUE,TextS);
            CTorch_lvl3_M = config.getInt(TileName+modes,TileTorcherino3,9,1,Byte.MAX_VALUE,TextM);
            CTorch_lvl3_R = config.getInt(TileName+radius,TileTorcherino3,9,1,Byte.MAX_VALUE,TextR);
//Compressed_Torch_lvl_4
            TileName = "Torcherino_lvl_4_Compressed";
            CTorch_lvl4_S = config.getInt(TileName+speed,TileTorcherino4,9,1,Byte.MAX_VALUE,TextS);
            CTorch_lvl4_M = config.getInt(TileName+modes,TileTorcherino4,12,1,Byte.MAX_VALUE,TextM);
            CTorch_lvl4_R = config.getInt(TileName+radius,TileTorcherino4,12,1,Byte.MAX_VALUE,TextR);
//Compressed_Torch_lvl_5
            TileName = "Torcherino_lvl_5_Compressed";
            CTorch_lvl5_S = config.getInt(TileName+speed,TileTorcherino5,9,1,Byte.MAX_VALUE,TextS);
            CTorch_lvl5_M = config.getInt(TileName+modes,TileTorcherino5,15,1,Byte.MAX_VALUE,TextM);
            CTorch_lvl5_R = config.getInt(TileName+radius,TileTorcherino5,15,1,Byte.MAX_VALUE,TextR);
//DCompressed_Torch_lvl_1
            TileName = "Torcherino_lvl_1_Double_Compressed";
            DTorch_lvl1_S = config.getInt(TileName+speed,TileTorcherino,81,1,Byte.MAX_VALUE,TextS);
            DTorch_lvl1_M = config.getInt(TileName+modes,TileTorcherino,3,1,Byte.MAX_VALUE,TextM);
            DTorch_lvl1_R = config.getInt(TileName+radius,TileTorcherino,3,1,Byte.MAX_VALUE,TextR);
//DCompressed_Torch_lvl_2
            TileName = "Torcherino_lvl_2_Double_Compressed";
            DTorch_lvl2_S = config.getInt(TileName+speed,TileTorcherino2,81,1,Byte.MAX_VALUE,TextS);
            DTorch_lvl2_M = config.getInt(TileName+modes,TileTorcherino2,6,1,Byte.MAX_VALUE,TextM);
            DTorch_lvl2_R = config.getInt(TileName+radius,TileTorcherino2,6,1,Byte.MAX_VALUE,TextR);
//DCompressed_Torch_lvl_3
            TileName = "Torcherino_lvl_3_Double_Compressed";
            DTorch_lvl3_S = config.getInt(TileName+speed,TileTorcherino3,81,1,Byte.MAX_VALUE,TextS);
            DTorch_lvl3_M = config.getInt(TileName+modes,TileTorcherino3,9,1,Byte.MAX_VALUE,TextM);
            DTorch_lvl3_R = config.getInt(TileName+radius,TileTorcherino3,9,1,Byte.MAX_VALUE,TextR);
//DCompressed_Torch_lvl_4
            TileName = "Torcherino_lvl_4_Double_Compressed";
            DTorch_lvl4_S = config.getInt(TileName+speed,TileTorcherino4,81,1,Byte.MAX_VALUE,TextS);
            DTorch_lvl4_M = config.getInt(TileName+modes,TileTorcherino4,12,1,Byte.MAX_VALUE,TextM);
            DTorch_lvl4_R = config.getInt(TileName+radius,TileTorcherino4,12,1,Byte.MAX_VALUE,TextR);
//DCompressed_Torch_lvl_5
            TileName = "Torcherino_lvl_5_Double_Compressed";
            DTorch_lvl5_S = config.getInt(TileName+speed,TileTorcherino5,81,1,Byte.MAX_VALUE,TextS);
            DTorch_lvl5_M = config.getInt(TileName+modes,TileTorcherino5,15,1,Byte.MAX_VALUE,TextM);
            DTorch_lvl5_R = config.getInt(TileName+radius,TileTorcherino5,15,1,Byte.MAX_VALUE,TextR);
        } finally {
            if(config.hasChanged()) config.save();
        }
    }
    public static void registerConfig(@NotNull FMLPreInitializationEvent event){
        Torcherino.config = new File(event.getModConfigurationDirectory()+"/"+Torcherino.MOD_NAME);
        Torcherino.config.mkdirs();
        init(new File(Torcherino.config.getPath(),Torcherino.MOD_NAME+".cfg"));
    }
}