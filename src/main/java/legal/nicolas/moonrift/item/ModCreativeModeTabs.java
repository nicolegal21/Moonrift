package legal.nicolas.moonrift.item;

import legal.nicolas.moonrift.Moonrift;
import legal.nicolas.moonrift.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Moonrift.MOD_ID);

    public static final Supplier<CreativeModeTab> MOONRIFT_TAB = CREATIVE_MODE_TAB.register("moonrift_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CRYSTALUNE.get()))
                    .title(Component.translatable("creativetab.moonrift.moonrift"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CRYSTALUNE);
                        output.accept(ModItems.LUNINGOT);
                        output.accept(ModItems.LENS);

                        output.accept(ModBlocks.SILVERMOON_LOG);
                        output.accept(ModBlocks.STRIPPED_SILVERMOON_LOG);
                        output.accept(ModBlocks.SILVERMOON_WOOD);
                        output.accept(ModBlocks.STRIPPED_SILVERMOON_WOOD);
                        output.accept(ModBlocks.SILVERMOON_PLANKS);
                        output.accept(ModBlocks.SILVERMOON_LEAVES);
                        output.accept(ModBlocks.SILVERMOON_SAPLING);

                        output.accept(ModBlocks.MOON_TOUCHED_STONE);
                        output.accept(ModItems.MOONSHARD_DUST);
                        output.accept(ModItems.MOONSHARD);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
