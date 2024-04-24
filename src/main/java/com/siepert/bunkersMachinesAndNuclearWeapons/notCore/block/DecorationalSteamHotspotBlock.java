package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class DecorationalSteamHotspotBlock extends Block {
    public DecorationalSteamHotspotBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBlockState(pPos.above()).getBlock() != ModBlocks.STEAM_CAP.get() &&
                pLevel.getBlockState(pPos.above()).getBlock() != ModBlocks.STONE_STEAM_HOTSPOT.get() &&
                pLevel.getBlockState(pPos.above()).getBlock() != ModBlocks.BEDROCK_STEAM_HOTSPOT.get()) {
            if (validSteamSource(pLevel, pPos)) {
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
                pLevel.addParticle(ParticleTypes.CLOUD, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 0.01, 0.5, 0.01);
            }
        }
    }

    private static boolean validSteamSource(Level pLevel, BlockPos pPos) {
        boolean hasBedrockSource = pLevel.getBlockState(pPos.atY(-64)).getBlock() == ModBlocks.BEDROCK_STEAM_HOTSPOT.get();
        boolean stillValid = true;
        for (int i = -63; i < pPos.getY(); i++) {
            if (pLevel.getBlockState(pPos.atY(i)).getBlock() != ModBlocks.BEDROCK_STEAM_HOTSPOT.get() &&
                    pLevel.getBlockState(pPos.atY(i)).getBlock() != ModBlocks.STONE_STEAM_HOTSPOT.get()) stillValid = false;
        }
        return stillValid && hasBedrockSource;
    }
}
