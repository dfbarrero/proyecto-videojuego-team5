/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoCamerino extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo;
    private Sound fail;
    private boolean derecha, mover,baile;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/camerino.png"); //Imagen de fondo
        ClaseEstatica.setSonidoPaso(new Sound("Musica/Sonidos/fx_paso.ogg"));
        derecha = true;
        mover = false;
        baile=false;
        ang = 200f;
        fail = new Sound("Musica/Sonidos/fx_fail.ogg");
        ClaseEstatica.setFail(fail);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (mover) {
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().draw(x, y);

            }else  if(baile){
                ClaseEstatica.getPersonaje().getBaile().draw(x, y);
            }else {
                ClaseEstatica.getPersonaje().getAnimI().draw(x, y);
            }
        } else {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        }
        //g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        mover = true;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            ClaseEstatica.getPersonaje().getMusicH8().play();;

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            ClaseEstatica.getPersonaje().getMusicH8().pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_B)) {
            derecha=false;
            baile=true;
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getBaile().start();
        }
        if (container.getInput().isKeyDown(Input.KEY_DELETE)) 
            game.enterState(1);
        if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimI().start();
            if (x > 217) {
                x -= delta * 0.4f;
                derecha = false;
                baile=false;
                if (!ClaseEstatica.getSonidoPaso().playing()) {
                    ClaseEstatica.getSonidoPaso().play();
                }
            }
        } else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if (x < 796) {
                x += delta * 0.4f;
                derecha = true;
                baile=false;
                if (!ClaseEstatica.getSonidoPaso().playing()) {
                    ClaseEstatica.getSonidoPaso().play();
                }
            }
        } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if (y > 271) {
                y -= delta * 0.4f;
                derecha = true;
                baile=false;
                if (!ClaseEstatica.getSonidoPaso().playing()) {
                    ClaseEstatica.getSonidoPaso().play();
                }
            }
        } else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if (y < 480) {
                y += delta * 0.4f;
                derecha = true;
                baile=false;
                if (!ClaseEstatica.getSonidoPaso().playing()) {
                    ClaseEstatica.getSonidoPaso().play();
                }
            } else if ((y >= 480) && (x >= 485) && (x <= 530)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
                game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
        } else {
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
            } else {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimI().setCurrentFrame(0);
            }
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        this.x = 354; //Coordenadas donde empieza el personaje
        this.y = 270;
        mover=false;
        baile=false;
        ClaseEstatica.getPersonaje().getAnimD().stop();
        ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
    }

}
