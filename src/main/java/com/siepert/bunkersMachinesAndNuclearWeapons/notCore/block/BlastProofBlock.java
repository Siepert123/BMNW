package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.world.level.block.Block;

public class BlastProofBlock extends Block {
    public int protectionValue;
    public BlastProofBlock(Properties pProperties, int pProtection) {
        super(pProperties);
        this.protectionValue = pProtection;
    }

    public int getProtectionValue() {
        return protectionValue;
    }
}
