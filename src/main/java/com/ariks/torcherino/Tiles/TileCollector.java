package com.ariks.torcherino.Tiles;

import com.ariks.torcherino.Register.AccelerationRegistry;
import com.ariks.torcherino.Torcherino;
import com.ariks.torcherino.network.ModPacketHandler;
import com.ariks.torcherino.network.UpdateGuiCollectorPacket;
import com.ariks.torcherino.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

public class TileCollector extends TileEntity implements ITickable {
    protected NetworkRegistry.TargetPoint packetTargetPoint;
    public boolean BooleanWork;
    public boolean OpenGuiCollector;
    private boolean OldBooleanWork;
    private int CooldownDecr;
    private int OldTimeCollect;
    public final int speed = Config.CollectorSpeed;
    public final int AreaModifier = Config.CollectorRadius;
    private int CooldownIncr = Config.CollectorTimeCooldownConfig;
    public int TimeCollect = Config.CollectorTimeCollectConfig;
    private final Random rand = new Random();
    protected int speedBase(int base) {
        return base;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        if (OpenGuiCollector) {
            updateGui();
        }
        if (!BooleanWork) {
            if (TimeCollect < Integer.MAX_VALUE) {
                CooldownIncr++;
                if (CooldownIncr >= 19) {
                    CooldownIncr = 0;
                    TimeCollect++;
                }
            }
        } else {
            if (TimeCollect > 1) {
                CooldownDecr++;
                UpdateTickArea();
                if (CooldownDecr >= 19) {
                    TimeCollect--;
                    CooldownDecr = 0;
                    WorkVisual();
                }
            } else {
                BooleanWork = false;
            }
        }
    }
    public void updateGui() {
        if (shouldSendGuiUpdatePacket()) {
            updateOldValues();
            ModPacketHandler.network.sendToAllTracking(new UpdateGuiCollectorPacket(pos, BooleanWork, TimeCollect), packetTargetPoint);
            if (Config.DebugMod) {
                Torcherino.logger.debug("Send Packet update GUI Tile Collector");
            }
        }
    }
    public void WorkVisual() {
        if (Config.BooleanVisualWork) {
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            EnumParticleTypes parc = EnumParticleTypes.FLAME;
            ((WorldServer) world).spawnParticle(parc, x + 0.5, y + 1.15, z + 0.5, 1, 0, 0, 0, 0, new int[0]);
        }
    }
    private boolean shouldSendGuiUpdatePacket() {
        return OldBooleanWork != BooleanWork || OldTimeCollect != TimeCollect;
    }
    private void updateOldValues() {
        OldBooleanWork = BooleanWork;
        OldTimeCollect = TimeCollect;
    }
    private void UpdateTickArea() {
        int AreaDecrX = pos.getX() - AreaModifier;
        int AreaDecrY = pos.getY() - AreaModifier;
        int AreaDecrZ = pos.getZ() - AreaModifier;
        int AreaIncrX = pos.getX() + AreaModifier;
        int AreaIncrY = pos.getY() + AreaModifier;
        int AreaIncrZ = pos.getZ() + AreaModifier;
        for (BlockPos pos : BlockPos.getAllInBox(AreaDecrX, AreaDecrY, AreaDecrZ, AreaIncrX, AreaIncrY, AreaIncrZ)) {
            AcelerationTick(pos);
        }
    }
    private void AcelerationTick(BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof BlockFluidBase || AccelerationRegistry.isBlockBlacklisted(block)) {
            return;
        }
        if (block.getTickRandomly()) {
            int speedBase = speedBase(speed);
            for (int i = 0; i < speedBase; i++) {
                if (world.getBlockState(pos) != blockState) {
                    break;
                }
                block.updateTick(world, pos, blockState, rand);
            }
        }
        if (block.hasTileEntity(world.getBlockState(pos))) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile == null || tile.isInvalid()) {
                return;
            }
            if (AccelerationRegistry.isTileBlacklisted(tile.getClass())) {
                return;
            }
            int speedBase = speedBase(speed);
            for (int i = 0; i < speedBase; i++) {
                if (tile.isInvalid()) {
                    break;
                }
                if (tile instanceof ITickable) {
                    ((ITickable) tile).update();
                }
            }
        }
    }
    public void toggleWork() {
        BooleanWork = !BooleanWork;
    }
    @Override
    public @NotNull NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("TimeCollect", TimeCollect);
        compound.setInteger("OldTimeCollect", OldTimeCollect);
        compound.setInteger("CooldownIncr", CooldownIncr);
        compound.setInteger("CooldownDecr", CooldownDecr);
        compound.setBoolean("BooleanWork", BooleanWork);
        compound.setBoolean("OldBooleanWork", OldBooleanWork);
        return super.writeToNBT(compound);
    }
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.TimeCollect = compound.getInteger("TimeCollect");
        this.OldTimeCollect = compound.getInteger("OldTimeCollect");
        this.TimeCollect = compound.getInteger("CooldownIncr");
        this.TimeCollect = compound.getInteger("CooldownDecr");
        this.BooleanWork = compound.getBoolean("BooleanWork");
        this.OldBooleanWork = compound.getBoolean("OldBooleanWork");
        super.readFromNBT(compound);
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
