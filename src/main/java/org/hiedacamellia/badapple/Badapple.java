package org.hiedacamellia.badapple;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.hiedacamellia.badapple.core.config.ClientConfig;
import org.slf4j.Logger;


@Mod(Badapple.MODID)
public class Badapple {

    public static final String MODID = "badapple";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Badapple(IEventBus modEventBus, ModContainer modContainer) {


        if(FMLLoader.getDist().isClient()) {
            modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
