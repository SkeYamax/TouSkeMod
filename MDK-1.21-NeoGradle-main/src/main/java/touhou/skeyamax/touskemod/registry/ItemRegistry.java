package touhou.skeyamax.touskemod.registry;

import cpw.mods.util.LambdaExceptionUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.item.DemoItem;

import java.util.function.Supplier;


public final class ItemRegistry {
    public static void init() {}

    public static final Supplier<BlockItem> DEMO = registerItem("demo", () -> new DemoItem(BlockRegistry.DEMO.get(), new Item.Properties()));


    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return TouSkeMod.COMMON_PLATFORM.registerItem(id, item);
    }


    public static final Supplier<CreativeModeTab> TouSkeTab = TouSkeMod.COMMON_PLATFORM.registerCreativeModeTab("demo_items", () -> TouSkeMod.COMMON_PLATFORM.newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + TouSkeMod.MODID + ".demo_items"))
            .icon(() -> new ItemStack(ItemRegistry.DEMO.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ItemRegistry.DEMO.get());
            })
            .build());
}
