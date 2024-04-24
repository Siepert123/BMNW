package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlastProofStairsBlock extends StairBlock {
    public int protectionValue;

    public BlastProofStairsBlock(BlockState pBaseState, Properties pProperties, int protectionValue) {
        super(pBaseState, pProperties);
        this.protectionValue = protectionValue;
    }

    public int getProtectionValue() {
        return protectionValue;
    }
}
