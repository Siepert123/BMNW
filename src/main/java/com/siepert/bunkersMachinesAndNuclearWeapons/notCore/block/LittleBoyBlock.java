package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.MyShapes;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil.NukeExplosionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LittleBoyBlock extends ExplosiveBlock {

    private static final VoxelShape SHAPE = Shapes.box(4, 0, -2, 12, 8, 22);
    public LittleBoyBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return MyShapes.LITTLE_BOY_SHAPE;
    }

    @Override
    public void explode(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        pLevel.playSeededSound(null, pPos.getX(), pPos.getY(), pPos.getZ(), ModSounds.STRONG_EXPLOSION.get(), SoundSource.MASTER, 1, 1, 256);
        BombUtils.advancedExplosion(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 64, true);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 256, 64);
        BombUtils.setAreaAflame(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 128, 64);
        BombUtils.createMushroomCloud(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), 10, false);
    }
}
