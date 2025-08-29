package net.godzin.frostveil.mixin;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.godzin.frostveil.enchantment.custom.ModEnchantments.hasSnowWalking;

@Mixin(PowderSnowBlock.class)
public class SnowWalkingMixin {

    /**
     * @author [BRS] Godzin
     * @reason To handle the enchantment Snow Walking
     */
    @Overwrite
    public static boolean canEntityWalkOnPowderSnow(Entity entity) {

        if (entity instanceof Player player) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (!boots.isEmpty() && hasSnowWalking(boots, player.level().registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT))) {
                return true;
            }
        }

        if (entity.getType().is(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
            return true;
        } else {
            return entity instanceof LivingEntity && ((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET).canWalkOnPowderedSnow((LivingEntity) entity);
        }
    }
}
