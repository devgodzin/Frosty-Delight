package net.godzin.frostydelight.enchantment.custom;

import net.godzin.frostydelight.FrostyDelight;
import net.godzin.frostydelight.enchantment.custom.snowwalking.SnowWalkingEnchantmentEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;


public class ModEnchantments {
    public static final ResourceKey<Enchantment> SNOW_WALKING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FrostyDelight.MOD_ID, "snow_walking"));


    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        register(context, SNOW_WALKING, Enchantment.enchantment(Enchantment
                        .definition(
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                items.getOrThrow(ItemTags.FOOT_ARMOR),
                                3,
                                1,
                                Enchantment.dynamicCost(17, 7),
                                Enchantment.dynamicCost(37, 7),
                                8,
                                EquipmentSlotGroup.FEET
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.TICK,
                        new SnowWalkingEnchantmentEffects()
                )
        );
    }

    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    public static boolean hasSnowWalking(ItemStack stack, HolderLookup.RegistryLookup<Enchantment> lookup) {
        if (stack.isEmpty()) return false;

        Holder<Enchantment> snowWalking = lookup.get(SNOW_WALKING).orElse(null);
        if (snowWalking == null) return false;

        return stack.getEnchantmentLevel(snowWalking) > 0;
    }
}
