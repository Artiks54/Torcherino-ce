package com.ariks.torcherino.Block.ParticleCollector;

import com.ariks.torcherino.Block.ExmapleBlock;
import com.ariks.torcherino.Register.RegistryGui;
import com.ariks.torcherino.Torcherino;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockParticleCollector extends ExmapleBlock {
    private final ParticleCollectorEnumLevel tileType;
    public BlockParticleCollector(String name, ParticleCollectorEnumLevel tileType) {
        super(name);
        this.tileType = tileType;
    }
    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return new AxisAlignedBB(0.062, 0, 0.062, 0.938, 0.875, 0.938);
    }
    @Override
    public void breakBlock(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        assert tileEntity != null;
        InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (!worldIn.isRemote && tile instanceof TileParticleCollector) {
            playerIn.openGui(Torcherino.instance, RegistryGui.GUI_PARTICLE_COLLECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
    @Override
    public TileEntity createTileEntity(@NotNull World world, @NotNull IBlockState state) {
        TileParticleCollector tileEntity = new TileParticleCollector();
        tileEntity.amount = tileType.getAmount();
        return tileEntity;
    }
}