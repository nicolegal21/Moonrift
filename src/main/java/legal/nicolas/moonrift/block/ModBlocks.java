package legal.nicolas.moonrift.block;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.custom.LunarMycelium;
import legal.nicolas.moonrift.block.custom.ModFlammableRotatedPillarBlock;
import legal.nicolas.moonrift.block.custom.MushmoonBlock;
import legal.nicolas.moonrift.item.ModItems;
import legal.nicolas.moonrift.worldgen.ModConfiguredFeatures;
import legal.nicolas.moonrift.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Moonrift.MOD_ID);

    public static final DeferredBlock<Block> SILVERMOON_LOG = registerBlock("silvermoon_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG))
    );
    public static final DeferredBlock<Block> SILVERMOON_WOOD = registerBlock("silvermoon_wood",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))
        );
    public static final DeferredBlock<Block> STRIPPED_SILVERMOON_LOG = registerBlock("stripped_silvermoon_log",
                () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))
        );
    public static final DeferredBlock<Block> STRIPPED_SILVERMOON_WOOD = registerBlock("stripped_silvermoon_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
    );

    public static final DeferredBlock<Block> SILVERMOON_PLANKS = registerBlock(
            "silvermoon_planks",
            () -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }
    );

    public static final DeferredBlock<Block> SILVERMOON_LEAVES = registerBlock("silvermoon_leaves",
            () -> new LeavesBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            }
    );

    public static final DeferredBlock<Block> SILVERMOON_SAPLING = registerBlock("silvermoon_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SILVERMOON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))
    );

    public static final DeferredBlock<Block> MOON_TOUCHED_STONE = registerBlock("moon_touched_stone",
            () -> new DropExperienceBlock(
                    ConstantInt.of(0),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
            )
    );

    public static final DeferredBlock<Block> LUNAR_LENS = registerBlock(
            "lunar_lens",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
                            .ignitedByLava()
            )
    );

    public static final DeferredBlock<Block> MUSHMOON = registerBlock(
            "mushmoon",
            () -> new MushmoonBlock(
                    ModConfiguredFeatures.HUGE_MUSHMOON_KEY,
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .instabreak()
                            .noOcclusion()
                            .sound(SoundType.FUNGUS)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .lightLevel(state -> 1)
            )
    );

    public static final DeferredBlock<Block> LUNAR_MYCELIUM = registerBlock(
            "lunar_mycelium",
            () -> new LunarMycelium(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MYCELIUM)
            )
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
