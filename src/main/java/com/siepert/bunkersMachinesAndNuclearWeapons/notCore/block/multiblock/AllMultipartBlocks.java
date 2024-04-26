package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class AllMultipartBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);
    public static final RegistryObject<Block> INDUSTRIAL_HEATER = registerBlock("industrial_heater",
            () -> new IndustrialHeaterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> INDUSTRIAL_HEATER_CORE = registerBlock("industrial_heater_core",
            () -> new IndustrialHeaterCoreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final RegistryObject<Block> INDUSTRIAL_HEATER_OUTER = registerBlock("industrial_heater_outer",
            () -> new IndustrialHeaterOuterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final RegistryObject<Block> INDUSTRIAL_FURNACE = registerBlock("industrial_furnace",
            () -> new IndustrialFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> INDUSTRIAL_FURNACE_CORE = registerBlock("industrial_furnace_core",
            () -> new IndustrialFurnaceCoreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final RegistryObject<Block> INDUSTRIAL_FURNACE_OUTER = registerBlock("industrial_furnace_outer",
            () -> new IndustrialFurnaceOuterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().pushReaction(PushReaction.BLOCK)));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Multipart Blocks");
        BLOCKS.register(eventBus);
    }
}
