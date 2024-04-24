package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TooltippedItem extends Item {
    boolean requireShift;
    Component tooltip;
    public TooltippedItem(Properties pProperties, Component tooltip, boolean requireShift) {
        super(pProperties);
        this.tooltip = tooltip;
        this.requireShift = requireShift;
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
