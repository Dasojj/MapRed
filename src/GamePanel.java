import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, MouseListener {
    KeyState keyState = new KeyState();
    long t1, t2;
    int currentBlock = 2;
    boolean isGen=true;
    BufferedImage tmp;
    Unit player = new Tank(450, 300, Color.RED);

    public GamePanel(){
        setFocusable(true);
        setBackground(Color.BLACK);
        t1 = System.currentTimeMillis();
        player.setBot(new Bot(player));
        for (int i = 0; i < 10; i++) {
            UnitCollection.spawn();
        }
    }

    private void controlGame() {

        if(keyState.keyDown(KeyEvent.VK_W)){
            if(isGen) Camera.move(0, -9);
            else{
                player.up();
                player.update(10);
            }
        }
        else if(keyState.keyDown(KeyEvent.VK_A)){
            if(isGen) Camera.move(-9, 0);
            else{
                player.left();
                player.update(10);
            }
        }
        else if(keyState.keyDown(KeyEvent.VK_S)){
            if(isGen) Camera.move(0, 9);
            else {
                player.down();
                player.update(10);
            }
        }
        else if(keyState.keyDown(KeyEvent.VK_D)){
            if(isGen) Camera.move(9, 0);
            else{
                player.right();
                player.update(10);
            }
        }
        else if(keyState.keyDown(KeyEvent.VK_3)){
            currentBlock=Map.BRICK;
        }
        else if(keyState.keyDown(KeyEvent.VK_2)){
            currentBlock=Map.WALL;
        }
        else if(keyState.keyDown(KeyEvent.VK_1)){
            currentBlock=Map.WATER;
        }
        else if(keyState.keyDown(KeyEvent.VK_4)){
            currentBlock = Map.GROUND;
        }
        else if(keyState.keyDown(KeyEvent.VK_5)){
            currentBlock=Map.SMALLB;
        }
        else if(keyState.keyDown(KeyEvent.VK_6)){
            currentBlock=Map.MEDIUMB;
        }
        else if(keyState.keyDown(KeyEvent.VK_7)){
            currentBlock=Map.BIGB;
        }
        else if(keyState.keyDown(KeyEvent.VK_8)){
            currentBlock = Map.SPEEDB;
        }
        else if(keyState.keyDown(KeyEvent.VK_Z)){
            Map.saveMatrix();
        }
        else if(keyState.keyDown(KeyEvent.VK_X)){
            Map.loadMatrix();
        }
        else if(keyState.keyDown(KeyEvent.VK_ENTER)){
            isGen=false;
        }
        keyState.update();
    }

    private void paintGame (Graphics g){
        Map.paint(g);
        player.paint(g);
        UnitCollection.paint(g);
    }

    private void updateGame(){
        if(!isGen) Camera.setPosition(player.getX()-getWidth()/2, player.getY()-getHeight()/2);
        UnitCollection.update(10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.fillRect(0, 0, 50, 25);
        controlGame();
        paintGame(g);

        updateGame();

        if(isGen == true) {
            try {
                tmp = ImageIO.read(new File("genins.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(tmp, (int) (this.getX()+this.getWidth()-0.19*this.getWidth()), 0, null);

        }

        addMouseListener(this);
        addKeyListener(this);

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int screenX, screenY;
        screenX = e.getX();
        screenY = e.getY();
        if(isGen==true) {
            if(currentBlock>=0 && currentBlock<=3) Map.spawnBlock(Camera.getWorldX(screenX), Camera.getWorldY(screenY), currentBlock);
            else if(currentBlock>=5 && currentBlock<=8) Map.spawnBuster(Camera.getWorldX(screenX), Camera.getWorldY(screenY), currentBlock);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyState.setKeyState( e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyState.setKeyState( e.getKeyCode(), false);
    }
}
