package legal.nicolas.moonrift.worldgen.biome;

import legal.nicolas.moonrift.Moonrift;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(
            ResourceLocation.fromNamespaceAndPath(Moonrift.MOD_ID, "overworld"),
            30
        ));
    }
}
