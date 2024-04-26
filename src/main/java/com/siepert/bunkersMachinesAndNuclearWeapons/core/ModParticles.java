package com.siepert.bunkersMachinesAndNuclearWeapons.core;

import com.mojang.logging.LogUtils;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class ModParticles {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY);

    public static final RegistryObject<SimpleParticleType> SMOKE_PARTICLES =
            PARTICLE_TYPES.register("smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> HUGE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("huge_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GIANT_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("giant_smoke_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> FIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("fire_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MEDIUM_FIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("medium_fire_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> HUGE_FIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("huge_fire_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GIANT_FIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("giant_fire_smoke_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> SOULFIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("soulfire_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> HUGE_SOULFIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("huge_soulfire_smoke_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GIANT_SOULFIRE_SMOKE_PARTICLES =
            PARTICLE_TYPES.register("giant_soulfire_smoke_particles", () -> new SimpleParticleType(true));


    public static final RegistryObject<SimpleParticleType> VOMIT_PARTICLES =
            PARTICLE_TYPES.register("vomit_particles", () -> new SimpleParticleType(true));


    public static void register(IEventBus eventBus) {
        LOGGER.info("Registering BMNW Particles");
        PARTICLE_TYPES.register(eventBus);
    }
}
