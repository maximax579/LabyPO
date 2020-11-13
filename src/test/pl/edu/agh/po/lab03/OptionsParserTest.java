package pl.edu.agh.po.lab03;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MoveDirection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionsParserTest {

    @Test
    void parseEmptyArray() {
        var array = new String[] { };
        var expected = List.of();
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseShortcutLeft() {
        var array = new String[] { "l" };
        var expected = List.of(MoveDirection.LEFT);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseFullLeft() {
        var array = new String[] { "left" };
        var expected = List.of(MoveDirection.LEFT);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseShortcutRight() {
        var array = new String[] { "r" };
        var expected = List.of(MoveDirection.RIGHT);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseFullRight() {
        var array = new String[] { "right" };
        var expected = List.of(MoveDirection.RIGHT);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseShortcutForward() {
        var array = new String[] { "f" };
        var expected = List.of(MoveDirection.FORWARD);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseFullForward() {
        var array = new String[] { "forward" };
        var expected = List.of(MoveDirection.FORWARD);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseShortcutBackward() {
        var array = new String[] { "b" };
        var expected = List.of(MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseFullBackward() {
        var array = new String[] { "backward" };
        var expected = List.of(MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseRandom() {
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[] { "alamakota" }));
    }

    @Test
    void parseArrayShortcutSequence() {
        var array = new String[] { "b", "f", "r", "l", "l", "b", "f" };
        var expected = List.of(
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD
        );
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseArrayFullSequence() {
        var array = new String[] { "backward", "forward", "right", "left", "left", "backward", "forward" };
        var expected = List.of(
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD
        );
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseArrayMixedSequence() {
        var array = new String[] { "backward", "f", "right", "left", "l", "backward", "f" };
        var expected = List.of(
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD
        );
        assertEquals(expected, OptionsParser.parse(array));
    }

    @Test
    void parseArrayWithRandomSequence() {
        var array = new String[] { ";;", "backward", "sdfsdf", "f", "alamakota", "right", "left", "l", "backward", "..", "f" };
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(array));
    }
}