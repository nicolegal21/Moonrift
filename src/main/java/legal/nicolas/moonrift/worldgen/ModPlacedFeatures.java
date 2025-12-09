package legal.nicolas.moonrift.worldgen;

import com.google.common.collect.ImmutableList;
import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import javax.annotation.Nullable;
import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SILVERMOON_PLACED_KEY = registerKey("silvermoon_placed");
    public static final ResourceKey<PlacedFeature> MOON_TOUCHED_STONE_PLACED_KEY = registerKey("moon_touched_stone_placed");
    public static final ResourceKey<PlacedFeature> HUGE_MUSHMOON_PLACED_KEY = registerKey("huge_mushmoon_placed");
    public static final ResourceKey<PlacedFeature> MUSHMOON_PATCH_PLACED_KEY = registerKey("mushmoon_patch_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, SILVERMOON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVERMOON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.SILVERMOON_SAPLING.get()));

        register(context, MOON_TOUCHED_STONE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MOON_TOUCHED_STONE_KEY),
                ModOrePlacement.exposedSurfaceOre(30)
        );

        register(context, HUGE_MUSHMOON_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_MUSHMOON_KEY),
                getMushroomPlacement(256, null));

        register(context, MUSHMOON_PATCH_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MUSHMOON_PATCH_KEY),
                List.of(
                        CountPlacement.of(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Moonrift.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> getMushroomPlacement(int rarity, @Nullable PlacementModifier placement) {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (placement != null) {
            builder.add(placement);
        }

        if (rarity != 0) {
            builder.add(RarityFilter.onAverageOnceEvery(rarity));
        }

        builder.add(InSquarePlacement.spread());
        builder.add(PlacementUtils.HEIGHTMAP);
        builder.add(BiomeFilter.biome());
        return builder.build();
    }
}