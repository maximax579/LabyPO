package pl.edu.agh.po.lab03;

public class World {
    public static void main(String[] args) {
        var moves = OptionsParser.parse(args);
        var animal = new Animal();

        for (var move : moves) {
            animal.move(move);
            System.out.println(animal);
        }
    }
}
