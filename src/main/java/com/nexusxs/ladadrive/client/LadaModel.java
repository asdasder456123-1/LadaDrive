package com.nexusxs.ladadrive.client;

import com.nexusxs.ladadrive.entity.LadaEntity;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;

public class LadaModel extends EntityModel<LadaEntity> {

    private final ModelPart root;

    public LadaModel(ModelPart root) {
        super();
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        // الجسم الأبيض
        root.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-14F, -5F, -7F, 28F, 7F, 14F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الجزء العلوي / الكبينة
        root.addChild(
                "cabin",
                ModelPartBuilder.create()
                        .uv(0, 22)
                        .cuboid(-9F, -10F, -6F, 18F, 6F, 12F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الكبوت
        root.addChild(
                "hood",
                ModelPartBuilder.create()
                        .uv(0, 40)
                        .cuboid(-14F, -7F, -6F, 7F, 2F, 12F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الشنطة
        root.addChild(
                "trunk",
                ModelPartBuilder.create()
                        .uv(0, 54)
                        .cuboid(7F, -7F, -6F, 7F, 2F, 12F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الزجاج الأمامي
        root.addChild(
                "front_window",
                ModelPartBuilder.create()
                        .uv(40, 40)
                        .cuboid(-4F, -9F, -6.2F, 8F, 4F, 1F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الزجاج الخلفي
        root.addChild(
                "rear_window",
                ModelPartBuilder.create()
                        .uv(40, 45)
                        .cuboid(-4F, -9F, 5.2F, 8F, 4F, 1F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // جوانب الزجاج
        root.addChild(
                "left_windows",
                ModelPartBuilder.create()
                        .uv(40, 50)
                        .cuboid(-9.2F, -9F, -5F, 1F, 4F, 10F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        root.addChild(
                "right_windows",
                ModelPartBuilder.create()
                        .uv(40, 50)
                        .cuboid(8.2F, -9F, -5F, 1F, 4F, 10F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // العجل
        addWheel(root, "wheel_fl", -9F, 0F, -7.5F);
        addWheel(root, "wheel_fr", -9F, 0F, 6.5F);
        addWheel(root, "wheel_rl", 9F, 0F, -7.5F);
        addWheel(root, "wheel_rr", 9F, 0F, 6.5F);

        // الصدام الأمامي
        root.addChild(
                "front_bumper",
                ModelPartBuilder.create()
                        .uv(0, 62)
                        .cuboid(-15F, -3F, -7.5F, 2F, 3F, 15F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        // الصدام الخلفي
        root.addChild(
                "rear_bumper",
                ModelPartBuilder.create()
                        .uv(34, 62)
                        .cuboid(13F, -3F, -7.5F, 2F, 3F, 15F),
                ModelTransform.pivot(0F, 18F, 0F)
        );

        return TexturedModelData.of(data, 64, 80);
    }

    private static void addWheel(
            ModelPartData root,
            String name,
            float x,
            float y,
            float z
    ) {
        root.addChild(
                name,
                ModelPartBuilder.create()
                        .uv(52, 0)
                        .cuboid(-2F, -3F, -1.5F, 4F, 6F, 3F),
                ModelTransform.pivot(x, 22F + y, z)
        );
    }

    @Override
    public void setAngles(
            LadaEntity entity,
            float limbAngle,
            float limbDistance,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        root.yaw = (float) Math.toRadians(-entity.getYaw());
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumer vertices,
            int light,
            int overlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        matrices.push();

        matrices.scale(
                0.055F,
                0.055F,
                0.055F
        );

        root.render(
                matrices,
                vertices,
                light,
                overlay,
                red,
                green,
                blue,
                alpha
        );

        matrices.pop();
    }
}
