package touhou.skeyamax.touskemod.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import touhou.skeyamax.touskemod.entity.custom.DogEntity;

public class DogRenderer extends GeoEntityRenderer<DogEntity> {
    public DogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DogModel());
        this.shadowRadius = 0.3f;
    }
}