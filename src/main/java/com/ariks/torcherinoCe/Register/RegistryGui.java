package com.ariks.torcherinoCe.Register;

import com.ariks.torcherinoCe.Block.MarkerAcceleration.ContainerMarker;
import com.ariks.torcherinoCe.Block.MarkerAcceleration.GuiMarker;
import com.ariks.torcherinoCe.Block.MarkerAcceleration.TileMarker;
import com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration.ContainerEnergyParticle;
import com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration.GuiEnergyParticle;
import com.ariks.torcherinoCe.Block.ParticleCollector.EnergyGeneration.TileEnergyParticle;
import com.ariks.torcherinoCe.Block.ParticleCollector.ContainerParticleCollector;
import com.ariks.torcherinoCe.Block.ParticleCollector.GuiParticleCollector;
import com.ariks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.ariks.torcherinoCe.Block.RfMolecular.ContainerRfMolecular;
import com.ariks.torcherinoCe.Block.RfMolecular.GuiRfMolecular;
import com.ariks.torcherinoCe.Block.RfMolecular.TileRfMolecular;
import com.ariks.torcherinoCe.Block.Time.Aceleration.ContainerAcceleration;
import com.ariks.torcherinoCe.Block.Time.Aceleration.GuiAcceleration;
import com.ariks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator.ContainerEnergyTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator.GuiEnergyTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.EnergyTimeManipulator.TileEnergyTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeCollector.ContainerTimeCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeCollector.GuiTimeCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeCollector.TileCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector.ContainerTimeEnergyCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector.GuiEnergyTimeCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeEnergyCollector.TileEnergyCollectors;
import com.ariks.torcherinoCe.Block.Time.TimeManipulator.ContainerTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeManipulator.GuiTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeManipulator.TileTimeManipulator;
import com.ariks.torcherinoCe.Block.Time.TimeStorage.ContainerTimeStorage;
import com.ariks.torcherinoCe.Block.Time.TimeStorage.GuiTimeStorage;
import com.ariks.torcherinoCe.Block.Time.TimeStorage.TileTimeStorage;
import com.ariks.torcherinoCe.Block.Torcherino.ContainerTorcherino;
import com.ariks.torcherinoCe.Block.Torcherino.GuiTorcherino;
import com.ariks.torcherinoCe.Block.Torcherino.TileTorcherinoBase;
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
    public static final int GUI_ENERGY_TIME_MANIPULATOR = 9;
    public static final int GUI_MARKER = 10;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_TIME_STORAGE) {
            return new ContainerTimeStorage(player.inventory,(TileTimeStorage)world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_PARTICLE_COLLECTOR) {
            return new ContainerParticleCollector(player.inventory,(TileParticleCollector)world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_COLLECTORS_TIME) {
            return new ContainerTimeCollectors(player.inventory,(TileCollectors) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_ENERGY_COLLECTORS_TIME) {
            return new ContainerTimeEnergyCollectors(player.inventory,(TileEnergyCollectors) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_ACCELERATION) {
            return new ContainerAcceleration(player.inventory,(TileAcceleration) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new ContainerTimeManipulator(player.inventory,(TileTimeManipulator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_ENERGY_TIME_MANIPULATOR) {
            return new ContainerEnergyTimeManipulator(player.inventory,(TileEnergyTimeManipulator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GUI_TORCHERINO) {
            return new ContainerTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new ContainerEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new ContainerRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_MARKER) {
            return new ContainerMarker(player.inventory, (TileMarker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_TIME_STORAGE) {
            return new GuiTimeStorage(player.inventory, (TileTimeStorage) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_PARTICLE_COLLECTOR) {
            return new GuiParticleCollector(player.inventory, (TileParticleCollector) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_COLLECTORS_TIME) {
            return new GuiTimeCollectors(player.inventory, (TileCollectors) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ENERGY_COLLECTORS_TIME) {
            return new GuiEnergyTimeCollectors(player.inventory, (TileEnergyCollectors) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_TIME_MANIPULATOR) {
            return new GuiTimeManipulator(player.inventory, (TileTimeManipulator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ENERGY_TIME_MANIPULATOR) {
            return new GuiEnergyTimeManipulator(player.inventory, (TileEnergyTimeManipulator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ACCELERATION) {
            return new GuiAcceleration(player.inventory, (TileAcceleration) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_TORCHERINO) {
            return new GuiTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new GuiEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new GuiRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)),player);
        }
        if (ID == GUI_MARKER) {
            return new GuiMarker(player.inventory, (TileMarker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}