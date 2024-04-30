package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MyShapes {
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
