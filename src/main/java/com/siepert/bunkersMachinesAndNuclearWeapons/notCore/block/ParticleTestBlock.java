package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
public class ParticleTestBlock extends Block {
    boolean onSurface;
    ParticleOptions particle;
    public ParticleTestBlock(Properties pProperties, boolean onSurface, ParticleOptions particle) {
        super(pProperties);
        this.onSurface = onSurface;
        this.particle = particle;
    }


    @Override
    @ParametersAreNonnullByDefault
    public InteractionResult use(@Nullable BlockState pState, Level pLevel, BlockPos pPos, @Nullable Player pPlayer, @Nullable InteractionHand pHand, @Nullable BlockHitResult pResult) {
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 10, true);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.BLOCKS, 1, 1, 512);
        return InteractionResult.SUCCESS;
    }
}
