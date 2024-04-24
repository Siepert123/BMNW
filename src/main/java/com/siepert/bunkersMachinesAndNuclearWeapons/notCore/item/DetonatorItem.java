package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import com.mojang.logging.LogUtils;
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

public class DetonatorItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    BlockPos target;
    public DetonatorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack stack = pContext.getItemInHand();
        if (pContext.getPlayer().isCrouching()) {
            BlockPos blockPos = pContext.getClickedPos();
            CompoundTag detonationPos = stack.getOrCreateTagElement("detonationPos");
            detonationPos.putInt("x", blockPos.getX());
            detonationPos.putInt("y", blockPos.getY());
            detonationPos.putInt("z", blockPos.getZ());
            if (pContext.getLevel().isClientSide) {
                pContext.getPlayer().sendSystemMessage(Component.translatable("text.bmnw.detonator_pos_set")
                        .append("X: " + blockPos.getX() + " Y: " + blockPos.getY() + " Z: " + blockPos.getZ()));
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        CompoundTag detonationPos = stack.getTagElement("detonationPos");
        if (detonationPos != null && !pPlayer.isCrouching()) {

            int x = detonationPos.getInt("x");
            int y = detonationPos.getInt("y");
            int z = detonationPos.getInt("z");
            BlockPos blockPos = new BlockPos(x, y, z);
            Block detonatedBlock = pLevel.getBlockState(blockPos).getBlock();
            if (detonatedBlock instanceof ExplosiveBlock) {
                if (pLevel.isClientSide) {
                    pPlayer.sendSystemMessage(Component.translatable("text.bmnw.detonator_explosion_success"));
                }
                ((ExplosiveBlock) detonatedBlock).explode(pLevel, blockPos);
            } else {
                if (pLevel.isClientSide) {
                    pPlayer.sendSystemMessage(Component.translatable("text.bmnw.detonator_explosion_invalid"));
                }
            }
        }
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag detonationPos = pStack.getTagElement("detonationPos");
        if (detonationPos != null) {
            int x = detonationPos.getInt("x");
            int y = detonationPos.getInt("y");
            int z = detonationPos.getInt("z");
            pTooltipComponents.add(Component.literal("§7X: " + x + " Y: " + y + " Z: " + z));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.detonator_set_pos_tutorial"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
