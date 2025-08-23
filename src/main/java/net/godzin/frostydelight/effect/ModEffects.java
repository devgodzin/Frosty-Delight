package net.godzin.frostydelight.effect;

import net.godzin.frostydelight.FrostyDelight;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister
            .create(BuiltInRegistries.MOB_EFFECT, FrostyDelight.MOD_ID);

    public static final Holder<MobEffect> FROST_BITE = MOB_EFFECTS.register("frost_bite",
            () -> new FrostBiteEffect(MobEffectCategory.HARMFUL, 0x404d6a)
                    .addAttributeModifier(Attributes.ARMOR,
                            ResourceLocation.fromNamespaceAndPath(FrostyDelight.MOD_ID, "frost_bite"), -0.13f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)); // The frostbite effect removes 35% of the armor fo the player

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
