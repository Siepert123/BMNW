package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item.blockItem;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BombBlockItem extends BlockItem {
    Component radiusC;
    Component wasteRadiusC;
    Component bombThing;
    public BombBlockItem(Block pBlock, Properties pProperties, String radius, String wasteRadius, Component bombThing) {
        super(pBlock, pProperties);
        this.bombThing = bombThing;
        if (radius != "") this.radiusC = Component.literal("§7Radius: " + radius);
        if (wasteRadius != "") this.wasteRadiusC = Component.literal("§7Waste Radius: " + wasteRadius);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(this.bombThing);
        if(Screen.hasShiftDown() || (radiusC == null && wasteRadiusC == null)) {
            if (radiusC != null) pTooltipComponents.add(radiusC);

            if (wasteRadiusC != null) pTooltipComponents.add(wasteRadiusC);
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.shift_for_info"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
