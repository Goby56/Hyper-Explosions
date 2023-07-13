package com.alpha.hyperexplosions.blockentity;

import com.alpha.hyperexplosions.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class NukeBlockEntity extends BlockEntity {
    public NukeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.NUKE_BLOCK_ENTITY, pos, state);
    }

}
