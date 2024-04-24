package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.blockEntity;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlockEntities;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.gui.menu.AlloyBlastFurnaceMenu;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModItems;
import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModSounds;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.block.AlloyBlastFurnaceBlock;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.recipe.AlloyBlastFurnaceRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@ParametersAreNonnullByDefault
public class AlloyBlastFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    public static class AlloyBlastFurnaceSlot {
        public static final int FUEL_SLOT = 0;
        public static final int INPUT_SLOT_1 = 1;
        public static final int INPUT_SLOT_2 = 2;
        public static final int OUTPUT_SLOT = 3;
        private AlloyBlastFurnaceSlot() {}
    }

    static List<Item> sFuel = List.of(Blocks.ACACIA_LOG.asItem(), Blocks.BIRCH_LOG.asItem(),
            Blocks.OAK_LOG.asItem(), Blocks.JUNGLE_LOG.asItem(), Blocks.SPRUCE_LOG.asItem(),
            Blocks.CHERRY_LOG.asItem(), Blocks.DARK_OAK_LOG.asItem(), Blocks.MANGROVE_LOG.asItem(),
            Blocks.STRIPPED_ACACIA_LOG.asItem(), Blocks.STRIPPED_BIRCH_LOG.asItem(),
            Blocks.STRIPPED_OAK_LOG.asItem(), Blocks.STRIPPED_JUNGLE_LOG.asItem(), Blocks.STRIPPED_SPRUCE_LOG.asItem(),
            Blocks.STRIPPED_CHERRY_LOG.asItem(), Blocks.STRIPPED_DARK_OAK_LOG.asItem(), Blocks.STRIPPED_MANGROVE_LOG.asItem(),
            Blocks.ACACIA_WOOD.asItem(), Blocks.BIRCH_WOOD.asItem(),
            Blocks.OAK_WOOD.asItem(), Blocks.JUNGLE_WOOD.asItem(), Blocks.SPRUCE_WOOD.asItem(),
            Blocks.CHERRY_WOOD.asItem(), Blocks.DARK_OAK_WOOD.asItem(), Blocks.MANGROVE_WOOD.asItem(),
            Blocks.STRIPPED_ACACIA_WOOD.asItem(), Blocks.STRIPPED_BIRCH_WOOD.asItem(),
            Blocks.STRIPPED_OAK_WOOD.asItem(), Blocks.STRIPPED_JUNGLE_WOOD.asItem(), Blocks.STRIPPED_SPRUCE_WOOD.asItem(),
            Blocks.STRIPPED_CHERRY_WOOD.asItem(), Blocks.STRIPPED_DARK_OAK_WOOD.asItem(), Blocks.STRIPPED_MANGROVE_WOOD.asItem());
    static List<Item> mFuel = List.of(Items.COAL, Items.CHARCOAL);
    static List<Item> lFuel = List.of(Blocks.COAL_BLOCK.asItem());
    static List<Item> psFuel = List.of(ModItems.PLAYSTATION.get(), ModItems.ACTIVE_FUSION_REACTION.get());

    private enum FuelTypes {
        SMALL, MEDIUM, LARGE, NONE, PLAYSTATION
    }

    private FuelTypes getFuelItemInSlot(AlloyBlastFurnaceBlockEntity entity) {
        Item fuelItem = this.itemHandler.getStackInSlot(AlloyBlastFurnaceSlot.FUEL_SLOT).getItem();
        if (sFuel.contains(fuelItem)) {
            return FuelTypes.SMALL;
        } else if (mFuel.contains(fuelItem)) {
            return FuelTypes.MEDIUM;
        } else if (lFuel.contains(fuelItem)) {
            return FuelTypes.LARGE;
        } else if (psFuel.contains(fuelItem)) {
            return FuelTypes.PLAYSTATION;
        } else {
            return FuelTypes.NONE;
        }

    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 240;
    private int fuel = 0;
    private int maxFuel = 6400;
    private int pressure = 0;
    private int maxPressure = 1000;
    public AlloyBlastFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ALLOY_BLAST_FURNACE_BE.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloyBlastFurnaceBlockEntity.this.progress;
                    case 1 -> AlloyBlastFurnaceBlockEntity.this.maxProgress;
                    case 2 -> AlloyBlastFurnaceBlockEntity.this.fuel;
                    case 3 -> AlloyBlastFurnaceBlockEntity.this.maxFuel;
                    case 4 -> AlloyBlastFurnaceBlockEntity.this.pressure;
                    case 5 -> AlloyBlastFurnaceBlockEntity.this.maxPressure;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloyBlastFurnaceBlockEntity.this.progress = value;
                    case 1 -> AlloyBlastFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> AlloyBlastFurnaceBlockEntity.this.fuel = value;
                    case 3 -> AlloyBlastFurnaceBlockEntity.this.maxFuel = value;
                    case 4 -> AlloyBlastFurnaceBlockEntity.this.pressure = value;
                    case 5 -> AlloyBlastFurnaceBlockEntity.this.maxPressure = value;
                }
            }
            public int getCount() {
                return 6;
            }
        };
    }
    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.bmnw.alloy_blast_furnace");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new AlloyBlastFurnaceMenu(pContainerId, pInventory, this, this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }
    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("alloy_blast_furnace.progress", progress);
        tag.putInt("alloy_blast_furnace.fuel", fuel);
        tag.putInt("alloy_blast_furnace.pressure", pressure);
        super.saveAdditional(tag);
    }

    private boolean topObstructed(BlockPos pPos) {
        Level level = this.level;
        assert level != null;
        return level.getBlockState(pPos.above()).canOcclude();
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("alloy_blast_furnace.progress");
        fuel = nbt.getInt("alloy_blast_furnace.fuel");
        pressure = nbt.getInt("alloy_blast_furnace.pressure");
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        assert this.level != null;
        Random random = new Random();
        boolean active = false;
        if (hasLavaBucketInFuelSlot() && this.maxFuel - this.fuel >= 6400) {
            clearItem(AlloyBlastFurnaceSlot.FUEL_SLOT, this.itemHandler);
            setItem(Items.BUCKET, AlloyBlastFurnaceSlot.FUEL_SLOT, this.itemHandler, 1);
            this.fuel += 6400;
        }
        FuelTypes currentFuel = getFuelItemInSlot(this);
        if (currentFuel != FuelTypes.NONE && this.maxFuel - this.fuel >= 100) {
            if (currentFuel == FuelTypes.SMALL && this.maxFuel - this.fuel >= 100) {
                clearItem(AlloyBlastFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 100;
            } else if (currentFuel == FuelTypes.MEDIUM && this.maxFuel - this.fuel >= 400) {
                clearItem(AlloyBlastFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 400;
            } else if (currentFuel == FuelTypes.LARGE && this.maxFuel - this.fuel >= 2000) {
                clearItem(AlloyBlastFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 2000;
            } else if (currentFuel == FuelTypes.PLAYSTATION) {
                this.fuel = this.maxFuel;
            }
        }

        if(hasRecipe() && hasEnoughFuel()) {
            this.progress++;
            setChanged(pLevel, pPos, pState);
            active = true;
            if(this.progress > this.maxProgress) {
                craftItem(this);
            }
            if (topObstructed(pPos)) {
                if (this.pressure < 1000) this.pressure++;
            } else {
                if (this.pressure > 0) {
                    this.pressure -= Math.min(5, this.pressure);
                }
            }
        } else {
            if (this.pressure > 0 && !topObstructed(pPos)) this.pressure--;
            this.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
        if (this.pressure >= this.maxPressure) {
            level.explode(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5, 2, Level.ExplosionInteraction.BLOCK);
        }
        if (this.pressure >= 750) {
            if (random.nextInt(100) == 0) {
                level.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5,
                        ModSounds.MACHINE_GROAN.get(), SoundSource.BLOCKS, 0.1f, 1, 16);
            }
        }
        if (random.nextInt(50) == 0 && hasRecipe() && hasEnoughFuel()) {
            level.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5,
                    SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1f, 1, 16);
        }
        pState = pState.setValue(AlloyBlastFurnaceBlock.ACTIVE, active);
        pLevel.setBlockAndUpdate(pPos, pState);
    }
    private boolean hasRecipe() {
        Level level = this.level;
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        Optional<AlloyBlastFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyBlastFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory, match.get().getResultItem().getCount())
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasEnoughFuel();
    }
    private boolean hasLavaBucketInFuelSlot() {
        return this.itemHandler.getStackInSlot(AlloyBlastFurnaceSlot.FUEL_SLOT).getItem() == Items.LAVA_BUCKET;
    }

    public boolean hasEnoughFuel() {
        return this.fuel >= 200;
    }

    private void craftItem(AlloyBlastFurnaceBlockEntity entity) {Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<AlloyBlastFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyBlastFurnaceRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            clearItem(AlloyBlastFurnaceSlot.INPUT_SLOT_1, entity.itemHandler);
            clearItem(AlloyBlastFurnaceSlot.INPUT_SLOT_2, entity.itemHandler);

            setItem(match.get().getResultItem().getItem(), AlloyBlastFurnaceSlot.OUTPUT_SLOT, entity.itemHandler, match.get().getResultItem().getCount());
            entity.fuel -= 200;

            entity.resetProgress();
        }
    }
    private void clearItem(int Slot, @NotNull ItemStackHandler handler) {
        handler.extractItem(Slot, 1, false);
    }
    private void setItem(@NotNull Item pItem, int Slot, @NotNull ItemStackHandler handler, int count) {
        handler.setStackInSlot(Slot, new ItemStack(pItem,
                handler.getStackInSlot(Slot).getCount() + count));
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(AlloyBlastFurnaceSlot.OUTPUT_SLOT).getItem() == output.getItem()
                || inventory.getItem(AlloyBlastFurnaceSlot.OUTPUT_SLOT).isEmpty();
    }
    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, int count) {
        return inventory.getItem(AlloyBlastFurnaceSlot.OUTPUT_SLOT).getMaxStackSize()
                >= inventory.getItem(AlloyBlastFurnaceSlot.OUTPUT_SLOT).getCount() + count;
    }
}
