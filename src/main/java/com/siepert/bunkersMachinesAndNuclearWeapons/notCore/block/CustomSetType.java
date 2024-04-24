package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import static net.minecraft.world.level.block.state.properties.BlockSetType.register;

public record CustomSetType(String name, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
    public static final BlockSetType CUSTOM_DOOR_1 =
            register(new BlockSetType("custom_door_1", true, SoundType.STONE, ModSounds.GOLD_LINED_DOOR_CLOSE.get(), ModSounds.GOLD_LINED_DOOR_OPEN.get(), ModSounds.WATER_SEAL_CLOSE.get(), ModSounds.WATER_SEAL_OPEN.get(), SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
}
