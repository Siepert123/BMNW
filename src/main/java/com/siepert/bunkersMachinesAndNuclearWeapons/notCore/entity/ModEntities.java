package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModEntities {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);


    public static final RegistryObject<EntityType<LittleBoyEntity>> AIRBORNE_LITTLE_BOY =
            ENTITY_TYPES.register("little_boy", () -> EntityType.Builder.of(LittleBoyEntity::new, MobCategory.MISC)
                    .sized(1.2f, 1.2f).build("little_boy"));

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Entities");
        ENTITY_TYPES.register(eventBus);
    }
}
