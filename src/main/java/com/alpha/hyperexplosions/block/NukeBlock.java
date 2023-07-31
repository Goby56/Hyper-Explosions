package com.alpha.hyperexplosions.block;

import com.alpha.hyperexplosions.blockentity.NukeBlockEntity;
import com.alpha.hyperexplosions.property.ModProperties;
import com.alpha.hyperexplosions.property.NukePart;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class NukeBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final EnumProperty<Direction> FACING = Properties.FACING;
    public static final EnumProperty<NukePart> PART = ModProperties.NUKE_PART;

    public static final int[] FIN_HITBOX_SHAPE = new int[] {
            5, 8,
            11, 4,
    };
    public static final int[] HEAD_HITBOX_SHAPE = new int[] {
            2, 5,
            4, 5,
            4, 4,
            4, 3
    };

    public NukeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PART, NukePart.FIN));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.isOf(state.getBlock())) {
            return;
        }
        if (world.isReceivingRedstonePower(pos)) {
            NukeBlock.primeNuke(world, pos);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            NukeBlock.primeNuke(world, pos);
            world.removeBlock(pos, false);
        }
    }

    public static void primeNuke(World world, BlockPos pos) {
        NukeBlock.primeNuke(world, pos, null);
    }

    public static void primeNuke(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (world.isClient) {
            return;
        }
        TntEntity tntEntity = new TntEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, igniter);
        world.spawnEntity(tntEntity);
        world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
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

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        int dx = facing.getOffsetX(), dy = facing.getOffsetY(), dz = facing.getOffsetZ();
        VoxelShape shape = VoxelShapes.empty();
        int[] hitbox = getHitboxShape(state.get(PART));
        int o = 0;
        int t;
        int w;
        int[] c0 = new int[3];
        int[] c1 = new int[3];
        for (int i = 0; i < (hitbox.length >> 1); i++) {
            t = o + hitbox[i*2];
            w = 8 - hitbox[i*2+1];

            c0[0] = 16 - t;
            c0[1] = w;
            c0[2] = o;

            c1[0] = 16 - o;
            c1[1] = 16 - w;
            c1[2] = t;

            shape = VoxelShapes.union(shape, Block.createCuboidShape(
                    c0[dx+1], c0[dy+1], c0[dz+1],
                    c1[dx+1], c1[dy+1], c1[dz+1]
            ));

            o = t;
        }
        // TODO RETURN ENTIRE HITBOX (OTHER PART TOO)
        return shape;
    }

    private static int[] getHitboxShape(NukePart part) {
        if (part == NukePart.HEAD) return HEAD_HITBOX_SHAPE;
        return FIN_HITBOX_SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NukeBlockEntity(pos, state);
    }
}
