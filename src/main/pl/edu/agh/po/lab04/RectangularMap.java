package pl.edu.agh.po.lab04;

import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab05.AbstractWorldMap;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Width and height of map cannot be negative");

        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width - 1, height - 1);

        mapAnimator.addFrame(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!(position.follows(lowerLeft) && position.precedes(upperRight)))
            return false;

        return super.canMoveTo(position);
    }

    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }
}
