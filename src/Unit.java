import java.awt.*;

public class Unit implements HitboxListener {
    protected Sprite bottom;
    protected Sprite top;
    protected Hitbox hb;
    protected Bot bot;

    public Unit(int x, int y) {
        this.bottom = new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.top =  new Sprite(x, y, Map.EMPTY, Map.EMPTY, 0, 0 );
        this.hb = new Hitbox(bottom, 5, this);
        this.bot = new Bot(this);
    }

    public void up () {
        bottom.setAlpha(-Math.PI/2);
        top.setAlpha(-Math.PI/2);
    }
    public void down () {
        bottom.setAlpha(Math.PI/2);
        top.setAlpha(Math.PI/2);
    }
    public void left () {
        bottom.setAlpha(Math.PI);
        top.setAlpha(Math.PI);
    }
    public void right () {
        bottom.setAlpha(0);
        top.setAlpha(0);
    }
//    public void show() {
//        bottom.show();
//    }

    public void update(int ms) {
        bottom.update(ms);
        top.update(ms);
        hb.update(ms);
        bot.update(ms);
    }
    public void paint(Graphics g) {
        bottom.paint(g);
        top.paint(g);
    }
    public double getX(){
        return this.bottom.getX();
    }
    public double getCenterX() {
        return bottom.getX() + Map.BLOCK_SIZE/2;
    }
    public double getY(){
        return this.bottom.getY();
    }
    public double getCenterY() {
        return bottom.getY() + Map.BLOCK_SIZE/2;
    }
    public void setBot(Bot bot) {
        this.bot = bot;
    }
    public void setTopAlpha(double alpha) {
        top.setAlpha(alpha);
    }

    @Override
    public void onCollision(HitboxEvent event) {
        bottom.reverse(event.ms);
        top.reverse(event.ms);
    }
}
