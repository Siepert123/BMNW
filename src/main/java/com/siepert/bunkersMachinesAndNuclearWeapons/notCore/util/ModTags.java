package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static void init() {
        Blocks.init();
    }

    public static class Blocks {
        private static void init() {}

        public static final TagKey<Block> INSTANT_EVAPORATE = tag("nuclear_handling/instant_evaporate");
        public static final TagKey<Block> CHARRING_LOGS = tag("nuclear_handling/charring_logs");


        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(BMNW.MOD_ID, name));
        }
    }
}
