import java.awt.*;

public class Sprite {

    private int startFrame, lastFrame;
    private int currentFrame;
    private int elapsedFrameTime, frameTime = 80;

    private double x, y;
    private double alpha, speed;

    public Sprite(double x, double y, int startFrame, int lastFrame,  double alpha, double speed) {
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
        this.lastFrame = lastFrame;
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void paint(Graphics g) {

        ImageHelper.paint(g, currentFrame, Camera.getScreenX(x), Camera.getScreenY(y), this.alpha);
    }

    private void updateXY(int ms) {
        x = x + Math.cos(alpha) * speed * ms / 1000.0;
        y = y + Math.sin(alpha) * speed * ms / 1000.0;
    }
    private void updateFrames(int ms) {
        elapsedFrameTime += ms;
        if(elapsedFrameTime >= frameTime) {
            elapsedFrameTime = 0;
            nextFrame();
        }

    }
    private void reverseXY(int ms) {
        x = x - Math.cos(alpha) * speed * ms / 1000.0;
        y = y - Math.sin(alpha) * speed * ms / 1000.0;
    }
    private void reverseFrames(int ms) {
        elapsedFrameTime -= ms;
        if(elapsedFrameTime < 0) {
            elapsedFrameTime = frameTime;
            prevFrame();
        }

    }

    private void nextFrame() {
        currentFrame++;
        if(currentFrame > lastFrame) {
            currentFrame = startFrame;
        }
    }

    private void prevFrame() {
        currentFrame--;
        if(currentFrame < startFrame) {
            currentFrame = lastFrame;
        }
    }

    public void update(int ms) {
        updateXY(ms);
        updateFrames(ms);
    }

    public void reverse(int ms){
        reverseFrames(ms);
        reverseXY(ms);
    }

}
