package com.alpha.hyperexplosions.particles;

import com.alpha.hyperexplosions.HyperExplosions;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType BLASTWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType FIREBALL = FabricParticleTypes.simple();
    public static final DefaultParticleType BLANK_FIREBALL = FabricParticleTypes.simple();
    public static final DefaultParticleType SMOKE = FabricParticleTypes.simple();
    public static final DefaultParticleType SPARKS = FabricParticleTypes.simple();
    public static final DefaultParticleType BUBBLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SHOCKWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLANK_SHOCKWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType UNDERWATERBLASTWAVE = FabricParticleTypes.simple();
    public static final DefaultParticleType UNDERWATERSPARKS = FabricParticleTypes.simple();

    private static void register(String name, DefaultParticleType particle) {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(HyperExplosions.MOD_ID, name), particle);
    }

    public static void registerParticles() {
        register("blastwave", BLASTWAVE);
        register("fireball", FIREBALL);
        register("blank_fireball", BLANK_FIREBALL);
        register("smoke", SMOKE);
        register("bubble", BUBBLE);
        register("shockwave", SHOCKWAVE);
        register("blank_shockwave", BLANK_SHOCKWAVE);
        register("underwaterblastwave", UNDERWATERBLASTWAVE);
        register("sparks", SPARKS);
        register("underwatersparks", UNDERWATERSPARKS);
    }
}
