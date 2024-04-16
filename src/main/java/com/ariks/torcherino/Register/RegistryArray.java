package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.*;
import com.ariks.torcherino.Items.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import java.util.ArrayList;
import java.util.List;

public final class RegistryArray {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Item Time_Wand_lvl1 = new TimeWands.TimeWand_lvl_1("time_wand_lvl1");
    public static final Item Time_Wand_lvl2 = new TimeWands.TimeWand_lvl_2("time_wand_lvl2");
    public static final Item Time_Wand_lvl3 = new TimeWands.TimeWand_lvl_3("time_wand_lvl3");
    public static final Item Time_Wand_lvl4 = new TimeWands.TimeWand_lvl_4("time_wand_lvl4");
    public static final Item Time_Wand_lvl5 = new TimeWands.TimeWand_lvl_5("time_wand_lvl5");
    public static final Item Time_Storage = new TimeStorage("time_storage");
    public static final Item Diamond_Clock = new itemBase("diamondclock");
    public static final Item Emerald_Clock = new itemBase("emeraldclock");
    public static final Item Lapis_Clock = new itemBase("lapisclock");
    public static final Item Redstone_Clock = new itemBase("redstoneclock");
    public static final Item Gold_Clock = new itemBase("goldclock");
    public static final Item Comp_Torch = new itemBase("compressedtorch");
    public static final Item Binding_Element = new itemBase("bindingelement");
    public static final Item Binding_Element2 = new itemBase("bindingelement2");
    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final Block Torch_lvl_1 = new BlockTorchBase("torch_lvl1");
    public static final Block Torch_lvl_2 = new BlockTorchBase("torch_lvl2");
    public static final Block Torch_lvl_3 = new BlockTorchBase("torch_lvl3");
    public static final Block Torch_lvl_4 = new BlockTorchBase("torch_lvl4");
    public static final Block Torch_lvl_5 = new BlockTorchBase("torch_lvl5");
    public static final Block Compressed_Torch_lvl1 = new CompressedTorch("compressed_torch_lvl1");
    public static final Block Compressed_Torch_lvl2 = new CompressedTorch("compressed_torch_lvl2");
    public static final Block Compressed_Torch_lvl3 = new CompressedTorch("compressed_torch_lvl3");
    public static final Block Compressed_Torch_lvl4 = new CompressedTorch("compressed_torch_lvl4");
    public static final Block Compressed_Torch_lvl5 = new CompressedTorch("compressed_torch_lvl5");
    public static final Block Time_Collector = new BlockCollector("time_collector");
}
