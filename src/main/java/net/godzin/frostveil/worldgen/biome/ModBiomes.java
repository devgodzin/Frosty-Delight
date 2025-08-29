package net.godzin.frostveil.worldgen.biome;

import net.godzin.frostveil.Frostveil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final ResourceKey<Biome> DUMMY = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Frostveil.MOD_ID, "dummy"));

    public static void bootstrap(BootstrapContext<Biome> context) {

    }
}
