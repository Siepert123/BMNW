package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FatManBlock extends ExplosiveBlock {
    public FatManBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return box(-2, 0, -2, 18, 20, 18);
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.MASTER, 3, 1, 1024);
        BombUtils.advancedExplosion(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 256, true);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 512, 64);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 256, 32);
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 40, false);
    }
}
