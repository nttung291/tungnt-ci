package game;
import game.background.BackGround;
import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.EnemySpawner;
import game.inputs.InputManager;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Nttung PC on 7/9/2017.
 */
public class GameWindow extends JFrame{
    public BackGround backGround;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphic2D;

    InputManager inputManager = new InputManager();

    public GameWindow() {
        setupWindow();
        addBackGround();
        addPlayer();
        setupImput();
        this.setVisible(true);
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();
    }

    public void addEnemySpwaner(){
        GameObject.add(new EnemySpawner());
    }

    public void addBackGround(){
        backGround = new BackGround();
        backGround.setPosition(0,this.getHeight()-backGround.renderer.image.getHeight());
        GameObject.add(backGround);
    }

    private void addPlayer(){
        Player player = new Player();
        player.setContraints(new Contraints(50,this.getHeight()-30,10,backGround.renderer.image.getWidth()-10));
        player.setInputManager(inputManager);
        player.position.set(backGround.renderer.image.getWidth()/2,this.getHeight()-50);
        GameObject.add(player);
    }
    private void setupImput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
               inputManager.keyReleased(e);
            }
        });
    }

    long lastUpdateTime;
    public void loop(){
        while(true){
            long curentTime = System.currentTimeMillis();
            if (curentTime - lastUpdateTime > 17){
                lastUpdateTime = curentTime;
                Run();
                Render();
            }
        }
    }

    private void Run() {
        GameObject.runall();
    }

    private void Render() {
        backBufferGraphic2D.setColor(Color.black);
        backBufferGraphic2D.fillRect(0,0,this.getWidth(),this.getHeight());
        backBufferGraphic2D.drawImage(backGround.renderer.image,0, (int) backGround.position.y,null);
        GameObject.renderALL(backBufferGraphic2D);
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage,0,0,null);
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
