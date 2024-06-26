package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock.AllMultipartBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock.DisplayBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.ModEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.client.LittleBoyRenderer;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.AlloyBlastFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.BuildersFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.DeepslateBuildersFurnaceScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.screen.GasCentrifugeScreen;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.ModTags;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.ModRecipes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY)
public class BMNW
{
    private static final boolean ADD_TO_VANILLA_TABS = false;
    // Define mod id in a common place for everything to reference
    public static final String THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY = "bmnw";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BMNW() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModFoods.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockItems.register(modEventBus);
        DisplayBlocks.register(modEventBus);
        AllMultipartBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModParticles.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModTags.init();

        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SEARCH) {
            event.accept(ModBlockItems.BEDROCK_STEAM_HOTSPOT.get());
            event.accept(ModBlockItems.STONE_STEAM_HOTSPOT.get());
            event.accept(ModBlockItems.BEDROCK_STEAM_HOTSPOT.get());
            event.accept(ModBlockItems.TELESCOPE.get());
        }
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.AIRBORNE_LITTLE_BOY.get(), LittleBoyRenderer::new);
            MenuScreens.register(ModMenuTypes.ALLOY_BLAST_FURNACE_MENU.get(), AlloyBlastFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.BUILDERS_FURNACE_MENU.get(), BuildersFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.DEEPSLATE_BUILDERS_FURNACE_MENU.get(), DeepslateBuildersFurnaceScreen::new);
        }
    }
}
