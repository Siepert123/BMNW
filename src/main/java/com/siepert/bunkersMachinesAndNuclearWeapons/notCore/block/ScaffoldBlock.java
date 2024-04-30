package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.modernUtil.RandomUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class ScaffoldBlock extends Block {
    public ScaffoldBlock(Properties pProperties) {
        super(pProperties);
    }
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    @Override
    public RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AXIS, pContext.getHorizontalDirection().getAxis());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(AXIS) == Direction.Axis.X) return RandomUtils.MyShapes.SCAFFOLD_X_SHAPE;
        if (pState.getValue(AXIS) == Direction.Axis.Z) return RandomUtils.MyShapes.SCAFFOLD_Z_SHAPE;

        throw new IllegalStateException("How is the axis wrong?");
    }
}
