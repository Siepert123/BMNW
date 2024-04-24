package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HatchBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape EAST_OPEN_AABB = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 2.0D, 3.0D, 16.0D),
            Block.box(-1.0D, 3.0D, 0.0D, 1.0D, 6.0D, 16.0D),
            Block.box(-2.0D, 6.0D, 0.0D, 0.0D, 9.0D, 16.0D),
            Block.box(-3.0D, 9.0D, 0.0D, -1.0D, 12.0D, 16.0D),
            Block.box(-4.0D, 12.0D, 0.0D, -2.0D, 15.0D, 16.0D));
    protected static final VoxelShape WEST_OPEN_AABB = Shapes.or(Block.box(14.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(15.0D, 3.0D, 0.0D, 17.0D, 6.0D, 16.0D),
            Block.box(16.0D, 6.0D, 0.0D, 18.0D, 9.0D, 16.0D),
            Block.box(17.0D, 9.0D, 0.0D, 19.0D, 12.0D, 16.0D),
            Block.box(18.0D, 12.0D, 0.0D, 20.0D, 15.0D, 16.0D));
    protected static final VoxelShape SOUTH_OPEN_AABB = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 2.0D),
            Block.box(0.0D, 3.0D, -1.0D, 16.0D, 6.0D, 1.0D),
            Block.box(0.0D, 6.0D, -2.0D, 16.0D, 9.0D, 0.0D),
            Block.box(0.0D, 9.0D, -3.0D, 16.0D, 12.0D, -1.0D),
            Block.box(0.0D, 12.0D, -4.0D, 16.0D, 15.0D, -2.0D));
    protected static final VoxelShape NORTH_OPEN_AABB = Shapes.or(Block.box(0.0D, 0.0D, 14.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, 3.0D, 15.0D, 16.0D, 6.0D, 17.0D),
            Block.box(0.0D, 6.0D, 16.0D, 16.0D, 9.0D, 18.0D),
            Block.box(0.0D, 9.0D, 17.0D, 16.0D, 12.0D, 19.0D),
            Block.box(0.0D, 12.0D, 18.0D, 16.0D, 15.0D, 20.0D));
    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private final BlockSetType type;

    public HatchBlock(BlockBehaviour.Properties pProperties, BlockSetType pBlockType) {
        super(pProperties.sound(pBlockType.soundType()));
        this.type = pBlockType;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.valueOf(false)).setValue(POWERED, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.getValue(OPEN);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pGetter, BlockPos pPos, CollisionContext pContext) {
        if (!pState.getValue(OPEN)) {
            return BOTTOM_AABB;
        } else {
            switch ((Direction)pState.getValue(FACING)) {
                case NORTH:
                default:
                    return NORTH_OPEN_AABB;
                case SOUTH:
                    return SOUTH_OPEN_AABB;
                case WEST:
                    return WEST_OPEN_AABB;
                case EAST:
                    return EAST_OPEN_AABB;
            }
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.getValue(OPEN);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pGetter, BlockPos pPos, PathComputationType pPathType) {
        switch (pPathType) {
            case LAND:
                return pState.getValue(OPEN);
            case WATER:
                return pState.getValue(WATERLOGGED);
            case AIR:
                return pState.getValue(OPEN);
            default:
                return false;
        }
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pResult) {
        if (!this.type.canOpenByHand()) {
            return InteractionResult.PASS;
        } else {
            pState = pState.cycle(OPEN);
            pLevel.setBlock(pPos, pState, 2);
            if (pState.getValue(WATERLOGGED)) {
                pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }

            this.playSound(pPlayer, pLevel, pPos, pState.getValue(OPEN));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

    protected void playSound(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, boolean pBool) {
        pLevel.playSound(pPlayer, pPos, pBool ? this.type.trapdoorOpen() : this.type.trapdoorClose(), SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.1F + 0.9F);
        pLevel.gameEvent(pPlayer, pBool ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pPos1, boolean pBool) {
        if (!pLevel.isClientSide) {
            boolean flag = pLevel.hasNeighborSignal(pPos);
            if (flag != pState.getValue(POWERED)) {
                if (pState.getValue(OPEN) != flag) {
                    pState = pState.setValue(OPEN, Boolean.valueOf(flag));
                    this.playSound(null, pLevel, pPos, flag);
                }

                pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(flag)), 2);
                if (pState.getValue(WATERLOGGED)) {
                    pLevel.scheduleTick(pPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
                }
            }

        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = this.defaultBlockState();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        Direction direction = pContext.getClickedFace();
        if (!pContext.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
            blockstate = blockstate.setValue(FACING, direction);
        } else {
            blockstate = blockstate.setValue(FACING, pContext.getHorizontalDirection().getOpposite());
        }

        if (pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())) {
            blockstate = blockstate.setValue(OPEN, Boolean.valueOf(true)).setValue(POWERED, Boolean.valueOf(true));
        }

        return blockstate.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, OPEN, POWERED, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public BlockState updateShape(BlockState p_57554_, Direction p_57555_, BlockState p_57556_, LevelAccessor p_57557_, BlockPos p_57558_, BlockPos p_57559_) {
        if (p_57554_.getValue(WATERLOGGED)) {
            p_57557_.scheduleTick(p_57558_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57557_));
        }

        return super.updateShape(p_57554_, p_57555_, p_57556_, p_57557_, p_57558_, p_57559_);
    }

    //Forge Start
    @Override
    public boolean isLadder(BlockState state, net.minecraft.world.level.LevelReader world, BlockPos pos, net.minecraft.world.entity.LivingEntity entity) {
        if (state.getValue(OPEN)) {
            BlockPos downPos = pos.below();
            BlockState down = world.getBlockState(downPos);
            return down.getBlock().makesOpenTrapdoorAboveClimbable(down, world, downPos, state);
        }
        return false;
    }
    //Forge End
}
