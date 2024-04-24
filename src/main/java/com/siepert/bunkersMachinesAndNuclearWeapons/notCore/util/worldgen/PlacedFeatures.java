package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.worldgen;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.worldgen.ore.OrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> URANIUM_ORE_PLACED_KEY = registerKey("uranium_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_URANIUM_ORE_PLACED_KEY = registerKey("deepslate_uranium_ore");
    public static final ResourceKey<PlacedFeature> THORIUM_ORE_PLACED_KEY = registerKey("thorium_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_THORIUM_ORE_PLACED_KEY = registerKey("deepslate_thorium_ore");
    public static final ResourceKey<PlacedFeature> GIANT_COAL_VEIN_PLACED_KEY = registerKey("giant_coal_vein");
    public static final ResourceKey<PlacedFeature> BISMUTH_CRYSTAL_VEIN_PLACED_KEY = registerKey("bismuth_crystal_vein");
    public static final ResourceKey<PlacedFeature> STEAM_HOTSPOT_PLACED_KEY = registerKey("steam_hotspot");

    public static void bootstrap(@NotNull BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.URANIUM_ORE_KEY),
                OrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40))));
        register(context, DEEPSLATE_URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.DEEPSLATE_URANIUM_ORE_KEY),
                OrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(-10))));
        register(context, THORIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.THORIUM_ORE_KEY),
            OrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(50))));
        register(context, DEEPSLATE_THORIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.DEEPSLATE_THORIUM_ORE_KEY),
                OrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(-40))));
        register(context, GIANT_COAL_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.GIANT_COAL_VEIN_KEY),
                OrePlacement.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(-50))));
        register(context, BISMUTH_CRYSTAL_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.BISMUTH_CRYSTAL_VEIN_KEY),
                OrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(-30))));
        register(context, STEAM_HOTSPOT_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.STEAM_HOTSPOT_KEY),
                OrePlacement.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(-49))));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {return ResourceKey.create(Registries.PLACED_FEATURE,
            new ResourceLocation(BMNW.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
