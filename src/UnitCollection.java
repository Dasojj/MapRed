import java.awt.*;
import java.util.ArrayList;

public class UnitCollection {

    private static ArrayList <Unit> list = new ArrayList<>(100);

    public static void add (Unit t) {
        list.add(t);
    }


    public static Unit getTarget(Unit owner ) {
        if(list.get(1) == owner) {
            return list.get(0);
        }
        return list.get(1);
    }

    public static void spawn () {
        int r, c;
        r = 4;
        c = 3;
        Unit t;
        if (list.size() == 1) {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, Color.BLUE);
        }
        else {
            t = new Tank(c * Map.BLOCK_SIZE, r * Map.BLOCK_SIZE, Color.RED);
        }
        list.add(t);

    }

    public static void update(int ms) {

        for (Unit u : list) {
            u.update(ms);
        }

    }
    public static void paint(Graphics g) {
        for (Unit u : list) {
            u.paint(g);
        }

    }



}
