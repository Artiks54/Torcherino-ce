package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.RfMolecular.BlockMolecularRf;
import com.ariks.torcherino.Block.Time.Aceleration.BlockAcceleration;
import com.ariks.torcherino.Block.EnergyGeneration.BlockEnergyParticle;
import com.ariks.torcherino.Block.Other.BlockGrowAccelerator;
import com.ariks.torcherino.Block.ParticleCollector.BlockParticleCollector;
import com.ariks.torcherino.Block.Time.TimeCollector.BlockCollectors;
import com.ariks.torcherino.Block.Time.TimeManipulator.BlockTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeStorage.BlockTimeStorage;
import com.ariks.torcherino.Block.Torcherino.BlockTorcherino;
import com.ariks.torcherino.Block.Torcherino.TorcherinoEnumLevel;
import com.ariks.torcherino.Torcherino;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistryBlock {
    public static final String modid = Torcherino.MOD_ID + "_";
    public static Block EnergyParticle = new BlockEnergyParticle(modid+"energy_particle");
    public static Block Grow_lvl1 = new BlockGrowAccelerator(modid+"grow_lvl1",10);
    public static Block Grow_lvl2 = new BlockGrowAccelerator(modid+"grow_lvl2",7);
    public static Block Grow_lvl3 = new BlockGrowAccelerator(modid+"grow_lvl3",5);
    public static Block Grow_lvl4 = new BlockGrowAccelerator(modid+"grow_lvl4",2);
    public static Block Grow_lvl5 = new BlockGrowAccelerator(modid+"grow_lvl5",1);
    public static Block Torch_lvl_1 = new BlockTorcherino(modid+"torch_lvl1", TorcherinoEnumLevel.lvl_1);
    public static Block Torch_lvl_2 = new BlockTorcherino(modid+"torch_lvl2",TorcherinoEnumLevel.lvl_2);
    public static Block Torch_lvl_3 = new BlockTorcherino(modid+"torch_lvl3",TorcherinoEnumLevel.lvl_3);
    public static Block Torch_lvl_4 = new BlockTorcherino(modid+"torch_lvl4",TorcherinoEnumLevel.lvl_4);
    public static Block Torch_lvl_5 = new BlockTorcherino(modid+"torch_lvl5",TorcherinoEnumLevel.lvl_5);
    public static Block Compressed_Torch_lvl1 = new BlockTorcherino(modid+"compressed_torch_lvl1",TorcherinoEnumLevel.c_lvl_1);
    public static Block Compressed_Torch_lvl2 = new BlockTorcherino(modid+"compressed_torch_lvl2",TorcherinoEnumLevel.c_lvl_2);
    public static Block Compressed_Torch_lvl3 = new BlockTorcherino(modid+"compressed_torch_lvl3",TorcherinoEnumLevel.c_lvl_3);
    public static Block Compressed_Torch_lvl4 = new BlockTorcherino(modid+"compressed_torch_lvl4",TorcherinoEnumLevel.c_lvl_4);
    public static Block Compressed_Torch_lvl5 = new BlockTorcherino(modid+"compressed_torch_lvl5",TorcherinoEnumLevel.c_lvl_5);
    public static Block D_Compressed_Torch_lvl1 = new BlockTorcherino(modid+"d_compressed_torch_lvl1",TorcherinoEnumLevel.d_lvl_1);
    public static Block D_Compressed_Torch_lvl2 = new BlockTorcherino(modid+"d_compressed_torch_lvl2",TorcherinoEnumLevel.d_lvl_2);
    public static Block D_Compressed_Torch_lvl3 = new BlockTorcherino(modid+"d_compressed_torch_lvl3",TorcherinoEnumLevel.d_lvl_3);
    public static Block D_Compressed_Torch_lvl4 = new BlockTorcherino(modid+"d_compressed_torch_lvl4",TorcherinoEnumLevel.d_lvl_4);
    public static Block D_Compressed_Torch_lvl5 = new BlockTorcherino(modid+"d_compressed_torch_lvl5",TorcherinoEnumLevel.d_lvl_5);
    public static Block Time_Acceleration = new BlockAcceleration(modid+"time_acceleration");
    public static Block Time_collectors = new BlockCollectors(modid+"time_collectors");
    public static Block Time_Manipulator = new BlockTimeManipulator(modid+"time_manipulator");
    public static Block Time_Storage = new BlockTimeStorage(modid+"time_storage");
    public static Block Particle_collectors = new BlockParticleCollector(modid+"particle_collector");
    public static Block RF_Molecular = new BlockMolecularRf(modid+"molecular_rf");
    public static final List<Block> BLOCKS = new ArrayList<>();
    static {
        Field[] fields = RegistryBlock.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (Block.class.isAssignableFrom(field.getType())) {
                    Block block = (Block) field.get(null);
                    BLOCKS.add(block);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public static void preInit() {
        for (Block block : BLOCKS) {
            setRegister(block);
        }
    }
    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        for (Block block : BLOCKS) {
            setRender(block);
        }
    }
    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
    }
    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"));
    }
}