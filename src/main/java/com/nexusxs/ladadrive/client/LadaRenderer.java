package com.nexusxs.ladadrive.client;

import com.nexusxs.ladadrive.entity.LadaEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LadaRenderer extends EntityRenderer<LadaEntity> {

    private final LadaModel model;

    private static final Identifier TEXTURE =
            new Identifier(
                    "ladadrive",
                    "textures/entity/lada_2107.png"
            );

    public LadaRenderer(EntityRendererFactory.Context context) {
        super(context);

        this.model = new LadaModel(
                context.getPart(LadaModelLayers.LADA)
        );

        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(
            LadaEntity entity,
            float yaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {
        matrices.push();

        matrices.translate(0.0D, 0.35D, 0.0D);

        VertexConsumer vertices =
                vertexConsumers.getBuffer(
                        RenderLayer.getEntityCutoutNoCull(TEXTURE)
                );

        model.setAngles(
                entity,
                0.0F,
                0.0F,
                entity.age + tickDelta,
                0.0F,
                0.0F
        );

        model.render(
                matrices,
                vertices,
                light,
                0,
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );

        matrices.pop();

        super.render(
                entity,
                yaw,
                tickDelta,
                matrices,
                vertexConsumers,
                light
        );
    }

    @Override
    public Identifier getTexture(LadaEntity entity) {
        return TEXTURE;
    }
}
