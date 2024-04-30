package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil.RandomUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SteamOutletBlock extends Block {
    public SteamOutletBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return RandomUtils.MyShapes.STEAM_OUTLET_SHAPE;
    }
}
