package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class IndustrialFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private static final String ID_KEY = "MachineCorePos";
    private int[] machineCorePos;
    public IndustrialFurnaceBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.INDUSTRIAL_FURNACE_BE.get(), pPos, pState);

    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putIntArray(ID_KEY, machineCorePos);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.machineCorePos = pTag.getIntArray(ID_KEY);
    }



    public int[] getMachineCorePos() {
        return machineCorePos;
    }

    public void setMachineCorePos(int[] value) {
        machineCorePos = value;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(getMachineCorePos().toString());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pInt, Inventory pInventory, Player pPlayer) {
        BlockPos pos = new BlockPos(machineCorePos[0], machineCorePos[1], machineCorePos[2]);

        LogUtils.getLogger().warn("Player " + pPlayer.getName().getString() + " tried opening WIP menu!");

        level.playSound(null, pos, ModSounds.LEVER_DOESNT_COMPLY.get(), SoundSource.BLOCKS);

        return null;
    }
}
