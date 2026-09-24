package com.nexusxs.ladadrive;

import com.nexusxs.ladadrive.client.LadaModelLayers;
import com.nexusxs.ladadrive.client.LadaRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LadaDriveClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LadaModelLayers.register();

        EntityRendererRegistry.register(
                LadaDriveEntities.LADA_2107,
                LadaRenderer::new
        );
    }
}
