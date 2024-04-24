package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util;

import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MathUtils {
    public enum MultiFactor {
        KILO,
        NORMAL,
        MILLI,
        MICRO
    }


    public static double toRadians(double degrees) {
        double pi = 3.14159265359;
        return degrees * pi / 180;
    }
    public static double toDegrees(double radians) {
        return radians * 57.2957795f;
    }

    public static double getRelative(String axis, double yaw, double pitch, float radius) {
        double x;
        double y;
        double z;
        x = Math.sin(toRadians(yaw)) * radius * Math.sin(toRadians(pitch));
        z = Math.cos(toRadians(yaw)) * radius * Math.sin(toRadians(pitch));
        y = Math.cos(toRadians(pitch)) * radius;
        return switch (axis) {
            case "x" -> x;
            case "z" -> z;
            case "y" -> y;
            default -> 0;
        };
    }
    public static float toKilo(float thing, MultiFactor factor) {
        return switch (factor) {
            case KILO -> thing;
            case NORMAL -> thing / 1000;
            case MILLI -> thing / 1000000;
            case MICRO -> thing / 1000000000;
        };
    }
    public static float toNormal(float thing, MultiFactor factor) {
        return switch (factor) {
            case KILO -> thing * 1000;
            case NORMAL -> thing;
            case MILLI -> thing / 1000;
            case MICRO -> thing / 1000000;
        };
    }
    public static float toMilli(float thing, MultiFactor factor) {
        return switch (factor) {
            case KILO -> thing * 1000000;
            case NORMAL -> thing * 1000;
            case MILLI -> thing;
            case MICRO -> thing / 1000;
        };
    }
    public static float toMicro(float thing, MultiFactor factor) {
        return switch (factor) {
            case KILO -> thing * 1000000000;
            case NORMAL -> thing * 1000000;
            case MILLI -> thing * 1000;
            case MICRO -> thing;
        };
    }
}
