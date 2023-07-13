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
        matrices.push();
        matrices.multiply(getRotation(entity.getCachedState().get(NukeBlock.FACING)));
        RenderSystem.enableDepthTest();
        if (entity.getCachedState().get(NukeBlock.PART) == NukePart.FIN) {
            HyperExplosionsClient.NUKE.render(matrices, entity.getPos().toCenterPos());
        }
        matrices.pop();
    }

    public static Quaternionf getRotation(Direction direction) {
        Quaternionf result;
        switch (direction) {
            case UP:
                result = RotationAxis.NEGATIVE_Z.rotationDegrees(90);
                break;
            case DOWN:
                result = RotationAxis.POSITIVE_Z.rotationDegrees(90);
                break;
            case NORTH:
                result = RotationAxis.NEGATIVE_Y.rotationDegrees(90);
                break;
            case SOUTH:
                result = RotationAxis.POSITIVE_Y.rotationDegrees(90);
                break;
            case EAST:
                result = RotationAxis.POSITIVE_Y.rotationDegrees(180);
                break;
            case WEST:
            default:
                result = RotationAxis.POSITIVE_Y.rotationDegrees(0);
                break;
        }
        return result;
    }
}
