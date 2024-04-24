package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.AlloyBlastFurnaceBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class SteamSwitchSmallBlock extends Block {
    public SteamSwitchSmallBlock(Properties pProperties) {
        super(pProperties);
    }
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    @Override
    public RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape begin = Block.box(6, 0, 6, 10, 16, 10);
        VoxelShape result = Shapes.block();
        if (pState.getValue(FACING) == Direction.WEST) result = Shapes.or(begin, Block.box(6, 6, 0, 10, 10, 10));
        if (pState.getValue(FACING) == Direction.NORTH) result = Shapes.or(begin, Block.box(6, 6, 6, 16, 10, 10));
        if (pState.getValue(FACING) == Direction.EAST) result = Shapes.or(begin, Block.box(6, 6, 6, 10, 10, 16));
        if (pState.getValue(FACING) == Direction.SOUTH) result = Shapes.or(begin, Block.box(0, 6, 6, 10, 10, 10));
        return result;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(ACTIVE);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(ACTIVE, false);
    }

    @Override
    public InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos,
                                          @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            pState = pState.cycle(ACTIVE);
            pLevel.setBlockAndUpdate(pPos, pState);
            pLevel.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, ModSounds.STEAM_LEVER.get(), SoundSource.BLOCKS, 1, 1, 16);
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
