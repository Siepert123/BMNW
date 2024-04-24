package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModItems {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BMNW.MOD_ID);

    /* ITEMS ITEMS ITEMS */


    //Ingredients
    public static final RegistryObject<Item> CONDUCTIVE_COPPER_INGOT = ITEMS.register("conductive_copper_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONDUCTIVE_COPPER_WIRE = ITEMS.register("conductive_copper_wire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_CRYSTAL = ITEMS.register("raw_bismuth",
            () -> new TooltippedItem(new Item.Properties(), Component.translatable("item.bmnw.raw_bismuth.desc"), false));
    public static final RegistryObject<Item> BISMUTH_INGOT = ITEMS.register("bismuth_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VALVE_HANDLE = ITEMS.register("valve_handle",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INSULATOR = ITEMS.register("insulator",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ISOLATOR = ITEMS.register("isolator",
            () -> new Item(new Item.Properties()));

    //PlayStation
    public static final RegistryObject<Item> PLAYSTATION = ITEMS.register("playstation",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CREATIVE_SOURCE_PLACER = ITEMS.register("creative_source_placer",
            () -> new CreativeSourcePlacerItem(new Item.Properties()));

    //Nuclear related items
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_THORIUM = ITEMS.register("raw_thorium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 9));
    public static final RegistryObject<Item> URANIUM_233_INGOT = ITEMS.register("uranium_233_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 15));
    public static final RegistryObject<Item> URANIUM_235_INGOT = ITEMS.register("uranium_235_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 12));
    public static final RegistryObject<Item> URANIUM_238_INGOT = ITEMS.register("uranium_238_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 9));
    public static final RegistryObject<Item> THORIUM_INGOT = ITEMS.register("thorium_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 5));
    public static final RegistryObject<Item> THORIUM_232_INGOT = ITEMS.register("thorium_232_ingot",
            () -> new RadioactiveItem(new Item.Properties(), 7));

    //Very hot items
    public static final RegistryObject<Item> ACTIVE_FUSION_REACTION = ITEMS.register("active_fusion_reaction",
            () -> new VeryHotItem(new Item.Properties().fireResistant().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> INACTIVE_FUSION_REACTION = ITEMS.register("inactive_fusion_reaction",
            () -> new Item(new Item.Properties()));

    //Tools and stuff


    public static final RegistryObject<Item> DETONATOR = ITEMS.register("detonator",
            () -> new DetonatorItem(new Item.Properties()));
    public static final RegistryObject<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter",
            () -> new GeigerCounterItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Items");
        ITEMS.register(eventBus);
    }
}
