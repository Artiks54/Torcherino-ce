package com.artiks.torcherinoCe.Register;

import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.ContainerCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.GuiCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.CobbleGenerator.TileCobbleGenerator;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.ContainerLuck;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.GuiLuck;
import com.artiks.torcherinoCe.Block.Energy.FurnaceLuck.TileLuck;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.ContainerRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.GuiRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.MolecularFarm.TileRfMolecularFarm;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.ContainerRfMolecularLegendary;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.GuiRfMolecularLegendary;
import com.artiks.torcherinoCe.Block.Energy.Molecular.RFMolecluarLegendary.TileRfMolecularLegendary;
import com.artiks.torcherinoCe.Block.ItemStorage.ContainerItemStorage;
import com.artiks.torcherinoCe.Block.ItemStorage.GuiItemStorage;
import com.artiks.torcherinoCe.Block.ItemStorage.TileItemStorage;
import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.ContainerMarker;
import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.GuiMarker;
import com.artiks.torcherinoCe.Block.Energy.MarkerAcceleration.TileMarker;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.ContainerEnergyParticle;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.GuiEnergyParticle;
import com.artiks.torcherinoCe.Block.Energy.ParticleEnergyGeneration.TileEnergyParticle;
import com.artiks.torcherinoCe.Block.ParticleCollector.ContainerParticleCollector;
import com.artiks.torcherinoCe.Block.ParticleCollector.GuiParticleCollector;
import com.artiks.torcherinoCe.Block.ParticleCollector.TileParticleCollector;
import com.artiks.torcherinoCe.Block.Energy.Molecular.ContainerRfMolecular;
import com.artiks.torcherinoCe.Block.Energy.Molecular.GuiRfMolecular;
import com.artiks.torcherinoCe.Block.Energy.Molecular.TileRfMolecular;
import com.artiks.torcherinoCe.Block.Time.Aceleration.ContainerAcceleration;
import com.artiks.torcherinoCe.Block.Time.Aceleration.GuiAcceleration;
import com.artiks.torcherinoCe.Block.Time.Aceleration.TileAcceleration;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.ContainerEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.GuiEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Energy.EnergyTimeManipulator.TileEnergyTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeCollector.ContainerTimeCollectors;
import com.artiks.torcherinoCe.Block.Time.TimeCollector.GuiTimeCollectors;
import com.artiks.torcherinoCe.Block.Time.TimeCollector.TileCollectors;
import com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector.ContainerTimeEnergyCollectors;
import com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector.GuiEnergyTimeCollectors;
import com.artiks.torcherinoCe.Block.Energy.TimeEnergyCollector.TileEnergyCollectors;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.ContainerTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.GuiTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeManipulator.TileTimeManipulator;
import com.artiks.torcherinoCe.Block.Time.TimeStorage.ContainerTimeStorage;
import com.artiks.torcherinoCe.Block.Time.TimeStorage.GuiTimeStorage;
import com.artiks.torcherinoCe.Block.Time.TimeStorage.TileTimeStorage;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.ContainerTorcherino;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.GuiTorcherino;
import com.artiks.torcherinoCe.Block.Energy.Torcherino.TileTorcherinoBase;
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
    public static final int GUI_LUCK = 11;
    public static final int GUI_ITEM_STORAGE = 12;
    public static final int GUI_RF_MOLECULAR_LEGENDARY = 13;
    public static final int GUI_RF_MOLECULAR_FARM = 14;
    public static final int GUI_COBBLE_GENERATOR = 15;

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
            return new ContainerTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new ContainerEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new ContainerRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_MARKER) {
            return new ContainerMarker(player.inventory, (TileMarker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_LUCK) {
            return new ContainerLuck(player.inventory, (TileLuck) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ITEM_STORAGE) {
            return new ContainerItemStorage(player.inventory, (TileItemStorage) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR_LEGENDARY) {
            return new ContainerRfMolecularLegendary(player.inventory, (TileRfMolecularLegendary) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR_FARM) {
            return new ContainerRfMolecularFarm(player.inventory, (TileRfMolecularFarm) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_COBBLE_GENERATOR) {
            return new ContainerCobbleGenerator(player.inventory, (TileCobbleGenerator) world.getTileEntity(new BlockPos(x, y, z)));
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
            return new GuiTorcherino(player.inventory, (TileTorcherinoBase) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ENERGY_PARTICLE) {
            return new GuiEnergyParticle(player.inventory, (TileEnergyParticle) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR) {
            return new GuiRfMolecular(player.inventory, (TileRfMolecular) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_MARKER) {
            return new GuiMarker(player.inventory, (TileMarker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_LUCK) {
            return new GuiLuck(player.inventory, (TileLuck) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ITEM_STORAGE) {
            return new GuiItemStorage(player.inventory, (TileItemStorage) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR_LEGENDARY) {
            return new GuiRfMolecularLegendary(player.inventory, (TileRfMolecularLegendary) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_RF_MOLECULAR_FARM) {
            return new GuiRfMolecularFarm(player.inventory, (TileRfMolecularFarm) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_COBBLE_GENERATOR) {
            return new GuiCobbleGenerator(player.inventory, (TileCobbleGenerator) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}