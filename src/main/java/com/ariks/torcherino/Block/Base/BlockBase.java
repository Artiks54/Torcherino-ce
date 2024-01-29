package com.ariks.torcherino.Block.Base;
import com.ariks.torcherino.Config;
import com.ariks.torcherino.Register.RegistryArray;
import com.ariks.torcherino.Tiles.TileTorcherinoBase;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.IHasModel;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Objects;

public class BlockBase extends BlockTorch implements IHasModel {
    public BlockBase(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setLightLevel(1);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Torcherino.torcherinoTab);
        RegistryArray.BLOCKS.add(this);
        RegistryArray.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

   @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
       if (Config.defaultModes) {
           if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND) && player.getHeldItemMainhand().isEmpty()) {
               TileEntity tile = world.getTileEntity(pos);
               if (player.isSneaking()) {
                   assert tile != null;
                   ((TileTorcherinoBase) tile).increaseSpeed();
                   player.sendStatusMessage(((TileTorcherinoBase) tile).getDiscription(), true);
               } else {
                   assert tile != null;
                   ((TileTorcherinoBase) tile).increaseArea();
                   player.sendStatusMessage(((TileTorcherinoBase) tile).getDiscription(), true);
               }
           }
           return true;
       }
       return false;
   }
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(world, pos, state);
        if(Config.logPlacement) Torcherino.logger.info(this.getClass().getName().substring(30) + "Torcherino place: "+ pos.getX()+","+pos.getY()+","+pos.getZ());
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public void registerModels() {Torcherino.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");}

}