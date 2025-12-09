package legal.nicolas.moonrift.worldgen;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVERMOON_KEY = registerKey("silvermoon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_MUSHMOON_KEY = registerKey("huge_mushmoon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHMOON_PATCH_KEY = registerKey("mushmoon_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MOON_TOUCHED_STONE_KEY = registerKey("moon_touched_stone");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, SILVERMOON_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.SILVERMOON_LOG.get()),
                        new FancyTrunkPlacer(16, 4, 4),
                        BlockStateProvider.simple(ModBlocks.SILVERMOON_LEAVES.get()),
                        new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );

        register(context, HUGE_MUSHMOON_KEY, Feature.HUGE_BROWN_MUSHROOM,
                new HugeMushroomFeatureConfiguration(
                        BlockStateProvider.simple(
                                Blocks.BROWN_MUSHROOM_BLOCK
                                        .defaultBlockState()
                                        .setValue(HugeMushroomBlock.UP, Boolean.valueOf(true))
                                        .setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))
                        ),
                        BlockStateProvider.simple(
                                Blocks.MUSHROOM_STEM
                                        .defaultBlockState()
                                        .setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
                                        .setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))
                        ),
                        3
                ));

        SimpleBlockConfiguration config = new SimpleBlockConfiguration(
                BlockStateProvider.simple(ModBlocks.MUSHMOON.get())
        );

        context.register(
                MUSHMOON_PATCH_KEY,
                new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, config)
        );

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        register(context,
                OVERWORLD_MOON_TOUCHED_STONE_KEY,
                Feature.ORE,
                new OreConfiguration(stoneReplaceables,
                ModBlocks.MOON_TOUCHED_STONE.get().defaultBlockState(), 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Moonrift.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}