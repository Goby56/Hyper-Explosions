package com.alpha.hyperexplosions.render;

import com.alpha.hyperexplosions.HyperExplosionsClient;
import com.alpha.hyperexplosions.block.NukeBlock;
import com.alpha.hyperexplosions.blockentity.NukeBlockEntity;
import com.alpha.hyperexplosions.property.NukePart;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public class NukeBlockEntityRenderer implements BlockEntityRenderer<NukeBlockEntity> {

    public NukeBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(NukeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.getCachedState().get(NukeBlock.PART) != NukePart.FIN) {
            return;
        }
        RenderSystem.enableDepthTest();

        Direction facing = entity.getCachedState().get(NukeBlock.FACING);
        int dx = facing.getOffsetX(), dy = facing.getOffsetY(), dz = facing.getOffsetZ();
        Vec3d pos = entity.getPos().toCenterPos();
        Vec3d cameraOffset = MinecraftClient.getInstance().gameRenderer.getCamera().getPos().subtract(pos);

        matrices.push();
        matrices.translate(0.5 + 0.48 * dx, 0.5 + 0.48 * dy, 0.5 + 0.48 * dz);
        matrices.scale(0.51f + 0.143f * Math.abs(dx), 0.51f + 0.143f * Math.abs(dy), 0.51f + 0.143f * Math.abs(dz));
        matrices.multiply(getRotation(facing));
        matrices.translate(cameraOffset.x, cameraOffset.y, cameraOffset.z);

        HyperExplosionsClient.NUKE.render(matrices, pos);
        matrices.pop();
    }

    public static Quaternionf getRotation(Direction direction) {
        return switch (direction) {
            case UP -> RotationAxis.NEGATIVE_Z.rotationDegrees(90);
            case DOWN -> RotationAxis.POSITIVE_Z.rotationDegrees(90);
            case NORTH -> RotationAxis.NEGATIVE_Y.rotationDegrees(90);
            case SOUTH -> RotationAxis.POSITIVE_Y.rotationDegrees(90);
            case EAST -> RotationAxis.POSITIVE_Y.rotationDegrees(180);
            case WEST -> RotationAxis.POSITIVE_Y.rotationDegrees(0);
        };
    }
}
