package legal.nicolas.moonrift.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.FluidState;

public class MushmoonBlock extends MushroomBlock {

    public static final IntegerProperty MOON_PHASE = IntegerProperty.create("moon_phase", 0, 7);

    public MushmoonBlock(ResourceKey<ConfiguredFeature<?, ?>> feature, Properties properties) {
        super(feature, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MOON_PHASE, 0));
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return switch (state.getValue(MOON_PHASE)) {
            case 0 -> 12;
            case 1 -> 10;
            case 2 -> 8;
            case 3 -> 4;
            case 4 -> 2;
            case 5 -> 4;
            case 6 -> 8;
            case 7 -> 10;
            default -> 2;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOON_PHASE);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide) {
            int currentPhase = level.getMoonPhase();
            BlockState newState = state.setValue(MOON_PHASE, currentPhase);
            level.setBlock(pos, newState, 3);
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }
}
