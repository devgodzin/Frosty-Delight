package net.godzin.frostveil.item;

import net.godzin.frostveil.Frostveil;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Frostveil.MOD_ID);

    public static final DeferredItem<Item> FROZEN_HEART = ITEMS.register("frozen_heart",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
