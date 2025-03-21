package touhou.skeyamax.touskemod.registry;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import touhou.skeyamax.touskemod.TouSkeMod;
import touhou.skeyamax.touskemod.entity.custom.DogEntity;

import java.util.function.BiConsumer;
import java.util.function.Supplier;



public final class EntityRegistry {
        public static void init() {

        }

    public static  final Supplier<EntityType<DogEntity>> DOG = registerEntity("dog", DogEntity::new, 0.7f,1.3f,0x1F1F1F, 0x0D0D0D);

        public static void registerEntityAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> registrar) {
            AttributeSupplier.Builder genericAttribs = PathfinderMob.createMobAttributes()
                    .add(Attributes.FOLLOW_RANGE, 16)
                    .add(Attributes.MAX_HEALTH, 1);
            AttributeSupplier.Builder genericMovingAttribs = PathfinderMob.createMobAttributes()
                    .add(Attributes.FOLLOW_RANGE, 16)
                    .add(Attributes.MAX_HEALTH, 1)
                    .add(Attributes.MOVEMENT_SPEED, 0.25f);
            AttributeSupplier.Builder genericMonsterAttribs = Monster.createMobAttributes()
                    .add(Attributes.FOLLOW_RANGE, 16)
                    .add(Attributes.MAX_HEALTH, 1)
                    .add(Attributes.MOVEMENT_SPEED, 0.25f)
                    .add(Attributes.ATTACK_DAMAGE, 5)
                    .add(Attributes.ATTACK_KNOCKBACK, 0.1);


            registrar.accept(EntityRegistry.DOG.get(), genericAttribs.build());
        }

        private static <T extends Mob> Supplier<EntityType<T>> registerEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, int primaryEggColor, int secondaryEggColor) {
            return TouSkeMod.COMMON_PLATFORM.registerEntity(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
        }
}
