package touhou.skeyamax.touskemod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;
import touhou.skeyamax.touskemod.entity.ModEntityTypes;
import touhou.skeyamax.touskemod.item.TouMod;
import touhou.skeyamax.touskemod.platform.TouSkeModPlatform;
import touhou.skeyamax.touskemod.registry.EntityRegistry;

import javax.swing.text.html.parser.Entity;
import java.util.ServiceLoader;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TouSkeMod.MODID)
public class TouSkeMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "touskemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final TouSkeModPlatform COMMON_PLATFORM = ServiceLoader.load(TouSkeModPlatform.class).findFirst().orElseThrow();

    public static void doRegistrations() {
        BlockRegistry.init();
        EntityRegistry.init();
    }



    // 创建一个延迟寄存器来保存方块，这些方块都将在“示例”名称空间下注册
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // 创建一个延迟注册器，以保存所有将在“示例”名称空间下注册的项目
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // 创建一个延迟注册来保存创造性标签，这些都将在“示例”名称空间下注册
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final DeferredBlock<Block> TOUMOD = BLOCKS.registerSimpleBlock("toumod",BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredBlock<Block> TOUMOD1 = BLOCKS.registerSimpleBlock("toumod1",BlockBehaviour.Properties.of().mapColor(MapColor.SNOW));
    public static final DeferredBlock<Block> THDRINK = BLOCKS.registerSimpleBlock("thdrink",BlockBehaviour.Properties.of().mapColor(MapColor.SNOW));


    public static final DeferredItem<BlockItem> TOUMOD_ITEM = ITEMS.registerSimpleBlockItem("toumod",TOUMOD);
    public static final DeferredItem<BlockItem> TOUHOU1_ITEM = ITEMS.registerSimpleBlockItem("toumod1",TOUMOD1);
    public static final DeferredItem<BlockItem> THDRINK_ITEM = ITEMS.registerSimpleBlockItem("thdrink",THDRINK);

    public static final DeferredRegister<CreativeModeTab> TOU_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOU_TAB = CREATIVE_MODE_TABS.register("tou_tab",() -> CreativeModeTab.builder()
            .title(Component.translatable("touhou"))
            .icon(() -> TOUMOD_ITEM.get().getDefaultInstance())
            .displayItems((parmeters, output) ->{
                output.accept(TOUHOU1_ITEM.get());
                output.accept(TOUMOD_ITEM.get());
                output.accept(THDRINK_ITEM.get());

            }).build()
    );


    // 创建一个名为“示例块：示例块”的新块，结合了名称空间和路径
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // 创建一个名为“示例块”的新块项，结合了名称空间和路径
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // 创建一个新的食物项目，id为“示例id”，营养度1和饱和度2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // 为示例选项卡创建一个创造性选项卡“示例选项卡”，放置在战斗选项卡之后
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.examplemod")) //你的创意细节的标题的语言键
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // 将示例项添加到您自己的选项卡的选项卡中，此方法比事件更可取
                output.accept(TOUMOD_ITEM.get());
            }).build());

    // mod类的构造函数是在加载mod时运行的第一个代码
    // FML将识别一些参数类型，如IEventBus总线或mod容器，并自动传递它们
    public TouSkeMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // 注册模式加载的通用设置方法
        modEventBus.addListener(this::commonSetup);

        TouMod.register(modEventBus);
        //注册物品类到mod事件总线,以便自定义物品被注册

        ModEntityTypes.register(modEventBus);
        //注册实体类到mod事件总线,以便实体被注册



        // 将延迟注册器注册到mod事件总线，以便块被注册
        BLOCKS.register(modEventBus);
        // 将延迟注册注册到mod事件总线，以便项目得到注册
        ITEMS.register(modEventBus);
        // 将延迟注册注册到mod事件总线，以便选项卡被注册
        CREATIVE_MODE_TABS.register(modEventBus);
        TOU_TABS.register(modEventBus);
        // 注册自己的服务器，我们感兴趣的和其他游戏事件
        // 注意，当且仅当我们希望这个类（示例例）直接响应事件时，这才是必要的
        // 如果这个类中没有@订阅事件注释函数，请不要添加这一行，比如下面启动（）
        NeoForge.EVENT_BUS.register(this);

        // 将该项目注册到一个创意选项卡中
        modEventBus.addListener(this::addCreative);

        // 注册我们的mod的模式配置规范，这样fml就可以为我们创建和加载配置文件
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // 一些常见的设置代码
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // 将示例块项添加到构建块选项卡中
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(TOUMOD_ITEM);
    }

    // 您可以使用下标事件，并让事件总线发现方法来调用
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // 当服务器启动时，做一些事情
        LOGGER.info("HELLO from server starting");
    }

    // 您可以使用事件订阅者在用@订阅事件注释的类中自动注册所有静态方法
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // 一些客户端设置代码
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
