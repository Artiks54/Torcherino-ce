package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class Config {
    protected static Configuration config;
    public static String TileName;
    private final static String TextS = "Speed 1 = 100%";
    private final static String TextM = "Number of speed modes";
    private final static String TextR = "Maximum Radius";
    private final static String radius = "_radius";
    private final static String speed = "_speed";
    private final static String modes = "_number_modes";
    public static boolean DebugMod;
    public static boolean BooleanHelloMsg;
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
    public static int RequiredGeneratorParticle;
    public static int RequiredTimeManipulator;
    public static int MaxStorageTimeAcceleration;
    public static int MaxStorageTimeManipulator;
    public static int MaxStorageTimeCollector;
    public static int MaxStorageTimeStorage;
    public static int Stored_Time_Bottle_Lvl_1,Stored_Time_Bottle_Lvl_2,Stored_Time_Bottle_Lvl_3,Stored_Time_Bottle_Lvl_4,Stored_Time_Bottle_Lvl_5;
    public static int SpeedWand_lvl1,SpeedWand_lvl2,SpeedWand_lvl3,SpeedWand_lvl4,SpeedWand_lvl5,SpeedWand_infinite;
    public static int MaxEnergyParticle, RFPerTickEnergyParticle,MaxEnergyParticleProgress;
    public static int MaxStorageTorcherino, RFPerTickEnergyTorcherino;
    public static int MTR_RecipeIngot,MTR_RecipeNugget;
    public static int EMCParticle,EMCIngot;
    public static void init(File file) {
        config = new Configuration(file);
        try {config.load();
            String Molecular = "Molecular_Transformer";
            String ProjectE = "ProjectE";
            String General = "General";
            String Render = "Render";
            String Item = "Item";
            String Tile = "Tile";
            String TileTorcherino = "Tile_Torcherino";
            String TileTorcherino2 = "Tile_Torcherino_lvl2";
            String TileTorcherino3 = "Tile_Torcherino_lvl3";
            String TileTorcherino4 = "Tile_Torcherino_lvl4";
            String TileTorcherino5 = "Tile_Torcherino_lvl5";
            config.setCategoryComment(ProjectE,"Integration projectE");
            config.setCategoryComment(Molecular,"RF Molecular Transformer recipes");
            config.setCategoryComment(General,"General settings");
            config.setCategoryComment(Item,"Item settings");
            config.setCategoryComment(Render,"Render settings");
            config.setCategoryComment(Tile,"TileEntity settings .Before changing these values, it is best to destroy the already installed TileEntity \n" +
                    "If this is not possible, then in order for the value in TileEntity to change, it will need to be set again.");
            config.setCategoryComment(TileTorcherino,"TileEntity settings .Before changing these values, it is best to destroy the already installed TileEntity \n" +
                    "If this is not possible, then in order for the value in TileEntity to change, it will need to be set again.");
//General
            BooleanHelloMsg = config.getBoolean("Hello_Message",General,true,"Send chat hello message");
            DebugMod = config.getBoolean("Debug_Mode",General,false,"Debug modes");
            IntegrationTheOneProbeTA = config.getBoolean("integration_top_addons",General,true,"Integration TheOneProbe-TopAddons");
//ProjectE
            IntegrationProjectE = config.getBoolean("integration_ProjectE",ProjectE,true,"Integration ProjectE");
            EMCIngot = config.getInt("emc_time_ingot",ProjectE,1998279,1,Integer.MAX_VALUE,"EMC Time_Ingot");
            EMCParticle = config.getInt("emc_time_particle",ProjectE,5411,1,Integer.MAX_VALUE,"EMC Time_Particle");
//Storage_Time_In_Bottle
            Stored_Time_Bottle_Lvl_1 = config.getInt("Time_Storage_lvl_1",Item,250,1,Integer.MAX_VALUE,"Storage time bottle lvl 1");
            Stored_Time_Bottle_Lvl_2 = config.getInt("Time_Storage_lvl_2",Item,750,1,Integer.MAX_VALUE,"Storage time bottle lvl 2");
            Stored_Time_Bottle_Lvl_3 = config.getInt("Time_Storage_lvl_3",Item,2250,1,Integer.MAX_VALUE,"Storage time bottle lvl 3");
            Stored_Time_Bottle_Lvl_4 = config.getInt("Time_Storage_lvl_4",Item,6750,1,Integer.MAX_VALUE,"Storage time bottle lvl 4");
            Stored_Time_Bottle_Lvl_5 = config.getInt("Time_Storage_lvl_5",Item,25000,1,Integer.MAX_VALUE,"Storage time bottle lvl 5");
//wand
            SpeedWand_lvl1 = config.getInt("Time_Wand_Speed_lvl1",Item,3,1,Byte.MAX_VALUE,"Speed Time Wand lvl 1. 1 = 100%.....");
            SpeedWand_lvl2 = config.getInt("Time_Wand_Speed_lvl2",Item,6,1,Byte.MAX_VALUE,"Speed Time Wand lvl 2. 1 = 100%.....");
            SpeedWand_lvl3 = config.getInt("Time_Wand_Speed_lvl3",Item,9,1,Byte.MAX_VALUE,"Speed Time Wand lvl 3. 1 = 100%.....");
            SpeedWand_lvl4 = config.getInt("Time_Wand_Speed_lvl4",Item,12,1,Byte.MAX_VALUE,"Speed Time Wand lvl 4. 1 = 100%.....");
            SpeedWand_lvl5 = config.getInt("Time_Wand_Speed_lvl5",Item,15,1,Byte.MAX_VALUE,"Speed Time Wand lvl 5. 1 = 100%.....");
            SpeedWand_infinite = config.getInt("Time_Wand_Speed_lvl6",Item,20,1,Byte.MAX_VALUE,"Speed Time Wand infinite. 1 = 100%.....");
//Render
            BooleanParcWand = config.getBoolean("Time_Wand_Parc",Render,true,"Spawn Particle Wand");
            BooleanVisualWork = config.getBoolean("Tile_Visual_Work",Render,true,"Tile spawn Particle");
            BooleanRender = config.getBoolean("Tile_Render",Render,true,"Tile render true/false");
//Generator particle tile
            RequiredGeneratorParticle = config.getInt("Tile_Generator_Particle_NeedTick",Tile,2450,1,Short.MAX_VALUE,"Need ticks to generate 100%");
//Time manipulator tile
            RequiredTimeManipulator = config.getInt("Tile_Time_Manipulator_Need_Time",Tile,750,1,Short.MAX_VALUE,"Need time");
            MaxStorageTimeManipulator = config.getInt("Tile_Time_Manipulator_MaxStorage",Tile,3500,1,Short.MAX_VALUE,"Time maximum storage");
//Storage tile
            MaxStorageTimeStorage = config.getInt("Tile_Time_Storage_MaxStorage",Tile,20000,1,Integer.MAX_VALUE,"Time maximum storage");
//Collectors tile
            MaxStorageTimeCollector = config.getInt("Tile_Time_Collectors_MaxStorage",Tile,2500,1,Integer.MAX_VALUE,"Time maximum storage");
//Acceleration tile
            AccelerationSpeed = config.getInt("Tile_Time_Acceleration_Speed",Tile,3,1, Byte.MAX_VALUE,TextS);
            AccelerationRadius = config.getInt("Tile_Time_Acceleration_Radius",Tile,3,1,Byte.MAX_VALUE,TextR);
            MaxStorageTimeAcceleration = config.getInt("Tile_Time_Acceleration_MaxStorage",Tile,4500,1,Integer.MAX_VALUE,"Time maximum storage");
//EnergyParticle
            RFPerTickEnergyParticle = config.getInt("Tile_Energy_Particle_Collector_RF_Tick",Tile,2700,1,Integer.MAX_VALUE,"Rf need to generate 1 time element");
            MaxEnergyParticle = config.getInt("Tile_Energy_Particle_Collector_Max_Energy",Tile,5000000,1,Integer.MAX_VALUE,"Max energy storage");
            MaxEnergyParticleProgress = config.getInt("Tile_Energy_Particle_Collector_Max_Progress",Tile,20,1,Integer.MAX_VALUE,"Max progress");
//EnergyTorcherino
            RFPerTickEnergyTorcherino = config.getInt("Tile_Energy_Torcherino_RF_Tick",Tile,750,1,Integer.MAX_VALUE,"Rf tick work");
            MaxStorageTorcherino = config.getInt("Tile_Energy_Torcherino_Max_Energy",Tile,6500000,1,Integer.MAX_VALUE,"Max energy storage");
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
//MolecularTransformers
            MTR_RecipeIngot = config.getInt("Tile_Molecular_Recipe_Time_Ingot",Molecular,50_000, 1, Integer.MAX_VALUE, "Recipe time_ingot rf Energy");
            MTR_RecipeNugget = config.getInt("Tile_Molecular_Recipe_Time_Nugget",Molecular,25_000,1,Integer.MAX_VALUE, "Recipe time_nugget rf Energy");
        } finally {
            if(config.hasChanged()) config.save();
        }
    }
    public static void registerConfig(@NotNull FMLPreInitializationEvent event){
        Torcherino.config = new File(event.getModConfigurationDirectory()+"/"+"AriksProject54");
        Torcherino.config.mkdirs();
        init(new File(Torcherino.config.getPath(),Torcherino.MOD_NAME+".cfg"));
    }
}