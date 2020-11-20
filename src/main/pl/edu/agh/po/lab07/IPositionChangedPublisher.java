package pl.edu.agh.po.lab07;

public interface IPositionChangedPublisher {
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
}
