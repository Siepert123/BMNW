package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event.libs.PlayerRadiationHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BMNW.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Random random = new Random();

        if (event.side == LogicalSide.SERVER) {
            PlayerRadiationHandler.handleRads(event, random);
        }
    }
}
