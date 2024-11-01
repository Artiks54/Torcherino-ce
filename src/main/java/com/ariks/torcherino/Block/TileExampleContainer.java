package com.ariks.torcherino.Block;

import com.ariks.torcherino.util.ITileHas;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class TileExampleContainer extends TileEntity implements ITileHas, IInteractionObject {
    private Container container;
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return !isInvalid() && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
    }
    public void setContainer(Container container) {
        this.container = container;
    }
    public Container getContainer() {
        return container;
    }
    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        if(container != null){
            return container;
        }
        return null;
    }
    @Override
    public @NotNull SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }
    @Override
    public @NotNull String getName() {
        return this.getBlockType().getUnlocalizedName() + ".name";
    }
    public int[] getValueList() {
        return new int[0];
    }
    @Override
    public boolean hasCustomName() {
        return false;
    }
    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }
    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }
    public void UpdateTile(){
        this.markDirty();
        this.world.notifyBlockUpdate(pos,world.getBlockState(pos),world.getBlockState(pos),3);
    }
    @Override
    public boolean shouldRefresh(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState oldState, @NotNull IBlockState newSate) {
        return super.shouldRefresh(world, pos, oldState, newSate);
    }
}