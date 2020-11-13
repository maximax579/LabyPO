package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.IWorldMap;
import pl.edu.agh.po.lab04.MapAnimator;
import pl.edu.agh.po.lab04.MapVisualiser;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements IWorldMap {

    private final List<IMapElement> elements;
    private final Map<Vector2d, List<IMapElement>> map;

    private final  MapVisualiser mapVisualiser;
    protected  MapAnimator mapAnimator;

    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public AbstractWorldMap() {
        this.elements = new LinkedList<>();
        this.mapVisualiser = new MapVisualiser(this);
        this.mapAnimator = new MapAnimator();
        this.map = new HashMap<>();
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException("Cannot place animal on position " + animal.getPosition());

        addElementToMap(animal);
        mapAnimator.addFrame(this);
        return true;
    }

    protected void addElementToMap(IMapElement element) {
        var elementsAtPosition = getElementsAtPosition(element.getPosition());
        elementsAtPosition.add(element);
        elements.add(element);
    }

    private List<IMapElement> getElementsAtPosition(Vector2d position) {
        return map.computeIfAbsent(position, k -> new LinkedList<>());
    }

    @Override
    public void run(List<MoveDirection> directions) {
        var movableElements = elements.stream()
                .filter(IMapElement::isMovable)
                .map(el -> (IMovableElement) el)
                .collect(Collectors.toList());

        var iterator = movableElements.iterator();
        if (!iterator.hasNext())
            return;

        for (var direction : directions) {
            if (!iterator.hasNext())
                iterator = movableElements.iterator();

            moveElement(iterator.next(), direction);
            mapAnimator.addFrame(this);
        }
    }

    protected void moveElement(IMovableElement element, MoveDirection direction) {
        getElementsAtPosition(element.getPosition()).remove(element);
        element.move(direction);
        getElementsAtPosition(element.getPosition()).add(element);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        var elementsAtPosition = getElementsAtPosition(position);

        if (elementsAtPosition.isEmpty()) {
            return Optional.empty();
        }
        else {
            elementsAtPosition.sort(Comparator.comparing(IMapElement::priority));
            return Optional.ofNullable(elementsAtPosition.get(0));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        var object = objectAt(position);
        return object.isEmpty() || !((IMapElement) object.get()).isBlocking();
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(lowerLeft, upperRight);
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }
}