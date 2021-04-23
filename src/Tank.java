import java.awt.*;

public class Tank extends Unit {
    public Tank(int x, int y, Color color) {
        super(x, y);
        if(color==Color.RED) {
            bottom = new Sprite(x, y, 10, 19, 0, 150);
            top = new Sprite(x, y, 20, 20, 0, 0);
        }
        else if(color== Color.BLUE){
            bottom = new Sprite(x, y, 30, 39, 0, 150);
            top = new Sprite(x, y, 40, 40, 0, 0);
        }
        this.hb = new Hitbox(bottom, 3, this);
        bot = new TankBot(this);
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        top.setX( bottom.getX() );
        top.setY( bottom.getY() );
    }

    @Override
    public void onCollision(HitboxEvent event) {
        super.onCollision(event);
    }
}
