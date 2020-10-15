// Maksymilian Wojnar
package pl.edu.agh.po.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void vectorToString1() {
        var vector = new Vector2d(1, 3);
        var expected = "(1,3)";
        assertEquals(expected, vector.toString());
    }

    @Test
    void vectorToString2() {
        var vector = new Vector2d(-12, 0);
        var expected = "(-12,0)";
        assertEquals(expected, vector.toString());
    }

    @Test
    void precedesWithDifferentCoordinatesTrue() {
        var vector1 = new Vector2d(3, 3);
        var vector2 = new Vector2d(1, 1);
        assertTrue(vector2.precedes(vector1));
    }

    @Test
    void precedesWithOneTheSameCoordinateTrue() {
        var vector1 = new Vector2d(3, 3);
        var vector2 = new Vector2d(1, 3);
        assertTrue(vector2.precedes(vector1));
    }

    @Test
    void precedesWithTheSameCoordinatesTrue() {
        var vector1 = new Vector2d(3, 3);
        var vector2 = new Vector2d(3, 3);
        assertTrue(vector2.precedes(vector1));
    }

    @Test
    void precedesFalse() {
        var vector1 = new Vector2d(3, 3);
        var vector2 = new Vector2d(1, 1);
        assertFalse(vector1.precedes(vector2));
    }

    @Test
    void followsWithDifferentCoordinatesTrue() {
        var vector1 = new Vector2d(1, 1);
        var vector2 = new Vector2d(2, 2);
        assertTrue(vector2.follows(vector1));
    }

    @Test
    void followsWithOneTheSameCoordinateTrue() {
        var vector1 = new Vector2d(1, 2);
        var vector2 = new Vector2d(2, 2);
        assertTrue(vector2.follows(vector1));
    }

    @Test
    void followsWithTheSameCoordinatesTrue() {
        var vector1 = new Vector2d(2, 2);
        var vector2 = new Vector2d(2, 2);
        assertTrue(vector2.follows(vector1));
    }

    @Test
    void followsFalse() {
        var vector1 = new Vector2d(1, 1);
        var vector2 = new Vector2d(2, 2);
        assertFalse(vector1.follows(vector2));
    }

    @Test
    void upperRight() {
        var vector1 = new Vector2d(1, 4);
        var vector2 = new Vector2d(2, 2);
        assertEquals(new Vector2d(2, 4), vector1.upperRight(vector2));
    }

    @Test
    void lowerLeft() {
        var vector1 = new Vector2d(1, 4);
        var vector2 = new Vector2d(2, 2);
        assertEquals(new Vector2d(1, 2), vector1.lowerLeft(vector2));
    }

    @Test
    void addVectors() {
        var vector1 = new Vector2d(1, 4);
        var vector2 = new Vector2d(2, 2);
        assertEquals(new Vector2d(3, 6), vector1.add(vector2));
    }

    @Test
    void subtractVectors() {
        var vector1 = new Vector2d(1, 4);
        var vector2 = new Vector2d(2, 2);
        assertEquals(new Vector2d(-1, 2), vector1.subtract(vector2));
    }

    @Test
    void equalsTrue() {
        var vector1 = new Vector2d(1, 3);
        var vector2 = new Vector2d(1, 3);
        assertEquals(vector2, vector1);
    }

    @Test
    void equalsFalse() {
        var vector1 = new Vector2d(1, 3);
        var vector2 = new Vector2d(-1, 3);
        assertNotEquals(vector2, vector1);
    }

    @Test
    void equalsWithNull() {
        var vector = new Vector2d(1, 3);
        assertNotEquals(vector, null);
    }

    @Test
    void equalsWithDifferentObject() {
        var vector = new Vector2d(1, 3);
        assertNotEquals(vector, new Object());
    }

    @Test
    void oppositeVector() {
        var vector1 = new Vector2d(1, 3);
        var vector2 = new Vector2d(-1, -3);
        assertEquals(vector2, vector1.opposite());
    }
}