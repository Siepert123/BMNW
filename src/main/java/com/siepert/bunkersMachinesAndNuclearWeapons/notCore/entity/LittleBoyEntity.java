package com.siepert.bunkersMachinesAndNuclearWeapons.notCore.entity;

import com.siepert.bunkersMachinesAndNuclearWeapons.core.ModBlocks;
import com.siepert.bunkersMachinesAndNuclearWeapons.notCore.util.bomb.BombUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

public class LittleBoyEntity extends Animal {
    public LittleBoyEntity(EntityType<? extends Animal> pType, Level pLevel) {
        super(pType, pLevel);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }



    @Override
    protected void defineSynchedData() {

    }



    @Override
    public boolean shouldRiderSit() {
        return super.shouldRiderSit();
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(ModBlocks.NUCLEAR_CHARGE.get().asItem());
    }

    @Override
    public boolean canRiderInteract() {
        return super.canRiderInteract();
    }

    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return super.canBeRiddenUnderFluidType(type, rider);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.onGround()) {
            BombUtils.createFireBall(this.level(), (int) this.getX(), (int) this.getY(), (int) this.getZ(), 5);
        }
    }

    @Override
    public float getStepHeight() {
        return super.getStepHeight();
    }

    @Override
    public boolean canStartSwimming() {
        return super.canStartSwimming();
    }

    @Override
    public boolean isPushedByFluid(FluidType type) {
        return super.isPushedByFluid(type);
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return super.canSwimInFluidType(type);
    }
}
