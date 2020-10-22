package pl.edu.agh.po.lab03;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testGetPosition() {
        var animal = new Animal();
        var expected_position = new Vector2d(2, 2);
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testGetDirection() {
        var animal = new Animal();
        var expected_direction = MapDirection.NORTH;
        assertEquals(expected_direction, animal.getDirection());
    }

    @Test
    void testToString() {
        var animal = new Animal();
        var expected = "Animal {orientacja=Północ, pozycja=(2,2)}";
        assertEquals(expected, animal.toString());
    }

    @Test
    void testToStringAfterMoves() {
        var animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        var expected = "Animal {orientacja=Południe, pozycja=(1,1)}";
        assertEquals(expected, animal.toString());
    }

    @Test
    void defaultPosition() {
        var animal = new Animal();

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testRightRotate() {
        var animal = new Animal();
        animal.move(MoveDirection.RIGHT);

        var expected_direction = MapDirection.EAST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testLeftRotate() {
        var animal = new Animal();
        animal.move(MoveDirection.LEFT);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testForward() {
        var animal = new Animal();
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 3);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testBackward() {
        var animal = new Animal();
        animal.move(MoveDirection.BACKWARD);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 1);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testMultipleRotate() {
        var animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        var expected_direction = MapDirection.SOUTH;
        var expected_position = new Vector2d(2, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testMultipleMove() {
        var animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 4);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testMixed() {
        var animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.SOUTH;
        var expected_position = new Vector2d(1, 1);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testIllegalMovesNorth() {
        var animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 4);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testIllegalMovesEast() {
        var animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.EAST;
        var expected_position = new Vector2d(4, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testIllegalMovesSouth() {
        var animal = new Animal();
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);

        var expected_direction = MapDirection.NORTH;
        var expected_position = new Vector2d(2, 0);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }

    @Test
    void testIllegalMovesWest() {
        var animal = new Animal();
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        var expected_direction = MapDirection.WEST;
        var expected_position = new Vector2d(0, 2);

        assertEquals(expected_direction, animal.getDirection());
        assertEquals(expected_position, animal.getPosition());
    }
}