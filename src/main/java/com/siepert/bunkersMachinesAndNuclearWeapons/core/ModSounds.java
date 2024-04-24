package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModSounds {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BMNW.MOD_ID);

    public static final RegistryObject<SoundEvent> STRONG_EXPLOSION = registerSoundEvents("event.explosion.strong");
    public static final RegistryObject<SoundEvent> VOMIT = registerSoundEvents("event.player.vomit");
    public static final RegistryObject<SoundEvent> INDUSTRIAL_LEVER = registerSoundEvents("usage.machine.industrial_lever");
    public static final RegistryObject<SoundEvent> INDUSTRIAL_LEVER_ABORT = registerSoundEvents("event.machine.industrial_lever_abort");
    public static final RegistryObject<SoundEvent> INDUSTRIAL_SLIDER = registerSoundEvents("usage.machine.industrial_slider");
    public static final RegistryObject<SoundEvent> LEVER_DOESNT_COMPLY = registerSoundEvents("usage.machine.lever_dud");
    public static final RegistryObject<SoundEvent> MACHINE_GROAN = registerSoundEvents("ambient.machine.groan");
    public static final RegistryObject<SoundEvent> GOLD_LINED_DOOR_OPEN = registerSoundEvents("usage.block.gold_lined_door_open");
    public static final RegistryObject<SoundEvent> GOLD_LINED_DOOR_CLOSE = registerSoundEvents("usage.block.gold_lined_door_close");
    public static final RegistryObject<SoundEvent> WATER_SEAL_OPEN = registerSoundEvents("usage.block.water_seal_open");
    public static final RegistryObject<SoundEvent> WATER_SEAL_CLOSE = registerSoundEvents("usage.block.water_seal_close");
    public static final RegistryObject<SoundEvent> GPINS_UP = registerSoundEvents("usage.machine.gpins_up");
    public static final RegistryObject<SoundEvent> GPINS_ROTATE = registerSoundEvents("usage.machine.gpins_rotate");
    public static final RegistryObject<SoundEvent> GPINS_DOWN = registerSoundEvents("usage.machine.gpins_down");
    public static final RegistryObject<SoundEvent> PAGE_FLIP = registerSoundEvents("usage.item.page_turn");
    public static final RegistryObject<SoundEvent> STEAM_LEVER = registerSoundEvents("usage.machine.steam_lever");
    public static final RegistryObject<SoundEvent> MACHINE_MOVE = registerSoundEvents("event.machine.move");
    public static final RegistryObject<SoundEvent> MACHINE_STUCK = registerSoundEvents("event.machine.movement_stuck");
    public static final RegistryObject<SoundEvent> STEAM_OUTLET_LOOP = registerSoundEvents("loop.machine.steam_outlet");
    public static final RegistryObject<SoundEvent> STEAM_LOOP = registerSoundEvents("loop.machine.steam_loop");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BMNW.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Sounds");
        SOUND_EVENTS.register(eventBus);
    }
}
