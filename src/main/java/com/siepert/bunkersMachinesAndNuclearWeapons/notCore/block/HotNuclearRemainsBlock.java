package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class HotNuclearRemainsBlock extends Block {
    BlockState cooledBlock;
    public HotNuclearRemainsBlock(Properties pProperties, BlockState cooledBlock) {
        super(pProperties);
        this.cooledBlock = cooledBlock;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, cooledBlock, 3);
        pLevel.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5,
                SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.1f, 0.5f, 32);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 7;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        pEntity.setRemainingFireTicks(100);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(4) == 0) {
            pLevel.addParticle(ParticleTypes.LAVA, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                    0, 0, 0);
        }
    }
}
