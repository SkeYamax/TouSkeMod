package touhou.skeyamax.touskemod.entity;

import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.entity.custom.DogEntity;

import java.util.function.Supplier;

import static javax.swing.text.html.parser.DTDConstants.ENTITIES;

public class ModEntityTypes {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, TouSkeMod.MODID);

    public static final Supplier<EntityType<DogEntity>> DOG = registerEntity();

    private static <T extends Mob> Supplier<EntityType<T>> registerEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, int primaryEggColor, int secondaryEggColor) {
        return TouSkeMod.COMMON_PLATFORM.registerEntity(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}