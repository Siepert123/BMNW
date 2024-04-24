package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class NuclearChargeBlock extends ExplosiveBlock {
    public NuclearChargeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.BLOCKS, 1, 1, 64);
        BombUtils.advancedExplosion(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 16, true);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 64, 32);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 32, 32);
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 2.5f, false);
    }
}
