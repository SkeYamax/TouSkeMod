package touhou.skeyamax.touskemod.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.block.DemoBlock;

import java.util.function.Supplier;

import static touhou.skeyamax.touskemod.TouSkeMod.BLOCKS;

public final class BlockRegistry {
    public static void init() {

    }

    static final Supplier<DemoBlock> DEMO = registerBlock("Demo", DemoBlock::new);

    public static final DeferredBlock<Block> DEMOBLOCK = BLOCKS.registerSimpleBlock("DEMOBLOCK", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    private static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        return TouSkeMod.COMMON_PLATFORM.registerBlock(id, block);
    }


}
