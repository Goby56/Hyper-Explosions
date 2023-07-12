package com.alpha.hyperexplosions;

import com.alpha.hyperexplosions.particles.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;

public class HyperExplosions implements ModInitializer {
	public static final String MOD_ID = "hyperexplosions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModParticles.registerParticles();

		System.out.println(HyperExplosions.getAssetsDir());
		HyperExplosions.LOGGER.debug(HyperExplosions.getAssetsDir().toString());
	}

	public static Path getAssetsDir() {
		return FabricLoader.getInstance().getModContainer(MOD_ID).get().findPath("assets").get().resolve(MOD_ID);
	}

}
