package pl.edu.agh.po.lab05;

import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab07.IPositionChangedPublisher;
import pl.edu.agh.po.lab07.MapBoundary;

import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private final int numberOfGrasses;
    private final MapBoundary boundary;

    public GrassField(int numberOfGrasses) {
        this.numberOfGrasses = numberOfGrasses;
        this.boundary = new MapBoundary();
        addGrass();
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
        super.addElementToMap(element);
        boundary.addMapElement(element);

        if (element instanceof IPositionChangedPublisher publisher)
            publisher.addObserver(boundary);
    }

    @Override
    public Vector2d getLowerLeft() {
        return boundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return boundary.getUpperRight();
    }
}
