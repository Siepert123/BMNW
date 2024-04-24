package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class BombProperties {
    private final BlockBehaviour.Properties blockProperties;
    private final float strength;
    private final int radius;
    private final int spacing;
    public BombProperties(BlockBehaviour.Properties blockProperties, float strength, int radius, int spacing) {
        this.blockProperties = blockProperties;
        this.strength = strength;
        this.radius = radius;
        this.spacing = spacing;
    }

    public BlockBehaviour.Properties getBlockProperties() {
        return blockProperties;
    }

    public float getStrength() {
        return strength;
    }

    public int getRadius() {
        return radius;
    }
    public int getSpacing() {
        return spacing;
    }
}
