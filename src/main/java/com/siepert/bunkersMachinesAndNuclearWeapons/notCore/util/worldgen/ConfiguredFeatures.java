package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.worldgen;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> URANIUM_ORE_KEY = registerKey("uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_URANIUM_ORE_KEY = registerKey("deepslate_uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THORIUM_ORE_KEY = registerKey("thorium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_THORIUM_ORE_KEY = registerKey("deepslate_thorium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_COAL_VEIN_KEY = registerKey("giant_coal_vein");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_CRYSTAL_VEIN_KEY = registerKey("bismuth_crystal_vein");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STEAM_HOTSPOT_KEY = registerKey("steam_hotspot");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);
        RuleTest sandReplaceabeles = new BlockMatchTest(Blocks.SAND);

        List<OreConfiguration.TargetBlockState> UraniumOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.URANIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> DeepslateUraniumOre = List.of(OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> ThoriumOre = List.of(OreConfiguration.target(stoneReplaceabeles, ModBlocks.THORIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> DeepslateThoriumOre = List.of(OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_THORIUM_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> GiantCoalVein = List.of(OreConfiguration.target(deepslateReplaceabeles, Blocks.DEEPSLATE_COAL_ORE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> BismuthCrystalVein = List.of(OreConfiguration.target(deepslateReplaceabeles, ModBlocks.BISMUTH_CRYSTAL_BLOCK.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> SteamHotspot = List.of(OreConfiguration.target(deepslateReplaceabeles, ModBlocks.STEAM_HOTSPOT_PLACER.get().defaultBlockState()));

        register(context, URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(UraniumOre, 6));
        register(context, DEEPSLATE_URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(DeepslateUraniumOre, 6));
        register(context, THORIUM_ORE_KEY, Feature.ORE, new OreConfiguration(ThoriumOre, 8));
        register(context, DEEPSLATE_THORIUM_ORE_KEY, Feature.ORE, new OreConfiguration(DeepslateThoriumOre, 8));
        register(context, GIANT_COAL_VEIN_KEY, Feature.ORE, new OreConfiguration(GiantCoalVein, 32));
        register(context, BISMUTH_CRYSTAL_VEIN_KEY, Feature.ORE, new OreConfiguration(BismuthCrystalVein, 3));
        register(context, STEAM_HOTSPOT_KEY, Feature.ORE, new OreConfiguration(SteamHotspot, 1));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(
            BMNW.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
