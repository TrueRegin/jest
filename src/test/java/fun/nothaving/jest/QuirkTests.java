package fun.nothaving.jest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import fun.nothaving.jest.engine.constants.Generic;
import fun.nothaving.jest.engine.core.state.PlayerState;
import fun.nothaving.jest.quirks.StrongQuirk;

public class QuirkTests {
    PlayerState state;

    @BeforeEach
    public void resetState() {
        state = new PlayerState(Generic.MAX_RUN_FATIGUE, Generic.MAX_SWIM_FATIGUE);
    }
    

    @DisplayName("Strong Quirk Tests")
    @Test
    public void StrongQuirkTests() {
        StrongQuirk quirk = new StrongQuirk();
        int initial_max_sprint_fatigue = state.getMaxSprintFatigue();
        int initial_max_swim_fatigue = state.getMaxSprintFatigue();

        for(int i = 0; i < 1000; i++) {
            quirk.apply(state);
        }

        for(int i = 0; i < 1000; i++) {
            quirk.remove(state);
        }

        int final_max_sprint_fatigue = state.getMaxSprintFatigue();
        int final_max_swim_fatigue = state.getMaxSprintFatigue();

        assertEquals(initial_max_sprint_fatigue, final_max_sprint_fatigue);
        assertEquals(initial_max_swim_fatigue, final_max_swim_fatigue);
    }
}
