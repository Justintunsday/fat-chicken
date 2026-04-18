package com.nameless.fatchicken;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FattestChickenRenderer extends MobRenderer<FattestChickenEntity, FattestChickenModel> {
    public FattestChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new FattestChickenModel(context.bakeLayer(ClientEvent.FATTEST_CHICKEN_LAYER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(FattestChickenEntity entity) {
        return new ResourceLocation("textures/entity/chicken.png");
    }

    @Override
    protected void scale(FattestChickenEntity entity, PoseStack poseStack, float partialTickTime) {

        poseStack.scale(4.0F, 4.0F, 4.0F);
    }

    @Override
    protected float getBob(FattestChickenEntity entity, float partialTicks) {
        float f = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        float f1 = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}