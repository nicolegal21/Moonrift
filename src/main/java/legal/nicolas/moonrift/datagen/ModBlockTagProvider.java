package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Moonrift.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MOON_TOUCHED_STONE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MOON_TOUCHED_STONE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SILVERMOON_LOG.get())
                .add(ModBlocks.SILVERMOON_WOOD.get())
                .add(ModBlocks.STRIPPED_SILVERMOON_LOG.get())
                .add(ModBlocks.STRIPPED_SILVERMOON_WOOD.get());

        tag(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(ModBlocks.LUNAR_MYCELIUM.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.LUNAR_MYCELIUM.get());
    }
}