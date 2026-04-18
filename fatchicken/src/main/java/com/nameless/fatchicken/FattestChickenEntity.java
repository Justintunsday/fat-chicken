package com.nameless.fatchicken;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class FattestChickenEntity extends Chicken {
    public FattestChickenEntity(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }
}