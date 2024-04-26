package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.client.AllModelLayers;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity.client.LittleBoyModel;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModParticles;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SMOKE_PARTICLES.get(),
                SmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.HUGE_SMOKE_PARTICLES.get(),
                HugeSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.GIANT_SMOKE_PARTICLES.get(),
                GiantSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.FIRE_SMOKE_PARTICLES.get(),
                FireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.MEDIUM_FIRE_SMOKE_PARTICLES.get(),
                MediumFireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.HUGE_FIRE_SMOKE_PARTICLES.get(),
                HugeFireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.GIANT_FIRE_SMOKE_PARTICLES.get(),
                GiantFireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.SOULFIRE_SMOKE_PARTICLES.get(),
                SoulfireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.HUGE_SOULFIRE_SMOKE_PARTICLES.get(),
                HugeSoulfireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.GIANT_SOULFIRE_SMOKE_PARTICLES.get(),
                GiantSoulfireSmokeParticles.Provider::new);
        event.registerSpriteSet(ModParticles.VOMIT_PARTICLES.get(),
                VomitParticles.Provider::new);
    }



    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AllModelLayers.LITTLE_BOY_LAYER, LittleBoyModel::createBodyLayer);
    }
}
