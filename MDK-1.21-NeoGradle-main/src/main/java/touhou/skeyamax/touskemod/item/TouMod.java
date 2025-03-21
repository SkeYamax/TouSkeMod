package touhou.skeyamax.touskemod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.item.custom.TswordItem;

import java.util.function.Supplier;

public class TouMod {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, TouSkeMod.MODID);
    public static final Supplier<Item> Tsword = ITEMS.register("tsword",() -> new TswordItem(new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
