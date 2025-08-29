package net.godzin.frostveil.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.godzin.frostveil.Frostveil;
import net.godzin.frostveil.enchantment.custom.snowwalking.SnowWalkingEnchantmentEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<?  extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Frostveil.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> SNOW_WALKING =
            ENTITY_ENCHANTMENT_EFFECTS.register("snow_walking", () -> SnowWalkingEnchantmentEffects.CODEC);

    public static void register(IEventBus bus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(bus);
    }
}
