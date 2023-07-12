package com.alpha.hyperexplosions.render;

import com.alpha.hyperexplosions.HyperExplosions;
import com.alpha.hyperexplosions.HyperExplosionsClient;
import com.alpha.hyperexplosions.render.model.NukeModel;
import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.render.Renderer3d;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.util.math.Vec3d;

public class NukeRenderer implements WorldRenderEvents.AfterEntities {
    @Override
    public void afterEntities(WorldRenderContext context) {
        RenderSystem.enableDepthTest();
        HyperExplosionsClient.NUKE.render(context.matrixStack(), new Vec3d(0, 100, 0));
    }
}
