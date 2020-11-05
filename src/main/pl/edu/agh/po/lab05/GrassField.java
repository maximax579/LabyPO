package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab04.IWorldMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {

    private final int numberOfGrasses;
    private final List<Grass> grasses;

    public GrassField(int numberOfGrasses) {
        this.numberOfGrasses = numberOfGrasses;
        this.grasses = new LinkedList<>();

        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(0, 0);

        addGrass();
        this.mapAnimator.addFrame(this);
    }

    private void addGrass() {
        var random = new Random();
        var bound = (int) Math.sqrt(10 * numberOfGrasses) + 1;
        Vector2d position;

        for (int i = 0; i < numberOfGrasses; i++) {
            do {
                position = new Vector2d(random.nextInt(bound), random.nextInt(bound));
            } while (isOccupied(position));
            addElementToMap(new Grass(position));
        }
    }

    @Override
    protected void addElementToMap(IMapElement element) {
        var position = element.getPosition();
        changeCorners(position);

        if (element instanceof Grass grass)
            grasses.add(grass);
        else if (element instanceof Animal animal)
            animals.add(animal);
    }

    @Override
    protected void moveAnimal(Animal animal, MoveDirection direction) {
        animal.move(direction);
        changeCorners(animal.getPosition());
    }

    protected void changeCorners(Vector2d position) {
        if (position.upperRight(upperRight).follows(upperRight))
            upperRight = position.upperRight(upperRight);

        if (position.lowerLeft(lowerLeft).precedes(lowerLeft))
            lowerLeft = position.lowerLeft(lowerLeft);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (var animal : animals) {
            if (animal.getPosition().equals(position))
                return false;
        }

        return true;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (var animal : animals) {
            if (animal.getPosition().equals(position))
                return Optional.of(animal);
        }

        for (var grass : grasses) {
            if (grass.getPosition().equals(position))
                return Optional.of(grass);
        }

        return Optional.empty();
    }
}
