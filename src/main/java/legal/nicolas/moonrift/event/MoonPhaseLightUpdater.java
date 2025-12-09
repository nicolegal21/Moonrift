package legal.nicolas.moonrift.event;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import legal.nicolas.moonrift.block.custom.MushmoonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Moonrift.MOD_ID)
public class MoonPhaseLightUpdater {

    private static int lastPhase = -1;
    private static final int CHUNK_RADIUS = 12; // rayon de chunks autour des joueurs à mettre à jour

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        ServerLevel level = event.getServer().getLevel(Level.OVERWORLD);
        if (level == null) return;

        int currentPhase = level.getMoonPhase();
        if (currentPhase != lastPhase) {
            lastPhase = currentPhase;

            level.players().forEach(player -> {
                int playerChunkX = player.blockPosition().getX() >> 4;
                int playerChunkZ = player.blockPosition().getZ() >> 4;

                for (int dx = -CHUNK_RADIUS; dx <= CHUNK_RADIUS; dx++) {
                    for (int dz = -CHUNK_RADIUS; dz <= CHUNK_RADIUS; dz++) {
                        LevelChunk chunk = level.getChunk(playerChunkX + dx, playerChunkZ + dz);
                        updateMoonphase(chunk, level, currentPhase);
                    }
                }
            });
        }
    }

    private static void updateMoonphase(LevelChunk chunk, ServerLevel level, int currentPhase) {
        int startX = chunk.getPos().getMinBlockX();
        int startZ = chunk.getPos().getMinBlockZ();

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < level.getMaxBuildHeight(); y++) {
                for (int z = 0; z < 16; z++) {
                    BlockPos pos = new BlockPos(startX + x, y, startZ + z);
                    BlockState state = level.getBlockState(pos);
                    Block block = state.getBlock();

                    if (block instanceof MushmoonBlock) {
                        BlockState newState = state.setValue(MushmoonBlock.MOON_PHASE, currentPhase);
                        level.setBlock(pos, newState, 3);
                        level.getChunkSource().getLightEngine().checkBlock(pos);
                    }
                }
            }
        }
    }


}
