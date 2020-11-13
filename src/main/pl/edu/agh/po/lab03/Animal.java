package pl.edu.agh.po.lab03;

import pl.edu.agh.po.lab02.MapDirection;
import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab04.IWorldMap;
import pl.edu.agh.po.lab05.IMovableElement;

public class Animal implements IMovableElement {
    private static final Vector2d DEFAULT_POSITION = new Vector2d(2, 2);

    private MapDirection direction;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        if (!map.canMoveTo(initialPosition))
            throw new IllegalArgumentException("Cannot place animal on position " + initialPosition);

        this.direction = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.map.place(this);
    }

    public Animal(IWorldMap map) {
        this(map, DEFAULT_POSITION);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (direction) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "ˬ";
            case WEST -> "<";
        };
    }

    @Override
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
        return map.canMoveTo(result) ? result : this.position;
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public int priority() {
        return 0;
    }
}
