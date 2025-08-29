package net.godzin.frostveil.worldgen.biome.surface;

import net.godzin.frostveil.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    public static SurfaceRules.RuleSource makeRules() {

        return null;
    }

    private static SurfaceRules.RuleSource makeRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
