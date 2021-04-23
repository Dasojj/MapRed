import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        ImageHelper.crop(1, 0, Map.WALL);
        ImageHelper.crop(0, 3, Map.WATER);
        ImageHelper.crop(0, 2, Map.GROUND);
        ImageHelper.crop(1, 1, Map.BRICK);
        ImageHelper.crop(0, 4, Map.EMPTY);
        ImageHelper.crop(6, 3, Map.SMALLB);
        ImageHelper.crop(4, 2, Map.MEDIUMB);
        ImageHelper.crop(4, 3, Map.BIGB);
        ImageHelper.crop(6, 0, Map.SPEEDB);


        // 10 - 19 Красная платформа
        for(int i = 10; i<=19; i++) ImageHelper.crop(2, i-10, i);

        // 30 - 39 Синяя платформа
        for(int i = 30; i<=39; i++) ImageHelper.crop(10, i-30, i);

        // 20 Красная башня
        ImageHelper.crop(3, 0, 20);

        // 40 Синяя башня
        ImageHelper.crop(3, 2, 40);

        new Window();

    }
}

class Window extends JFrame {
    public Window()  {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(MAXIMIZED_BOTH);
        setBounds(0,0,600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        GamePanel panel = new GamePanel();
        add(panel);
        addKeyListener(panel);
        addMouseMotionListener(panel);
        addMouseListener(panel);
        revalidate();
    }
}
