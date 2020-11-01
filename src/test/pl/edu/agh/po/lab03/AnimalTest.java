package pl.edu.agh.po.lab03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab04.RectangularMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalTest {
    
    static RectangularMap map;
    
    @BeforeAll
    static void setup() {
        map = new RectangularMap(5, 5);
    }

    @Test
    void testGetDefaultPosition() {
        var animal = new Animal(map);
        var expectedPosition = new Vector2d(2, 2);
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testGetCustomPosition() {
        var animal = new Animal(map, new Vector2d(1, 1));
        var expectedPosition = new Vector2d(1, 1);
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testIllegalPosition() {
        var illegal_position = new Vector2d(5, 5);
        assertThrows(IllegalArgumentException.class, () -> new Animal(map, illegal_position));
    }

    @Test
    void testGetDirection() {
        var animal = new Animal(map);
        var expectedDirection = MapDirection.NORTH;
        assertEquals(expectedDirection, animal.getDirection());
    }

    @Test
    void testToStringNorth() {
        var animal = new Animal(map);
        var expected = "^";
        assertEquals(expected, animal.toString());
    }

    @Test
    void testToStringEast() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);

        var expected = ">";
        assertEquals(expected, animal.toString());
    }

    @Test
    void testToStringSouth() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        var expected = "ˬ";
        assertEquals(expected, animal.toString());
    }

    @Test
    void testToStringWest() {
        var animal = new Animal(map);
        animal.move(MoveDirection.LEFT);

        var expected = "<";
        assertEquals(expected, animal.toString());
    }

    @Test
    void testToStringAfterMoves() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        var expected = "ˬ";
        assertEquals(expected, animal.toString());
    }

    @Test
    void defaultPosition() {
        var animal = new Animal(map);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testRightRotate() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);

        var expectedDirection = MapDirection.EAST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testLeftRotate() {
        var animal = new Animal(map);
        animal.move(MoveDirection.LEFT);

        var expectedDirection = MapDirection.WEST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testForward() {
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 3);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testBackward() {
        var animal = new Animal(map);
        animal.move(MoveDirection.BACKWARD);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 1);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testMultipleRotate() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        var expectedDirection = MapDirection.SOUTH;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testMultipleMove() {
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 4);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testMixed() {
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.SOUTH;
        var expectedPosition = new Vector2d(1, 1);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testIllegalMovesNorth() {
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 4);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testIllegalMovesEast() {
        var animal = new Animal(map);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.EAST;
        var expectedPosition = new Vector2d(4, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testIllegalMovesSouth() {
        var animal = new Animal(map);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 0);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void testIllegalMovesWest() {
        var animal = new Animal(map);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expectedDirection = MapDirection.WEST;
        var expectedPosition = new Vector2d(0, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }
}