package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.worldgen;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class BiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_URANIUM_ORE = registerKey("uranium_ore");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_URANIUM_ORE = registerKey("deepslate_uranium_ore");
    public static final ResourceKey<BiomeModifier> ADD_THORIUM_ORE = registerKey("thorium_ore");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_THORIUM_ORE = registerKey("deepslate_thorium_ore");
    public static final ResourceKey<BiomeModifier> ADD_GIANT_COAL_VEIN = registerKey("giant_coal_vein");
    public static final ResourceKey<BiomeModifier> ADD_BISMUTH_CRYSTAL_VEIN = registerKey("bismuth_crystal_vein");
    public static final ResourceKey<BiomeModifier> ADD_STEAM_HOTSPOT = registerKey("steam_hotspot");

    public static void bootstrap(@NotNull BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_URANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.URANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_DEEPSLATE_URANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.DEEPSLATE_URANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_THORIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.THORIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_DEEPSLATE_THORIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.DEEPSLATE_THORIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_GIANT_COAL_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.GIANT_COAL_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_BISMUTH_CRYSTAL_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.BISMUTH_CRYSTAL_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_STEAM_HOTSPOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_VILLAGE_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.STEAM_HOTSPOT_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }


    private static @NotNull ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(BMNW.MOD_ID, name));
    }
}
