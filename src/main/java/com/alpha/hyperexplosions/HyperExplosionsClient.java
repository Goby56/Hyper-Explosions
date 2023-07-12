package com.alpha.hyperexplosions;

import com.alpha.hyperexplosions.config.HyperExplosionsConfig;
import com.alpha.hyperexplosions.particles.ModParticles;
import com.alpha.hyperexplosions.particles.custom.*;
import com.alpha.hyperexplosions.render.NukeRenderer;
import com.alpha.hyperexplosions.render.model.NukeModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class HyperExplosionsClient implements ClientModInitializer {
    public static NukeModel NUKE = new NukeModel();

    @Override
    public void onInitializeClient() {
        //Loads the config, powered by YACL
        HyperExplosionsConfig.INSTANCE.load();

        ParticleFactoryRegistry.getInstance().register(ModParticles.BLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLANK_FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SMOKE, SmokeParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKS, SparkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BUBBLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLANK_SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.UNDERWATERBLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.UNDERWATERSPARKS, UnderwaterSparkParticle.Factory::new);

        WorldRenderEvents.AFTER_ENTITIES.register(new NukeRenderer());
    }
}
