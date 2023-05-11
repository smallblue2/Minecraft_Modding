package net.smallblue2.firstmod.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smallblue2.firstmod.FirstMod;

public class ModPaintings {
    // Deferred Registerer
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, FirstMod.MOD_ID);

    // Registering my different paintings
    public static final RegistryObject<PaintingVariant> PLANT = PAINTING_VARIANTS.register("plant",
            () -> new PaintingVariant(16, 16)); // width, height
    public static final RegistryObject<PaintingVariant> WANDERER = PAINTING_VARIANTS.register("wanderer",
            () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> SUNSET = PAINTING_VARIANTS.register("sunset",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> GARLIC = PAINTING_VARIANTS.register("garlic",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> RUFUS0 = PAINTING_VARIANTS.register("rufus0",
            () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> RUFUS1 = PAINTING_VARIANTS.register("rufus1",
            () -> new PaintingVariant(64, 64));

    // Register method
    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}