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
    private int playerY;
    private int backgroundY;

    private boolean checkUp,checkDown,checkRight,checkLeft;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;

    public GameWindow() {
        setupWindow();
        loadImgage();
        setupPlayer();
        backgroundY = this.getHeight()-backGround.getHeight();
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
        setupInput();
        this.setVisible(true);
    }

    private void setupPlayer() {
        playerX = (backGround.getWidth()-Player.getWidth())/2;
        playerY = this.getHeight()-Player.getHeight();
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        checkRight = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        checkLeft = true;
                        break;
                    case KeyEvent.VK_UP:
                        checkUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        checkDown = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        checkRight = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        checkLeft = false;
                        break;
                    case KeyEvent.VK_UP:
                        checkUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        checkDown = false;
                        break;
                }
            }
        });
    }

    private void Move(){
        if (checkRight == true && playerX < backGround.getWidth()-Player.getWidth()){
            playerX+=5;
        }
        else if (checkLeft == true && playerX>0){
            playerX-=5;
        }
        if(checkUp == true && playerY > Player.getHeight()){
            playerY-=5;
        }
        else if(checkDown == true && playerY < 800-Player.getHeight()){
            playerY+=5;
        }
    }

    public void Run(){
        while(true){
            try {
                Thread.sleep(17);
                backBufferGraphic2D.setColor(Color.black);
                backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());
                backBufferGraphic2D.drawImage(backGround,0,backgroundY,null);
                backBufferGraphic2D.drawImage(Player,playerX,playerY,null);
                changeBackGround();
                Move();
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

    private void changeBackGround(){
        while(backgroundY < 0){
            backgroundY += 2;
            break;
        }
    }

    @Override
    public void paint(Graphics g) {

    }
}
