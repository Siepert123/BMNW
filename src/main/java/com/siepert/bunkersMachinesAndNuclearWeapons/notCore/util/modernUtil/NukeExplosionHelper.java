package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

public class NukeExplosionHelper {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int NUCLEAR_SEED = "methamphetamine".hashCode();
    public static final NukePreconfiguration LITTLE_BOY_CONFIGURATION =
            new NukePreconfiguration(256, 0.5, 1281, "Little Boy");

    public static void nuclearExplosion(Level pLevel, BlockPos pPos, NukePreconfiguration preconfiguration) {
        nuclearExplosion(pLevel, pPos, preconfiguration.getRayStrength(), preconfiguration.getDegreePerPart(), preconfiguration.getExpectedBoundingBox());
    }

    private static boolean[][][] setRelativeInArray(int arraySize, int xOffset, int yOffset, int zOffset, boolean[][][] array, boolean set) {
        //Sets coordinates in array from middle point.
        int newX = arraySize/2 + xOffset;
        int newY = arraySize/2 + yOffset;
        int newZ = arraySize/2 + zOffset;
        boolean[][][] newArray = array.clone();
        newArray[newX][newY][newZ] = set;
        return newArray;
    }

    private static boolean comparePositionArrays(int[] array1, int[] array2) {
        if (array1[0] != array2[0]) return false;
        if (array1[1] != array2[1]) return false;
        return array1[2] == array2[2];
    }

    private static BlockPos posArrayToClass(int[] posArray) {
        return new BlockPos(posArray[0], posArray[1], posArray[2]);
    }

    public static void nuclearExplosion(Level pLevel, BlockPos pPos, float pRayStrength, double pDegreePerPart, int pExpectedBoundingBox) {
        if (pExpectedBoundingBox % 2 == 0) {
            LOGGER.warn("Bounding box isn't uneven, might cause bugs!");
        }
        float rayDistanceHelper = 0.5f;
        float rayDistanceNow;
        float rayStrengthNow;
        BlockPos currentBlockPos;
        BlockPos lastBlockPos;
        int[] currentPos = new int[3];
        int[] lastPos = new int[3];
        boolean[][][] boundingBox = new boolean[pExpectedBoundingBox][pExpectedBoundingBox][pExpectedBoundingBox];
        for (double yaw = 0; yaw < 360; yaw += pDegreePerPart) {
            for (double pitch = 0; pitch < 180; pitch += pDegreePerPart) {
                rayDistanceNow = 0;
                rayStrengthNow = pRayStrength;
                while (rayStrengthNow > 0 && rayDistanceNow > pRayStrength) {
                    currentPos[0] = (int) Math.round(MathUtils.getRelative("x", yaw, pitch, rayDistanceNow));
                    currentPos[1] = (int) Math.round(MathUtils.getRelative("y", yaw, pitch, rayDistanceNow));
                    currentPos[2] = (int) Math.round(MathUtils.getRelative("z", yaw, pitch, rayDistanceNow));
                    currentBlockPos = posArrayToClass(currentPos);
                    if (!comparePositionArrays(currentPos, lastPos)) {
                        rayStrengthNow -= pLevel.getBlockState(currentBlockPos).getBlock().getExplosionResistance();
                        if (rayStrengthNow <= 0) break;
                        boundingBox = setRelativeInArray(pExpectedBoundingBox, currentPos[0], currentPos[1], currentPos[2], boundingBox, true);
                    }
                    rayDistanceNow += rayDistanceHelper;
                }
            }
        }
        for (int x = 0; x < pExpectedBoundingBox; x++) {
            for (int y = 0; y < pExpectedBoundingBox; y++) {
                for (int z = 0; z < pExpectedBoundingBox; z++) {
                    if (boundingBox[x][y][z]) {
                        pLevel.setBlock(pPos.offset(x - pExpectedBoundingBox/2, y - pExpectedBoundingBox/2, z - pExpectedBoundingBox/2), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
