package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event.libs.PlayerRadiationHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Random random = new Random();

        if (event.side == LogicalSide.SERVER) {
            PlayerRadiationHandler.handleRads(event, random);
        }
    }


}
