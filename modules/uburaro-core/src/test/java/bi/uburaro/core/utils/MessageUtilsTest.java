package bi.uburaro.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageUtilsTest {

    @Test
    void logMessage() {
        String message = "Test {} of class {}";
        String expected = "Test LogMessageUtils of class 2";
        String actual = MessageUtils.format(message, "LogMessageUtils", "2");
        assertEquals(expected,actual);

        actual = MessageUtils.format(message, ()-> "LogMessageUtils",()-> 2);
        assertEquals(expected,actual);
    }
}
