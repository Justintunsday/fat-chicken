package com.nameless.fatchicken;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FatChicken.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    public static final ModelLayerLocation FAT_CHICKEN_LAYER = new ModelLayerLocation(new ResourceLocation(FatChicken.MOD_ID, "fat_chicken"), "main");
    public static final ModelLayerLocation FATTEST_CHICKEN_LAYER = new ModelLayerLocation(new ResourceLocation(FatChicken.MOD_ID, "fattest_chicken"), "main");

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FatChicken.FAT_CHICKEN.get(), FatChickenRenderer::new);
        event.registerEntityRenderer(FatChicken.FATTEST_CHICKEN.get(), FattestChickenRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(FAT_CHICKEN_LAYER, net.minecraft.client.model.ChickenModel::createBodyLayer);
        event.registerLayerDefinition(FATTEST_CHICKEN_LAYER, net.minecraft.client.model.ChickenModel::createBodyLayer);
    }
}