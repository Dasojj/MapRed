import java.awt.*;

public class Hitbox {

    private int [] blackList = {Map.BRICK, Map.WATER, Map.WALL};
    //private int [] busterList = {Map.BRICK, Map.WATER, Map.WALL};
    private int padding;
    Sprite body;
    HitboxListener listener;

    public Hitbox (Sprite s, int padding, HitboxListener listener) {
        this.body = s;
        this.padding = padding;
        this.listener = listener;
    }

    public void update (int ms) {
        checkMapCollision(ms);
    }

    private void checkMapCollision(int ms) {
        Point[] points = getCornerPoints();
        for (Point p : points) {
            int col = Map.getColByX(p.x);
            int row = Map.getRowByY(p.y);
            int block = Map.getBlock(row, col);
            //int buster = Map.getBuster(row, col);
            for (int curBlock : blackList) {
                if (curBlock == block) {
                    if(listener != null) {

                        HitboxEvent event = new HitboxEvent();
                        event.block = block;
                        event.x = p.x;
                        event.y = p.y;
                        event.ms = ms;
                        listener.onCollision(event);
                    }
                    return;
                }
            }
        }
        return;
    }



    private Point[] getCornerPoints () {
        double x = body.getX();
        double y = body.getY();
        Point p0 = new Point((int)x + padding, (int)y + padding);
        Point p1 = new Point((int)x + Map.BLOCK_SIZE - padding, (int)y + padding);
        Point p2 = new Point((int)x + Map.BLOCK_SIZE - padding, (int)y + Map.BLOCK_SIZE - padding);
        Point p3 = new Point((int)x + padding, (int)y + Map.BLOCK_SIZE - padding);
        return new Point[] {p0, p1, p2, p3};

    }














}
