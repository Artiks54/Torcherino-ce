package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiCollectorsPacket;
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

public class TileCollectors extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    public boolean OpenGuiCollectors;
    private int Cooldown;
    private int OldTimeCollect;
    public int TimeCollect;
    public int ConfigCooldownCollectors = Config.RequiredTimeCollectors;
    public int ConfigCollectorsMaxStorage = Config.MaxStorageTimeCollectors;
    @Override
    public void update() {
        if(world.isRemote)return;
        Cooldown++;
        if(OpenGuiCollectors){
            updateGui();
        }
        if(Cooldown >= ConfigCooldownCollectors) {
            Cooldown = 0;
            if (TimeCollect < ConfigCollectorsMaxStorage) {
                TimeCollect++;
            }
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            updateOldValues();
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiCollectorsPacket(pos, TimeCollect), packetTargetPoint);
            if (Config.DebugMod) {
                Torcherino.logger.debug("Send Packet update GUI Tile Collector");
            }
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return OldTimeCollect != TimeCollect;
    }
    private void updateOldValues() {
        OldTimeCollect = TimeCollect;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TimeCollect", TimeCollect);
        nbt.setInteger("OldTimeCollect", OldTimeCollect);
        nbt.setInteger("Cooldown", Cooldown);
        return super.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.TimeCollect = nbt.getInteger("TimeCollect");
        this.OldTimeCollect = nbt.getInteger("OldTimeCollect");
        this.Cooldown = nbt.getInteger("Cooldown");
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
