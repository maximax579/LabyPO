package pl.edu.agh.po.lab04;

import pl.edu.agh.po.lab02.MoveDirection;
import pl.edu.agh.po.lab02.Vector2d;
import pl.edu.agh.po.lab03.Animal;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

// Zdecydowałem się na użycie tablicy Animal[][] map (a tym samym świadomego dopuszczenia wartości null w kodzie),
// ponieważ uznałem, że takie rozwiązanie jest najprostsze.
//
// Alternatywą było użycie ArrayList<ArrayList<Optional<Animal>>>, ale wiązało się to ze skompikowaną
// inicjalizacją (nie znalazłem sposobu na odgórne ustalenie rozmiaru - czegoś na kształt map = new Animal[width][height],
// zatem w pętli musiałbym dodawać poszczególne wiersze lub kolumny, co miałoby wpływ na wydajność). Dodatkowo,
// wydaje mi się, że sama konstrukcja tego typu i dostawanie się do jej poszczególnych pól było na tyle skomplikowane,
// że mogły zaciemniać właściwe przeznaczenie kodu.
//
// Inna opcja to użycie jednowymiarowej ArrayList<Optional<Animal>> - inicjalizacja jest prostsza,
// ale uważam, że dostęp do poszczególnych komórek przez wyliczanie odpowiedniej pozycji (np. x * width + y)
// może być źródłem błędów i utrudniać zrozumienie kodu dla nowych programistów.

public class RectangularMap implements IWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    private final Animal[][] map;
    private final MapVisualiser mapVisualiser;
    private final MapAnimator mapAnimator;
    private final List<Animal> animals;

    public RectangularMap(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Szerokość i wysokość mapy nie może być ujemna");

        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width - 1, height - 1);

        animals = new LinkedList<>();
        mapVisualiser = new MapVisualiser(this);
        map = new Animal[width][height];

        mapAnimator = new MapAnimator();
        mapAnimator.addFrame(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        placeAnimalOnMap(animal);
        animals.add(animal);
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

            moveAnimal(iterator.next(), direction);
            mapAnimator.addFrame(this);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return getCell(position) != null;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(getCell(position));
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(lowerLeft, upperRight);
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }

    private Animal getCell(Vector2d position) {
        if (!(position.follows(lowerLeft) && position.precedes(upperRight)))
            return null;

        return map[position.x][position.y];
    }

    private void moveAnimal(Animal animal, MoveDirection direction) {
        removeAnimalFromMap(animal);
        animal.move(direction);
        placeAnimalOnMap(animal);
    }

    private void placeAnimalOnMap(Animal animal) {
        map[animal.getPosition().x][animal.getPosition().y] = animal;
    }

    private void removeAnimalFromMap(Animal animal) {
        map[animal.getPosition().x][animal.getPosition().y] = null;
    }
}
