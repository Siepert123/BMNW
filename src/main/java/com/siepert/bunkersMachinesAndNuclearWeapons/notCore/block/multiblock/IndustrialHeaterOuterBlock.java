package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.IndustrialHeaterBlockEntity;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.MachineTomfoolery;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class IndustrialHeaterOuterBlock extends BaseEntityBlock {


    public IndustrialHeaterOuterBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        IndustrialHeaterBlockEntity blockEntity =  new IndustrialHeaterBlockEntity(pos,state);
        blockEntity.setMachineCorePos(MachineTomfoolery.industrialHeaterCorePos);
        return blockEntity;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof IndustrialHeaterBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)player), (IndustrialHeaterBlockEntity)entity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public Item asItem() {
        return AllMultipartBlocks.INDUSTRIAL_HEATER.get().asItem();
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof IndustrialHeaterBlockEntity) {
            int[] p = ((IndustrialHeaterBlockEntity) blockEntity).getMachineCorePos();
            if(p != null){
                BlockPos b_p = new BlockPos(p[0],p[1],p[2]);

                level.destroyBlock(b_p,willHarvest);

            }

        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);

    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof IndustrialHeaterBlockEntity) {
            int[] p = ((IndustrialHeaterBlockEntity) blockEntity).getMachineCorePos();
            if(p != null){
                BlockPos b_p = new BlockPos(p[0],p[1],p[2]);

                level.destroyBlock(b_p,true);

            }

        }
        super.onBlockExploded(state, level, pos, explosion);
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pGetter, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pGetter, BlockPos pPos) {
        return true;
    }



    public float getShadeBrightness(BlockState pState, BlockGetter pGetter, BlockPos pPos) {
        return 1.0F;
    }
}
