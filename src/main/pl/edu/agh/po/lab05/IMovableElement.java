package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.MoveDirection;

public interface IMovableElement extends IMapElement {
    void move(MoveDirection direction);
}
