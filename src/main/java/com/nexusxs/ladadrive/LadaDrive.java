package com.nexusxs.ladadrive;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LadaDrive implements ModInitializer {

    public static final String MOD_ID = "ladadrive";
    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("LadaDrive initialized.");
        LOGGER.info("Lada 2107 vehicle system starting...");
    }
}
