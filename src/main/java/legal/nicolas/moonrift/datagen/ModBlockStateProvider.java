package legal.nicolas.moonrift.datagen;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import legal.nicolas.moonrift.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Moonrift.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LUNAR_LENS);
        blockWithItem(ModBlocks.MOON_TOUCHED_STONE);

        logBlock((RotatedPillarBlock) ModBlocks.SILVERMOON_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.SILVERMOON_WOOD.get(), blockTexture(ModBlocks.SILVERMOON_LOG.get()), blockTexture(ModBlocks.SILVERMOON_LOG.get()));

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SILVERMOON_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SILVERMOON_WOOD.get(), blockTexture(ModBlocks.STRIPPED_SILVERMOON_LOG.get()), blockTexture(ModBlocks.STRIPPED_SILVERMOON_LOG.get()));

        blockItem(ModBlocks.SILVERMOON_LOG);
        blockItem(ModBlocks.SILVERMOON_WOOD);
        blockItem(ModBlocks.STRIPPED_SILVERMOON_LOG);
        blockItem(ModBlocks.STRIPPED_SILVERMOON_WOOD);

        blockWithItem(ModBlocks.SILVERMOON_PLANKS);

        leavesBlock(ModBlocks.SILVERMOON_LEAVES);
        saplingBlock(ModBlocks.SILVERMOON_SAPLING);

        mushroomBlock(ModBlocks.MUSHMOON);
        grassLikeBlock(ModBlocks.LUNAR_MYCELIUM);

    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("moonrift:block/" + deferredBlock.getId().getPath()));
    }

    private void mushroomBlock(DeferredBlock<Block> block) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        simpleBlock(
                block.get(),
                models().singleTexture(
                        name,
                        ResourceLocation.parse("minecraft:block/cross"),
                        "cross",
                        modLoc("block/" + name)
                ).renderType("cutout")
        );
    }

    private void grassLikeBlock(DeferredBlock<Block> block) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        ResourceLocation top = modLoc("block/" + name + "_top");
        ResourceLocation side = modLoc("block/" + name + "_side");
        ResourceLocation bottom = ResourceLocation.parse("minecraft:block/dirt"); // ou ton fond à toi

        ModelFile model = models().cubeBottomTop(
                name,
                side,
                bottom,
                top
        );

        simpleBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }


}
