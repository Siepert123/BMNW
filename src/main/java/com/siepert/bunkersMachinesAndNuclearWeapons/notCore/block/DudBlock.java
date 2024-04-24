package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil.NukeExplosionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class DudBlock extends ExplosiveBlock {
    public DudBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.MASTER, 1, 1, 256);
        BombUtils.advancedExplosion(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 32, false);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 128, 8);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 64, 4);
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 10, true);
    }
}
