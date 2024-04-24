package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RadioactiveItem extends Item {
    int microSievertsTick;
    public RadioactiveItem(Properties pProperties, int pMicroSievertsTick) {
        super(pProperties);
        this.microSievertsTick = pMicroSievertsTick;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        CompoundTag playerData = pEntity.getPersistentData();
        int currentMicroSieverts = playerData.getInt("microsieverts");
        playerData.putInt("microsieverts", currentMicroSieverts + (microSievertsTick * pStack.getCount()));

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    public float getMicroSievertsTick() {
        return this.microSievertsTick;
    }
    public float getMilliSievertsTick() {
        return getMicroSievertsTick() / 1000;
    }
    public float getSievertsTick() {
        return getMilliSievertsTick() / 1000;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (getSievertsTick() > 0.1) {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.radioactive_item")
                    .append(" - " + getSievertsTick() + " ")
                    .append(Component.translatable("text.bmnw.sieverts"))
                    .append("/t"));
        } else if (getMilliSievertsTick() > 0.1) {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.radioactive_item")
                    .append(" - " + getMilliSievertsTick() + " ")
                    .append(Component.translatable("text.bmnw.millisieverts"))
                    .append("/t"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.bmnw.radioactive_item")
                    .append(" - " + getMicroSievertsTick() + " ")
                    .append(Component.translatable("text.bmnw.microsieverts"))
                    .append("/t"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
