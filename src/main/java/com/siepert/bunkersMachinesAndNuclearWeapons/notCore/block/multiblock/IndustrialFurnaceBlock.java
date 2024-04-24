package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.multiblock;

import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.MachineTomfoolery;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IndustrialFurnaceBlock extends Block {
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

    public IndustrialFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }
    private static final String ID_KEY = "MachineID";

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        List<BlockState> s = List.of(Blocks.AIR.defaultBlockState(), Blocks.WATER.defaultBlockState(),
                Blocks.GRASS.defaultBlockState(), Blocks.TALL_GRASS.defaultBlockState());
        return s.contains(level.getBlockState(pos.east()))
                && s.contains(level.getBlockState(pos.west())) && s.contains(level.getBlockState(pos.north()))
                && s.contains(level.getBlockState(pos.south())) && s.contains(level.getBlockState(pos.south().west()))
                && s.contains(level.getBlockState(pos.south().east()))
                && s.contains(level.getBlockState(pos.north().west()))
                && s.contains(level.getBlockState(pos.north().east()));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state1, boolean b) {




        BlockState core = AllMultipartBlocks.INDUSTRIAL_FURNACE_CORE.get().withPropertiesOf(level.getBlockState(pos));
        BlockState blockstate = AllMultipartBlocks.INDUSTRIAL_FURNACE_OUTER.get().defaultBlockState();

        MachineTomfoolery.industrialFurnaceCorePos = new int[3];
        MachineTomfoolery.industrialFurnaceCorePos[0] = pos.getX();
        MachineTomfoolery.industrialFurnaceCorePos[1] = pos.getY();
        MachineTomfoolery.industrialFurnaceCorePos[2] = pos.getZ();



        level.setBlock( pos,core,3);
        level.setBlock( pos.east(),blockstate,3);
        level.setBlock( pos.north(),blockstate,3);
        level.setBlock( pos.west(),blockstate,3);
        level.setBlock( pos.south(),blockstate,3);
        level.setBlock( pos.east().north(),blockstate,3);
        level.setBlock( pos.west().north(),blockstate,3);
        level.setBlock( pos.east().south(),blockstate,3);
        level.setBlock( pos.west().south(),blockstate,3);



    }
}
