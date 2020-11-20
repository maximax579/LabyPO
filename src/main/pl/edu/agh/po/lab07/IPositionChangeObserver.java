package pl.edu.agh.po.lab07;

import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab05.IMapElement;

public interface IPositionChangeObserver {
    void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
