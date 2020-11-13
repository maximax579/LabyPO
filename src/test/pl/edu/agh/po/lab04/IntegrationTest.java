package pl.edu.agh.po.lab04;

import org.junit.jupiter.api.Test;
import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab03.OptionsParser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTest {
    
    @Test
    void emptyArrayWithoutAnimals() {
        var directions = OptionsParser.parse(new String[] { });
        var map = new RectangularMap(10, 5);
        map.run(directions);

        assertEquals(Optional.empty(), map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void emptyArrayWithOneAnimal() {
        var directions = OptionsParser.parse(new String[] { });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var animalExpectedPosition = new Vector2d(2, 2);
        var animalExpectedDirection = MapDirection.NORTH;

        assertEquals(animal, map.objectAt(animalExpectedPosition).orElseThrow());
        assertEquals(animalExpectedPosition, animal.getPosition());
        assertEquals(animalExpectedDirection, animal.getDirection());
    }
    
    @Test
    void emptyArrayWithTwoAnimals() {
        var directions = OptionsParser.parse(new String[] { });
        var map = new RectangularMap(10, 5);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(3, 4));
        map.run(directions);

        var animal1ExpectedPosition = new Vector2d(2, 2);
        var animal2ExpectedPosition = new Vector2d(3, 4);
        var animal1ExpectedDirection = MapDirection.NORTH;
        var animal2ExpectedDirection = MapDirection.NORTH;

        assertEquals(animal1, map.objectAt(animal1ExpectedPosition).orElseThrow());
        assertEquals(animal2, map.objectAt(animal2ExpectedPosition).orElseThrow());
        assertEquals(animal1ExpectedPosition, animal1.getPosition());
        assertEquals(animal2ExpectedPosition, animal2.getPosition());
        assertEquals(animal1ExpectedDirection, animal1.getDirection());
        assertEquals(animal2ExpectedDirection, animal2.getDirection());
    }

    @Test
    void arrayTestSequenceWithoutAnimals() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f" }
        );

        var map = new RectangularMap(10, 5);
        map.run(directions);

        assertEquals(Optional.empty(), map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void arrayTestSequenceWithOneAnimal() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f" }
        );

        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var animalExpectedPosition = new Vector2d(2, 4);
        var animalExpectedDirection = MapDirection.NORTH;

        assertEquals(animal, map.objectAt(animalExpectedPosition).orElseThrow());
        assertEquals(animalExpectedPosition, animal.getPosition());
        assertEquals(animalExpectedDirection, animal.getDirection());
    }

    @Test
    void arrayTestSequenceWithTwoAnimals() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f" }
        );
        var map = new RectangularMap(10, 5);
        var animal1 = new Animal(map);
        var animal2 = new Animal(map, new Vector2d(3, 4));
        map.run(directions);

        var animal1ExpectedPosition = new Vector2d(2, 0);
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
    void shortcutLeft() {
        var directions = OptionsParser.parse(new String[] { "l" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.WEST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void fullLeft() {
        var directions = OptionsParser.parse(new String[] { "left" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.WEST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void shortcutRight() {
        var directions = OptionsParser.parse(new String[] { "r" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.EAST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void fullRight() {
        var directions = OptionsParser.parse(new String[] { "right" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.EAST;
        var expectedPosition = new Vector2d(2, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void shortcutForward() {
        var directions = OptionsParser.parse(new String[] { "f" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 3);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void fullForward() {
        var directions = OptionsParser.parse(new String[] { "forward" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 3);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void shortcutBackward() {
        var directions = OptionsParser.parse(new String[] { "b" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 1);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void fullBackward() {
        var directions = OptionsParser.parse(new String[] { "backward" });
        var map = new RectangularMap(10, 5);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 1);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void randomText() {
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(new String[] { "alamakota" }));
    }

    @Test
    void illegalMovesNorth() {
        var directions = OptionsParser.parse(new String[] { "f", "f", "f" });
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 3);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void illegalMovesEast() {
        var directions = OptionsParser.parse(new String[] { "r", "f", "f", "f" });
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.EAST;
        var expectedPosition = new Vector2d(3, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void illegalMovesSouth() {
        var directions = OptionsParser.parse(new String[] { "b", "b", "b", "b" });
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2d(2, 0);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void illegalMovesWest() {
        var directions = OptionsParser.parse(new String[] { "l", "f", "f", "f", "f" });
        var map = new RectangularMap(4, 4);
        var animal = new Animal(map);
        map.run(directions);

        var expectedDirection = MapDirection.WEST;
        var expectedPosition = new Vector2d(0, 2);

        assertEquals(expectedDirection, animal.getDirection());
        assertEquals(expectedPosition, animal.getPosition());
    }

    @Test
    void twoAnimalsOnOnePosition() {
        var map = new RectangularMap(5, 5);
        new Animal(map);
        assertThrows(IllegalArgumentException.class, () -> new Animal(map));
    }

    @Test
    void toStringWithoutAnimals() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f" }
        );
        var map = new RectangularMap(10, 5);
        map.run(directions);

        var expected = " y\\x  0 1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
                "  5: ---------------------" + System.lineSeparator() +
                "  4: | | | | | | | | | | |" + System.lineSeparator() +
                "  3: | | | | | | | | | | |" + System.lineSeparator() +
                "  2: | | | | | | | | | | |" + System.lineSeparator() +
                "  1: | | | | | | | | | | |" + System.lineSeparator() +
                "  0: | | | | | | | | | | |" + System.lineSeparator() +
                " -1: ---------------------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }

    @Test
    void toStringWithOneAnimal() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "r", "f", "f", "f" }
        );
        var map = new RectangularMap(10, 5);
        new Animal(map);
        map.run(directions);

        var expected = " y\\x  0 1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
                "  5: ---------------------" + System.lineSeparator() +
                "  4: | | | | | | | | | | |" + System.lineSeparator() +
                "  3: | | | | | | | | | | |" + System.lineSeparator() +
                "  2: | | | | | | | | | | |" + System.lineSeparator() +
                "  1: | | | | | | | | | | |" + System.lineSeparator() +
                "  0: | | |ˬ| | | | | | | |" + System.lineSeparator() +
                " -1: ---------------------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }

    @Test
    void toStringWithTwoAnimals() {
        var directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f" }
        );
        var map = new RectangularMap(10, 5);
        new Animal(map);
        new Animal(map, new Vector2d(3, 4));
        map.run(directions);

        var expected = " y\\x  0 1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
                "  5: ---------------------" + System.lineSeparator() +
                "  4: | | | |^| | | | | | |" + System.lineSeparator() +
                "  3: | | | | | | | | | | |" + System.lineSeparator() +
                "  2: | | | | | | | | | | |" + System.lineSeparator() +
                "  1: | | | | | | | | | | |" + System.lineSeparator() +
                "  0: | | |ˬ| | | | | | | |" + System.lineSeparator() +
                " -1: ---------------------" + System.lineSeparator();
        assertEquals(expected, map.toString());
    }
}
