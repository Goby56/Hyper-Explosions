package com.alpha.hyperexplosions;

import com.alpha.hyperexplosions.registry.BlockEntityRegistry;
import com.alpha.hyperexplosions.registry.ParticleRegistry;
import com.alpha.hyperexplosions.registry.BlockRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class HyperExplosions implements ModInitializer {
	public static final String MOD_ID = "hyperexplosions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BlockRegistry.registerBlocks();
		BlockEntityRegistry.registerBlockEntities();
		ParticleRegistry.registerParticles();

		System.out.println(HyperExplosions.getAssetsDir());
		HyperExplosions.LOGGER.debug(HyperExplosions.getAssetsDir().toString());
	}

	public static Path getAssetsDir() {
		return FabricLoader.getInstance().getModContainer(MOD_ID).get().findPath("assets").get().resolve(MOD_ID);
	}

}
