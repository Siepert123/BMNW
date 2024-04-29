package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock.AllMultipartBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModCreativeTabs {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);

    public static final RegistryObject<CreativeModeTab> BMNW_TAB = CREATIVE_MODE_TABS.register("bmnw_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PLAYSTATION.get()))
                    .title(Component.translatable("itemGroup.bmnw.main"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlockItems.LV_MACHINE_CASING.get());

                        pOutput.accept(ModBlockItems.THORIUM_ORE.get());
                        pOutput.accept(ModBlockItems.URANIUM_ORE.get());
                        pOutput.accept(ModBlockItems.DEEPSLATE_THORIUM_ORE.get());
                        pOutput.accept(ModBlockItems.DEEPSLATE_URANIUM_ORE.get());

                        pOutput.accept(ModBlockItems.RAW_THORIUM_BLOCK.get());
                        pOutput.accept(ModBlockItems.THORIUM_BLOCK.get());
                        pOutput.accept(ModBlockItems.RAW_URANIUM_BLOCK.get());
                        pOutput.accept(ModBlockItems.URANIUM_BLOCK.get());
                        pOutput.accept(ModBlockItems.BISMUTH_CRYSTAL_BLOCK.get());
                        pOutput.accept(ModBlockItems.BISMUTH_BLOCK.get());

                        pOutput.accept(ModItems.RAW_THORIUM.get());
                        pOutput.accept(ModItems.THORIUM_INGOT.get());
                        pOutput.accept(ModItems.THORIUM_232_INGOT.get());
                        pOutput.accept(ModItems.RAW_URANIUM.get());
                        pOutput.accept(ModItems.URANIUM_INGOT.get());
                        pOutput.accept(ModItems.URANIUM_233_INGOT.get());
                        pOutput.accept(ModItems.URANIUM_235_INGOT.get());
                        pOutput.accept(ModItems.URANIUM_238_INGOT.get());
                        pOutput.accept(ModItems.BISMUTH_CRYSTAL.get());
                        pOutput.accept(ModItems.BISMUTH_INGOT.get());

                        pOutput.accept(ModItems.STEEL_NUGGET.get());
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModBlockItems.STEEL_BLOCK.get());
                        pOutput.accept(ModItems.CONDUCTIVE_COPPER_INGOT.get());
                        pOutput.accept(ModItems.IRON_PLATE.get());
                        pOutput.accept(ModItems.STEEL_PLATE.get());
                        pOutput.accept(ModItems.COPPER_WIRE.get());
                        pOutput.accept(ModItems.CONDUCTIVE_COPPER_WIRE.get());

                        pOutput.accept(ModItems.VALVE_HANDLE.get());
                        pOutput.accept(ModItems.INSULATOR.get());
                        pOutput.accept(ModItems.ACTIVE_FUSION_REACTION.get());

                        pOutput.accept(ModBlockItems.OBSIDIAN_GRAVEL.get());

                        pOutput.accept(ModBlockItems.CONCRETE.get());
                        pOutput.accept(ModBlockItems.CONCRETE_STAIRS.get());
                        pOutput.accept(ModBlockItems.CONCRETE_SLAB.get());
                        pOutput.accept(ModBlockItems.CONCRETE_BRICKS.get());
                        pOutput.accept(ModBlockItems.CONCRETE_BRICKS_STAIRS.get());
                        pOutput.accept(ModBlockItems.CONCRETE_BRICKS_SLAB.get());

                        pOutput.accept(ModBlockItems.FOUNDATION_CONCRETE.get());
                        pOutput.accept(ModBlockItems.STEEL_REINFORCED_GLASS.get());
                        pOutput.accept(ModBlockItems.CREATIVE_CONCRETE_BRICKS.get());

                        pOutput.accept(ModBlockItems.WATER_SEAL_HATCH.get());
                        pOutput.accept(ModBlockItems.GOLD_LINED_DOOR.get());

                        pOutput.accept(ModBlockItems.STEAM_CAP.get());
                        pOutput.accept(ModBlockItems.STEAM_SWITCH_SMALL.get());
                        pOutput.accept(ModBlockItems.STEAM_OUTLET.get());
                        pOutput.accept(ModBlockItems.STEAM_PIPE.get());

                        pOutput.accept(ModBlockItems.ALLOY_BLAST_FURNACE.get());
                        pOutput.accept(ModBlockItems.BUILDERS_FURNACE.get());
                        pOutput.accept(AllMultipartBlocks.INDUSTRIAL_HEATER.get());
                        pOutput.accept(AllMultipartBlocks.INDUSTRIAL_FURNACE.get());
                        pOutput.accept(ModBlockItems.FLUID_DEPOSITOR.get());
                        pOutput.accept(ModBlockItems.BRICK_CHIMNEY.get());
                        pOutput.accept(ModBlockItems.SCAFFOLD.get());
                        pOutput.accept(ModBlockItems.GAS_CENTRIFUGE.get());

                        pOutput.accept(ModBlockItems.HOT_NUCLEAR_REMAINS.get());
                        pOutput.accept(ModBlockItems.NUCLEAR_REMAINS.get());
                        pOutput.accept(ModBlockItems.HOT_NUCLEAR_DIAMOND_ORE_REMAINS.get());
                        pOutput.accept(ModBlockItems.NUCLEAR_DIAMOND_ORE_REMAINS.get());
                        pOutput.accept(ModBlockItems.HOT_NUCLEAR_EMERALD_ORE_REMAINS.get());
                        pOutput.accept(ModBlockItems.NUCLEAR_EMERALD_ORE_REMAINS.get());

                        pOutput.accept(ModBlockItems.DUD.get());
                        pOutput.accept(ModBlockItems.INCENDIARY_CHARGE.get());
                        pOutput.accept(ModBlockItems.NUCLEAR_CHARGE.get());
                        pOutput.accept(ModBlockItems.LITTLE_BOY.get());
                        pOutput.accept(ModBlockItems.FAT_MAN.get());

                        pOutput.accept(ModItems.DETONATOR.get());
                        pOutput.accept(ModItems.GEIGER_COUNTER.get());

                        pOutput.accept(ModFoods.VERY_WEAK_RAD_AWAY.get());
                        pOutput.accept(ModFoods.WEAK_RAD_AWAY.get());
                        pOutput.accept(ModFoods.RAD_AWAY.get());
                        pOutput.accept(ModFoods.JAR_OF_PISS.get());

                        pOutput.accept(ModItems.CREATIVE_SOURCE_PLACER.get());
                        pOutput.accept(ModFoods.URANIUM_SANDWICH.get());
                        pOutput.accept(ModFoods.PROEMEVLAAI.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> SILLY_BMNW_TAB = CREATIVE_MODE_TABS.register("silly_bmnw_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlockItems.OBJ_TEST.get()))
                    .title(Component.translatable("itemGroup.bmnw.silly"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlockItems.OBJ_TEST.get());
                        pOutput.accept(ModBlockItems.TELESCOPE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Creative Tabs");
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
