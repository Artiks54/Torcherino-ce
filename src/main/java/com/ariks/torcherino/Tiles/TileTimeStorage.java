package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiStoragePacket;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;

public class TileTimeStorage extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    public int TimeStorage;
    private int OldTimeStorage;
    public boolean OpenGuiStorage;
    public int TimeStorageMax = Config.TimeStorageMaxTime;
    @Override
    public void update() {
        if (world.isRemote) return;
        if (OpenGuiStorage) {
            updateGui();
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            updateOldValues();
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiStoragePacket(pos,TimeStorage), packetTargetPoint);
            if (Config.DebugMod) {
                Torcherino.logger.debug("Send Packet update GUI Tile Storage");
            }
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return OldTimeStorage != TimeStorage;
    }
    private void updateOldValues() {
        OldTimeStorage = TimeStorage;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TimeStorage", TimeStorage);
        nbt.setInteger("OldTimeStorage", OldTimeStorage);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeStorage = nbt.getInteger("TimeStorage");
        this.OldTimeStorage = nbt.getInteger("OldTimeStorage");
        super.readFromNBT(nbt);
    }
    @Override
    public @NotNull SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }
    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }
    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
        this.getWorld().markBlockRangeForRenderUpdate(this.pos, this.pos);
    }
    @Override
    public boolean shouldRefresh(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState oldState, @NotNull IBlockState newSate) {
        return super.shouldRefresh(world, pos, oldState, newSate);
    }
    @Override
    public void onLoad() {
        this.world.getBlockState(pos);
        if (!this.world.isRemote) {
            this.packetTargetPoint = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 64);
        }
    }
}
