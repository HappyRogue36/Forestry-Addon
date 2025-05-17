package com.example.forestryaddon;

import com.example.forestryaddon.Frame.CustomBeeModifier;
import com.example.forestryaddon.Frame.ItemCustomFrame;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ForestryAddon.MODID)
public class ForestryAddon
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "forestryaddon";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "forestryaddon" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Регистрируем 4 рамки
    public static final RegistryObject<Item> CHOCOLATE_FRAME = ITEMS.register("chocolate_frame", () -> new ItemCustomFrame(new Item.Properties(), new CustomBeeModifier(
            1.0f,    // Продолжительность жизни не меняется
            1.5f,    // Увеличиваем скорость производства на 50%
            1.0f,    // Шанс мутации не меняется
            1.0f,    // Эффективность опыления не меняется
            1.0f,    // Генетическое старение не меняется
            false,   // Не запечатывает улья
            false,   // Пчёлы не всегда активны
            false,   // Солнечный свет не имитируется
            false    // Пчёлы не адские
    )));

    public static final RegistryObject<Item> HEALING_FRAME = ITEMS.register("healing_frame", () -> new ItemCustomFrame(new Item.Properties(), new CustomBeeModifier(
            0.5f,    // Увеличиваем продолжительность жизни на 50%
            0.75f,   // Уменьшаем скорость производства до 75%
            0.5f,    // Уменьшаем шанс мутации до 50%
            1.0f,    // Эффективность опыления не меняется
            1.0f,    // Генетическое старение не меняется
            false,   // Не запечатывает улья
            false,   // Пчёлы не всегда активны
            false,   // Солнечный свет не имитируется
            false    // Пчёлы не адские
    )));

    public static final RegistryObject<Item> RESTRAINT_FRAME = ITEMS.register("restraint_frame", () -> new ItemCustomFrame(new Item.Properties(), new CustomBeeModifier(
            1.75f,   // Уменьшаем продолжительность жизни до 75%
            0.75f,   // Уменьшаем скорость производства до 75%
            1.0f,    // Шанс мутации не меняется
            1.0f,    // Эффективность опыления не меняется
            1.0f,    // Генетическое старение не меняется
            false,   // Не запечатывает улья
            false,   // Пчёлы не всегда активны
            false,   // Солнечный свет не имитируется
            false    // Пчёлы не адские
    )));

    public static final RegistryObject<Item> SOUL_FRAME = ITEMS.register("soul_frame", () -> new ItemCustomFrame(new Item.Properties(), new CustomBeeModifier(
            2.2f,   // Уменьшаем продолжительность жизни до 75%
            0.1f,   // Уменьшаем скорость производства до 25%
            1.8f,    // Увеличиваем шанс мутации на 50%
            1.0f,    // Эффективность опыления не меняется
            1.0f,    // Генетическое старение не меняется
            false,   // Не запечатывает улья
            false,   // Пчёлы не всегда активны
            false,   // Солнечный свет не имитируется
            false    // Пчёлы не адские
    )));

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "forestryaddon");

    public static final RegistryObject<CreativeModeTab> FRAME_TAB = CREATIVE_MODE_TABS.register("frame_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.forestryaddon.frame_tab"))
                    .icon(() -> new ItemStack(CHOCOLATE_FRAME.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(CHOCOLATE_FRAME.get());
                        output.accept(HEALING_FRAME.get());
                        output.accept(RESTRAINT_FRAME.get());
                        output.accept(SOUL_FRAME.get());
                    })
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .build());

    public ForestryAddon(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // ClientModEvents to register textures
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
        }
    }
}

