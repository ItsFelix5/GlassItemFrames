package glassitemframes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class Main implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanRule> ALLOW_FLOATING_FRAMES = GameRuleRegistry.register("allowFloatingFrames", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

    public static final Item GLASS_ITEM_FRAME;
    public static final Item GLOW_GLASS_ITEM_FRAME;

    static {
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("Invisible", true);
        nbt.putString("id", "item_frame");

        GLASS_ITEM_FRAME = Registry.register(Registries.ITEM, Identifier.of("glassitemframes", "glass_item_frame"),
                new ItemFrameItem(EntityType.ITEM_FRAME, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("glassitemframes", "glass_item_frame"))).component(DataComponentTypes.ENTITY_DATA, TypedEntityData.create(EntityType.ITEM_FRAME, nbt))));

        nbt.putString("id", "glow_item_frame");

        GLOW_GLASS_ITEM_FRAME = Registry.register(Registries.ITEM, Identifier.of("glassitemframes", "glow_glass_item_frame"),
                new ItemFrameItem(EntityType.GLOW_ITEM_FRAME,
                        new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of("glassitemframes", "glow_glass_item_frame"))).component(DataComponentTypes.ENTITY_DATA,
                                TypedEntityData.create(EntityType.GLOW_ITEM_FRAME, nbt))));
    }

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(group -> {
            group.add(GLASS_ITEM_FRAME);
            group.add(GLOW_GLASS_ITEM_FRAME);
        });
    }
}
