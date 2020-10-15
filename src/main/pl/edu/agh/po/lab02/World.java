// Maksymilian Wojnar
package pl.edu.agh.po.lab02;

public class World {
    public static void main(String[] args) {
        var position1 = new Vector2d(1,2);
        System.out.println(position1);

        var position2 = new Vector2d(-2,1);
        System.out.println(position2);

        System.out.println(position1.add(position2));
        System.out.println();

        System.out.println(MapDirection.NORTH.toString());
        System.out.println(MapDirection.WEST.toString());
        System.out.println();

        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.WEST.next());
        System.out.println();

        System.out.println(MapDirection.NORTH.previous());
        System.out.println(MapDirection.WEST.previous());
        System.out.println();

        System.out.println(MapDirection.NORTH.toUnitVector());
        System.out.println(MapDirection.WEST.toUnitVector());
    }
}
