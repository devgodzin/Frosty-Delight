package net.godzin.frostveil.events;

import net.godzin.frostveil.Frostveil;
import net.godzin.frostveil.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@EventBusSubscriber(modid = Frostveil.MOD_ID)
public class FrostBiteHandler {
    @SubscribeEvent
    public static void onLivinHurtFrostBitten(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(ModEffects.FROST_BITE)) {
            int amplifier = Objects.requireNonNull(entity.getEffect(ModEffects.FROST_BITE)).getAmplifier();

            float multiplier = 1.5f + (0.5f * amplifier);
            event.setAmount(event.getAmount() * multiplier);
        }
    }

    private static final Random random = new Random();
    private static boolean shaking = false;
    private static int ticksRemaining = 0;
    private static float intensity = 0.0f;

    public static void startShake(int durationTicks, float strength) {
        shaking = true;
        ticksRemaining = durationTicks;
        intensity = strength;
    }

    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        if (shaking && ticksRemaining > 0) {
            float shakeYaw = (random.nextFloat() - 0.5f) * intensity;
            float shakePitch = (random.nextFloat() - 0.5f) * intensity;

            event.setYaw(event.getYaw() + shakeYaw);
            event.setPitch(event.getPitch() + shakePitch);

            ticksRemaining--;
        } else {
            shaking = false;
        }
    }

    // ########################################################################
    // Handler for giving the effect
    // ########################################################################

    private static final int frostBiteDuration = 120; // In Seconds

    @SubscribeEvent
    public static void onPlayerTickOnFrozen(PlayerTickEvent.Post event) {
        LivingEntity livingEntity = event.getEntity();
        int ticksFrozen = livingEntity.getTicksFrozen();
        if (ticksFrozen > 0) {
            applyFrostBite(livingEntity,  ticksFrozen);
        }
    }

    private static void applyFrostBite(LivingEntity entity, int ticks) {
        System.out.println(ticks);
        if (ticks % 20 == 0) {
            entity.addEffect(
                    new MobEffectInstance(
                            ModEffects.FROST_BITE,
                            ((frostBiteDuration + ticks) * 20),
                            (int) (double) (ticks / 20),
                            true,
                            true
                    )
            );
        }
    }

    // ########################################################################
    // Handler curing the effect
    // ########################################################################

    private static final Map<Block, Float> heatSources = new HashMap<>(); // Heat sources that reduce the frost bite effect
    private static final int radius = 3; // Distance that the heat source must be from the player

    static {
        heatSources.put(Blocks.REDSTONE_TORCH, 0.25f);
        heatSources.put(Blocks.TORCH, 1.0f);
        heatSources.put(Blocks.WALL_TORCH, 1.0f);
        heatSources.put(Blocks.LANTERN, 1.25f);
        heatSources.put(Blocks.SOUL_LANTERN, 1.25f);
        heatSources.put(Blocks.JACK_O_LANTERN, 1.25f);
        heatSources.put(Blocks.CAMPFIRE, 3.50f);
        heatSources.put(Blocks.SOUL_CAMPFIRE, 3.50f);
        heatSources.put(Blocks.LAVA, 5.0f);
        heatSources.put(Blocks.LAVA_CAULDRON, 5.0f);
        heatSources.put(Blocks.MAGMA_BLOCK, 7.0f);
    }

    @SubscribeEvent
    public static void onPlayerTickOnNearHeatSource(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        BlockPos playerPos = player.blockPosition();
        boolean isNearHeatSource = false;
        int heatPower = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = playerPos.offset(x, y, z);
                    Block block = player.level().getBlockState(checkPos).getBlock();
                    if (heatSources.containsKey(block)) {
                        isNearHeatSource = true;
                        heatPower += heatSources.get(block); // Determines the source block near the player
                        break;
                    }
                }
                if (isNearHeatSource) break;
            }
            if (isNearHeatSource) break;
        }

        if (isNearHeatSource) {
            handleRemovingEffect(player, heatPower);
        }
    }

    private static void handleRemovingEffect(Player player, int heatSource) {
        MobEffectInstance frost = player.getEffect(ModEffects.FROST_BITE);
        if (frost != null) {
            int currentDuration = frost.getDuration();
            int reduceTicks = (int) (heatSource * 20);
            int newDuration = Math.max(0, currentDuration - reduceTicks);

            player.removeEffect(ModEffects.FROST_BITE);
            player.addEffect(new MobEffectInstance(
                    ModEffects.FROST_BITE,
                    newDuration,
                    frost.getAmplifier(),
                    true,
                    true
            ));
        }
    }
}
