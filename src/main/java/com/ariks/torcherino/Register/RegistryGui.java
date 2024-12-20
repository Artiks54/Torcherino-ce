package com.ariks.torcherino.Register;

import com.ariks.torcherino.Block.EnergyGeneration.ContainerEnergyParticle;
import com.ariks.torcherino.Block.EnergyGeneration.GuiEnergyParticle;
import com.ariks.torcherino.Block.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherino.Block.RfMolecular.ContainerRfMolecular;
import com.ariks.torcherino.Block.RfMolecular.GuiRfMolecular;
import com.ariks.torcherino.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherino.Block.Time.Aceleration.ContainerAcceleration;
import com.ariks.torcherino.Block.Time.Aceleration.GuiAcceleration;
import com.ariks.torcherino.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherino.Block.Time.TimeCollector.ContainerTimeCollectors;
import com.ariks.torcherino.Block.Time.TimeCollector.GuiTimeCollectors;
import com.ariks.torcherino.Block.Time.TimeCollector.TileCollectors;
import com.ariks.torcherino.Block.ParticleCollector.*;
import com.ariks.torcherino.Block.Time.TimeEnergyCollector.ContainerTimeEnergyCollectors;
import com.ariks.torcherino.Block.Time.TimeEnergyCollector.GuiEnergyTimeCollectors;
import com.ariks.torcherino.Block.Time.TimeEnergyCollector.TileEnergyCollectors;
import com.ariks.torcherino.Block.Time.TimeManipulator.ContainerTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeManipulator.GuiTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherino.Block.Time.TimeStorage.ContainerTimeStorage;
import com.ariks.torcherino.Block.Time.TimeStorage.GuiTimeStorage;
import com.ariks.torcherino.Block.Time.TimeStorage.TileTimeStorage;
import com.ariks.torcherino.Block.Torcherino.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class RegistryGui implements IGuiHandler {
    public static final int GUI_PARTICLE_COLLECTOR = 0;
    public static final int GUI_TIME_STORAGE = 1;
    public static final int GUI_TORCHERINO = 2;
    public static final int GUI_COLLECTORS_TIME = 3;
    public static final int GUI_ACCELERATION = 4;
    public static final int GUI_TIME_MANIPULATOR = 5;
    public static final int GUI_ENERGY_PARTICLE = 6;
    public static final int GUI_RF_MOLECULAR = 7;
    public static final int GUI_ENERGY_COLLECTORS_TIME = 8;
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
        if (ID == GUI_ENERGY_COLLECTORS_TIME) {
            return new ContainerTimeEnergyCollectors(player.inventory,(TileEnergyCollectors) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_ACCELERATION) {
            return new ContainerAcceleration(player.inventory,(TileAcceleration) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new ContainerTimeManipulator(player.inventory,(TileTimeManipulator) world.getTileEntity(new BlockPos(x,y,z)),player);
        }
        if (ID == GUI_TORCHERINO) {
            return new ContainerTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new ContainerEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new ContainerRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
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
        if (ID == GUI_ENERGY_COLLECTORS_TIME) {
            return new GuiEnergyTimeCollectors(player.inventory, (TileEnergyCollectors) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new GuiTimeManipulator(player.inventory, (TileTimeManipulator) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ACCELERATION) {
            return new GuiAcceleration(player.inventory, (TileAcceleration) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_TORCHERINO) {
            return new GuiTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new GuiEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new GuiRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        return null;
    }
}