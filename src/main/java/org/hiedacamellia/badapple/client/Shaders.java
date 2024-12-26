/*
 * Code from https://github.com/LouisQuepierts/ThatSkyInteractions
 * net.quepierts.thatskyinteractions.client.registry
 * Shaders.java
 *
 * This code is under the MIT License.
 */


package org.hiedacamellia.badapple.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import org.hiedacamellia.badapple.Badapple;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT, modid = Badapple.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Shaders {
    @Nullable
    private static ShaderInstance badApple;

    public static boolean isBadAppleShaderInitialized() {
        return badApple != null;
    }
    
    public static ShaderInstance getBadAppleShader() {
        return Objects.requireNonNull(badApple, "Attempted to call getBadAppleShader before it was initialized");
    }

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        ResourceProvider provider = event.getResourceProvider();
        event.registerShader(
                new ShaderInstance(
                        provider,
                        Badapple.rl("bad_apple"),
                        DefaultVertexFormat.BLIT_SCREEN
                ),
                (shader) -> badApple = shader
        );
    }
}
