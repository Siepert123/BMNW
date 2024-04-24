package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem.BombBlockItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem.RadioactiveBlockItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem.TooltippedBlockItem;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem.VeryHotRadioactiveBlockItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModBlockItems {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BMNW.MOD_ID);
    //Silly

    public static final RegistryObject<Item> OBJ_TEST = ITEMS.register("obj_test",
            () -> new RadioactiveBlockItem(ModBlocks.OBJ_TEST.get(), new Item.Properties(), 1000000000));

    //Natural Blocks
    public static final RegistryObject<Item> URANIUM_ORE = ITEMS.register("uranium_ore",
            () -> new BlockItem(ModBlocks.URANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_URANIUM_ORE = ITEMS.register("deepslate_uranium_ore",
            () -> new BlockItem(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> THORIUM_ORE = ITEMS.register("thorium_ore",
            () -> new BlockItem(ModBlocks.THORIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_THORIUM_ORE = ITEMS.register("deepslate_thorium_ore",
            () -> new BlockItem(ModBlocks.DEEPSLATE_THORIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BEDROCK_STEAM_HOTSPOT = ITEMS.register("bedrock_steam_hotspot",
            () -> new BlockItem(ModBlocks.BEDROCK_STEAM_HOTSPOT.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_STEAM_HOTSPOT = ITEMS.register("stone_steam_hotspot",
            () -> new BlockItem(ModBlocks.STONE_STEAM_HOTSPOT.get(), new Item.Properties()));
    //Misc
    public static final RegistryObject<Item> OBSIDIAN_GRAVEL = ITEMS.register("obsidian_gravel",
            () -> new BlockItem(ModBlocks.OBSIDIAN_GRAVEL.get(), new Item.Properties()));

    //Machines & Machine Casings
    public static final RegistryObject<Item> LV_MACHINE_CASING = ITEMS.register("lv_machine_casing",
            () -> new BlockItem(ModBlocks.LV_MACHINE_CASING.get(), new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_BLAST_FURNACE = ITEMS.register("alloy_blast_furnace",
            () -> new BlockItem(ModBlocks.ALLOY_BLAST_FURNACE.get(), new Item.Properties()));

    public static final RegistryObject<Item> FLUID_DEPOSITOR = ITEMS.register("fluid_depositor",
            () -> new TooltippedBlockItem(ModBlocks.FLUID_DEPOSITOR.get(), new Item.Properties(), Component.translatable("block.bmnw.fluid_depositor.desc"), true));
    public static final RegistryObject<Item> BRICK_CHIMNEY = ITEMS.register("brick_chimney",
            () -> new TooltippedBlockItem(ModBlocks.BRICK_CHIMNEY.get(), new Item.Properties(), Component.translatable("block.bmnw.brick_chimney.desc"), true));
    public static final RegistryObject<Item> SCAFFOLD = ITEMS.register("scaffold",
            () -> new BlockItem(ModBlocks.SCAFFOLD.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEAM_CAP = ITEMS.register("steam_cap",
            () -> new BlockItem(ModBlocks.STEAM_CAP.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEAM_SWITCH_SMALL = ITEMS.register("steam_switch_small",
            () -> new BlockItem(ModBlocks.STEAM_SWITCH_SMALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEAM_PIPE = ITEMS.register("steam_pipe",
            () -> new BlockItem(ModBlocks.STEAM_PIPE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STEAM_OUTLET = ITEMS.register("steam_outlet",
            () -> new BlockItem(ModBlocks.STEAM_OUTLET.get(), new Item.Properties()));

    public static final RegistryObject<Item> GAS_CENTRIFUGE = ITEMS.register("gas_centrifuge",
            () -> new BlockItem(ModBlocks.GAS_CENTRIFUGE.get(), new Item.Properties()));


    //Concrete
    public static final RegistryObject<Item> CONCRETE = ITEMS.register("concrete",
            () -> new BlockItem(ModBlocks.CONCRETE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_STAIRS = ITEMS.register("concrete_stairs",
            () -> new BlockItem(ModBlocks.CONCRETE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_SLAB = ITEMS.register("concrete_slab",
            () -> new BlockItem(ModBlocks.CONCRETE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_BRICKS = ITEMS.register("concrete_bricks",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_BRICKS_STAIRS = ITEMS.register("concrete_bricks_stairs",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONCRETE_BRICKS_SLAB = ITEMS.register("concrete_bricks_slab",
            () -> new BlockItem(ModBlocks.CONCRETE_BRICKS_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> CREATIVE_CONCRETE_BRICKS = ITEMS.register("creative_concrete_bricks",
            () -> new TooltippedBlockItem(ModBlocks.CREATIVE_CONCRETE_BRICKS.get(), new Item.Properties(),
                    Component.translatable("block.bmnw.creative_concrete_bricks.desc"), true));

    public static final RegistryObject<Item> FOUNDATION_CONCRETE = ITEMS.register("foundation_concrete",
            () -> new BlockItem(ModBlocks.FOUNDATION_CONCRETE.get(), new Item.Properties()));

    //Other strong blocks
    public static final RegistryObject<Item> STEEL_REINFORCED_GLASS = ITEMS.register("steel_reinforced_glass",
            () -> new BlockItem(ModBlocks.STEEL_REINFORCED_GLASS.get(), new Item.Properties()));

    //Nuclear remains
    public static final RegistryObject<Item> NUCLEAR_REMAINS = ITEMS.register("nuclear_remains",
            () -> new BlockItem(ModBlocks.NUCLEAR_REMAINS.get(), new Item.Properties()));
    public static final RegistryObject<Item> NUCLEAR_DIAMOND_ORE_REMAINS = ITEMS.register("nuclear_diamond_ore_remains",
            () -> new BlockItem(ModBlocks.NUCLEAR_DIAMOND_ORE_REMAINS.get(), new Item.Properties()));
    public static final RegistryObject<Item> NUCLEAR_EMERALD_ORE_REMAINS = ITEMS.register("nuclear_emerald_ore_remains",
            () -> new BlockItem(ModBlocks.NUCLEAR_EMERALD_ORE_REMAINS.get(), new Item.Properties()));

    public static final RegistryObject<Item> HOT_NUCLEAR_REMAINS = ITEMS.register("hot_nuclear_remains",
            () -> new VeryHotRadioactiveBlockItem(ModBlocks.HOT_NUCLEAR_REMAINS.get(), new Item.Properties(), 1000));
    public static final RegistryObject<Item> HOT_NUCLEAR_DIAMOND_ORE_REMAINS = ITEMS.register("hot_nuclear_diamond_ore_remains",
            () -> new VeryHotRadioactiveBlockItem(ModBlocks.HOT_NUCLEAR_DIAMOND_ORE_REMAINS.get(), new Item.Properties(), 1000));
    public static final RegistryObject<Item> HOT_NUCLEAR_EMERALD_ORE_REMAINS = ITEMS.register("hot_nuclear_emerald_ore_remains",
            () -> new VeryHotRadioactiveBlockItem(ModBlocks.HOT_NUCLEAR_EMERALD_ORE_REMAINS.get(), new Item.Properties(), 1000));

    public static final RegistryObject<Item> SMOULDERING_LOG = ITEMS.register("smouldering_log",
            () -> new BlockItem(ModBlocks.SMOULDERING_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHARRED_LOG = ITEMS.register("charred_log",
            () -> new BlockItem(ModBlocks.CHARRED_LOG.get(), new Item.Properties()));

    //Bomb utilities
    public static final RegistryObject<Item> PARTICLE_SPAWNER = ITEMS.register("test_particle_spawner",
            () -> new BlockItem(ModBlocks.PARTICLE_SPAWNER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DUD = ITEMS.register("dud",
            () -> new BombBlockItem(ModBlocks.DUD.get(), new Item.Properties(),
                    "32", "", Component.translatable("tooltip.bmnw.soulfire_bomb")));
    public static final RegistryObject<Item> INCENDIARY_CHARGE = ITEMS.register("incendiary_charge",
            () -> new BombBlockItem(ModBlocks.INCENDIARY_CHARGE.get(), new Item.Properties(),
                    "", "", Component.translatable("tooltip.bmnw.incendiary_bomb")));
    public static final RegistryObject<Item> NUCLEAR_CHARGE = ITEMS.register("nuclear_charge",
            () -> new BombBlockItem(ModBlocks.NUCLEAR_CHARGE.get(), new Item.Properties(),
                    "16", "32", Component.translatable("tooltip.bmnw.nuclear_bomb")));
    public static final RegistryObject<Item> LITTLE_BOY = ITEMS.register("lil_bro",
            () -> new BombBlockItem(ModBlocks.LITTLE_BOY.get(), new Item.Properties().stacksTo(1),
                    "64", "128", Component.translatable("tooltip.bmnw.nuclear_bomb")));
    public static final RegistryObject<Item> FAT_MAN = ITEMS.register("caseoh",
            () -> new BombBlockItem(ModBlocks.FAT_MAN.get(), new Item.Properties().stacksTo(1),
                    "256", "512", Component.translatable("tooltip.bmnw.nuclear_bomb")));



    //Doors & hatches
    public static final RegistryObject<Item> WATER_SEAL_HATCH = ITEMS.register("water_seal_hatch",
            () -> new BlockItem(ModBlocks.WATER_SEAL_HATCH.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_LINED_DOOR = ITEMS.register("gold_lined_door",
            () -> new BlockItem(ModBlocks.GOLD_LINED_DOOR.get(), new Item.Properties()));

    //other
    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block",
            () -> new BlockItem(ModBlocks.STEEL_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_CRYSTAL_BLOCK = ITEMS.register("raw_bismuth_block",
            () -> new BlockItem(ModBlocks.BISMUTH_CRYSTAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BISMUTH_BLOCK = ITEMS.register("bismuth_block",
            () -> new BlockItem(ModBlocks.BISMUTH_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> RAW_URANIUM_BLOCK = ITEMS.register("raw_uranium_block",
            () -> new RadioactiveBlockItem(ModBlocks.RAW_URANIUM_BLOCK.get(), new Item.Properties(), 9));
    public static final RegistryObject<Item> RAW_THORIUM_BLOCK = ITEMS.register("raw_thorium_block",
            () -> new RadioactiveBlockItem(ModBlocks.RAW_THORIUM_BLOCK.get(), new Item.Properties(), 5));
    public static final RegistryObject<Item> URANIUM_BLOCK = ITEMS.register("uranium_block",
            () -> new RadioactiveBlockItem(ModBlocks.URANIUM_BLOCK.get(), new Item.Properties(), 81));
    public static final RegistryObject<Item> THORIUM_BLOCK = ITEMS.register("thorium_block",
            () -> new RadioactiveBlockItem(ModBlocks.THORIUM_BLOCK.get(), new Item.Properties(), 45));

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW BlockItems");
        ITEMS.register(eventBus);
    }
}
