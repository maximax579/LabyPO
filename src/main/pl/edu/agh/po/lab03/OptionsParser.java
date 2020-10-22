package pl.edu.agh.po.lab03;

import pl.edu.agh.po.lab02.MoveDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] array) {
        return Arrays.stream(array)
                .map(OptionsParser::mapDirection)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static MoveDirection mapDirection(String s) {
        return switch (s) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> null;
        };
    }
}
