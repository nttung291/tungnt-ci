package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class PlayerSpell extends GameObject{

    public PlayerSpell() {
        position = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/1.png"));
    }
    public void run(){
        this.position.addUp(0,-10);
    }

}
