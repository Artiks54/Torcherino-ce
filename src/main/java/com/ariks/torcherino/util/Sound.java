package com.ariks.torcherino.util;

import com.ariks.torcherino.Torcherino;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Sound {
    public static final SoundEvent SOUND_MOLECULAR = new SoundEvent(new ResourceLocation(Torcherino.MOD_ID, "molecular")).setRegistryName("molecular");
    private int tick = 0;
    public void play(World world, BlockPos pos, Boolean work,SoundEvent soundEvent) {
        if (work) {
            tick++;
            if (tick >= 30) {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                tick = 0;
            }
        }
    }
}
