package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.block.ModBlocks;
import legal.nicolas.moonrift.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SILVERMOON_LOG.get());
        dropSelf(ModBlocks.SILVERMOON_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_SILVERMOON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SILVERMOON_WOOD.get());
        dropSelf(ModBlocks.SILVERMOON_PLANKS.get());
        dropSelf(ModBlocks.SILVERMOON_SAPLING.get());
        dropSelf(ModBlocks.LUNAR_LENS.get());

        this.add(ModBlocks.SILVERMOON_LEAVES.get(), block ->
            createLeavesDrops(block, ModBlocks.SILVERMOON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
        );

        add(ModBlocks.MOON_TOUCHED_STONE.get(),
                block -> createOreDrop(ModBlocks.MOON_TOUCHED_STONE.get(),
                        ModItems.MOONSHARD_DUST.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
