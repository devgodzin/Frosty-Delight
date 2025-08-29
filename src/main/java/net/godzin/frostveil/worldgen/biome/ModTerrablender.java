package net.godzin.frostveil.worldgen.biome;

import net.godzin.frostveil.Frostveil;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(Frostveil.MOD_ID, "overworld"), 3));
    }
}
