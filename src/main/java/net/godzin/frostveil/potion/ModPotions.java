package net.godzin.frostveil.potion;

import net.godzin.frostveil.Frostveil;
import net.godzin.frostveil.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, Frostveil.MOD_ID);

    public static final Holder<Potion> FROST_BITE_POTION = POTIONS.register("frost_bite_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.FROST_BITE, 900, 0)));

    public static final Holder<Potion> FROST_BITE_POTION_II = POTIONS.register("frost_bite_potion_ii",
            () -> new Potion(new MobEffectInstance(ModEffects.FROST_BITE, 900, 1)));

    public static void register(IEventBus bus) {
        POTIONS.register(bus);
    }
}
