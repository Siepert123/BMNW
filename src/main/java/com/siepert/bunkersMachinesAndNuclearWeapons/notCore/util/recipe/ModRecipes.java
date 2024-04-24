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
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BMNW.MOD_ID);
    public static final RegistryObject<RecipeSerializer<AlloyBlastFurnaceRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", () -> AlloyBlastFurnaceRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Recipe Serializers");
        SERIALIZERS.register(eventBus);
    }
}
