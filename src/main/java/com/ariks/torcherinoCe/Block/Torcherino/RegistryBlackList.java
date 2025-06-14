package com.ariks.torcherinoCe.Block.Torcherino;

import com.ariks.torcherinoCe.Register.RegistryBlock;
import com.ariks.torcherinoCe.Tags;
import com.ariks.torcherinoCe.torcherinoCe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public final class RegistryBlackList {
    public static void preInit() {
        //Minecraft block
        RegistryAcceleration.blacklistBlock(Blocks.AIR);
        RegistryAcceleration.blacklistBlock(Blocks.WATER);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_WATER);
        RegistryAcceleration.blacklistBlock(Blocks.LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.FLOWING_LAVA);
        RegistryAcceleration.blacklistBlock(Blocks.FURNACE);
        //All mods blocks
        for (Block block : RegistryBlock.BLOCKS) {
            RegistryAcceleration.blacklistBlock(block);
                torcherinoCe.logger.info(Tags.MODNAME + ": Block add black list to acceleration: {}", block.getLocalizedName());
        }
    }
}