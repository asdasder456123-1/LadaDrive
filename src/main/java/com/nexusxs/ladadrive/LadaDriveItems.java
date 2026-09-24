package com.nexusxs.ladadrive;

import com.nexusxs.ladadrive.item.LadaItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class LadaDriveItems {

    public static final Item LADA_2107 =
            Registry.register(
                    Registries.ITEM,
                    new Identifier(LadaDrive.MOD_ID, "lada_2107"),
                    new LadaItem(
                            new Item.Settings().maxCount(1)
                    )
            );

    private LadaDriveItems() {
    }

    public static void register() {

        ItemGroupEvents.modifyEntriesEvent(
                ItemGroups.TOOLS
        ).register(entries -> entries.add(LADA_2107));

        LadaDrive.LOGGER.info(
                "LadaDrive items registered."
        );
    }
}
