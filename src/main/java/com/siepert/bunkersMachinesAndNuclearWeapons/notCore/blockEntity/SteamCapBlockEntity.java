package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockItems;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.sql.Time;
import java.util.List;
import java.util.Random;

import static com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.steam.SteamSwitchSmallBlock.ACTIVE;

@ParametersAreNonnullByDefault
public class SteamCapBlockEntity extends BlockEntity {

    private static final List<Block> VALID_STEAM_HOTSPOT_CONDUCTORS =
            List.of(ModBlocks.STONE_STEAM_HOTSPOT.get(),
                    ModBlocks.BEDROCK_STEAM_HOTSPOT.get());

    public SteamCapBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.STEAM_CAP_BE.get(), pPos, pState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        assert this.level != null;
        Random random = new Random();
        if (pLevel.isClientSide()) clientLevelActions(pLevel, pPos, pState, random);
        if (!pLevel.isClientSide()) serverLevelActions(pLevel, pPos, pState, random);
    }

    private static void clientLevelActions(Level pLevel, BlockPos pPos, BlockState pState, Random pRandom) {
        if (pLevel.getBlockState(pPos.above(2)).getBlock() == ModBlocks.STEAM_OUTLET.get() &&
                pLevel.getBlockState(pPos.above(1)).getBlock() == ModBlocks.STEAM_SWITCH_SMALL.get() &&
        validSteamSource(pLevel, pPos)) {
            if (!pLevel.getBlockState(pPos.above()).getValue(ACTIVE)) {
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 3.2, pPos.getZ() + 0.5, 0.1, 0.5, 0.1);
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 3.2, pPos.getZ() + 0.5, 0.1, 0.5, 0.1);
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 3.2, pPos.getZ() + 0.5, 0.1, 0.5, 0.1);
            }
        }
    }

    private static void serverLevelActions(Level pLevel, BlockPos pPos, BlockState pState, Random pRandom) {
        if (pLevel.getBlockState(pPos.above(2)).getBlock() == ModBlocks.STEAM_OUTLET.get() &&
                pLevel.getBlockState(pPos.above(1)).getBlock() == ModBlocks.STEAM_SWITCH_SMALL.get() &&
                validSteamSource(pLevel, pPos)) {
            if (!pLevel.getBlockState(pPos.above()).getValue(ACTIVE)) {
                if (pLevel.getGameTime() % 40 == 0) {
                    pLevel.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 3.2, pPos.getZ() + 0.5, ModSounds.STEAM_OUTLET_LOOP.get(), SoundSource.BLOCKS, 1, 1, 32);
                }
            } else {
                if (pLevel.getGameTime() % 40 == 0) {
                    pLevel.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 1.5, pPos.getZ() + 0.5, ModSounds.STEAM_LOOP.get(), SoundSource.BLOCKS, 0.1f, 1, 32);
                }
            }
        }
    }

    private static boolean validSteamSource(Level pLevel, BlockPos pPos) {
        boolean hasBedrockSource = pLevel.getBlockState(pPos.atY(-64)).getBlock() == ModBlocks.BEDROCK_STEAM_HOTSPOT.get();
        boolean stillValid = true;
        for (int i = -63; i < pPos.getY(); i++) {
            if (!VALID_STEAM_HOTSPOT_CONDUCTORS.contains(pLevel.getBlockState(pPos.atY(i)).getBlock())) stillValid = false;
        }
        return stillValid && hasBedrockSource;
    }
}

