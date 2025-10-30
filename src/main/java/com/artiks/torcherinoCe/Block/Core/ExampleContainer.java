package com.artiks.torcherinoCe.Block.Core;

import com.artiks.torcherinoCe.network.DataSerializer;
import com.artiks.torcherinoCe.network.Packet.TileSyncMessageClient;
import com.artiks.torcherinoCe.network.RegistryNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class ExampleContainer extends Container {

    private final TileExampleContainer tileEntity;
    private final BlockPos pos;
    private final Map<String, Object> lastSentData = new HashMap<>();

    public ExampleContainer(TileExampleContainer tile) {
        this.tileEntity = tile;
        this.pos = tile.getPos();
    }

    protected void PlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
        PlayerHotbar(inventoryPlayer);
    }
    private void PlayerHotbar(InventoryPlayer inventoryPlayer) {
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 144));
        }
    }

    @Override
    public void addListener(@NotNull IContainerListener listener) {
        super.addListener(listener);
        Map<String, Object> fullData = new HashMap<>();
        tileEntity.getSyncData(fullData);
        byte[] serializedData = DataSerializer.serialize(fullData);
        if (listener instanceof EntityPlayerMP playerMP) {
            RegistryNetwork.network.sendTo(new TileSyncMessageClient(pos, serializedData), playerMP);
        }
        lastSentData.putAll(fullData);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        Map<String, Object> currentData = new HashMap<>();
        Map<String, Object> changedData = new HashMap<>();
        tileEntity.getSyncData(currentData);
        for (Map.Entry<String, Object> entry : currentData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!value.equals(lastSentData.get(key))) {
                changedData.put(key, value);
            }
        }
        if (!changedData.isEmpty()) {
            byte[] serializedData = DataSerializer.serialize(changedData);
            for (IContainerListener listener : this.listeners) {
                if (listener instanceof EntityPlayerMP playerMP) {
                    RegistryNetwork.network.sendTo(new TileSyncMessageClient(pos, serializedData), playerMP);
                }
            }
            lastSentData.putAll(changedData);
        }
    }

    @Override
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);
    }

    @Override
    public boolean canInteractWith(@NotNull EntityPlayer entityPlayer) {
        return this.tileEntity.isUsableByPlayer(entityPlayer);
    }

    @Override
    public @NotNull ItemStack transferStackInSlot(@NotNull EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }
}