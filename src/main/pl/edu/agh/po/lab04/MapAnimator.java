package pl.edu.agh.po.lab04;

import java.util.LinkedList;
import java.util.List;

public class MapAnimator {
    private final List<String> animation;

    public MapAnimator() {
        this.animation = new LinkedList<>();
    }

    public List<String> getAnimation() {
        return animation;
    }

    public void addFrame(IWorldMap map) {
        animation.add(map.toString());
    }
}
