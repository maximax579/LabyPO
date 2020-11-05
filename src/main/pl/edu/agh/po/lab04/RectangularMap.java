package pl.edu.agh.po.lab04;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;
import pl.edu.agh.po.lab05.AbstractWorldMap;
import pl.edu.agh.po.lab05.IMapElement;

import java.util.HashMap;
import java.util.Optional;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

    private final HashMap<Vector2d, IMapElement> map;

    public RectangularMap(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Szerokość i wysokość mapy nie może być ujemna");

        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width - 1, height - 1);

        map = new HashMap<>();
        mapAnimator.addFrame(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    protected void addElementToMap(IMapElement element) {
        map.put(element.getPosition(), element);

        if (element instanceof Animal animal)
            animals.add(animal);
    }

    @Override
    protected void moveAnimal(Animal animal, MoveDirection direction) {
        map.remove(animal.getPosition());
        animal.move(direction);
        map.put(animal.getPosition(), animal);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(map.get(position));
    }
}
