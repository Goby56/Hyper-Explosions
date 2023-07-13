package com.alpha.hyperexplosions.registry;

import com.alpha.hyperexplosions.HyperExplosions;
import com.alpha.hyperexplosions.block.NukeBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegistry {
    public static final Block NUKE_BLOCK = registerBlock("nuke_block",
            new NukeBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ItemGroups.REDSTONE, ItemGroups.COMBAT);

    private static Block registerBlock(String name, Block block, ItemGroup... itemGroups) {
        registerBlockItem(name, block, itemGroups);
        return Registry.register(Registries.BLOCK, new Identifier(HyperExplosions.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup... itemGroups) {
        Item item = Registry.register(Registries.ITEM, new Identifier(HyperExplosions.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        for (ItemGroup group : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        }
    }

    public static void registerBlocks() {
    }
}
