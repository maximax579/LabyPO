package pl.edu.agh.po.lab03;

import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Animal {orientacja=" + direction + ", pozycja=" + position + "}";
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> this.position = correctMove(this.direction.toUnitVector());
            case BACKWARD -> this.position = correctMove(this.direction.toUnitVector().opposite());
        }
    }

    private Vector2d correctMove(Vector2d move) {
        var result = this.position.add(move);
        var isMoveCorrect = result.follows(new Vector2d(0, 0)) && result.precedes(new Vector2d(4, 4));
        return isMoveCorrect ? result : this.position;
    }
}
