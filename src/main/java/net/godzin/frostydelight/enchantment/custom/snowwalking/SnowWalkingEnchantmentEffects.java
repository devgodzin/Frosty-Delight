package net.godzin.frostydelight.enchantment.custom.snowwalking;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SnowWalkingEnchantmentEffects() implements EnchantmentEntityEffect {
    public static final MapCodec<SnowWalkingEnchantmentEffects> CODEC
            = MapCodec.unit(SnowWalkingEnchantmentEffects::new);

    @Override
    public void apply(ServerLevel serverLevel, int level, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }


}
