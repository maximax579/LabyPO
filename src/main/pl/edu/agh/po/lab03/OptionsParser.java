package pl.edu.agh.po.lab03;

import pl.edu.agh.po.lab02.MoveDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] array) {
        return Arrays.stream(array)
                .map(OptionsParser::mapDirection)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private static Optional<MoveDirection> mapDirection(String s) {
        return switch (s) {
            case "f", "forward" -> Optional.of(MoveDirection.FORWARD);
            case "b", "backward" -> Optional.of(MoveDirection.BACKWARD);
            case "r", "right" -> Optional.of(MoveDirection.RIGHT);
            case "l", "left" -> Optional.of(MoveDirection.LEFT);
            default -> Optional.empty();
        };
    }
}
