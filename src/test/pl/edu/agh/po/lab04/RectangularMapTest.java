package pl.edu.agh.po.lab04;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void illegalMapSizeWidth() {
        int illegalWidth = -1;
        assertThrows(IllegalArgumentException.class, () -> new RectangularMap(illegalWidth, 4));
    }

    @Test
    void illegalMapSizeHeight() {
        int illegalHeight = -1;
        assertThrows(IllegalArgumentException.class, () -> new RectangularMap(4, illegalHeight));
    }

    @Test
    void legalMapSize() {
        assertDoesNotThrow(() -> new RectangularMap(4, 4));
    }

    @Test
    void canMoveToEmptyCell() {
        var map = new RectangularMap(4, 4);
        assertTrue(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void canMoveToCellOutOfTheRange() {
        var map = new RectangularMap(4, 4);
        assertFalse(map.canMoveTo(new Vector2d(4, 6)));
    }

    @Test
    void canMoveToOccupiedCell() {
        var map = new RectangularMap(4, 4);
        new Animal(map);
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void placeCorrectAnimal() {
        var map = new RectangularMap(4, 4);
        assertDoesNotThrow(() -> new Animal(map));
    }

    @Test
    void placeTwoAnimalsInOneCell() {
        var map = new RectangularMap(4, 4);
        new Animal(map);
        assertThrows(IllegalArgumentException.class, () -> new Animal(map));
    }

    @Test
    void placeAnimalInCellOutOfTheRange() {
        var map = new RectangularMap(4, 4);
        assertThrows(IllegalArgumentException.class, () -> new Animal(map, new Vector2d(5, 5)));
    }

    @Test
    void runWithoutAnimals() {
        var directions = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );

        IWorldMap map = new RectangularMap(4, 4);
        map.run(directions);

        assertEquals(Optional.empty(), map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void runWithOneAnimal() {
        var directions = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );

        IWorldMap map = new RectangularMap(4, 4);
        var animal = new Animal(map, new Vector2d(2, 1));
        map.run(directions);

        var animalExpectedPosition = new Vector2d(2, 3);
        var animalExpectedDirection = MapDirection.NORTH;

        assertEquals(animal, map.objectAt(animalExpectedPosition).orElseThrow());
        assertEquals(animalExpectedPosition, animal.getPosition());
        assertEquals(animalExpectedDirection, animal.getDirection());
    }

    @Test
    void runWithTwoAnimals() {
        var directions = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );

        IWorldMap map = new RectangularMap(4, 4);
        var animal1 = new Animal(map, new Vector2d(2, 1));
        var animal2 = new Animal(map, new Vector2d(3, 3));
        map.run(directions);

        var animal1ExpectedPosition = new Vector2d(2, 0);
        var animal2ExpectedPosition = new Vector2d(3, 3);
        var animal1ExpectedDirection = MapDirection.SOUTH;
        var animal2ExpectedDirection = MapDirection.NORTH;

        assertEquals(animal1, map.objectAt(animal1ExpectedPosition).orElseThrow());
        assertEquals(animal2, map.objectAt(animal2ExpectedPosition).orElseThrow());
        assertEquals(animal1ExpectedPosition, animal1.getPosition());
        assertEquals(animal2ExpectedPosition, animal2.getPosition());
        assertEquals(animal1ExpectedDirection, animal1.getDirection());
        assertEquals(animal2ExpectedDirection, animal2.getDirection());
    }

    @Test
    void isOccupiedFalse() {
        var map = new RectangularMap(4, 4);
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void isOccupiedOutOfIndex() {
        var map = new RectangularMap(4, 4);
        assertFalse(map.isOccupied(new Vector2d(5, 2)));
    }

    @Test
    void isOccupiedTrue() {
        var map = new RectangularMap(4, 4);
        new Animal(map);
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAtEmpty() {
        var map = new RectangularMap(4, 4);
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void objectAtOutOfIndex() {
        var map = new RectangularMap(4, 4);
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(4, 2)));
    }

    @Test
    void objectAtOccupied() {
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)).orElseThrow());
    }

    @Test
    void testToStringEmpty() {
        var map = new RectangularMap(4, 4);

        var expected = " y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | | | |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }

    @Test
    void testToStringWithOneAnimal() {
        var map = new RectangularMap(4, 4);
        new Animal(map);

        var expected = " y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | | | |" + System.lineSeparator() +
                "  2: | | |^| |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }

    @Test
    void testToStringWithTwoAnimals() {
        var map = new RectangularMap(4, 4);
        new Animal(map);
        new Animal(map, new Vector2d(3, 3));

        var expected = " y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | | |^|" + System.lineSeparator() +
                "  2: | | |^| |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }

    @Test
    void animationWithOneAnimal() {
        var directions = List.of(
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.LEFT
        );
        var map = new RectangularMap(4, 4);
        new Animal(map);
        map.run(directions);

        var expected = new LinkedList<String>();
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | | | |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | | | |" + System.lineSeparator() +
                "  2: | | |^| |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | |^| |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | | |<| |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | |<| | |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());
        expected.add(" y\\x  0 1 2 3" + System.lineSeparator() +
                "  4: ---------" + System.lineSeparator() +
                "  3: | |ˬ| | |" + System.lineSeparator() +
                "  2: | | | | |" + System.lineSeparator() +
                "  1: | | | | |" + System.lineSeparator() +
                "  0: | | | | |" + System.lineSeparator() +
                " -1: ---------" + System.lineSeparator());

        assertEquals(expected, map.getMapAnimator().getAnimation());
    }

}