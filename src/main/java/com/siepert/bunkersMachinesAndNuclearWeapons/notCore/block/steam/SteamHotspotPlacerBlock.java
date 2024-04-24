package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Random;

public class SteamHotspotPlacerBlock extends Block {
    public SteamHotspotPlacerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
        pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
        pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        Random random = new Random();
        BlockPos genPos = new BlockPos(pPos.getX(), pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pPos.getX(), pPos.getZ()), pPos.getZ());
        pLevel.setBlock(genPos.atY(-64), ModBlocks.BEDROCK_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        for (int i = -63; i < genPos.getY(); i++) {
            pLevel.setBlock(genPos.atY(i), ModBlocks.STONE_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        }
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if (random.nextInt(3) > 0 && (i != 0 || j != 0)) {
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                            pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 1, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                                    pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 2, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                }
            }
        }
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        Random random = new Random();
        BlockPos genPos = new BlockPos(pPos.getX(), pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pPos.getX(), pPos.getZ()), pPos.getZ());
        pLevel.setBlock(genPos.atY(-64), ModBlocks.BEDROCK_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        for (int i = -63; i < genPos.getY(); i++) {
            pLevel.setBlock(genPos.atY(i), ModBlocks.STONE_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        }
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if (random.nextInt(3) > 0 && i != 0 && j != 0) {
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                                    pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 1, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                                    pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 2, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                }
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        Random random = new Random();
        BlockPos genPos = new BlockPos(pPos.getX(), pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pPos.getX(), pPos.getZ()), pPos.getZ());
        pLevel.setBlock(genPos.atY(-64), ModBlocks.BEDROCK_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        for (int i = -63; i < genPos.getY(); i++) {
            pLevel.setBlock(genPos.atY(i), ModBlocks.STONE_STEAM_HOTSPOT.get().defaultBlockState(), 3);
        }
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if (random.nextInt(3) > 0 && i != 0 && j != 0) {
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                                    pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 1, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                    pLevel.setBlock(new BlockPos(genPos.getX() + i,
                                    pLevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, genPos.getX() + i, genPos.getZ() + j) - 2, genPos.getZ() + j),
                            Blocks.STONE.defaultBlockState(), 3);
                }
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }
}
