package com.nexusxs.ladadrive;

import com.nexusxs.ladadrive.entity.LadaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class LadaDriveEntities {

    public static final EntityType<LadaEntity> LADA_2107 =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier(LadaDrive.MOD_ID, "lada_2107"),
                    FabricEntityTypeBuilder
                            .create(SpawnGroup.MISC, LadaEntity::new)
                            .dimensions(EntityDimensions.fixed(1.8f, 1.35f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(3)
                            .build()
            );

    private LadaDriveEntities() {
    }

    public static void register() {
        LadaDrive.LOGGER.info("LadaDrive entities registered.");
    }
}
