package com.nameless.fatchicken;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FatChickenRenderer extends MobRenderer<FatChickenEntity, FatChickenModel> {
    public FatChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new FatChickenModel(context.bakeLayer(ClientEvent.FAT_CHICKEN_LAYER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(FatChickenEntity entity) {
        return new ResourceLocation("textures/entity/chicken.png");
    }


    @Override
    protected float getBob(FatChickenEntity entity, float partialTicks) {
        float f = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        float f1 = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}