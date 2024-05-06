package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.bomb;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class LittleBoyBlockEntity extends BlockEntity {
    boolean isExploding;
    int explosionCurrent;
    final int explosionCurrentMax = 64;

    public LittleBoyBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.LITTLE_BOY_BE.get(), pPos, pState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("explosionCurrent", explosionCurrent);
        pTag.putBoolean("isExploding", isExploding);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        isExploding = nbt.getBoolean("isExploding");
        explosionCurrent = nbt.getInt("explosionCurrent");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        assert this.level != null;
        Random random = new Random();
        if (pLevel.getBlockState(pPos.above()).is(Blocks.VOID_AIR)) isExploding = true;
        if (pLevel.isClientSide()) clientLevelActions(pLevel, pPos, pState, random);
        if (!pLevel.isClientSide()) serverLevelActions(pLevel, pPos, pState, random);
    }

    private void clientLevelActions(Level pLevel, BlockPos pPos, BlockState pState, Random pRandom) {
    }

    private void serverLevelActions(Level pLevel, BlockPos pPos, BlockState pState, Random pRandom) {
        if (isExploding) {
            if (explosionCurrent > explosionCurrentMax) {
                RandomUtils.NewBombHelper.ExplodeFinalizeNuke(pLevel, pPos, explosionCurrentMax);
                BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 256, 64);
                BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 128, 64);
                isExploding = false;
                pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
            } else {
                RandomUtils.NewBombHelper.ExplodeAtStage(pLevel, pPos, explosionCurrentMax, explosionCurrent);
                explosionCurrent++;
            }
        }
    }

    public void initExplosion(Level pLevel, BlockPos pPos) {
        isExploding = true;
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.BLOCKS, 1, 1, 64);
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 10, false);
        pLevel.setBlock(pPos.above(), Blocks.VOID_AIR.defaultBlockState(), 3);
    }
}

