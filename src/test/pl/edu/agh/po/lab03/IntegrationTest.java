package pl.edu.agh.po.lab03;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.Vector2d;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    private Animal animalAfterMoves(String[] array) {
        var animal = new Animal();
        var moves = OptionsParser.parse(array);

        for (var move : moves) {
            animal.move(move);
        }

        return animal;
    }

    @Test
    void emptyArray() {
        var array = new String[] { };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void shortcutLeft() {
        var array = new String[] { "l" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void fullLeft() {
        var array = new String[] { "left" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void shortcutRight() {
        var array = new String[] { "r" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.EAST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void fullRight() {
        var array = new String[] { "right" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.EAST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void shortcutForward() {
        var array = new String[] { "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 3);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void fullForward() {
        var array = new String[] { "forward" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 3);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void shortcutBackward() {
        var array = new String[] { "b" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 1);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void fullBackward() {
        var array = new String[] { "backward" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 1);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void randomText() {
        var array = new String[] { "alamakota" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayShortcutSequence() {
        var array = new String[] { "b", "f", "r", "l", "l", "b", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void aArrayFullSequence() {
        var array = new String[] { "backward", "forward", "right", "left", "left", "backward", "forward" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayMixedSequence() {
        var array = new String[] { "backward", "f", "right", "left", "l", "backward", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayWithRandomSequence() {
        var array = new String[] { ";;", "backward", "sdfsdf", "f", "alamakota", "right", "left", "l", "backward", "..", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayWithIllegalMovesNorth() {
        var array = new String[] { "f", "f", "f", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 4);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayWithIllegalMovesSouth() {
        var array = new String[] { "b", "b", "b", "b" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 0);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayWithIllegalMovesEast() {
        var array = new String[] { "r", "f", "f", "f", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.EAST;
        var expected_position = new Vector2d(4, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void arrayWithIllegalMovesWest() {
        var array = new String[] { "l", "f", "f", "f", "f" };
        var animal = animalAfterMoves(array);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(0, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }
}