package Game;

import sun.awt.image.PixelConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nttung PC on 7/9/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage backGround;
    BufferedImage Player;
    private int playerX;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;

    public GameWindow() {
        setupWindow();
        loadImgage();
        playerX = (backGround.getWidth()-Player.getWidth())/2;
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
        setupImput();
        this.setVisible(true);
    }

    private void setupImput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        if (playerX < backGround.getWidth()-Player.getWidth()){
                            playerX+=5;
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        if (playerX>0){
                            playerX-=5;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void Run(){
        while(true){
            try {
                Thread.sleep(17);
                backBufferGraphic2D.setColor(Color.black);
                backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());
                backBufferGraphic2D.drawImage(backGround,0,this.getHeight()-backGround.getHeight(),null);
                backBufferGraphic2D.drawImage(Player,playerX,this.getHeight()-Player.getHeight(),null);
                Graphics2D g2d = (Graphics2D) this.getGraphics();
                g2d.drawImage(backBufferImage,0,0,null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadImgage() {
        try {
            backGround = ImageIO.read (new File("assets/images/background/0.png"));
            Player = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupWindow() {
        this.setSize(600,800);
        this.setResizable(false);
        this.setTitle("Touhou - Remade by tungNT");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);
            }
        });
    }

    @Override
    public void paint(Graphics g) {

    }
}
