package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltippedBlockItem extends BlockItem {
    boolean requireShift;
    Component tooltip;
    public TooltippedBlockItem(Block pBlock, Properties pProperties, Component tooltip, boolean pRequireShift) {
        super(pBlock, pProperties);
        this.requireShift = pRequireShift;
        this.tooltip = tooltip;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown() || !this.requireShift) {
            pTooltipComponents.add(this.tooltip);
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.shift_for_info"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
