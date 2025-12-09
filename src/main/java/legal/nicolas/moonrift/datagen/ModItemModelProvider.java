package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import legal.nicolas.moonrift.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Moonrift.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CRYSTALUNE.get());
        basicItem(ModItems.MOONSHARD_DUST.get());
        basicItem(ModItems.MOONSHARD.get());
        basicItem(ModItems.LENS.get());
        basicItem(ModItems.LUNINGOT.get());
        basicItem(ModItems.MUSHMOON_SPORES.get());
        saplingItem(ModBlocks.SILVERMOON_SAPLING);
        saplingItem(ModBlocks.MUSHMOON);
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Moonrift.MOD_ID, "block/" + item.getId().getPath()));
    }
}
