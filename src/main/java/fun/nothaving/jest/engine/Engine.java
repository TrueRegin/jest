package fun.nothaving.jest.engine;

import fun.nothaving.jest.engine.constants.CustomEffects;
import fun.nothaving.jest.engine.constants.Generic;

public class Engine {
    private static boolean initialized = false;

    /**
     * 
     * @return boolean on whether the initialization succeeded. If it fails, plugin is terminated by throwing an error.
     */
    public static boolean init() {
        StateManager.init();
        Generic.init();
        CustomEffects.init();
        initialized = true;
        return true;
    }

    public static boolean initialized() {
        return initialized;
    }

    public static void tick() {
        StateManager.tick();
    }
}
