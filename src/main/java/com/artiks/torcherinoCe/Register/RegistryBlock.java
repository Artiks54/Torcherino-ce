package com.artiks.torcherinoCe.Register;

import com.artiks.torcherinoCe.Block.Core.ExampleBlock;
import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.BlockCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.BlockLuck;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.BlockMolecularRfFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.BlockMolecularRfLegendary;
import com.artiks.torcherinoCe.Block.ItemStorage.BlockItemStorage;
import com.artiks.torcherinoCe.Block.ItemStorage.ItemStorageType;
import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.blockMarker;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.BlockEnergyParticle;
import com.artiks.torcherinoCe.Block.Other.BlockGrowAccelerator;
import com.artiks.torcherinoCe.Block.ParticleCollector.BlockParticleCollector;
import com.artiks.torcherinoCe.Block.Energy.Molecular.BlockMolecularRf;
import com.artiks.torcherinoCe.Block.Time.Aceleration.BlockAcceleration;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.BlockEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeCollector.BlockCollectors;
import com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector.BlockEnergyCollector;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.BlockTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeStorage.BlockTimeStorage;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.BlockTorcherino;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TorcherinoEnumLevel;
import com.artiks.torcherinoCe.Tags;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

public final class RegistryBlock {
    public static final String mod_id = Tags.MODID + "_";
    public static Block EnergyParticle = new BlockEnergyParticle(mod_id +"energy_particle");
    public static Block Grow_lvl1 = new BlockGrowAccelerator(mod_id +"grow_lvl1",10);
    public static Block Grow_lvl2 = new BlockGrowAccelerator(mod_id +"grow_lvl2",7);
    public static Block Grow_lvl3 = new BlockGrowAccelerator(mod_id +"grow_lvl3",5);
    public static Block Grow_lvl4 = new BlockGrowAccelerator(mod_id +"grow_lvl4",2);
    public static Block Grow_lvl5 = new BlockGrowAccelerator(mod_id +"grow_lvl5",1);
    public static Block Torch_lvl_1 = new BlockTorcherino(mod_id +"torch_lvl1", TorcherinoEnumLevel.lvl_1);
    public static Block Torch_lvl_2 = new BlockTorcherino(mod_id +"torch_lvl2",TorcherinoEnumLevel.lvl_2);
    public static Block Torch_lvl_3 = new BlockTorcherino(mod_id +"torch_lvl3",TorcherinoEnumLevel.lvl_3);
    public static Block Torch_lvl_4 = new BlockTorcherino(mod_id +"torch_lvl4",TorcherinoEnumLevel.lvl_4);
    public static Block Torch_lvl_5 = new BlockTorcherino(mod_id +"torch_lvl5",TorcherinoEnumLevel.lvl_5);
    public static Block Compressed_Torch_lvl1 = new BlockTorcherino(mod_id +"compressed_torch_lvl1",TorcherinoEnumLevel.c_lvl_1);
    public static Block Compressed_Torch_lvl2 = new BlockTorcherino(mod_id +"compressed_torch_lvl2",TorcherinoEnumLevel.c_lvl_2);
    public static Block Compressed_Torch_lvl3 = new BlockTorcherino(mod_id +"compressed_torch_lvl3",TorcherinoEnumLevel.c_lvl_3);
    public static Block Compressed_Torch_lvl4 = new BlockTorcherino(mod_id +"compressed_torch_lvl4",TorcherinoEnumLevel.c_lvl_4);
    public static Block Compressed_Torch_lvl5 = new BlockTorcherino(mod_id +"compressed_torch_lvl5",TorcherinoEnumLevel.c_lvl_5);
    public static Block D_Compressed_Torch_lvl1 = new BlockTorcherino(mod_id +"d_compressed_torch_lvl1",TorcherinoEnumLevel.d_lvl_1);
    public static Block D_Compressed_Torch_lvl2 = new BlockTorcherino(mod_id +"d_compressed_torch_lvl2",TorcherinoEnumLevel.d_lvl_2);
    public static Block D_Compressed_Torch_lvl3 = new BlockTorcherino(mod_id +"d_compressed_torch_lvl3",TorcherinoEnumLevel.d_lvl_3);
    public static Block D_Compressed_Torch_lvl4 = new BlockTorcherino(mod_id +"d_compressed_torch_lvl4",TorcherinoEnumLevel.d_lvl_4);
    public static Block D_Compressed_Torch_lvl5 = new BlockTorcherino(mod_id +"d_compressed_torch_lvl5",TorcherinoEnumLevel.d_lvl_5);
    public static Block Time_Acceleration = new BlockAcceleration(mod_id +"time_acceleration");
    public static Block Time_collectors = new BlockCollectors(mod_id +"time_collectors");
    public static Block Time_energy_collectors = new BlockEnergyCollector(mod_id +"time_energy_collectors");
    public static Block Time_Manipulator = new BlockTimeManipulator(mod_id +"time_manipulator");
    public static Block Time_Energy_Manipulator = new BlockEnergyTimeManipulator(mod_id +"time_energy_manipulator");
    public static Block Time_Storage = new BlockTimeStorage(mod_id +"time_storage");
    public static Block Particle_collectors = new BlockParticleCollector(mod_id +"particle_collector");
    public static Block RF_Molecular = new BlockMolecularRf(mod_id +"molecular_rf");
    public static Block RF_Molecular_Legendary = new BlockMolecularRfLegendary(mod_id +"molecular_rf_legendary");
    public static Block RF_Molecular_Farm = new BlockMolecularRfFarm(mod_id +"molecular_rf_farm");
    public static Block Block_Marker = new blockMarker(mod_id +"block_marker");
    public static Block Block_Luck = new BlockLuck(mod_id +"block_luck");
    public static Block BlockItemStorage_1 = new BlockItemStorage(mod_id + "block_storage_1", ItemStorageType.lvl1);
    public static Block BlockItemStorage_2 = new BlockItemStorage(mod_id + "block_storage_2", ItemStorageType.lvl2);
    public static Block BlockItemStorage_3 = new BlockItemStorage(mod_id + "block_storage_3", ItemStorageType.lvl3);
    public static Block BlockItemStorage_4 = new BlockItemStorage(mod_id + "block_storage_4", ItemStorageType.lvl4);
    public static Block BlockItemStorage_5 = new BlockItemStorage(mod_id + "block_storage_5", ItemStorageType.lvl5);
    public static Block Block_Red_Star = new ExampleBlock(mod_id +"block_red_star", Material.IRON);
    public static Block Block_Cobble_Generator = new BlockCobbleGenerator(mod_id +"cobble_generator");
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
                //noinspection CallToPrintStackTrace
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