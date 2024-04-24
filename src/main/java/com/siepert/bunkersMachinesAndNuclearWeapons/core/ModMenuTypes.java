package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.AlloyBlastFurnaceMenu;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.GasCentrifugeMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModMenuTypes {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, BMNW.MOD_ID);
    public static final RegistryObject<MenuType<AlloyBlastFurnaceMenu>> ALLOY_BLAST_FURNACE_MENU =
            registerMenuType(AlloyBlastFurnaceMenu::new, "alloy_blast_furnace_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Menus");
        MENUS.register(eventBus);
    }
}
