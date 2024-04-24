package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ObsidianGravelBlock extends FallingBlock {
    public ObsidianGravelBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getDustColor(BlockState pState, BlockGetter pGetter, BlockPos pPos) {
        return 3342387;
    }
}
