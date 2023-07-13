package com.alpha.hyperexplosions;

import com.alpha.hyperexplosions.blockentity.NukeBlockEntity;
import com.alpha.hyperexplosions.config.HyperExplosionsConfig;
import com.alpha.hyperexplosions.particles.*;
import com.alpha.hyperexplosions.registry.BlockEntityRegistry;
import com.alpha.hyperexplosions.registry.ParticleRegistry;
import com.alpha.hyperexplosions.render.NukeBlockEntityRenderer;
import com.alpha.hyperexplosions.render.model.NukeModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.impl.client.rendering.BlockEntityRendererRegistryImpl;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class HyperExplosionsClient implements ClientModInitializer {
    public static NukeModel NUKE = new NukeModel();

    @Override
    public void onInitializeClient() {
        //Loads the config, powered by YACL
        HyperExplosionsConfig.INSTANCE.load();

        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.BLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.BLANK_FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SMOKE, SmokeParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SPARKS, SparkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.BUBBLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.BLANK_SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.UNDERWATERBLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.UNDERWATERSPARKS, UnderwaterSparkParticle.Factory::new);

        BlockEntityRendererFactories.register(BlockEntityRegistry.NUKE_BLOCK_ENTITY, NukeBlockEntityRenderer::new);
    }
}
