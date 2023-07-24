package com.alpha.hyperexplosions.block;

import com.alpha.hyperexplosions.blockentity.NukeBlockEntity;
import com.alpha.hyperexplosions.property.ModProperties;
import com.alpha.hyperexplosions.property.NukePart;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class NukeBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final EnumProperty<Direction> FACING = Properties.FACING;
    public static final EnumProperty<NukePart> PART = ModProperties.NUKE_PART;

    public NukeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PART, NukePart.FIN));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
        BlockPos thisPartPos = ctx.getBlockPos();
        BlockPos otherPartPos = thisPartPos.offset(direction);
        World world = ctx.getWorld();
        if (world.getBlockState(otherPartPos).canReplace(ctx) && world.getWorldBorder().contains(otherPartPos)) {
            return this.getDefaultState().with(FACING, direction);
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == NukeBlock.getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
            if (neighborState.isOf(this) && neighborState.get(PART) != state.get(PART)) {
                return state;
            }
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private static Direction getDirectionTowardsOtherPart(NukePart part, Direction direction) {
        return part == NukePart.FIN ? direction : direction.getOpposite();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            NukePart nukePart = state.get(PART);
            if (nukePart == NukePart.HEAD) {
                BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(nukePart, state.get(FACING)));
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isOf(this) && blockState.get(PART) == NukePart.HEAD) {
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                    world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
                }
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos blockPos = pos.offset(state.get(FACING));
            world.setBlockState(blockPos, state.with(PART, NukePart.HEAD), Block.NOTIFY_ALL);
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
        }
    }

    @Override
    public long getRenderingSeed(BlockState state, BlockPos pos) {
        BlockPos blockPos = pos.offset(state.get(FACING), state.get(PART) == NukePart.HEAD ? 0 : 1);
        return MathHelper.hashCode(blockPos.getX(), pos.getY(), blockPos.getZ());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(PART);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

//    @Override
//    protected ImmutableMap<BlockState, VoxelShape> getShapesForStates(Function<BlockState, VoxelShape> stateToShape) {
//        // TODO FIX OUTLINE SHAPE
//        return super.getShapesForStates(stateToShape);
//    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//        Direction direction = NukeBlock.getOppositePartDirection(state);
//        int dx = direction.getOffsetX() * 16, dy = direction.getOffsetY() * 16, dz = direction.getOffsetZ() * 16;
//        return Block.createCuboidShape(dx, dy, dz, 16 + dx, 16 + dy, 16 + dz);
//    }

    public static Direction getOppositePartDirection(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(PART) == NukePart.HEAD ? direction.getOpposite() : direction;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NukeBlockEntity(pos, state);
    }
}
