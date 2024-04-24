package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = BMNW.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue URANIUM_MICROSIEVERTS_TICK = BUILDER
            .comment("Radioactivity of uranium in microsieverts per tick")
            .defineInRange("uraniumMicroSievertsTick", 9, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue THORIUM_MICROSIEVERTS_TICK = BUILDER
            .comment("Radioactivity of thorium in microsieverts per tick")
            .defineInRange("thoriumMicroSievertsTick", 5, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.BooleanValue DISABLE_FUNNY_USERNAME_TOMFOOLERY = BUILDER
            .comment("Disable funny username thingies (aww)")
            .define("disableFunnyUsernameTomfoolery", false);

    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static int uraniumMicroSievertsTick = URANIUM_MICROSIEVERTS_TICK.getDefault();
    public static int thoriumMicroSievertsTick = THORIUM_MICROSIEVERTS_TICK.getDefault();
    public static boolean disableFunnyUsernameTomfoolery = DISABLE_FUNNY_USERNAME_TOMFOOLERY.getDefault();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        uraniumMicroSievertsTick = URANIUM_MICROSIEVERTS_TICK.get();
        thoriumMicroSievertsTick = THORIUM_MICROSIEVERTS_TICK.get();
        disableFunnyUsernameTomfoolery = DISABLE_FUNNY_USERNAME_TOMFOOLERY.get();
    }
}
