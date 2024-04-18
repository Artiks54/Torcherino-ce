package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.*;
import com.ariks.torcherino.Items.*;
import com.ariks.torcherino.Torcherino;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import java.util.ArrayList;
import java.util.List;

public final class RegistryArray {
    public static final String modid = Torcherino.MOD_ID + "_";
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Item Time_Wand_lvl1 = new TimeWands.TimeWand_lvl_1(modid+"time_wand_lvl1");
    public static final Item Time_Wand_lvl2 = new TimeWands.TimeWand_lvl_2(modid+"time_wand_lvl2");
    public static final Item Time_Wand_lvl3 = new TimeWands.TimeWand_lvl_3(modid+"time_wand_lvl3");
    public static final Item Time_Wand_lvl4 = new TimeWands.TimeWand_lvl_4(modid+"time_wand_lvl4");
    public static final Item Time_Wand_lvl5 = new TimeWands.TimeWand_lvl_5(modid+"time_wand_lvl5");
    public static final Item time_storage_lvl1 = new TimeStorages.TimeStorage_Lvl1(modid+"time_storage_lvl1");
    public static final Item time_storage_lvl2 = new TimeStorages.TimeStorage_Lvl2(modid+"time_storage_lvl2");
    public static final Item time_storage_lvl3 = new TimeStorages.TimeStorage_Lvl3(modid+"time_storage_lvl3");
    public static final Item time_storage_lvl4 = new TimeStorages.TimeStorage_Lvl4(modid+"time_storage_lvl4");
    public static final Item time_storage_lvl5 = new TimeStorages.TimeStorage_Lvl5(modid+"time_storage_lvl5");
    public static final Item time_storage_infinite = new TimeStorages.TimeStorage_infinite(modid+"time_storage_infinite");
    public static final Item time_element = new itemBase(modid+"time_element");
    public static final Item time_nugget = new itemBase(modid+"time_nugget");
    public static final Item time_ingot = new itemBase(modid+"time_ingot");
    public static final Item Diamond_Clock = new itemBase(modid+"diamondclock");
    public static final Item Emerald_Clock = new itemBase(modid+"emeraldclock");
    public static final Item Lapis_Clock = new itemBase(modid+"lapisclock");
    public static final Item Redstone_Clock = new itemBase(modid+"redstoneclock");
    public static final Item Gold_Clock = new itemBase(modid+"goldclock");
    public static final Item Comp_Torch = new itemBase(modid+"compressedtorch");
    public static final Item Binding_Element = new itemBase(modid+"bindingelement");
    public static final Item Binding_Element2 = new itemBase(modid+"bindingelement2");
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final Block Torch_lvl_1 = new BlockTorchBase(modid+"torch_lvl1");
    public static final Block Torch_lvl_2 = new BlockTorchBase(modid+"torch_lvl2");
    public static final Block Torch_lvl_3 = new BlockTorchBase(modid+"torch_lvl3");
    public static final Block Torch_lvl_4 = new BlockTorchBase(modid+"torch_lvl4");
    public static final Block Torch_lvl_5 = new BlockTorchBase(modid+"torch_lvl5");
    public static final Block Compressed_Torch_lvl1 = new CompressedTorch(modid+"compressed_torch_lvl1");
    public static final Block Compressed_Torch_lvl2 = new CompressedTorch(modid+"compressed_torch_lvl2");
    public static final Block Compressed_Torch_lvl3 = new CompressedTorch(modid+"compressed_torch_lvl3");
    public static final Block Compressed_Torch_lvl4 = new CompressedTorch(modid+"compressed_torch_lvl4");
    public static final Block Compressed_Torch_lvl5 = new CompressedTorch(modid+"compressed_torch_lvl5");
    public static final Block Time_Collector = new BlockCollector(modid+"time_collector");
}
