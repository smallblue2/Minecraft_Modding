package net.smallblue2.firstmod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smallblue2.firstmod.FirstMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRSTMOD_TAB)));

    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRSTMOD_TAB)));

    // Apache Garlic Dip!
    private static final FoodProperties APACHE_GARLIC_DIP_FOOD = new FoodProperties.Builder().nutrition(12).saturationMod(1.2F).build();
    public static final RegistryObject<Item> APACHE_GARLIC_DIP = ITEMS.register("apache_garlic_dip",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FIRSTMOD_TAB).food(APACHE_GARLIC_DIP_FOOD)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
