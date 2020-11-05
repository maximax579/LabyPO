package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.IWorldMap;
import pl.edu.agh.po.lab04.MapAnimator;
import pl.edu.agh.po.lab04.MapVisualiser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals;

    protected MapAnimator mapAnimator;
    protected MapVisualiser mapVisualiser;

    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    public AbstractWorldMap() {
        this.animals = new LinkedList<>();
        this.mapVisualiser = new MapVisualiser(this);
        this.mapAnimator = new MapAnimator();
    }

    protected abstract void addElementToMap(IMapElement element);

    protected abstract void moveAnimal(Animal animal, MoveDirection direction);

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        addElementToMap(animal);
        mapAnimator.addFrame(this);
        return true;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        var iterator = animals.iterator();
        if (!iterator.hasNext())
            return;

        for (var direction : directions) {
            if (!iterator.hasNext())
                iterator = animals.iterator();

            var animal = iterator.next();
            moveAnimal(animal, direction);
            mapAnimator.addFrame(this);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(lowerLeft, upperRight);
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }
}