package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.*;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam.*;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.uraniumHexafluorite.GasCentrifugeBlock;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);

    //silly
    public static final RegistryObject<Block> OBJ_TEST = BLOCKS.register("obj_test",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    //Natural Blocks
    public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = BLOCKS.register("deepslate_uranium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> THORIUM_ORE = BLOCKS.register("thorium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_THORIUM_ORE = BLOCKS.register("deepslate_thorium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STEAM_HOTSPOT_PLACER = BLOCKS.register("steam_hotspot_placer",
            () -> new SteamHotspotPlacerBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).requiresCorrectToolForDrops().randomTicks()));
    public static final RegistryObject<Block> BEDROCK_STEAM_HOTSPOT = BLOCKS.register("bedrock_steam_hotspot",
            () -> new DecorationalSteamHotspotBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final RegistryObject<Block> STONE_STEAM_HOTSPOT = BLOCKS.register("stone_steam_hotspot",
            () -> new DecorationalSteamHotspotBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).requiresCorrectToolForDrops()));
    //Misc
    public static final RegistryObject<Block> OBSIDIAN_GRAVEL = BLOCKS.register("obsidian_gravel",
            () -> new ObsidianGravelBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).sound(SoundType.GRAVEL).requiresCorrectToolForDrops()));

    //Machines & Machine Casings
    public static final RegistryObject<Block> LV_MACHINE_CASING = BLOCKS.register("lv_machine_casing",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALLOY_BLAST_FURNACE = BLOCKS.register("alloy_blast_furnace",
            () -> new AlloyBlastFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BUILDERS_FURNACE = BLOCKS.register("builders_furnace",
            () -> new BuildersFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FLUID_DEPOSITOR = BLOCKS.register("fluid_depositor",
            () -> new FluidDepositorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> BRICK_CHIMNEY = BLOCKS.register("brick_chimney",
            () -> new ChimneyBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> SCAFFOLD = BLOCKS.register("scaffold",
            () -> new ScaffoldBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> TELESCOPE = BLOCKS.register("telescope",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> STEAM_CAP = BLOCKS.register("steam_cap",
            () -> new SteamCapBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> STEAM_SWITCH_SMALL = BLOCKS.register("steam_switch_small",
            () -> new SteamSwitchSmallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> STEAM_PIPE = BLOCKS.register("steam_pipe",
            () -> new SteamPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> STEAM_OUTLET = BLOCKS.register("steam_outlet",
            () -> new SteamOutletBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> GAS_CENTRIFUGE = BLOCKS.register("gas_centrifuge",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> MULTIBLOCK_EXTENSION = BLOCKS.register("multiblock_extension",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().noOcclusion()));


    //Concrete
    public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete",
            () -> new BlastProofBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 5));
    public static final RegistryObject<Block> CONCRETE_STAIRS = BLOCKS.register("concrete_stairs",
            () -> new BlastProofStairsBlock(CONCRETE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 5));
    public static final RegistryObject<Block> CONCRETE_SLAB = BLOCKS.register("concrete_slab",
            () -> new BlastProofSlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 5));
    public static final RegistryObject<Block> CONCRETE_BRICKS = BLOCKS.register("concrete_bricks",
            () -> new BlastProofBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 7));
    public static final RegistryObject<Block> CONCRETE_BRICKS_STAIRS = BLOCKS.register("concrete_bricks_stairs",
            () -> new BlastProofStairsBlock(CONCRETE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 7));
    public static final RegistryObject<Block> CONCRETE_BRICKS_SLAB = BLOCKS.register("concrete_bricks_slab",
            () -> new BlastProofSlabBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 7));
    public static final RegistryObject<Block> CREATIVE_CONCRETE_BRICKS = BLOCKS.register("creative_concrete_bricks",
            () -> new BlastProofBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).requiresCorrectToolForDrops(), 32));

    public static final RegistryObject<Block> FOUNDATION_CONCRETE = BLOCKS.register("foundation_concrete",
            () -> new BlastProofBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops(), 32));

    //Other strong blocks
    public static final RegistryObject<Block> STEEL_REINFORCED_GLASS = BLOCKS.register("steel_reinforced_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().requiresCorrectToolForDrops()));

    //Nuclear remains
    public static final RegistryObject<Block> NUCLEAR_REMAINS = BLOCKS.register("nuclear_remains",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NUCLEAR_DIAMOND_ORE_REMAINS = BLOCKS.register("nuclear_diamond_ore_remains",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NUCLEAR_EMERALD_ORE_REMAINS = BLOCKS.register("nuclear_emerald_ore_remains",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> HOT_NUCLEAR_REMAINS = BLOCKS.register("hot_nuclear_remains",
            () -> new HotNuclearRemainsBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().randomTicks(),
                    ModBlocks.NUCLEAR_REMAINS.get().defaultBlockState()));
    public static final RegistryObject<Block> HOT_NUCLEAR_DIAMOND_ORE_REMAINS = BLOCKS.register("hot_nuclear_diamond_ore_remains",
            () -> new HotNuclearRemainsBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().randomTicks(),
                    ModBlocks.NUCLEAR_DIAMOND_ORE_REMAINS.get().defaultBlockState()));
    public static final RegistryObject<Block> HOT_NUCLEAR_EMERALD_ORE_REMAINS = BLOCKS.register("hot_nuclear_emerald_ore_remains",
            () -> new HotNuclearRemainsBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().randomTicks(),
                    ModBlocks.NUCLEAR_EMERALD_ORE_REMAINS.get().defaultBlockState()));

    public static final RegistryObject<Block> SMOULDERING_LOG = BLOCKS.register("smouldering_log",
            () -> new SmoulderingLogBlock(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).randomTicks()));
    public static final RegistryObject<Block> CHARRED_LOG = BLOCKS.register("charred_log",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)));

    //Bomb utilities
    public static final RegistryObject<Block> PARTICLE_SPAWNER = BLOCKS.register("test_particle_spawner",
            () -> new ParticleTestBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), true, ParticleTypes.FLAME));
    public static final RegistryObject<Block> DUD = BLOCKS.register("dud",
            () -> new DudBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> INCENDIARY_CHARGE = BLOCKS.register("incendiary_charge",
            () -> new IncendiaryChargeBlock(BombUtils.testFireBombProperties));
    public static final RegistryObject<Block> NUCLEAR_CHARGE = BLOCKS.register("nuclear_charge",
            () -> new NuclearChargeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LITTLE_BOY = BLOCKS.register("lil_bro",
            () -> new LittleBoyBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FAT_MAN = BLOCKS.register("caseoh",
            () -> new FatManBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().requiresCorrectToolForDrops()));



    //Doors & hatches
    public static final RegistryObject<Block> WATER_SEAL_HATCH = BLOCKS.register("water_seal_hatch",
            () -> new HatchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(CustomSetType.CUSTOM_DOOR_1.soundType()).requiresCorrectToolForDrops(), CustomSetType.CUSTOM_DOOR_1));
    public static final RegistryObject<Block> GOLD_LINED_DOOR = BLOCKS.register("gold_lined_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(CustomSetType.CUSTOM_DOOR_1.soundType()).requiresCorrectToolForDrops(), CustomSetType.CUSTOM_DOOR_1));

    //other
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BISMUTH_CRYSTAL_BLOCK = BLOCKS.register("raw_bismuth_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BISMUTH_BLOCK = BLOCKS.register("bismuth_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_URANIUM_BLOCK = BLOCKS.register("raw_uranium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_THORIUM_BLOCK = BLOCKS.register("raw_thorium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> URANIUM_BLOCK = BLOCKS.register("uranium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> THORIUM_BLOCK = BLOCKS.register("thorium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL).requiresCorrectToolForDrops()));


    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Blocks");
        BLOCKS.register(eventBus);
    }
}
