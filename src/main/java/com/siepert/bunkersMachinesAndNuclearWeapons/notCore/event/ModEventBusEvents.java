package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.BMNW;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event.libs.PlayerRadiationHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.GenericEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BMNW.THE_IDENTIFIER_OF_THIS_COOL_MODIFICATION_OF_THE_BLOCK_GAME_CALLED_MINECRAFT_WHICH_WAS_MADE_IN_2009_AND_IS_STILL_RECEIVING_UPDATES_TO_THIS_DAY)
public class ModEventBusEvents {
    private static final Random random = new Random();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = event.player.level();

        if (event.side == LogicalSide.SERVER) {
            PlayerRadiationHandler.handleRads(event, random);
            CompoundTag playerTag = player.getPersistentData();
            boolean hasThingied = playerTag.getBoolean("thingy");
            if (!hasThingied) {
                playerTag.putBoolean("thingy", true);
                if (player.getName().getString().equals("Kaupenjoe") || player.getName().getString().equals("Dev")) {
                    player.sendSystemMessage(Component.literal("Hey Kaupenjoe! It is recommended you install JEI and Patchouli."));
                    player.sendSystemMessage(Component.literal("I would also recommend scrolling down in the Search tab!"));
                    player.sendSystemMessage(Component.literal("And last but certainly not least, run the following command:"));
                    player.sendSystemMessage(Component.literal("/give @s bmnw:playstation")
                            .withStyle((p_214489_) -> p_214489_.withColor(ChatFormatting.GREEN)
                                    .withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/give @s bmnw:playstation"))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("text.bmnw.click_to_cmd")))));
                }
            }
        }
    }
}
