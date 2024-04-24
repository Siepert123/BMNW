package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GeigerCounterItem extends Item {
    private Component getMessage(float microSieverts) {
        if (microSieverts / 1000000 > 0.8) {
            return Component.literal(": " + microSieverts / 1000000 + " ")
                    .append(Component.translatable("text.bmnw.sieverts"))
                    .append(" (" + sievertPercentage(microSieverts) + "%)");
        } else if (microSieverts / 1000 > 0.8) {
            return Component.literal(": " + microSieverts / 1000 + " ")
                    .append(Component.translatable("text.bmnw.millisieverts"))
                    .append(" (" + sievertPercentage(microSieverts) + "%)");
        } else {
            return Component.literal(": " + microSieverts + " ")
                    .append(Component.translatable("text.bmnw.microsieverts"))
                    .append(" (" + sievertPercentage(microSieverts) + "%)");
        }
    }

    private Component getTooltip(float microSieverts) {
        if (microSieverts / 1000000 > 0.8) {
            return Component.literal("§7: " + microSieverts / 1000000 + " ")
                    .append(Component.literal("§7Sv"))
                    .append("§7 (" + sievertPercentage(microSieverts) + "%)");
        } else if (microSieverts / 1000 > 0.8) {
            return Component.literal("§7: " + microSieverts / 1000 + " ")
                    .append(Component.literal("§7mSv"))
                    .append("§7 (" + sievertPercentage(microSieverts) + "%)");
        } else {
            return Component.literal("§7: " + microSieverts + " ")
                    .append(Component.literal("§7μSv"))
                    .append("§7 (" + sievertPercentage(microSieverts) + "%)");
        }
    }
    public GeigerCounterItem(Properties pProperties) {
        super(pProperties);
    }

    public float sievertPercentage(float microsieverts) {
        final float maxMicrosieverts = 5000000;
        return microsieverts / maxMicrosieverts * 100;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            CompoundTag tag = stack.getOrCreateTagElement("recorded_data");
            CompoundTag playerData = pPlayer.getPersistentData();
            if (!pPlayer.isCreative() && !pPlayer.isSpectator()) {
                pPlayer.sendSystemMessage(Component.translatable("text.bmnw.player_contamination").append(getMessage(playerData.getInt("microsieverts"))));
            } else {
                pPlayer.sendSystemMessage(Component.translatable("text.bmnw.player_rad_immune"));
            }
            tag.putInt("microsieverts", playerData.getInt("microsieverts"));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getTagElement("recorded_data");
        if (tag != null) {
            pTooltipComponents.add(Component.translatable("text.bmnw.player_contamination")
                    .append(getTooltip(tag.getInt("microsieverts"))));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
