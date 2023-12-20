package com.newkinidev.nkisdefense;

import com.mojang.logging.LogUtils;

import com.newkinidev.nkisdefense.block.GunWorkbench;
import com.newkinidev.nkisdefense.gui.GunWorkbenchGui;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NKISDefense.MODID)
public class NKISDefense
{
    public static final String MODID = "nkisdefense";
    public static final String MODVER = "1.0.0";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> ITEM_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<MenuType<?>> GUI = DeferredRegister.create(Registries.MENU, MODID);

    public static DeferredHolder<Block, Block> GUN_WORKBENCH;

    public static DeferredHolder<Item, Item> PISTOL;
    public static DeferredHolder<Item, BlockItem> GUN_WORKBENCH_ITEM;
    public static DeferredHolder<Item, Item> PISTOL_BULLET;

    static {
        GUN_WORKBENCH = BLOCKS.register("gun_workbench", () -> new GunWorkbench(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .strength(1.0F, 1.0F)
                .requiresCorrectToolForDrops()));
        GUN_WORKBENCH_ITEM = ITEMS.register("gun_workbench", () -> new BlockItem(GUN_WORKBENCH.get(), new Item.Properties()));
        PISTOL_BULLET = ITEMS.register("pistol_bullet", () -> new Item(new Item.Properties()));
        PISTOL = ITEMS.register("pistol", () -> new Item(new Item.Properties()));
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GUN_TAB = ITEM_TAB.register("gun", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nkisdefense.gun"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> GUN_WORKBENCH_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(PISTOL_BULLET.get());
                output.accept(PISTOL.get());
                output.accept(GUN_WORKBENCH_ITEM.get());
            }).build());

    public static final DeferredHolder<MenuType<?>, MenuType<GunWorkbenchGui>> GUN_WORKBENCH_GUI = GUI.register("gun_workbench_menu", () -> new MenuType<>(GunWorkbenchGui::new, FeatureFlags.DEFAULT_FLAGS));

    public NKISDefense(IEventBus modEventBus)
    {
        LOGGER.info("NKISDefense v{}", MODVER);

        // Register the commonSet up method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ITEM_TAB.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        //     event.accept(GUN_WORKBENCH_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
