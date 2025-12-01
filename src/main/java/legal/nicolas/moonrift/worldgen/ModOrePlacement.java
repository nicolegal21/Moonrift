package legal.nicolas.moonrift.worldgen;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    public static List<PlacementModifier> exposedSurfaceOre(int count) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),

                HeightRangePlacement.uniform(
                        VerticalAnchor.bottom(),
                        VerticalAnchor.top()
                ),

                SurfaceRelativeThresholdFilter.of(
                        Heightmap.Types.WORLD_SURFACE_WG,
                        0,
                        0
                ),

                BiomeFilter.biome()
        );
    }
}