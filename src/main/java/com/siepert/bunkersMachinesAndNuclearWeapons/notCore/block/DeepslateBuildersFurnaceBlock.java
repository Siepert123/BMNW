package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.BuildersFurnaceBlockEntity;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.DeepslateBuildersFurnaceBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class DeepslateBuildersFurnaceBlock extends BaseEntityBlock {
    public DeepslateBuildersFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    @Override
    public RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
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
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof DeepslateBuildersFurnaceBlockEntity) {
                ((DeepslateBuildersFurnaceBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos,
                                          @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof DeepslateBuildersFurnaceBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (DeepslateBuildersFurnaceBlockEntity) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new DeepslateBuildersFurnaceBlockEntity(pPos, pState);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(ACTIVE)) {
            pLevel.addParticle(ParticleTypes.LARGE_SMOKE, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                    0, 0.45, 0);
            pLevel.addParticle(ParticleTypes.LARGE_SMOKE, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                    0, 0.55, 0);
            pLevel.addParticle(ParticleTypes.LARGE_SMOKE, pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5,
                    0, 0.5, 0);
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.getValue(ACTIVE)) {
            return 15;
        }
        return 0;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.DEEPSLATE_BUILDERS_FURNACE_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
