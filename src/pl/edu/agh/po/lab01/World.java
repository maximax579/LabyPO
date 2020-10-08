// Maksymilian Wojnar
package pl.edu.agh.po.lab01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        var directions = argsToDirectionList(args);
        run(directions);

        System.out.println("Stop");
    }

    private static List<Direction> argsToDirectionList(String[] arr) {
        return Arrays.stream(arr)
                .map(World::stringToDirection)
                .collect(Collectors.toList());
    }

    private static Direction stringToDirection(String s) {
        return switch (s) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "l" -> Direction.LEFT;
            case "r" -> Direction.RIGHT;
            default -> null;
        };
    }

    private static void run(List<Direction> directions) {
        directions.stream()
                .filter(Objects::nonNull)
                .forEach(World::printDirection);
    }

    private static void printDirection(Direction direction) {
        System.out.println(
                switch (direction) {
                    case FORWARD -> "Zwierzak idzie do przodu";
                    case BACKWARD -> "Zwierzak idzie do tylu";
                    case LEFT -> "Zwierzak skreca w lewo";
                    case RIGHT -> "Zwierzak skreca w prawo";
                }
        );
    }
}
