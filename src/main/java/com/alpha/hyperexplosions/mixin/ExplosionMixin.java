package com.alpha.hyperexplosions.mixin;

import com.alpha.hyperexplosions.particles.ModParticles;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import com.alpha.hyperexplosions.HyperExplosions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.alpha.hyperexplosions.config.HyperExplosionsConfig.INSTANCE;
import static com.alpha.hyperexplosions.HyperExplosions.LOGGER;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {
	@Shadow @Final private Random random;
	private boolean isUnderWater = false;

	@Redirect(method = "affectWorld(Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
	public void affectWorld(World world, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		if(INSTANCE.getConfig().debugLogs) {
			LOGGER.info("affectWorld has been called!");
		}
		BlockPos pos = new BlockPos(x, y, z);
		if(world.getFluidState(pos).isIn(FluidTags.WATER) && INSTANCE.getConfig().underwaterExplosions) {
			//If underwater
			isUnderWater = true;
			if(INSTANCE.getConfig().debugLogs) {
				LOGGER.info("Particle is underwater!");
			}
		}
		if(INSTANCE.getConfig().modEnabled) {
			if(!isUnderWater) {
				if(INSTANCE.getConfig().debugLogs) {
					LOGGER.info("Particle is being shown!");
				}
				if(INSTANCE.getConfig().showBlastWave) {
					world.addParticle(ModParticles.BLASTWAVE, x, y, z, 0, 0, 0);
				}
				if(INSTANCE.getConfig().showFireball) {
					world.addParticle(ModParticles.FIREBALL, x, y + 0.5, z, 0, 0, 0);
				} else if (INSTANCE.getConfig().showSparks) {
					world.addParticle(ModParticles.BLANK_FIREBALL, x, y + 0.5, z, 0, 0, 0);
				}
				if(INSTANCE.getConfig().showMushroomCloud) {
					//I'm aware DRY is a thing, but I couldn't figure out any other way to get even a similar effect that I was happy with, so unfortunately, this will have to do.
					world.addParticle(ModParticles.SMOKE, x, y, z, 0, 0.15, 0);
					world.addParticle(ModParticles.SMOKE, x, y, z, 0, 0.4, 0);
					world.addParticle(ModParticles.SMOKE, x, y, z, 0.15, 0.4, 0);
					world.addParticle(ModParticles.SMOKE, x, y, z, 0, 0.4, 0.15);
					world.addParticle(ModParticles.SMOKE, x, y, z, -0.15, 0.4, 0);
					world.addParticle(ModParticles.SMOKE, x, y, z, 0, 0.4, -0.15);
				}
				if(INSTANCE.getConfig().showDefaultExplosion) {
					world.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1.0, 0.0, 0.0);
				}
			} else {
				if(INSTANCE.getConfig().showShockwave) {
					world.addParticle(ModParticles.SHOCKWAVE, x, y + 0.5, z, 0, 0, 0);
				} else if (INSTANCE.getConfig().showUnderwaterSparks) {
					world.addParticle(ModParticles.BLANK_SHOCKWAVE, x, y + 0.5, z, 0, 0, 0);
				}
				if(INSTANCE.getConfig().showUnderwaterBlastWave) {
					world.addParticle(ModParticles.UNDERWATERBLASTWAVE, x, y + 0.5, z, 0, 0, 0);
				}
				for(int total = INSTANCE.getConfig().bubbleAmount; total >= 1; total--) {
					world.addParticle(ModParticles.BUBBLE, x, y, z, this.random.nextBetween(1, 7) * 0.3 * this.random.nextBetween(-1, 1), this.random.nextBetween(1, 10) * 0.1, this.random.nextBetween(1, 7) * 0.3 * this.random.nextBetween(-1, 1));
				}
				if(INSTANCE.getConfig().showDefaultExplosionUnderwater) {
					world.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1.0, 0.0, 0.0);
				}
			}
		} else {
			world.addParticle(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1.0, 0.0, 0.0);
		}
	}
}