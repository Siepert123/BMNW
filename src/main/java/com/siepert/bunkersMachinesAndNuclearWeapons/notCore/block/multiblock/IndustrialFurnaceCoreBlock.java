package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class IndustrialFurnaceCoreBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public IndustrialFurnaceCoreBlock(Properties pProperties) {
        super(pProperties);
    }

    private void HandleRemove(Level pLevel, BlockPos pPos){
        BlockState air = Blocks.AIR.defaultBlockState();
        pLevel.setBlock(pPos.east(), air, 3);
        pLevel.setBlock(pPos.north(), air, 3);
        pLevel.setBlock(pPos.west(), air, 3);
        pLevel.setBlock(pPos.south(), air, 3);
        pLevel.setBlock(pPos.east().north(), air, 3);
        pLevel.setBlock(pPos.west().north(), air, 3);
        pLevel.setBlock(pPos.east().south(), air, 3);
        pLevel.setBlock(pPos.west().south(), air, 3);
    }
    @Override
    public Item asItem() {
        return AllMultipartBlocks.INDUSTRIAL_FURNACE.get().asItem();
    }



    @Override
    public void onBlockExploded(BlockState pState, Level pLevel, BlockPos pPos, Explosion pExplosion) {
        HandleRemove(pLevel, pPos);
        super.onBlockExploded(pState, pLevel, pPos, pExplosion);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pState1, boolean pBool) {

        HandleRemove(pLevel, pPos);
        super.onRemove(pState, pLevel, pPos, pState1, pBool);
    }
}
