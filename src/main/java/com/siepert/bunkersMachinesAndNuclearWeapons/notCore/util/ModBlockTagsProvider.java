package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BMNW.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Blocks.INSTANT_EVAPORATE).add(Blocks.WATER, Blocks.OAK_LEAVES, Blocks.BIRCH_LEAVES, Blocks.SPRUCE_LEAVES,
                Blocks.JUNGLE_LEAVES, Blocks.AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES, Blocks.TALL_SEAGRASS,
                Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.MANGROVE_LEAVES, Blocks.SEAGRASS,
                Blocks.CHERRY_LEAVES, Blocks.KELP, Blocks.KELP_PLANT, Blocks.DRIED_KELP_BLOCK, Blocks.SNOW,
                Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW, Blocks.VINE,
                Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
        tag(ModTags.Blocks.CHARRING_LOGS).add(Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.CHERRY_LOG,
                Blocks.JUNGLE_LOG, Blocks.MANGROVE_LOG, Blocks.DARK_OAK_LOG, Blocks.OAK_LOG,
                Blocks.SPRUCE_LOG,
                Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_CHERRY_LOG,
                Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_DARK_OAK_LOG,
                Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_SPRUCE_LOG);
    }
}
