package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output,
                lookupProvider,
                blockTags,
                Moonrift.MOD_ID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SILVERMOON_LOG.get().asItem())
                .add(ModBlocks.SILVERMOON_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SILVERMOON_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SILVERMOON_WOOD.get().asItem());


        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.SILVERMOON_PLANKS.asItem());
    }

}
