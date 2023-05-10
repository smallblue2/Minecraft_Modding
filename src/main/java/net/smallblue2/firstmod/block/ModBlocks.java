package net.smallblue2.firstmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smallblue2.firstmod.FirstMod;
import net.smallblue2.firstmod.block.advanced.JumpyBlock;
import net.smallblue2.firstmod.item.ModCreativeModeTab;
import net.smallblue2.firstmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    // Helper method to register a block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    // Helper method to register a block's item
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    // Zircon Block
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.FIRSTMOD_TAB);

    // Zircon Ore Block
    public static final RegistryObject<Block> ZIRCON_ORE_BLOCK = registerBlock("zircon_ore_block",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.FIRSTMOD_TAB);

    // Deepslate Zircon Ore Block
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE_BLOCK = registerBlock("deepslate_zircon_ore_block",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(4, 8)), ModCreativeModeTab.FIRSTMOD_TAB);

    public static final RegistryObject<JumpyBlock> JUMPY_BLOCK = registerBlock("jumpy_block",
            () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2f)), ModCreativeModeTab.FIRSTMOD_TAB);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
