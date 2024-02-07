package com.ariks.torcherino.Items;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.IHasModel;
import net.minecraft.item.Item;

public class itemBase extends Item implements IHasModel {
        public itemBase(String name){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(64);
        this.setCreativeTab(Torcherino.torcherinoTab);
        RegistryArray.ITEMS.add(this);
    }
    @Override
    public void registerModels() {
        Torcherino.proxy.registerItemRenderer(this,0,"inventory");
    }
}
