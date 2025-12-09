package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.block.ModBlocks;
import legal.nicolas.moonrift.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
        dropSelf(ModBlocks.MUSHMOON.get());
        dropWhenSilkTouch(ModBlocks.LUNAR_MYCELIUM.get());

        add(ModBlocks.SILVERMOON_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.SILVERMOON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
        );

        add(ModBlocks.MOON_TOUCHED_STONE.get(),
                block -> createOreDrop(ModBlocks.MOON_TOUCHED_STONE.get(),
                        ModItems.MOONSHARD_DUST.get()));

        add(ModBlocks.MUSHMOON.get(), block ->
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(applyExplosionDecay(block,
                                        LootItem.lootTableItem(ModBlocks.MUSHMOON.get())
                                                .when(HAS_SHEARS.invert())
                                ))
                        )
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(applyExplosionDecay(block,
                                        LootItem.lootTableItem(ModItems.MUSHMOON_SPORES.get())
                                                .when(HAS_SHEARS)
                                ))
                        )
        );
    }
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
}
