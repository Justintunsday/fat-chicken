package com.nameless.fatchicken;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FatChickenEntity extends Chicken {
    private static final EntityDataAccessor<Byte> STAGE = SynchedEntityData.defineId(FatChickenEntity.class, EntityDataSerializers.BYTE);

    public FatChickenEntity(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STAGE, (byte) 0);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!this.level().isClientSide && itemStack.is(Items.WHEAT_SEEDS) && getState() < 7) {
            itemStack.shrink(1);
            this.entityData.set(STAGE, (byte) Math.min(7, this.entityData.get(STAGE) + 1));
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("stage", this.entityData.get(STAGE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(STAGE, compound.getByte("stage"));
    }

    @Override
    public void die(DamageSource cause) {
        if (!this.level().isClientSide) {
            int dropCount = getState() > 0 ? getState() : 1;
            ItemStack meatDrop = new ItemStack(this.isOnFire() ? Items.COOKED_CHICKEN : Items.CHICKEN, dropCount);
            ItemStack featherDrop = new ItemStack(Items.FEATHER, dropCount);

            this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), meatDrop));
            this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), featherDrop));
        }
        super.die(cause);
    }

    public int getState() {
        return this.entityData.get(STAGE);
    }
}