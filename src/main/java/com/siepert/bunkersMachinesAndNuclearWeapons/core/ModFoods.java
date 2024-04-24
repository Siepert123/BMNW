package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.JarOfPissItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.RadAwayItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.RadioactiveItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.TooltippedItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModFoods {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BMNW.MOD_ID);



    public static final RegistryObject<Item> RAD_AWAY = ITEMS.register("rad_away",
            () -> new RadAwayItem(new Item.Properties().stacksTo(1), 50000));
    public static final RegistryObject<Item> WEAK_RAD_AWAY = ITEMS.register("weak_rad_away",
            () -> new RadAwayItem(new Item.Properties().stacksTo(1), 5000));
    public static final RegistryObject<Item> VERY_WEAK_RAD_AWAY = ITEMS.register("very_weak_rad_away",
            () -> new RadAwayItem(new Item.Properties().stacksTo(1), 500));
    public static final RegistryObject<Item> JAR_OF_PISS = ITEMS.register("jar_of_piss",
            () -> new JarOfPissItem(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> URANIUM_SANDWICH = ITEMS.register("uranium_sandwich",
            () -> new RadioactiveItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(7)
                            .saturationMod(20).build()), 69));

    public static final RegistryObject<Item> PROEMEVLAAI = ITEMS.register("proemevlaai",
            () -> new TooltippedItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(5)
                            .saturationMod(5).build()), Component.translatable("proemevlaai"), true));

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Foods");
        ITEMS.register(eventBus);
    }
}
