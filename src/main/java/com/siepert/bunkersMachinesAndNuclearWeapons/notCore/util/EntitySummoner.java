package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;

public class EntitySummoner {

    public static void summonEntity(Level level, double x, double y, double z, EntityType<?> entityType) {

        // Check if the blockEntity type is valid
        if (entityType != null) {
            Entity entity = entityType.create(level);
            if (entity != null) {
                entity.setPos(x, y, z);
                entity.setNoGravity(true);
                level.addFreshEntity(entity);
            }
        }
    }
    public static void summonEntity(Level level, double x, double y, double z, EntityType<?> entityType, Component name, boolean nameVisible) {

        // Check if the blockEntity type is valid
        if (entityType != null) {
            Entity entity = entityType.create(level);
            if (entity != null) {
                entity.setPos(x, y, z);
                entity.setCustomName(name);
                entity.setCustomNameVisible(nameVisible);
                level.addFreshEntity(entity);
            }
        }
    }

    public static void spawnItem(Level level, double x, double y, double z) {
        Entity item = EntityType.ITEM.create(level);

    }
}