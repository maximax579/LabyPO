// Maksymilian Wojnar
package pl.edu.agh.po.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void nextDirectionNorth() {
        var direction = MapDirection.NORTH;
        var expected = MapDirection.EAST;
        assertEquals(direction.next(), expected);
    }

    @Test
    void nextDirectionEast() {
        var direction = MapDirection.EAST;
        var expected = MapDirection.SOUTH;
        assertEquals(direction.next(), expected);
    }

    @Test
    void nextDirectionSouth() {
        var direction = MapDirection.SOUTH;
        var expected = MapDirection.WEST;
        assertEquals(direction.next(), expected);
    }

    @Test
    void nextDirectionWest() {
        var direction = MapDirection.WEST;
        var expected = MapDirection.NORTH;
        assertEquals(direction.next(), expected);
    }

    @Test
    void previousDirectionNorth() {
        var direction = MapDirection.NORTH;
        var expected = MapDirection.WEST;
        assertEquals(direction.previous(), expected);
    }

    @Test
    void previousDirectionEast() {
        var direction = MapDirection.EAST;
        var expected = MapDirection.NORTH;
        assertEquals(direction.previous(), expected);
    }

    @Test
    void previousDirectionSouth() {
        var direction = MapDirection.SOUTH;
        var expected = MapDirection.EAST;
        assertEquals(direction.previous(), expected);
    }

    @Test
    void previousDirectionWest() {
        var direction = MapDirection.WEST;
        var expected = MapDirection.SOUTH;
        assertEquals(direction.previous(), expected);
    }
}