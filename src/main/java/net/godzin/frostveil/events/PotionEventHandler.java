package net.godzin.frostveil.events;

import net.godzin.frostveil.Frostveil;
import net.godzin.frostveil.item.ModItems;
import net.godzin.frostveil.potion.ModPotions;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = Frostveil.MOD_ID)
public class PotionEventHandler {

    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                ModItems.FROZEN_HEART.get(),
                ModPotions.FROST_BITE_POTION
        );

        builder.addMix(
                ModPotions.FROST_BITE_POTION,
                ModItems.FROZEN_HEART.get(),
                ModPotions.FROST_BITE_POTION_II
        );
    }
}
