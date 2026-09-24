package com.nexusxs.ladadrive.client;

import com.nexusxs.ladadrive.LadaDrive;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public final class LadaModelLayers {

    public static final EntityModelLayer LADA =
            new EntityModelLayer(
                    new Identifier(LadaDrive.MOD_ID, "lada_2107"),
                    "main"
            );

    private LadaModelLayers() {
    }

    public static void register() {
        EntityModelLayerRegistry.registerModelLayer(
                LADA,
                LadaModel::getTexturedModelData
        );
    }
}
