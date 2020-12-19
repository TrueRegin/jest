package fun.nothaving.jest.engine.constants;

import fun.nothaving.jest.engine.core.utils.PairList;

/**
 * Jest constants without a proper location; generally applicable to the whole program and not a crucial part of any big core functionality(such as potions or in game events).
 */

public class Generic {
    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 3;

    public static final int SCATTER_HEIGHT = 200;
    public static final int SCATTER_X_MAX = 10000;
    public static final int SCATTER_Z_MAX = 10000;

    // Times are in milliseconds
    public static final int MIN_SWIM_FATIGUE = 0;
    public static final int DFT_MAX_SWIM_FATIGUE = 3000;
    public static final int MAX_SWIM_FATIGUE = 10000;
    public static final int MIN_RUN_FATIGUE = 0;
    public static final int DFT_MAX_RUN_FATIGUE = 5000;
    public static final int MAX_RUN_FATIGUE = 25000;

    // Effect Durations (in milliseconds)
    public static final int SPRINT_NAUSEA_DURATION = 4500;
    public static final int SWIM_BLINDNESS_DURATION = 7000;

    private static final String[] DIFFICULTY_LABELS = {"easy", "medium", "hard"};
    private static final Integer[] DIFFICULTY_VALUES = {1, 2, 3};
    public static final PairList<Integer> DIFFICULTIES = new PairList<Integer>(DIFFICULTY_LABELS, DIFFICULTY_VALUES);

    public static void init() {

    }
}
