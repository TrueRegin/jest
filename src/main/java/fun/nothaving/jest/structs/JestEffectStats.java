package fun.nothaving.jest.structs;

import java.security.InvalidParameterException;

import org.bukkit.Bukkit;

import fun.nothaving.jest.engine.constants.Errors;
import fun.nothaving.jest.engine.core.math.MathUtils;

public class JestEffectStats {

    private int duration;
    private long prev_update_timestamp;
    private byte intensity;

    /**
     * Constructor for JestEffectStats
     * 
     * @param duration  - Duration in seconds
     * @param intensity - Intensity of the potion effect, this is interpreted
     *                  uniquely for each quirk in an effect
     */
    public JestEffectStats(int duration, byte intensity) {
        if (duration < 0 || intensity < 0) {
            throw new InvalidParameterException(Errors.INVALID_PARAMETER_INTERNAL);
        }

        this.duration = duration;
        this.intensity = intensity;
        this.prev_update_timestamp = System.currentTimeMillis();
    }

    public int getDuration() {
        return duration;
    }

    public byte getIntensity() {
        return intensity;
    }

    public boolean hasExpired() {
        return duration == 0;
    }

    public void updateDuration() {
        long curr_timestamp = System.currentTimeMillis();
        int timestamp_diff = (int) (curr_timestamp - prev_update_timestamp);
        duration = MathUtils.clamp(0, Integer.MAX_VALUE, duration - timestamp_diff);
        prev_update_timestamp = curr_timestamp;
    }
}
