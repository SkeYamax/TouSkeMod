package touhou.skeyamax.touskemod.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class DemoBlock extends Block {

    public DemoBlock() {
        super(Properties.of().noOcclusion()
                .mapColor(MapColor.STONE)
                .strength(1.5f)
                .sound(SoundType.STONE));
    }
}
