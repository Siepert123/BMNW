package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModMenuTypes;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity.AlloyBlastFurnaceBlockEntity;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.ModResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AlloyBlastFurnaceMenu extends AbstractContainerMenu {
    private final AlloyBlastFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public AlloyBlastFurnaceMenu(int pContainerId, Inventory inv,@NotNull FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(6));
    }
    public AlloyBlastFurnaceMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ALLOY_BLAST_FURNACE_MENU.get(), pContainerId);
        checkContainerSize(inv, 4);
        blockEntity = ((AlloyBlastFurnaceBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler,
                    AlloyBlastFurnaceBlockEntity.AlloyBlastFurnaceSlot.FUEL_SLOT, 18, 50));
            this.addSlot(new SlotItemHandler(handler,
                    AlloyBlastFurnaceBlockEntity.AlloyBlastFurnaceSlot.INPUT_SLOT_1, 66, 16));
            this.addSlot(new SlotItemHandler(handler,
                    AlloyBlastFurnaceBlockEntity.AlloyBlastFurnaceSlot.INPUT_SLOT_2, 66, 50));
            this.addSlot(new ModResultSlot(handler,
                    AlloyBlastFurnaceBlockEntity.AlloyBlastFurnaceSlot.OUTPUT_SLOT, 114, 33));
        });

        addDataSlots(data);
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 28; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuel() {
        int fuel = this.data.get(2);
        int maxFuel = this.data.get(3);  // Max Progress
        int fuelFlameSize = 13; // This is the height in pixels of your arrow
        return maxFuel != 0 && fuel != 0 ? 13 - (fuel * fuelFlameSize / maxFuel) : 13;
    }
    public int getPressureGaugeY() {
        int pressure = this.data.get(4);
        if (pressure >= 1000) {
            return 80;
        }
        if (pressure >= 800) {
            return 64;
        }
        if (pressure >= 600) {
            return 48;
        }
        if (pressure >= 400) {
            return 32;
        }
        if (pressure >= 200) {
            return 16;
        }
        return 0;
    }
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.ALLOY_BLAST_FURNACE.get());
    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
