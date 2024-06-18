package com.ariks.torcherino.Block;

import com.ariks.torcherino.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherino.Items.TimeStorage;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.util.Config;
import com.ariks.torcherino.util.ITileTimeStorage;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public abstract class ExampleBlock extends Block {
    public ExampleBlock(String name) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Torcherino.torcherinoTab);
        this.setHardness(2.5F);
        this.setResistance(4.5f);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
    }
    @Override
    public boolean onBlockActivated(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, EntityPlayer playerIn, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(playerIn.getHeldItem(hand).getItem() instanceof TimeStorage){
            return false;
        }
        TileEntity tile = worldIn.getTileEntity(pos);
        if(!worldIn.isRemote && tile instanceof TileExampleContainer){
            int id = Integer.parseInt(((TileExampleContainer) tile).getGuiID());
            playerIn.openGui(Torcherino.instance,id,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (Config.DebugMod && !worldIn.isRemote) {
            Torcherino.logger.info(
                    "TorcherinoCE-log: Block place: "+getUnlocalizedName()+" Cord: " + pos.getX() + "," + pos.getY() + "," + pos.getZ()+" DismID: "+worldIn.provider.getDimension()+" PlayerName: "+placer.getName());
        }
        if (!worldIn.isRemote) {
            TileEntity tile =  worldIn.getTileEntity(pos);
            if (tile != null && stack.hasTagCompound()) {
                if (tile instanceof ITileTimeStorage) {
                    NBTTagCompound nbt = stack.getTagCompound();
                    assert nbt != null;
                    if (nbt.hasKey("TimeStorage")) {
                        ((ITileTimeStorage) tile).AddTimeStorage(nbt.getInteger("TimeStorage"));
                    }
                }
            }
        }
    }
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (!world.isRemote) {
            TileEntity TileEntity =  world.getTileEntity(pos);
            if (TileEntity != null) {
                if (TileEntity instanceof ITileTimeStorage) {
                    ItemStack itemStack = new ItemStack(this.getItemDropped(state, world.rand, 0));
                    if (!itemStack.isEmpty()) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("TimeStorage", ((ITileTimeStorage) TileEntity).GetTimeStorage());
                        itemStack.setTagCompound(nbt);
                        spawnAsEntity(world, pos, itemStack);
                    }
                }
            }
        }
        super.onBlockHarvested(world, pos, state, player);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            assert nbt != null;
            if (nbt.hasKey("TimeStorage")) {
                int time = nbt.getInteger("TimeStorage");
                tooltip.add(TextFormatting.GRAY + "Time: " + time);
            }
        }
    }
    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {return false;}
    @Override
    public boolean isNormalCube(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {return false;}
    @Override
    public boolean isFullCube(@NotNull IBlockState state) {return false;}
    @Override
    public boolean hasTileEntity(@NotNull IBlockState state) {return true;}
    @Override
    public boolean canHarvestBlock(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EntityPlayer player) {
        TileEntity TileEntity =  world.getTileEntity(pos);
        return !(TileEntity instanceof TileParticleCollector || TileEntity instanceof ITileTimeStorage);
    }
}