package org.hiedacamellia.badapple.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue enableBadAppleRenderer = BUILDER
            .comment("Enable the Bad Apple Renderer")
            .define("enableBadAppleRenderer", false);

    public static final ModConfigSpec.DoubleValue badAppleContrast = BUILDER
            .comment("Contrast of the Bad Apple Renderer")
            .defineInRange("badAppleContrast", 1.0, 0.5, 10.0);

    public static final ModConfigSpec.BooleanValue reverseColor = BUILDER
            .comment("Reverse the color")
            .define("reverseColor", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
