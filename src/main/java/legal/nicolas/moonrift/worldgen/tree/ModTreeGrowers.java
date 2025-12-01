package legal.nicolas.moonrift.worldgen.tree;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SILVERMOON = new TreeGrower(Moonrift.MOD_ID + ":silvermoon",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SILVERMOON_KEY), Optional.empty());
}
