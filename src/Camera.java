public class Camera {

    private static double x = 0, y = 0;
    private static int w = 1933, h = 1040;

    public static double getLeft() {
        return x;
    }

    public static double getTop() {
        return y;
    }

    public static double getRight() {
        return x + w - 1;
    }

    public static double getBottom() {
        return y + h - 1;
    }

    public static void setPosition (double x, double y) {
        Camera.x = x;
        Camera.y = y;

        if (Camera.x < 0) {
            Camera.x = 0;
        }
        if (Camera.y < 0) {
            Camera.y = 0;
        }

        if (Camera.x+Camera.w > 3199) {
            Camera.x = 3199-Camera.w;
        }
        if(Camera.y+Camera.h > 3198){
            Camera.y = 3198-Camera.h;
        }
        // Ограничения справа и снизу

    }

    public static void move (double dX, double dY) {
        setPosition(x + dX, y + dY  );
    }

    public static double getWorldX (double screenX) {
        return (screenX + x);
    }
    public static double getWorldY (double screenY) {
        return (screenY + y);
    }

    public static int getScreenX (double wX) {
        return (int)(wX - x);
    }
    public static int getScreenY (double wY) {
        return (int)(wY - y);
    }

}
