package pl.edu.agh.po.lab07;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.RectangularMap;

import static org.junit.jupiter.api.Assertions.*;

class MapBoundaryTest {

    @Test
    void testUpperRightWithoutAnimals() {
        var mapBoundary = new MapBoundary();
        var expected = new Vector2d(0, 0);
        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftWithoutAnimals() {
        var mapBoundary = new MapBoundary();
        var expected = new Vector2d(0, 0);
        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightWithOneAnimal() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftWithOneAnimal() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightWithTwoAnimals() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(1, 1));

        mapBoundary.addMapElement(animal1);
        mapBoundary.addMapElement(animal2);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftWithTwoAnimals() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(1, 1));

        mapBoundary.addMapElement(animal1);
        mapBoundary.addMapElement(animal2);
        var expected = new Vector2d(1, 1);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightPositionYChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 3);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftPositionYChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 3);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightPositionXChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(3, 2);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftPositionXChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(3, 2);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightDirectionChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        animal.move(MoveDirection.LEFT);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftDirectionChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        animal.move(MoveDirection.LEFT);

        mapBoundary.addMapElement(animal);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }

    @Test
    void testUpperRightPositionNotChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(0, 0));
        var animal3 = new Animal(map, new Vector2d(1, 1));

        mapBoundary.addMapElement(animal1);
        mapBoundary.addMapElement(animal2);
        mapBoundary.addMapElement(animal3);
        animal3.move(MoveDirection.FORWARD);
        var expected = new Vector2d(2, 2);

        assertEquals(expected, mapBoundary.getUpperRight());
    }

    @Test
    void testLowerLeftPositionNotChanged() {
        var mapBoundary = new MapBoundary();
        var map = new RectangularMap(4, 4);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(0, 0));
        var animal3 = new Animal(map, new Vector2d(1, 1));

        mapBoundary.addMapElement(animal1);
        mapBoundary.addMapElement(animal2);
        mapBoundary.addMapElement(animal3);
        animal3.move(MoveDirection.FORWARD);
        var expected = new Vector2d(0, 0);

        assertEquals(expected, mapBoundary.getLowerLeft());
    }
}