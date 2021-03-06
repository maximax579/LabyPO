package pl.edu.agh.po.lab05;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.IWorldMap;
import pl.edu.agh.po.lab04.RectangularMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveToEmptyCell() {
        var map = new GrassField(10);
        assertTrue(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void canMoveToOccupiedCell() {
        var map = new GrassField(10);
        new Animal(map);
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void placeCorrectAnimal() {
        var map = new GrassField(10);
        assertDoesNotThrow(() -> new Animal(map));
    }

    @Test
    void placeTwoAnimalsInOneCell() {
        var map = new GrassField(10);
        new Animal(map);
        assertThrows(IllegalArgumentException.class, () -> new Animal(map));
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

        IWorldMap map = new GrassField(10);
        map.run(directions);

        assertTrue(map.objectAt(new Vector2d(2, 2)).isEmpty() ||
                map.objectAt(new Vector2d(2, 2)).get() instanceof Grass);
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

        IWorldMap map = new GrassField(10);
        var animal = new Animal(map, new Vector2d(2, 1));
        map.run(directions);

        var animalExpectedPosition = new Vector2d(2, 4);
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

        IWorldMap map = new GrassField(10);
        var animal1 = new Animal(map, new Vector2d(2, 1));
        var animal2 = new Animal(map, new Vector2d(3, 3));
        map.run(directions);

        var animal1ExpectedPosition = new Vector2d(2, -1);
        var animal2ExpectedPosition = new Vector2d(3, 4);
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
        var map = new GrassField(10);
        var position = new Vector2d(2, 2);
        assertFalse(!(map.objectAt(position).orElse(new Grass(position)) instanceof Grass) && map.isOccupied(position));
    }

    @Test
    void isOccupiedTrue() {
        var map = new GrassField(10);
        new Animal(map);
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAtEmptyOrGrass() {
        var map = new RectangularMap(4, 4);
        assertTrue(map.objectAt(new Vector2d(2, 2)).isEmpty() ||
                map.objectAt(new Vector2d(2, 2)).get() instanceof Grass);
    }

    @Test
    void objectAtAnimal() {
        var map = new GrassField(10);
        var animal = new Animal(map);
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)).orElseThrow());
    }

    @Test
    void getLowerLeftWithoutAnimals() {
        var map = new GrassField(0);
        var expected = new Vector2d(0, 0);
        assertEquals(expected, map.getLowerLeft());
    }

    @Test
    void getUpperRightWithoutAnimals() {
        var map = new GrassField(0);
        var expected = new Vector2d(0, 0);
        assertEquals(expected, map.getUpperRight());
    }

    @Test
    void getLowerLeftWithOneAnimal() {
        var map = new GrassField(0);
        new Animal(map);
        var expected = new Vector2d(2, 2);
        assertEquals(expected, map.getLowerLeft());
    }

    @Test
    void getUpperRightWithOneAnimal() {
        var map = new GrassField(0);
        new Animal(map);
        var expected = new Vector2d(2, 2);
        assertEquals(expected, map.getUpperRight());
    }

    @Test
    void getLowerLeftWithTwoAnimals() {
        var map = new GrassField(0);
        new Animal(map);
        new Animal(map, new Vector2d(3, 3));

        var expected = new Vector2d(2, 2);
        assertEquals(expected, map.getLowerLeft());
    }

    @Test
    void getUpperRightWithTwoAnimals() {
        var map = new GrassField(0);
        new Animal(map);
        new Animal(map, new Vector2d(3, 3));

        var expected = new Vector2d(3, 3);
        assertEquals(expected, map.getUpperRight());
    }

}