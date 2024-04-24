package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.enums.TallExplosionDirections;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;
import java.lang.Math;

public class Exploder {
    public static void doTheExplode(@NotNull Level pLevel, double x, double y, double z, float pStrength) {
        pLevel.explode(null, x, y, z, pStrength, false, Level.ExplosionInteraction.BLOCK);
    }
    public static void doTheIncendiaryExplode(@NotNull Level pLevel, double x, double y, double z, float pStrength) {
        pLevel.explode(null, x, y, z, pStrength, true, Level.ExplosionInteraction.BLOCK);
    }

    public static void doTheExplode(@NotNull Level pLevel, BlockPos pPos, float pStrength) {
        pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), pStrength, false, Level.ExplosionInteraction.BLOCK);
    }
    public static void doTheIncendiaryExplode(@NotNull Level pLevel, BlockPos pPos, float pStrength) {
        pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), pStrength, false, Level.ExplosionInteraction.BLOCK);
    }

    public static void doTheTallExplode(@NotNull Level pLevel, double x, double y, double z, float pStrength, int height, int spacing, TallExplosionDirections direction) {
        for (int i = 0; i < height; i++) {
            switch (direction) {
                case UP -> doTheExplode(pLevel, x, y+i*spacing, z, pStrength);
                case DOWN -> doTheExplode(pLevel, x, y-i*spacing, z, pStrength);
                case Y_AXIS -> {doTheExplode(pLevel, x, y+i*spacing, z, pStrength);
                    doTheExplode(pLevel, x, y-i*spacing, z, pStrength);}
                case NORTH -> doTheExplode(pLevel, x, y, z-i*spacing, pStrength);
                case SOUTH -> doTheExplode(pLevel, x, y, z+i*spacing, pStrength);
                case Z_AXIS -> {doTheExplode(pLevel, x, y, z-i*spacing, pStrength);
                    doTheExplode(pLevel, x, y, z+i*spacing, pStrength);}
                case EAST -> doTheExplode(pLevel, x+i*spacing, y, z, pStrength);
                case WEST -> doTheExplode(pLevel, x-i*spacing, y, z, pStrength);
                case X_AXIS -> {doTheExplode(pLevel, x-i*spacing, y, z, pStrength);
                    doTheExplode(pLevel, x+i*spacing, y, z, pStrength);}
                default -> {doTheExplode(pLevel, x+i*spacing, y, z, pStrength);
                    doTheExplode(pLevel, x-i*spacing, y, z, pStrength);
                    doTheExplode(pLevel, x, y+i*spacing, z, pStrength);
                    doTheExplode(pLevel, x, y-i*spacing, z, pStrength);
                    doTheExplode(pLevel, x, y, z+i*spacing, pStrength);
                    doTheExplode(pLevel, x, y, z-i*spacing, pStrength);}
            }
        }
    }
    public static void doTheTallIncendiaryExplode(@NotNull Level pLevel, double x, double y, double z, float pStrength, int height, int spacing, TallExplosionDirections direction) {
        for (int i = 0; i < height; i++) {
            switch (direction) {
                case UP -> doTheIncendiaryExplode(pLevel, x, y+i*spacing, z, pStrength);
                case DOWN -> doTheIncendiaryExplode(pLevel, x, y-i*spacing, z, pStrength);
                case Y_AXIS -> {doTheIncendiaryExplode(pLevel, x, y+i*spacing, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y-i*spacing, z, pStrength);}
                case NORTH -> doTheIncendiaryExplode(pLevel, x, y, z-i*spacing, pStrength);
                case SOUTH -> doTheIncendiaryExplode(pLevel, x, y, z+i*spacing, pStrength);
                case Z_AXIS -> {doTheIncendiaryExplode(pLevel, x, y, z-i*spacing, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y, z+i*spacing, pStrength);}
                case EAST -> doTheIncendiaryExplode(pLevel, x+i*spacing, y, z, pStrength);
                case WEST -> doTheIncendiaryExplode(pLevel, x-i*spacing, y, z, pStrength);
                case X_AXIS -> {doTheIncendiaryExplode(pLevel, x-i*spacing, y, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x+i*spacing, y, z, pStrength);}
                default -> {doTheIncendiaryExplode(pLevel, x+i*spacing, y, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x-i*spacing, y, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y+i*spacing, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y-i*spacing, z, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y, z+i*spacing, pStrength);
                    doTheIncendiaryExplode(pLevel, x, y, z-i*spacing, pStrength);}
            }
        }
    }

    public static void doTheSphereExplode(Level pLevel, double x, double y, double z, float pStrength, int radius, int pOrbMode) {
        if (radius < 0) {
            throw new RuntimeException("Radius can't be less than 1 (smh)");
        }
        double h;
        double dist;
        if (pOrbMode == 1) {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = -radius; k <= radius; k++) {
                        h = Math.sqrt(i * i + k * k);
                        dist = Math.sqrt(h * h + j * j);
                        if (Math.round(dist) == radius) {
                            doTheExplode(pLevel, x + i, y + j, z + k, pStrength);
                        }
                    }
                }
            }
        }
    }
    public static void doTheSphereIncendiaryExplode(Level pLevel, double x, double y, double z, float pStrength, int radius, int pOrbMode) {
        if (radius < 0) {
            throw new RuntimeException("Radius can't be less than 1 (smh)");
        }
        double h;
        double dist;
        if (pOrbMode == 1) {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    for (int k = -radius; k <= radius; k++) {
                        h = Math.sqrt(i * i + k * k);
                        dist = Math.sqrt(h * h + j * j);
                        if (Math.round(dist) == radius) {
                            doTheIncendiaryExplode(pLevel, x + i, y + j, z + k, pStrength);
                        }
                    }
                }
            }
        }
    }

    public static void doTheCircularExplode(Level pLevel, double x, double y, double z, float pStrength, int radius, boolean onSurface) {
        if (radius < 0) {
            throw new RuntimeException("Radius can't be less than 1 (smh)");
        }
        double dist;
        if (onSurface) {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    dist = Math.sqrt(i*i + j*j);
                    if (Math.round(dist) <= radius) {
                        doTheExplode(pLevel, x + i, pLevel.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x + i, (int) z + j), z + j, pStrength);
                    }
                }
            }
        } else {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    dist = Math.sqrt(i*i + j*j);
                    if (Math.round(dist) <= radius) {
                        doTheExplode(pLevel, x + i, y, z + j, pStrength);
                    }
                }
            }
        }
    }

    public static void doTheCircularIncendiaryExplode(Level pLevel, double x, double y, double z, float pStrength, int radius, boolean onSurface) {
        if (radius < 0) {
            throw new RuntimeException("Radius can't be less than 1 (smh)");
        }
        double dist;
        if (onSurface) {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    dist = Math.sqrt(i*i + j*j);
                    if (Math.round(dist) <= radius) {
                        doTheIncendiaryExplode(pLevel, x + i, pLevel.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x + i, (int) z + j), z + j, pStrength);
                    }
                }
            }
        } else {
            for (int i = -radius; i <= radius; i++) {
                for (int j = -radius; j <= radius; j++) {
                    dist = Math.sqrt(i*i + j*j);
                    if (Math.round(dist) <= radius) {
                        doTheIncendiaryExplode(pLevel, x + i, y, z + j, pStrength);
                    }
                }
            }
        }
    }
}
