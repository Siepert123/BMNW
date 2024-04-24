package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil;

public class NukePreconfiguration {
    private final int rayStrength;
    private final double degreePerPart;
    private final String nukeName;
    private final int expectedBoundingBox;

    public int getRayStrength() {
        return rayStrength;
    }

    public double getDegreePerPart() {
        return degreePerPart;
    }

    public int getExpectedBoundingBox() {
        return expectedBoundingBox;
    }
    public String getNukeName() {
        return nukeName;
    }

    public NukePreconfiguration(int rayStrength, double degreePerPart, int expectedBoundingBox, String nukeName) {
        this.rayStrength = rayStrength;
        this.degreePerPart = degreePerPart;
        this.expectedBoundingBox = expectedBoundingBox;
        this.nukeName = nukeName;
    }
}
