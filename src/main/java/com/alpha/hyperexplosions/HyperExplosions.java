package com.alpha.hyperexplosions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HyperExplosions implements ModInitializer {
	public static final String MOD_ID = "hyperexplosions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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

	@Override
	public void onInitialize() {
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "blastwave"), BLASTWAVE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "fireball"), FIREBALL);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "blank_fireball"), BLANK_FIREBALL);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "smoke"), SMOKE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "bubble"), BUBBLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "shockwave"), SHOCKWAVE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "blank_shockwave"), BLANK_SHOCKWAVE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "underwaterblastwave"), UNDERWATERBLASTWAVE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "sparks"), SPARKS);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "underwatersparks"), UNDERWATERSPARKS);
	}

}
