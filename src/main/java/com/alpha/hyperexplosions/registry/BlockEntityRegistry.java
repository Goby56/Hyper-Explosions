package com.alpha.hyperexplosions.registry;

import com.alpha.hyperexplosions.HyperExplosions;
import com.alpha.hyperexplosions.blockentity.NukeBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegistry {

    public static BlockEntityType<NukeBlockEntity> NUKE_BLOCK_ENTITY = register("nuke_block_entity", NukeBlockEntity::new, BlockRegistry.NUKE_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<T> entityReference, Block block) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(HyperExplosions.MOD_ID, name),
                FabricBlockEntityTypeBuilder.create(entityReference, block).build());

    }

    public static void registerBlockEntities() {
    }
}
