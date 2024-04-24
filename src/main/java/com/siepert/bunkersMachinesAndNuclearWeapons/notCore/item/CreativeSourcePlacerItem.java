package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import com.mojang.logging.LogUtils;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.ExplosiveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.List;

public class CreativeSourcePlacerItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    public CreativeSourcePlacerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack stack = pContext.getItemInHand();
        if (pContext.getPlayer().isCrouching()) {
            BlockPos blockPos = pContext.getClickedPos();
            pContext.getLevel().setBlock(blockPos, ModBlocks.STEAM_HOTSPOT_PLACER.get().defaultBlockState(), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.bmnw.creative_source_placer.desc"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
