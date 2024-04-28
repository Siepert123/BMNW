package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);
    public static final RegistryObject<RecipeSerializer<AlloyBlastFurnaceRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", () -> AlloyBlastFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<BuildersFurnaceRecipe>> BUILDERS_SMELTING_SERIALIZER =
            SERIALIZERS.register("builders_smelting", () -> BuildersFurnaceRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Recipe Serializers");
        SERIALIZERS.register(eventBus);
    }
}
