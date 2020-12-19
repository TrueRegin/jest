package fun.nothaving.jest.engine.core.math;

public class MathUtils {
    public static int clamp(int min, int max, int value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    public static float clamp(float min, float max, float value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    public static double clamp(double min, double max, double value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }

    public static long clamp(long min, long max, long value) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
}
