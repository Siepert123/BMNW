package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class SmoulderingLogBlock extends Block {
    public SmoulderingLogBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pSource) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        int belo = 0;
        BlockState h;
        for (int i = 0;  true; i++) {
            h = pLevel.getBlockState(pPos.below(i));
            if (h == Blocks.AIR.defaultBlockState()) {
                belo = i;
            } else {
                break;
            }
        }
        pLevel.setBlock(pPos.below(belo), ModBlocks.CHARRED_LOG.get().defaultBlockState(), 3);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pSource) {
        Direction direction;
        int nextInt = pSource.nextInt(6);
        direction = switch (nextInt) {
            case 0 -> Direction.UP;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.NORTH;
            case 3 -> Direction.EAST;
            case 4 -> Direction.SOUTH;
            case 5 -> Direction.WEST;
            default -> throw new IllegalStateException("Unexpected value: " + nextInt);
        };

        if (pSource.nextInt(8) == 0) {
            ParticleUtils.spawnParticleOnFace(pLevel, pPos, direction, ParticleTypes.FLAME, Vec3.ZERO, 0.5);
        }
    }
}
