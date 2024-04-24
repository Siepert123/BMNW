package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.event.libs;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModParticles;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.MathUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.TickEvent;

import java.util.Random;

public class PlayerRadiationHandler {
    public static void vomit(TickEvent.PlayerTickEvent event, ServerLevel serverLevel, Random random) {
        event.player.level().playSeededSound(null,
                event.player.getX(), event.player.getY(), event.player.getZ(),
                ModSounds.VOMIT.get(), SoundSource.PLAYERS, 1f, 1f, 16);
        double xExtend = MathUtils.getRelative("x", event.player.getYHeadRot(), event.player.getXRot(), 0.5f);
        double yExtend = MathUtils.getRelative("y", event.player.getYHeadRot(), event.player.getXRot(), 0.5f);
        double zExtend = MathUtils.getRelative("z", event.player.getYHeadRot(), event.player.getXRot(), 0.5f);

        serverLevel.sendParticles(ModParticles.VOMIT_PARTICLES.get(),
                event.player.getX(), event.player.getEyeY(), event.player.getZ(),
                32, xExtend, yExtend, zExtend, 1);
    }

    public static void handleRads(TickEvent.PlayerTickEvent event, Random random) {
        ServerLevel serverLevel = (ServerLevel) event.player.level();
        CompoundTag playerData = event.player.getPersistentData();
        String playerName = event.player.getName().getString();

        byte action = switch (playerName) {
            case "Skyphoenix9" -> 1;
            case "Yanosiq" -> 2;
            case "Siepert" -> 3;
            case "YourLocalSoup" -> 4;
            case "Kaupenjoe" -> 5;
            default -> 0;
        };

        int playerMicroSieverts = playerData.getInt("microsieverts");
        int playerVomitCooldown = playerData.getInt("vomit_cooldown");

        playerData.putInt("vomit_cooldown", Math.max(0, playerVomitCooldown - 1));

        playerVomitCooldown = playerData.getInt("vomit_cooldown");

        if (MathUtils.toNormal(playerMicroSieverts, MathUtils.MultiFactor.MICRO) >= 1) {
            if (random.nextInt(500) == 0) {
                event.player.hurt(event.player.level().damageSources().magic(), 2.5f);

                if (playerVomitCooldown <= 0 && !event.player.isCreative() && !event.player.isSpectator()) {
                    vomit(event, serverLevel, random);
                    if (action == 5) {
                        event.player.sendSystemMessage(Component.translatable("text.bmnw.im_sorry_for_the_pain")
                                .append(", " + event.player.getName().getString()));
                    }
                    event.player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20, 20));

                    playerData.putInt("vomit_cooldown", 1000);
                }
            }
        }
        if (MathUtils.toNormal(playerMicroSieverts, MathUtils.MultiFactor.MICRO) >= 4) {
            if (random.nextInt(20) == 0) {
                event.player.hurt(event.player.level().damageSources().magic(), 1.0f);
            }
        }
        if (MathUtils.toNormal(playerMicroSieverts, MathUtils.MultiFactor.MICRO) >= 5) {
            event.player.hurt(event.player.level().damageSources().magic(), 5.0f);
        }

        if (!event.player.isCreative() && !event.player.isSpectator()) {
            playerData.putInt("microsieverts", Math.max(0, playerMicroSieverts - 1));
        } else {
            playerData.putInt("microsieverts", 0);
        }
    }
}
