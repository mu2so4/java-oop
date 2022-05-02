package mur.lab2.calculator;

import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ContextBadLabelsTest {
    @Test
    @DisplayName("define with wrong label")
    void defineWithWrongLabels() {
        Context context = new Context();
        Random rand = new Random();
        String[] goodLabels = {"_12oeu", ",bbb", "a1", "_44444444", "label"};
        for(String label: goodLabels) {
            assertDoesNotThrow(() -> context.define(label, rand.nextDouble()));
        }
        String[] badLabels = {"3a", "9eou", "0label"};
        for(String label: badLabels) {
            assertThrows(IllegalLabelException.class, () -> context.define(label, rand.nextDouble()));
        }
    }
}
