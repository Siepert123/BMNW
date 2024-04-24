package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import com.mojang.logging.LogUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RadAwayItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    int heal;
    public RadAwayItem(Properties pProperties, int heal) {
        super(pProperties);
        this.heal = heal;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        CompoundTag playerData = pPlayer.getPersistentData();
        int currentMicroSieverts = playerData.getInt("microsieverts");

        if (!pLevel.isClientSide()) {

            if (currentMicroSieverts >= heal) {
                playerData.putInt("microsieverts", Math.max(0, currentMicroSieverts - heal));
                pPlayer.sendSystemMessage(Component.translatable("text.bmnw.i_really_needed_that"));
                if (!pPlayer.isCreative()) {
                    pPlayer.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.AIR));
                }
                return new InteractionResultHolder<>(InteractionResult.CONSUME, pPlayer.getItemInHand(pUsedHand));
            } else {
                pPlayer.sendSystemMessage(Component.translatable("text.bmnw.not_sick"));
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.bmnw.rad_away.desc").append("§7 " + (float) heal / 1000 + " mSv!"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
