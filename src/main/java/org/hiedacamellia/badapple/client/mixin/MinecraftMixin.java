package org.hiedacamellia.badapple.client.mixin;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import org.hiedacamellia.badapple.client.Shaders;
import org.hiedacamellia.badapple.core.config.ClientConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Redirect(method = "runTick",at = @At(target = "Lcom/mojang/blaze3d/pipeline/RenderTarget;blitToScreen(II)V",value = "INVOKE"))
    private void renderBA(RenderTarget instance, int width, int height) {
        if(!ClientConfig.enableBadAppleRenderer.get()||!Shaders.isBadAppleShaderInitialized()) {
            instance.blitToScreen(width, height);
            return;
        }
        RenderSystem.setShader(Shaders::getBadAppleShader);
        RenderSystem.assertOnRenderThread();
        GlStateManager._colorMask(true, true, true, false);
        GlStateManager._disableDepthTest();
        GlStateManager._depthMask(false);
        GlStateManager._viewport(0, 0, width, height);
        GlStateManager._disableBlend();
        ShaderInstance badAppleShader = Shaders.getBadAppleShader();
        badAppleShader.setSampler("DiffuseSampler", instance.getColorTextureId());
        badAppleShader.safeGetUniform("contrast").set(ClientConfig.badAppleContrast.get().floatValue());
        badAppleShader.safeGetUniform("reverse").set(ClientConfig.reverseColor.get()?1:0);
        badAppleShader.apply();
        BufferBuilder bufferbuilder = RenderSystem.renderThreadTesselator().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.BLIT_SCREEN);
        bufferbuilder.addVertex(0.0F, 0.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, 0.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, 1.0F, 0.0F);
        bufferbuilder.addVertex(0.0F, 1.0F, 0.0F);
        BufferUploader.draw(bufferbuilder.buildOrThrow());
        badAppleShader.clear();
        GlStateManager._depthMask(true);
        GlStateManager._colorMask(true, true, true, true);
    }


}
