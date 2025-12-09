package legal.nicolas.moonrift.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.neoforged.neoforge.common.util.TriState;

public class LunarMycelium extends MyceliumBlock {
    public static final MapCodec<net.minecraft.world.level.block.MyceliumBlock> CODEC = simpleCodec(net.minecraft.world.level.block.MyceliumBlock::new);

    public LunarMycelium(Properties properties) {
        super(properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && (Integer)blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(levelReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(levelReader, blockpos));
            return i < levelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rand) {
        super.animateTick(blockState, level, pos, rand);
        if (rand.nextInt(10) == 0) {
            level.addParticle(
                    ParticleTypes.MYCELIUM,
                    (double)pos.getX() + rand.nextDouble(),
                    (double)pos.getY() + 1.1,
                    (double)pos.getZ() + rand.nextDouble(),
                    0.0,
                    0.0,
                    0.0
            );
        }
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        return TriState.TRUE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) return;
            level.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3)) return;

            // Propagates only at night
            if (level.getMaxLocalRawBrightness(pos.above()) <= 8) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1,
                            random.nextInt(5) - 3,
                            random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).is(Blocks.DIRT) &&
                            canPropagate(blockstate, level, blockpos)) {
                        level.setBlockAndUpdate(blockpos,
                                (BlockState)blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW))
                        );
                    }
                }
            }
        }
    }
}