package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class SteamPipeBlock extends Block {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty HAS_SUPPORT = BooleanProperty.create("has_support");
    public SteamPipeBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Shapes.or(Block.box(6, 6, 6, 10, 10, 10));
        if (pState.getValue(UP)) shape = Shapes.or(shape, Block.box(6, 10, 6, 10, 16, 10));
        if (pState.getValue(DOWN)) shape = Shapes.or(shape, Block.box(6, 0, 6, 10, 10, 10));
        if (pState.getValue(NORTH)) shape = Shapes.or(shape, Block.box(6, 6, 0, 10, 10, 10));
        if (pState.getValue(EAST)) shape = Shapes.or(shape, Block.box(6, 6, 6, 16, 10, 10));
        if (pState.getValue(SOUTH)) shape = Shapes.or(shape, Block.box(6, 6, 6, 10, 10, 16));
        if (pState.getValue(WEST)) shape = Shapes.or(shape, Block.box(0, 6, 6, 10, 10, 10));
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(UP);
        pBuilder.add(DOWN);
        pBuilder.add(NORTH);
        pBuilder.add(EAST);
        pBuilder.add(SOUTH);
        pBuilder.add(WEST);
        pBuilder.add(HAS_SUPPORT);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean up, down, north, east, south, west;
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos().offset(pContext.getClickedFace().getNormal());
        up = validConnection(level, pos, Direction.UP);
        down = validConnection(level, pos, Direction.DOWN);
        north = validConnection(level, pos, Direction.NORTH);
        east = validConnection(level, pos, Direction.EAST);
        south = validConnection(level, pos, Direction.SOUTH);
        west = validConnection(level, pos, Direction.WEST);
        return this.defaultBlockState().setValue(UP, up).setValue(DOWN, down)
                .setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west)
                .setValue(HAS_SUPPORT, false);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        boolean up, down, north, east, south, west, sup;
        up = validConnection((Level) pLevel, pPos, Direction.UP);
        down = validConnection((Level) pLevel, pPos, Direction.DOWN);
        north = validConnection((Level) pLevel, pPos, Direction.NORTH);
        east = validConnection((Level) pLevel, pPos, Direction.EAST);
        south = validConnection((Level) pLevel, pPos, Direction.SOUTH);
        west = validConnection((Level) pLevel, pPos, Direction.WEST);
        sup = pLevel.getBlockState(pPos.below()).isFaceSturdy(pLevel, pPos.below(), Direction.UP, SupportType.CENTER);
        return this.defaultBlockState().setValue(UP, up).setValue(DOWN, down).setValue(NORTH, north).
                setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west).setValue(HAS_SUPPORT, sup);
    }

    private boolean validConnection(Level level, BlockPos pos, Direction direction) {
        if (level.getBlockState(pos.relative(direction)).getBlock() == ModBlocks.STEAM_PIPE.get()) {
            return true;
        }
        if (level.getBlockState(pos.relative(direction)).getBlock() == ModBlocks.STEAM_SWITCH_SMALL.get()) {
            if (direction != Direction.UP && direction != Direction.DOWN) {
                return level.getBlockState(pos.relative(direction)).getValue(HORIZONTAL_FACING).getCounterClockWise() == direction;
            }
        }
        if (direction == Direction.DOWN && level.getBlockState(pos.relative(direction)).getBlock() == ModBlocks.STEAM_CAP.get()) return true;
        return false;
    }


    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }
}
