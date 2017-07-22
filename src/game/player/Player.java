package game.player;

import game.Utils;
import game.bases.*;
import game.inputs.InputManager;

import java.util.ArrayList;

/**
 * Created by Nttung PC on 7/11/2017.
 */
public class Player extends GameObject{

    Contraints contraints;
    InputManager inputManager;

    FrameCounter coolDownCounter;
    boolean spellDisabled;

    Vector2D verlocity;
    public Player() {
        this.position = new Vector2D();
        this.verlocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        this.coolDownCounter = new FrameCounter(17);
    }

    public void move(int dx, int dy) {
        this.position.addUp(dx, dy);
        contraints.make(this.position);
    }

    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    @Override
    public void run() {
        move();
        castSpell();
    }

    private void castSpell() {
        if (inputManager.xPressed){
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0, -40));
            GameObject.add(playerSpell);
        }
    }

    private void move() {
        this.verlocity.set(0,0);
        if (inputManager.leftPressed){
            this.verlocity.x-=5;
        }
        if (inputManager.rightPressed){
            this.verlocity.x+=5;
        }
        if (inputManager.upPressed){
            this.verlocity.y -=5;
        }
        if (inputManager.downPressed){
            this.verlocity.y +=5;
        }
        this.position.addUp(verlocity);
        this.contraints.make(this.position);
    }


    public void castSpell(ArrayList<PlayerSpell> playerSpells) {
        if (!spellDisabled) {

//            playerSpells.add(playerSpell);
//            spellDisabled = true;
        }
    }

    public void coolDown() {
        if(spellDisabled){
            boolean status = coolDownCounter.run();
            if (status){
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
