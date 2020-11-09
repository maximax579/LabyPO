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
    private final HashMap<Vector2d, List<IMapElement>> map;

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
            return false;

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
        var animals = elements.stream()
                .filter(el -> el instanceof Animal)
                .map(el -> (Animal) el)
                .collect(Collectors.toList());

        var iterator = animals.iterator();
        if (!iterator.hasNext())
            return;

        for (var direction : directions) {
            if (!iterator.hasNext())
                iterator = animals.iterator();

            moveAnimal(iterator.next(), direction);
            mapAnimator.addFrame(this);
        }
    }

    protected void moveAnimal(Animal animal, MoveDirection direction) {
        getElementsAtPosition(animal.getPosition()).remove(animal);
        animal.move(direction);
        getElementsAtPosition(animal.getPosition()).add(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        var elementsAtPosition = getElementsAtPosition(position);
        Object objectAt = null;

        for (var element : elementsAtPosition) {
            if (element instanceof Animal)
                return Optional.of(element);
            else
                objectAt = element;
        }

        return Optional.ofNullable(objectAt);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position).orElse(null) instanceof Animal);
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(lowerLeft, upperRight);
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }
}