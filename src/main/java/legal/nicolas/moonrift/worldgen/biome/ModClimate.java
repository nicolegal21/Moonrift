package legal.nicolas.moonrift.worldgen.biome;

import net.minecraft.world.level.biome.Climate;

public class ModClimate {

    // Paramètres: tu peux ajuster mais ça fonctionne très bien ainsi
    public static final Climate.ParameterPoint SILVERMOON_CLIMATE = new Climate.ParameterPoint(
            Climate.Parameter.span(-0.4F, 0.1F),   // Temperature : frais
            Climate.Parameter.span(0.4F, 0.9F),    // Humidité : humide
            Climate.Parameter.span(0.2F, 0.8F),    // Continentalité : moyenne
            Climate.Parameter.span(0.1F, 0.7F),    // Érosion : vallonnée
            Climate.Parameter.point(0.0F),   // Weirdness : neutre
            Climate.Parameter.span(0.0F, 1.0F),    // Profondeur : standard
            0
    );
}
