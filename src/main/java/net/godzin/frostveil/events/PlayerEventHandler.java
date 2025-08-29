package net.godzin.frostveil.events;

import net.godzin.frostveil.Frostveil;
import net.godzin.frostveil.enchantment.custom.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Frostveil.MOD_ID)
public class PlayerEventHandler {
    @SubscribeEvent
    public static void onLivingTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (hasSnowWalking(player)) {
            player.setTicksFrozen(0);

        }
    }

    private static boolean hasSnowWalking(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        if (boots.isEmpty()) {
            return false;
        }

        HolderLookup.RegistryLookup<Enchantment> lookup = player.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);

        ItemEnchantments enchantments = boots.getAllEnchantments(lookup);

        int level = enchantments.getLevel(lookup.getOrThrow(ModEnchantments.SNOW_WALKING));
        return level > 0;
    }
}
