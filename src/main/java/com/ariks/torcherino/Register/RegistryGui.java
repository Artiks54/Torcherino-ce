package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.Aceleration.*;
import com.ariks.torcherino.Block.TimeCollector.*;
import com.ariks.torcherino.Block.ParticleCollector.*;
import com.ariks.torcherino.Block.TimeManipulator.ContainerTimeManipulator;
import com.ariks.torcherino.Block.TimeManipulator.GuiTimeManipulator;
import com.ariks.torcherino.Block.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.TimeStorage.*;
import com.ariks.torcherino.Block.Torcherino.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegistryGui implements IGuiHandler {
    public static final int GUI_PARTICLE_COLLECTOR = 0;
    public static final int GUI_TIME_STORAGE = 1;
    public static final int GUI_TORCHERINO = 2;
    public static final int GUI_COLLECTORS_TIME = 3;
    public static final int GUI_ACELERATION = 4;
    public static final int GUI_TIME_MANIPULATOR = 5;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_TIME_STORAGE) {
            return new ContainerTimeStorage(player.inventory,(TileTimeStorage)world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_PARTICLE_COLLECTOR) {
            return new ContainerParticleCollector(player.inventory,(TileParticleCollector)world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_COLLECTORS_TIME) {
            return new ContainerTimeCollectors(player.inventory,(TileCollectors) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_ACELERATION) {
            return new ContainerAceleration(player.inventory,(TileAcceleration) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new ContainerTimeManipulator(player.inventory,(TileTimeManipulator) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_TORCHERINO) {
            return new ContainerTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        return null;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_TIME_STORAGE) {
            return new GuiTimeStorage(player.inventory, (TileTimeStorage) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_PARTICLE_COLLECTOR) {
            return new GuiParticleCollector(player.inventory, (TileParticleCollector) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_COLLECTORS_TIME) {
            return new GuiTimeCollectors(player.inventory, (TileCollectors) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new GuiTimeManipulator(player.inventory, (TileTimeManipulator) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ACELERATION) {
            return new GuiAceleration(player.inventory, (TileAcceleration) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_TORCHERINO) {
            return new GuiTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        return null;
    }
}