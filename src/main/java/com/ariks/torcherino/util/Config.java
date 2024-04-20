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
    public static String TileNameCompressed;
    public static String TileNameDCompressed;
    private final static String TextS = "Acceleration 1 = 100%";
    private final static String TextM = "Number of acceleration modes";
    private final static String TextR = "Working radius";
    public static boolean DebugMod;
    public static boolean BooleanParcWand,BooleanRenderFilledBox,BooleanRender,BooleanRenderLine,BooleanVisualWork;
    public static boolean BooleanTOP;
    public static int SpeedWand_lvl1,SpeedWand_lvl2,SpeedWand_lvl3,SpeedWand_lvl4,SpeedWand_lvl5,SpeedWand_lvl6;
    public static int
            Torch_Color_lvl1_R,Torch_Color_lvl1_G,Torch_Color_lvl1_B,
            Torch_Color_lvl2_R,Torch_Color_lvl2_G,Torch_Color_lvl2_B,
            Torch_Color_lvl3_R,Torch_Color_lvl3_G,Torch_Color_lvl3_B,
            Torch_Color_lvl4_R,Torch_Color_lvl4_G,Torch_Color_lvl4_B,
            Torch_Color_lvl5_R,Torch_Color_lvl5_G,Torch_Color_lvl5_B;
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
    public static int AccelerationSpeed,AccelerationRadius,AccelerationTimeCollectConfig;
    public static int TimeStorageMaxTime;
    public static int MaxStorageTimeCollectors,RequiredTimeCollectors;
    public static int Stored_Time_Bottle_Lvl_1,Stored_Time_Bottle_Lvl_2,Stored_Time_Bottle_Lvl_3,Stored_Time_Bottle_Lvl_4,Stored_Time_Bottle_Lvl_5,Stored_Time_Bottle_infinite;
    public static void init(File file) {
        config = new Configuration(file);
        try {config.load();
            String General = "General";
            String Render = "Render";
            String Item = "Item";
            String ColorRenderTile = "Color render line";
            //BlackList
            blacklistedBlocks = config.getStringList("blacklistedBlocks", "blacklist", new String[]{}, "modid:unlocalized");
            blacklistedTiles = config.getStringList("blacklistedTiles", "blacklist", new String[]{}, "Fully qualified class name");
            //General
            DebugMod = config.getBoolean("Debug_Mode",General,false,"(For Server Owners) Debug modes");
            BooleanTOP = config.getBoolean("integration_top_addons",General,true,"True/false Integration TheOneProbe-TopAddons");
            //Storage_Time_In_Bottle
            Stored_Time_Bottle_Lvl_1 = config.getInt("Time_Storage_lvl_1",Item,500,1,Integer.MAX_VALUE,"Storage time bottle lvl 1");
            Stored_Time_Bottle_Lvl_2 = config.getInt("Time_Storage_lvl_2",Item,1500,1,Integer.MAX_VALUE,"Storage time bottle lvl 2");
            Stored_Time_Bottle_Lvl_3 = config.getInt("Time_Storage_lvl_3",Item,4500,1,Integer.MAX_VALUE,"Storage time bottle lvl 3");
            Stored_Time_Bottle_Lvl_4 = config.getInt("Time_Storage_lvl_4",Item,13500,1,Integer.MAX_VALUE,"Storage time bottle lvl 4");
            Stored_Time_Bottle_Lvl_5 = config.getInt("Time_Storage_lvl_5",Item,45000,1,Integer.MAX_VALUE,"Storage time bottle lvl 5");
            Stored_Time_Bottle_infinite = config.getInt("Time_Storage_infinite",Item,Integer.MAX_VALUE,1,Integer.MAX_VALUE,"Storage time infinite");
            //wand
            SpeedWand_lvl1 = config.getInt("Time_Wand_Speed_lvl1",Item,3,1,Short.MAX_VALUE,"Speed Time Wand lvl 1. 1 = 100%.....");
            SpeedWand_lvl2 = config.getInt("Time_Wand_Speed_lvl2",Item,6,1,Short.MAX_VALUE,"Speed Time Wand lvl 2. 1 = 100%.....");
            SpeedWand_lvl3 = config.getInt("Time_Wand_Speed_lvl3",Item,9,1,Short.MAX_VALUE,"Speed Time Wand lvl 3. 1 = 100%.....");
            SpeedWand_lvl4 = config.getInt("Time_Wand_Speed_lvl4",Item,12,1,Short.MAX_VALUE,"Speed Time Wand lvl 4. 1 = 100%.....");
            SpeedWand_lvl5 = config.getInt("Time_Wand_Speed_lvl5",Item,15,1,Short.MAX_VALUE,"Speed Time Wand lvl 5. 1 = 100%.....");
            SpeedWand_lvl6 = config.getInt("Time_Wand_Speed_lvl6",Item,20,1,Short.MAX_VALUE,"Speed Time Wand lvl 6. 1 = 100%.....");
            //Render
            BooleanParcWand = config.getBoolean("Time_Wand_Parc",Render,true,"Spawn Particle Wand");
            BooleanVisualWork = config.getBoolean("Tile_Visual_Work",Render,true,"Tile spawn Particle flame");
            BooleanRender = config.getBoolean("Tile_Render",Render,true,"Tile render true/false");
            BooleanRenderFilledBox = config.getBoolean("Tile_Render_Box",Render,false,"Tile filling the box render");
            BooleanRenderLine = config.getBoolean("Tile_Render_Line",Render,true,"Tile render line");
            //Render color line lvl1
            String[] rgbComponents1 = config.getString("Color_lvl1_RGB", ColorRenderTile, "0,0,255", "Render color line lvl-1 RGB max value 255").split(",");
            Torch_Color_lvl1_R = Integer.parseInt(rgbComponents1[0]);
            Torch_Color_lvl1_G = Integer.parseInt(rgbComponents1[1]);
            Torch_Color_lvl1_B = Integer.parseInt(rgbComponents1[2]);
            //Render color line lvl2
            String[] rgbComponents2 = config.getString("Color_lvl2_RGB", ColorRenderTile, "0,255,0", "Render color line lvl-2 RGB max value 255").split(",");
            Torch_Color_lvl2_R = Integer.parseInt(rgbComponents2[0]);
            Torch_Color_lvl2_G = Integer.parseInt(rgbComponents2[1]);
            Torch_Color_lvl2_B = Integer.parseInt(rgbComponents2[2]);
            //Render color line lvl3
            String[] rgbComponents3 = config.getString("Color_lvl3_RGB", ColorRenderTile, "255,0,0", "Render color line lvl-3 RGB max value 255").split(",");
            Torch_Color_lvl3_R = Integer.parseInt(rgbComponents3[0]);
            Torch_Color_lvl3_G = Integer.parseInt(rgbComponents3[1]);
            Torch_Color_lvl3_B = Integer.parseInt(rgbComponents3[2]);
            //Render color line lvl4
            String[] rgbComponents4 = config.getString("Color_lvl4_RGB", ColorRenderTile, "255,255,0", "Render color line lvl-4 RGB max value 255").split(",");
            Torch_Color_lvl4_R = Integer.parseInt(rgbComponents4[0]);
            Torch_Color_lvl4_G = Integer.parseInt(rgbComponents4[1]);
            Torch_Color_lvl4_B = Integer.parseInt(rgbComponents4[2]);
            //Render color line lvl5
            String[] rgbComponents5 = config.getString("Color_lvl5_RGB", ColorRenderTile, "0,255,255", "Render color line lvl-5 RGB max value 255").split(",");
            Torch_Color_lvl5_R = Integer.parseInt(rgbComponents5[0]);
            Torch_Color_lvl5_G = Integer.parseInt(rgbComponents5[1]);
            Torch_Color_lvl5_B = Integer.parseInt(rgbComponents5[2]);
            //Storage
            TileName = "Tile_Time_Storage";
            TimeStorageMaxTime = config.getInt("MaxStoredTime",TileName,1000000,1,Integer.MAX_VALUE,"Time Storage");
            //Collectors
            TileName = "Tile_Time_Collectors";
            MaxStorageTimeCollectors = config.getInt("MaxStoredTime",TileName,1500,1,Integer.MAX_VALUE,"Maximum storage size");
            RequiredTimeCollectors = config.getInt("Cooldown",TileName,10,1,Short.MAX_VALUE,"The required number of ticks to generate 1 second");
            //Acceleration
            TileName = "Tile_Time_Acceleration";
            AccelerationSpeed = config.getInt("Speed",TileName,3,1, Short.MAX_VALUE,TextS);
            AccelerationRadius = config.getInt("Area",TileName,3,1,Short.MAX_VALUE,TextR);
            AccelerationTimeCollectConfig = config.getInt("Time",TileName,0,1,Integer.MAX_VALUE,"Initial accumulated time");
            //Torch_lvl_1
            TileName = "Torcherino_lvl_1";
            Torch_lvl1_S = config.getInt("Speed",TileName,1,1, Short.MAX_VALUE,TextS);
            Torch_lvl1_M = config.getInt("Mode",TileName,3,1,Short.MAX_VALUE,TextM);
            Torch_lvl1_R = config.getInt("Area",TileName,3,1, Short.MAX_VALUE,TextR);
            //Torch_lvl_2
            TileName = "Torcherino_lvl_2";
            Torch_lvl2_S = config.getInt("Speed",TileName,1,1,Short.MAX_VALUE,TextS);
            Torch_lvl2_M = config.getInt("Mode",TileName,6,1,Short.MAX_VALUE,TextM);
            Torch_lvl2_R = config.getInt("Area",TileName,6,1, Short.MAX_VALUE,TextR);
            //Torch_lvl_3
            TileName = "Torcherino_lvl_3";
            Torch_lvl3_S = config.getInt("Speed",TileName,1,1,Short.MAX_VALUE,TextS);
            Torch_lvl3_M = config.getInt("Mode",TileName,9,1,Short.MAX_VALUE,TextM);
            Torch_lvl3_R = config.getInt("Area",TileName,9,1, Short.MAX_VALUE,TextR);
            //Torch_lvl_4
            TileName = "Torcherino_lvl_4";
            Torch_lvl4_S = config.getInt("Speed",TileName,1,1,Short.MAX_VALUE,TextS);
            Torch_lvl4_M = config.getInt("Mode",TileName,12,1,Short.MAX_VALUE,TextM);
            Torch_lvl4_R = config.getInt("Area",TileName,12,1, Short.MAX_VALUE,TextR);
            //Torch_lvl_5
            TileName = "Torcherino_lvl_5";
            Torch_lvl5_S = config.getInt("Speed",TileName,1,1,Short.MAX_VALUE,TextS);
            Torch_lvl5_M = config.getInt("Mode",TileName,15,1,Short.MAX_VALUE,TextM);
            Torch_lvl5_R = config.getInt("Area",TileName,15,1, Short.MAX_VALUE,TextR);
            //Compressed_Torch_lvl_1
            TileNameCompressed = "Torcherino_lvl_1_Compressed";
            CTorch_lvl1_S = config.getInt("Speed",TileNameCompressed,9,1,Short.MAX_VALUE,TextS);
            CTorch_lvl1_M = config.getInt("Mode",TileNameCompressed,3,1,Short.MAX_VALUE,TextM);
            CTorch_lvl1_R = config.getInt("Area",TileNameCompressed,3,1, Short.MAX_VALUE,TextR);
            //Compressed_Torch_lvl_2
            TileNameCompressed = "Torcherino_lvl_2_Compressed";
            CTorch_lvl2_S = config.getInt("Speed",TileNameCompressed,9,1,Short.MAX_VALUE,TextS);
            CTorch_lvl2_M = config.getInt("Mode",TileNameCompressed,6,1,Short.MAX_VALUE,TextM);
            CTorch_lvl2_R = config.getInt("Area",TileNameCompressed,6,1, Short.MAX_VALUE,TextR);
            //Compressed_Torch_lvl_3
            TileNameCompressed = "Torcherino_lvl_3_Compressed";
            CTorch_lvl3_S = config.getInt("Speed",TileNameCompressed,9,1,Short.MAX_VALUE,TextS);
            CTorch_lvl3_M = config.getInt("Mode",TileNameCompressed,9,1,Short.MAX_VALUE,TextM);
            CTorch_lvl3_R = config.getInt("Area",TileNameCompressed,9,1, Short.MAX_VALUE,TextR);
            //Compressed_Torch_lvl_4
            TileNameCompressed = "Torcherino_lvl_4_Compressed";
            CTorch_lvl4_S = config.getInt("Speed",TileNameCompressed,9,1,Short.MAX_VALUE,TextS);
            CTorch_lvl4_M = config.getInt("Mode",TileNameCompressed,12,1,Short.MAX_VALUE,TextM);
            CTorch_lvl4_R = config.getInt("Area",TileNameCompressed,12,1, Short.MAX_VALUE,TextR);
            //Compressed_Torch_lvl_5
            TileNameCompressed = "Torcherino_lvl_5_Compressed";
            CTorch_lvl5_S = config.getInt("Speed",TileNameCompressed,9,1,Short.MAX_VALUE,TextS);
            CTorch_lvl5_M = config.getInt("Mode",TileNameCompressed,15,1,Short.MAX_VALUE,TextM);
            CTorch_lvl5_R = config.getInt("Area",TileNameCompressed,15,1, Short.MAX_VALUE,TextR);
            //DCompressed_Torch_lvl_1
            TileNameDCompressed = "Torcherino_lvl_1_Double_Compressed";
            DTorch_lvl1_S = config.getInt("Speed",TileNameDCompressed,81,1,Short.MAX_VALUE,TextS);
            DTorch_lvl1_M = config.getInt("Mode",TileNameDCompressed,3,1,Short.MAX_VALUE,TextM);
            DTorch_lvl1_R = config.getInt("Area",TileNameDCompressed,3,1, Short.MAX_VALUE,TextR);
            //DCompressed_Torch_lvl_2
            TileNameDCompressed = "Torcherino_lvl_2_Double_Compressed";
            DTorch_lvl2_S = config.getInt("Speed",TileNameDCompressed,81,1,Short.MAX_VALUE,TextS);
            DTorch_lvl2_M = config.getInt("Mode",TileNameDCompressed,6,1,Short.MAX_VALUE,TextM);
            DTorch_lvl2_R = config.getInt("Area",TileNameDCompressed,6,1, Short.MAX_VALUE,TextR);
            //DCompressed_Torch_lvl_3
            TileNameDCompressed = "Torcherino_lvl_3_Double_Compressed";
            DTorch_lvl3_S = config.getInt("Speed",TileNameDCompressed,81,1,Short.MAX_VALUE,TextS);
            DTorch_lvl3_M = config.getInt("Mode",TileNameDCompressed,9,1,Short.MAX_VALUE,TextM);
            DTorch_lvl3_R = config.getInt("Area",TileNameDCompressed,9,1, Short.MAX_VALUE,TextR);
            //DCompressed_Torch_lvl_4
            TileNameDCompressed = "Torcherino_lvl_4_Double_Compressed";
            DTorch_lvl4_S = config.getInt("Speed",TileNameDCompressed,81,1,Short.MAX_VALUE,TextS);
            DTorch_lvl4_M = config.getInt("Mode",TileNameDCompressed,12,1,Short.MAX_VALUE,TextM);
            DTorch_lvl4_R = config.getInt("Area",TileNameDCompressed,12,1, Short.MAX_VALUE,TextR);
            //DCompressed_Torch_lvl_5
            TileNameDCompressed = "Torcherino_lvl_5_Double_Compressed";
            DTorch_lvl5_S = config.getInt("Speed",TileNameDCompressed,81,1,Short.MAX_VALUE,TextS);
            DTorch_lvl5_M = config.getInt("Mode",TileNameDCompressed,15,1,Short.MAX_VALUE,TextM);
            DTorch_lvl5_R = config.getInt("Area",TileNameDCompressed,15,1, Short.MAX_VALUE,TextR);
        } finally {if(config.hasChanged()) config.save();}}
    public static void registerConfig(@NotNull FMLPreInitializationEvent event){
        Torcherino.config = new File(event.getModConfigurationDirectory()+"/"+Torcherino.MOD_NAME);
        Torcherino.config.mkdirs();
        init(new File(Torcherino.config.getPath(),Torcherino.MOD_NAME+".cfg"));
    }
}
