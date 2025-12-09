package legal.nicolas.moonrift.worldgen.biome;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifiers;

public class ModBiomes {

    public static final ResourceKey<Biome> SILVERMOON_FOREST = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Moonrift.MOD_ID, "silvermoon_forest"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(SILVERMOON_FOREST, silvermoonForestBiome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome silvermoonForestBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5));

//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

//        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
//        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
//        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
//        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
//        BiomeDefaultFeatures.addFerns(biomeBuilder);
//        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
//        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
//
//        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
//
//        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED_KEY);
        biomeBuilder.addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                context.lookup(Registries.PLACED_FEATURE)
                        .getOrThrow(ModPlacedFeatures.MOON_TOUCHED_STONE_PLACED_KEY));

        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                context.lookup(Registries.PLACED_FEATURE)
                        .getOrThrow(ModPlacedFeatures.MUSHMOON_PATCH_PLACED_KEY)
        );

        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                context.lookup(Registries.PLACED_FEATURE)
                        .getOrThrow(ModPlacedFeatures.SILVERMOON_PLACED_KEY)
                );

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.65f)          // Humidité légèrement accrue
                .temperature(0.5f)        // Frais, atmosphère nocturne
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x4A76D1)       // Eau bleu lunaire
                        .waterFogColor(0x2F4A89)    // Eau plus sombre
                        .fogColor(0x1C2033)         // Brouillard nocturne léger
                        .skyColor(0x3A4F7F)         // Ciel bleu nuit
                        .grassColorOverride(0x2A4F7C) // Herbe sombre tirant vers le bleu
                        .foliageColorOverride(0x1E3B6F) // Feuillage bleu nuit pâle
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.GAME) // Musique douce
                        .build())
                .build();


    }
}
