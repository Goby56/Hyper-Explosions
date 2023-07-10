package com.alpha.hyperexplosions.config;

import com.alpha.hyperexplosions.HyperExplosions;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class HyperExplosionsConfig {

    public static final ConfigInstance<HyperExplosionsConfig> INSTANCE = new GsonConfigInstance<>(HyperExplosionsConfig.class, Path.of("./config/explosiveenhancement.json"));

    @ConfigEntry public boolean showBlastWave = true;
    @ConfigEntry public boolean showFireball = true;
    @ConfigEntry public boolean showMushroomCloud = true;
    @ConfigEntry public boolean showSparks = true;
    @ConfigEntry public float sparkSize = 5.3F;
    @ConfigEntry public float sparkOpacity = 0.70F;
    @ConfigEntry public boolean showDefaultExplosion = false;
    @ConfigEntry public boolean underwaterExplosions = true;
    @ConfigEntry public boolean showShockwave = true;
    @ConfigEntry public boolean showUnderwaterBlastWave = true;
    @ConfigEntry public int bubbleAmount = 50;
    @ConfigEntry public boolean showUnderwaterSparks = false;
    @ConfigEntry public float underwaterSparkSize = 4.0F;
    @ConfigEntry public float underwaterSparkOpacity = 0.30F;
    @ConfigEntry public boolean showDefaultExplosionUnderwater = false;
    @ConfigEntry public boolean modEnabled = true;
    @ConfigEntry public boolean debugLogs = false;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            //Default Explosion category
            var defaultCategoryBuilder = ConfigCategory.createBuilder()
                    .name(translatable("category.default"));

            //Explosion particles group
            var explosionGroup = OptionGroup.createBuilder()
                    .name(translatable("explosion.group"))
                    .tooltip(translatable("explosion.group.tooltip"));
            var showBlastWave = Option.createBuilder(boolean.class)
                    .name(translatable("blastwave.enabled"))
                    .tooltip(translatable("blastwave.enabled.tooltip"))
                    .binding(
                            defaults.showBlastWave,
                            () -> config.showBlastWave,
                            val -> config.showBlastWave = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var showFireball = Option.createBuilder(boolean.class)
                    .name(translatable("fireball.enabled"))
                    .tooltip(translatable("fireball.enabled.tooltip"))
                    .binding(
                            defaults.showFireball,
                            () -> config.showFireball,
                            val -> config.showFireball = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var showMushroomCloud = Option.createBuilder(boolean.class)
                    .name(translatable("mushroomcloud.enabled"))
                    .tooltip(translatable("mushroomcloud.enabled.tooltip"))
                    .binding(
                            defaults.showMushroomCloud,
                            () -> config.showMushroomCloud,
                            val -> config.showMushroomCloud = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var showSparks = Option.createBuilder(boolean.class)
                    .name(translatable("sparks.enabled"))
                    .tooltip(translatable("sparks.enabled.tooltip"))
                    .binding(
                            defaults.showSparks,
                            () -> config.showSparks,
                            val -> config.showSparks = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var sparkSize = Option.createBuilder(Float.class)
                    .name(translatable("sparks.size"))
                    .tooltip(translatable("sparks.size.tooltip"))
                    .binding(
                            defaults.sparkSize,
                            () -> config.sparkSize,
                            val -> config.sparkSize = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0F, 10F, 0.1F))
                    .build();
            var sparkOpacity = Option.createBuilder(Float.class)
                    .name(translatable("sparks.opacity"))
                    .tooltip(translatable("sparks.opacity.tooltip"))
                    .binding(
                            defaults.sparkOpacity,
                            () -> config.sparkOpacity,
                            val -> config.sparkOpacity = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0.00F, 1.00F, 0.05F))
                    .build();
            var showDefaultExplosion = Option.createBuilder(boolean.class)
                    .name(translatable("default.enabled"))
                    .tooltip(translatable("default.enabled.tooltip"))
                    .binding(
                            defaults.showDefaultExplosion,
                            () -> config.showDefaultExplosion,
                            val -> config.showDefaultExplosion = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            explosionGroup.option(showBlastWave);
            explosionGroup.option(showFireball);
            explosionGroup.option(showMushroomCloud);
            explosionGroup.option(showSparks);
            explosionGroup.option(sparkSize);
            explosionGroup.option(sparkOpacity);
            explosionGroup.option(showDefaultExplosion);
            defaultCategoryBuilder.group(explosionGroup.build());

            var underwaterGroup = OptionGroup.createBuilder()
                    .name(translatable("underwater.group"))
                    .tooltip(translatable("underwater.group.tooltip"));

            var underwaterExplosions = Option.createBuilder(boolean.class)
                    .name(translatable("underwater.enabled"))
                    .tooltip(translatable("underwater.enabled.tooltip"))
                    .binding(
                            defaults.underwaterExplosions,
                            () -> config.underwaterExplosions,
                            val -> config.underwaterExplosions = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var showShockwave = Option.createBuilder(boolean.class)
                    .name(translatable("underwater.shockwave"))
                    .tooltip(translatable("underwater.shockwave.tooltip"))
                    .binding(
                            defaults.showShockwave,
                            () -> config.showShockwave,
                            val -> config.showShockwave = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var showUnderwaterBlast = Option.createBuilder(boolean.class)
                    .name(translatable("underwater.blast"))
                    .tooltip(translatable("underwater.blast.tooltip"))
                    .binding(
                            defaults.showUnderwaterBlastWave,
                            () -> config.showUnderwaterBlastWave,
                            val -> config.showUnderwaterBlastWave = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var bubbleAmount = Option.createBuilder(Integer.class)
                    .name(translatable("underwater.bubbleamount"))
                    .tooltip(translatable("underwater.bubbleamount.tooltip"))
                    .tooltip(translatable("underwater.bubbleamount.warningtooltip"))
                    .binding(
                            defaults.bubbleAmount,
                            () -> config.bubbleAmount,
                            val -> config.bubbleAmount = val
                    )
                    .controller(integerOption -> new <Number>IntegerSliderController(integerOption, 0, 500, 5))
                    .build();
            var showUnderwaterSparks = Option.createBuilder(boolean.class)
                    .name(translatable("underwater.sparks"))
                    .tooltip(translatable("underwater.sparks.tooltip"))
                    .binding(
                            defaults.showUnderwaterSparks,
                            () -> config.showUnderwaterSparks,
                            val -> config.showUnderwaterSparks = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var underwaterSparkSize = Option.createBuilder(Float.class)
                    .name(translatable("underwater.sparks.size"))
                    .tooltip(translatable("underwater.sparks.size.tooltip"))
                    .binding(
                            defaults.underwaterSparkSize,
                            () -> config.underwaterSparkSize,
                            val -> config.underwaterSparkSize = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0F, 10F, 0.1F))
                    .build();
            var underwaterSparkOpacity = Option.createBuilder(Float.class)
                    .name(translatable("underwater.sparks.opacity"))
                    .tooltip(translatable("underwater.sparks.opacity.tooltip"))
                    .binding(
                            defaults.underwaterSparkOpacity,
                            () -> config.underwaterSparkOpacity,
                            val -> config.underwaterSparkOpacity = val
                    )
                    .controller(floatOption -> new <Number>FloatSliderController(floatOption, 0.00F, 1.00F, 0.05F))
                    .build();
            var showDefaultExplosionUnderwater = Option.createBuilder(boolean.class)
                    .name(translatable("underwater.default"))
                    .tooltip(translatable("underwater.default.tooltip"))
                    .binding(
                            defaults.showDefaultExplosionUnderwater,
                            () -> config.showDefaultExplosionUnderwater,
                            val -> config.showDefaultExplosionUnderwater = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();

            underwaterGroup.option(underwaterExplosions);
            underwaterGroup.option(showShockwave);
            underwaterGroup.option(showUnderwaterBlast);
            underwaterGroup.option(bubbleAmount);
            underwaterGroup.option(showUnderwaterSparks);
            underwaterGroup.option(underwaterSparkSize);
            underwaterGroup.option(underwaterSparkOpacity);
            underwaterGroup.option(showDefaultExplosionUnderwater);
            defaultCategoryBuilder.group(underwaterGroup.build());



            var extrasCategoryBuilder = ConfigCategory.createBuilder()
                    .name(translatable("category.extras"));

            var extrasGroup = OptionGroup.createBuilder()
                    .name(translatable("extras.group"))
                    .tooltip(translatable("extras.group.tooltip"));

            var modEnabled = Option.createBuilder(boolean.class)
                    .name(translatable("extras.enabled"))
                    .tooltip(translatable("extras.enabled.tooltip"))
                    .binding(
                            defaults.modEnabled,
                            () -> config.modEnabled,
                            val -> config.modEnabled = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            var debugLogs = Option.createBuilder(boolean.class)
                    .name(translatable("extras.logs"))
                    .tooltip(translatable("extras.logs.tooltip"))
                    .tooltip(translatable("extras.logs.warningtooltip"))
                    .binding(
                            defaults.debugLogs,
                            () -> config.debugLogs,
                            val -> config.debugLogs = val
                    )
                    .controller(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
            extrasGroup.option(modEnabled);
            extrasGroup.option(debugLogs);
            extrasCategoryBuilder.group(extrasGroup.build());

            return builder
                    .title(translatable("config.title"))
                    .category(defaultCategoryBuilder.build())
                    .category(extrasCategoryBuilder.build());
        }).generateScreen(parent);
    }

    public static MutableText translatable(String field) {
        return Text.translatable(String.format("%s.%s", HyperExplosions.MOD_ID, field));
    }

}
