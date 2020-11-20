package pl.edu.agh.po.lab07;

import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab05.IMapElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MapBoundary implements IPositionChangeObserver {

    private final List<IMapElement> elementsByX;
    private final List<IMapElement> elementsByY;

    public MapBoundary() {
        elementsByX = new ArrayList<>();
        elementsByY = new ArrayList<>();
    }

    public void addMapElement(IMapElement element) {
        elementsByX.add(element);
        sortByX();

        elementsByY.add(element);
        sortByY();
    }

    public Vector2d getUpperRight() {
        if (elementsByY.isEmpty() || elementsByX.isEmpty())
            return new Vector2d(0, 0);

        return new Vector2d(getLastXPosition(), getLastYPosition());
    }

    public Vector2d getLowerLeft() {
        if (elementsByY.isEmpty() || elementsByX.isEmpty())
            return new Vector2d(0, 0);

        return new Vector2d(getFirstXPosition(), getFirstYPosition());
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        if ((oldPosition.x == getFirstXPosition() || oldPosition.x == getLastXPosition()) &&
                oldPosition.x != newPosition.x) {
            sortByX();
        }

        if ((oldPosition.y == getFirstYPosition() || oldPosition.y == getLastYPosition()) &&
                oldPosition.y != newPosition.y) {
            sortByY();
        }
    }

    private int getFirstXPosition() {
        return elementsByX.get(0).getPosition().x;
    }

    private int getLastXPosition() {
        return elementsByX.get(elementsByX.size() - 1).getPosition().x;
    }

    private int getFirstYPosition() {
        return elementsByY.get(0).getPosition().y;
    }

    private int getLastYPosition() {
        return elementsByY.get(elementsByY.size() - 1).getPosition().y;
    }

    private void sortByX() {
        elementsByX.sort(Comparator.comparingInt(el -> el.getPosition().x));
    }

    private void sortByY() {
        elementsByY.sort(Comparator.comparingInt(el -> el.getPosition().y));
    }
}
