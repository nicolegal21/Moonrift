package legal.nicolas.moonrift.item;

import legal.nicolas.moonrift.Moonrift;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Moonrift.MOD_ID);

    public static final DeferredItem<Item> CRYSTALUNE = ITEMS.register("crystalune",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LUNINGOT = ITEMS.register("luningot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LENS = ITEMS.register("lens",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOONSHARD_DUST = ITEMS.register("moonshard_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOONSHARD = ITEMS.register("moonshard",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MUSHMOON_SPORES = ITEMS.register("mushmoon_spores",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
