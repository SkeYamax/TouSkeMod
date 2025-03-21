package touhou.skeyamax.touskemod.entity.renderer;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.entity.custom.DogEntity;

public class DogModel extends DefaultedEntityGeoModel<DogEntity> {
    private static final ResourceLocation DogEntity_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/dog.png");

    public DogModel() {
        super(ResourceLocation.fromNamespaceAndPath(TouSkeMod.MODID, "entity/dog"));
    }

    @Override
    public ResourceLocation getTextureResource(DogEntity Monster) {
        return DogEntity_TEXTURE;
    }
//    @Override
//    public ResourceLocation getModelResource(DogEntity object) {
//        return new ResourceLocation(TouSkeMod.MODID,"geo/dog.geo.json");
//    }


}
