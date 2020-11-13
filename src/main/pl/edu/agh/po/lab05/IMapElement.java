package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.Vector2d;

public interface IMapElement {
    default boolean isMovable() {
        return false;
    }

    Vector2d getPosition();
    boolean isBlocking();
    int priority();
}
