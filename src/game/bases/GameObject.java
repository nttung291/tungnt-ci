package game.bases;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Nttung PC on 7/18/2017.
 */
public class GameObject {
    public Vector2D position;
    public  ImageRenderer renderer;

    private static Vector<GameObject> gameObjects = new Vector<>();
    public  static Vector<GameObject> newGameObjects = new Vector<>();

    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
    }
    public static void renderALL(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            gameObject.render(g2d);
        }
    }
    public static void runall(){
        for (GameObject gameObject : gameObjects){
            gameObject.run();
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }
    public GameObject() {
        this.position = new Vector2D();
        this.renderer = null;
    }

    public void render(Graphics2D g2d){
        if (renderer != null){
            renderer.render(g2d,this.position);
        }
    }

    public void run(){

    }
}
