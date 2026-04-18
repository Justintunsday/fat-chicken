package com.nameless.fatchicken;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(FatChicken.MOD_ID)
public class FatChicken {
    public static final String MOD_ID = "fatchicken";

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<EntityType<FatChickenEntity>> FAT_CHICKEN = ENTITY_TYPES.register("fat_chicken",
            () -> EntityType.Builder.of(FatChickenEntity::new, MobCategory.CREATURE).sized(0.4f, 0.7f).clientTrackingRange(10).build("fat_chicken"));

    public static final RegistryObject<EntityType<FattestChickenEntity>> FATTEST_CHICKEN = ENTITY_TYPES.register("fattest_chicken",
            () -> EntityType.Builder.of(FattestChickenEntity::new, MobCategory.CREATURE).sized(4.0f, 4.0f).clientTrackingRange(10).build("fattest_chicken"));

    public static final RegistryObject<Item> FAT_CHICKEN_SPAWN_EGG = ITEMS.register("fat_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(FAT_CHICKEN, 16777215, 0, new Item.Properties()));

    public static final RegistryObject<Item> FATTEST_CHICKEN_SPAWN_EGG = ITEMS.register("fattest_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(FATTEST_CHICKEN, 0, 16777215, new Item.Properties()));

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("fat_chicken_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.fatchicken"))
                    .icon(() -> new ItemStack(FAT_CHICKEN_SPAWN_EGG.get()))
                    .displayItems((params, output) -> {
                        output.accept(FAT_CHICKEN_SPAWN_EGG.get());
                        output.accept(FATTEST_CHICKEN_SPAWN_EGG.get());
                    }).build());

    public FatChicken() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::registerAttributes);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FAT_CHICKEN.get(), Chicken.createAttributes().build());
        event.put(FATTEST_CHICKEN.get(), Chicken.createAttributes().build());
    }
}