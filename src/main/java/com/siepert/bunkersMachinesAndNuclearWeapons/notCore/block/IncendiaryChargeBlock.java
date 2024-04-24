package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombProperties;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.Exploder;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class IncendiaryChargeBlock extends ExplosiveBlock {
    BombProperties bombProperties;
    public IncendiaryChargeBlock(@NotNull BombProperties bombProperties) {
        super(bombProperties.getBlockProperties());
        this.bombProperties = bombProperties;
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        BombUtils.advancedExplosion(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 4, false);
        BombUtils.createFireBall(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 1);
        Exploder.doTheExplode(pLevel, pPos, 4);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 2, 1, 16);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), bombProperties.getRadius(), bombProperties.getSpacing());
    }
}
