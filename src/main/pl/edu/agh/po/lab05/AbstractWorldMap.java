package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.IWorldMap;
import pl.edu.agh.po.lab04.MapAnimator;
import pl.edu.agh.po.lab04.MapVisualiser;
import pl.edu.agh.po.lab07.IPositionChangeObserver;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final List<IMapElement> elements;
    private final Map<Vector2d, List<IMapElement>> map;

    private final  MapVisualiser mapVisualiser;
    protected  MapAnimator mapAnimator;

    public AbstractWorldMap() {
        this.elements = new LinkedList<>();
        this.mapVisualiser = new MapVisualiser(this);
        this.mapAnimator = new MapAnimator();
        this.map = new HashMap<>();
    }

    public abstract Vector2d getLowerLeft();
    public abstract Vector2d getUpperRight();

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException("Cannot place animal on position " + animal.getPosition());

        animal.addObserver(this);
        addElementToMap(animal);
        return true;
    }

    protected void addElementToMap(IMapElement element) {
        getElementsAtPosition(element.getPosition()).add(element);
        elements.add(element);
        mapAnimator.addFrame(this);
    }

    private List<IMapElement> getElementsAtPosition(Vector2d position) {
        return map.computeIfAbsent(position, k -> new LinkedList<>());
    }

    @Override
    public void run(List<MoveDirection> directions) {
        var movableElements = elements.stream()
                .filter(IMovableElement.class::isInstance)
                .map(IMovableElement.class::cast)
                .collect(Collectors.toList());

        var iterator = movableElements.iterator();
        if (!iterator.hasNext())
            return;

        for (var direction : directions) {
            if (!iterator.hasNext())
                iterator = movableElements.iterator();

            iterator.next().move(direction);
            mapAnimator.addFrame(this);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<IMapElement> objectAt(Vector2d position) {
        var elementsAtPosition = getElementsAtPosition(position);
        return elementsAtPosition.stream().min(Comparator.comparing(IMapElement::priority));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        var object = objectAt(position);
        return object.isEmpty() || !object.get().isBlocking();
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(getLowerLeft(), getUpperRight());
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        getElementsAtPosition(oldPosition).remove(movedElement);
        getElementsAtPosition(newPosition).add(movedElement);
    }
}