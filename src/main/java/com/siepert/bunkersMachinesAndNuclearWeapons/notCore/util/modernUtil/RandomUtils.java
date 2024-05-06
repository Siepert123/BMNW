package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils.noReinforcersAbove;


public class RandomUtils {
    public static class MyShapes {
        public static final VoxelShape CHIMNEY_COLLISION_SHAPE = Shapes.or(Block.box(0, 0, 0, 16, 16, 1),
                Block.box(0, 0, 0, 1, 16, 16),
                Block.box(0, 0, 15, 16, 16, 16),
                Block.box(15, 0, 0, 16, 16, 16));

        public static final VoxelShape LITTLE_BOY_SHAPE = Block.box(4, 0, -2, 12, 8, 21);
        public static final VoxelShape FAT_MAN_SHAPE = Block.box(-2, 0, -2, 18, 20, 18);

        public static final VoxelShape STEAM_CAP_SHAPE = Shapes.or(Block.box(6, 0, 6, 10, 16, 10),
                Block.box(0, 0, 0, 16, 1, 16),
                Block.box(2, 1, 2, 14, 8, 14));
        public static final VoxelShape STEAM_OUTLET_SHAPE = Block.box(6, 0, 6, 10, 20, 10);

        public static final VoxelShape SCAFFOLD_X_SHAPE = Block.box(2, 0, 0, 14, 16, 16);
        public static final VoxelShape SCAFFOLD_Z_SHAPE = Block.box(0, 0, 2, 16, 16, 14);
    }

    public static class NewBombHelper {
        public static void ExplodeAtStage(Level pLevel, BlockPos pPos, int radius, int stage) {
            int x = pPos.getX();
            int y = pPos.getY();
            int z = pPos.getZ();
            boolean bool;
            double h;
            double dist;
            BlockPos pos;
            Block air = Blocks.AIR;
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = radius; k >= -radius; k--) {
                        pos = new BlockPos(x + i, y + k, z + j);
                        if (!(x + i == x && y + k == y && z + j == z)) {
                            if (pLevel.getBlockState(pos).getBlock() != Blocks.AIR && pLevel.getBlockState(pos).getBlock() != Blocks.BEDROCK) {
                                h = Math.sqrt(i * i + j * j);
                                dist = Math.sqrt(h * h + (k * k * 2));
                                bool = stage - 1 < dist && dist <= stage;
                                if (bool && noReinforcersAbove(pLevel, pos)) {
                                    pLevel.setBlock(pos, air.defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }

        public static void ExplodeFinalizeNuke(Level pLevel, BlockPos pPos, int radius) {
            int x = pPos.getX();
            int y = pPos.getY();
            int z = pPos.getZ();
            boolean bool;
            double h;
            double dist;
            BlockPos pos;
            Block air = Blocks.AIR;
            radius += 3;
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = radius; k >= -radius; k--) {
                        pos = new BlockPos(x + i, y + k, z + j);
                        if (!(x + i == x && y + k == y && z + j == z)) {
                            if (pLevel.getBlockState(pos).getBlock() != Blocks.AIR && pLevel.getBlockState(pos).getBlock() != Blocks.BEDROCK) {
                                h = Math.sqrt(i * i + j * j);
                                dist = Math.sqrt(h * h + (k * k * 2));
                                if (dist > radius - 5 && noReinforcersAbove(pLevel, pos)) {
                                    pLevel.setBlock(pos, ModBlocks.HOT_NUCLEAR_REMAINS.get().defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
