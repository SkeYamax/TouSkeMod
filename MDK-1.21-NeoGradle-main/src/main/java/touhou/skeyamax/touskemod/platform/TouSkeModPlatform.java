package touhou.skeyamax.touskemod.platform;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface TouSkeModPlatform {
    //注册实体方块
    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType);
    //注册普通方块
    <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block);
    //注册实体(怪物,生物)
    <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity);
    //注册盔甲材质
    <T extends ArmorMaterial> Holder<T> registerArmorMaterial(String id, Supplier<T> armorMaterial);
    //注册物品(武器,工具)
    <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item);
    //注册音效
    <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound);
    //注册创造模式标签页
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);

    //生成怪物蛋物品
    <E extends Mob> Supplier<SpawnEggItem> makeSpawnEggFor(Supplier<EntityType<E>> entityType, int primaryEggColour, int secondaryEggColour, Item.Properties itemProperties);
    //创建创造模式标签页
    CreativeModeTab.Builder newCreativeTabBuilder();


}
