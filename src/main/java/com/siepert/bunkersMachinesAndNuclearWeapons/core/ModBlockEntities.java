package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.*;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock.AllMultipartBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModBlockEntities {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BMNW.MOD_ID);

    public static final RegistryObject<BlockEntityType<IndustrialHeaterBlockEntity>> INDUSTRIAL_HEATER_BE =
            BLOCK_ENTITIES.register("industrial_heater_be", () ->
                    BlockEntityType.Builder.of(IndustrialHeaterBlockEntity::new,
                            AllMultipartBlocks.INDUSTRIAL_HEATER_OUTER.get()).build(null));

    public static final RegistryObject<BlockEntityType<IndustrialFurnaceBlockEntity>> INDUSTRIAL_FURNACE_BE =
            BLOCK_ENTITIES.register("industrial_furnace_be", () ->
                    BlockEntityType.Builder.of(IndustrialFurnaceBlockEntity::new,
                            AllMultipartBlocks.INDUSTRIAL_FURNACE_OUTER.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlloyBlastFurnaceBlockEntity>> ALLOY_BLAST_FURNACE_BE =
            BLOCK_ENTITIES.register("alloy_blast_furnace_be", () ->
                    BlockEntityType.Builder.of(AlloyBlastFurnaceBlockEntity::new,
                            ModBlocks.ALLOY_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteamCapBlockEntity>> STEAM_CAP_BE =
            BLOCK_ENTITIES.register("steam_cap_be", () ->
                    BlockEntityType.Builder.of(SteamCapBlockEntity::new,
                            ModBlocks.STEAM_CAP.get()).build(null));

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Block Entities");
        BLOCK_ENTITIES.register(eventBus);
    }

}
