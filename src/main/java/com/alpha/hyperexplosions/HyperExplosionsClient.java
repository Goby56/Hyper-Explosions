package com.alpha.hyperexplosions;

import com.alpha.hyperexplosions.config.HyperExplosionsConfig;
import com.alpha.hyperexplosions.particles.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import com.alpha.hyperexplosions.particles.*;

public class HyperExplosionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //Loads the config, powered by YACL
        HyperExplosionsConfig.INSTANCE.load();

        ParticleFactoryRegistry.getInstance().register(HyperExplosions.BLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.BLANK_FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.SMOKE, SmokeParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.SPARKS, SparkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.BUBBLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.BLANK_SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.UNDERWATERBLASTWAVE, BlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(HyperExplosions.UNDERWATERSPARKS, UnderwaterSparkParticle.Factory::new);
    }
}
