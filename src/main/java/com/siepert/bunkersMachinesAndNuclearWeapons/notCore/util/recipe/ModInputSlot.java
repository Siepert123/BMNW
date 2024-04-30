package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModInputSlot extends SlotItemHandler {
    public ModInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return true;
    }
}
