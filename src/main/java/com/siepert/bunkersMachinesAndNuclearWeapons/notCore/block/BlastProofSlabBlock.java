package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.world.level.block.SlabBlock;

public class BlastProofSlabBlock extends SlabBlock {
    public int protectionValue;
    public BlastProofSlabBlock(Properties pProperties, int pProtection) {
        super(pProperties);
        this.protectionValue = pProtection;
    }
    public int getProtectionValue() {
        return protectionValue;
    }
}
