/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
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
public class EstadoSeleccion extends BasicGameState {


    private Sprite ALFREDO;
    private Sprite MOLDOVA;
    private Sprite LUDWIG;
    private Image fondo, hudAlfredo, hudMoldova, hudMozart, backAlfredo, backMoldova, backMozart;
    private int indicador;
    private Sprite puntero;
    private Personaje AlfredoMercurio,LudwigvanMozart,MoldovaSax;
    private SpriteSheet spriteAlfredoD, spriteAlfredoI,spriteLudwigD,spriteLudwigI,spriteMoldovaD,spriteMoldovaI;
    private Animation alfredoD,alfredoI,ludwigD,ludwigI,moldovaD,moldovaI;
    private Music seleccionAlfredo;

    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        this.fondo = new Image("Design/escenario.jpg");
        ALFREDO = new Sprite("Design/FreddieStillBIG.png",500,380);
        MOLDOVA = new Sprite("Design/SaxGuyStillBIG.png", 500,380);
        LUDWIG = new Sprite("Design/BombinStillBIG.png", 500,380);
        //puntero = new Sprite("Design/cursor1.png", ALFREDO);
        this.indicador = 0;
        //personaje = new Personaje(200, "Alfredo Mercurio");
        Sound laser = new Sound(("Musica/Sonidos/fx_laser.ogg"));
        hudAlfredo = new Image("Design/battlev1UI.png");
        hudMoldova = new Image("Design/battlev2UI.png");
        hudMozart = new Image("Design/battlev3UI.png");
        backMozart = new Image("Design/battlebombin1.png");
        backAlfredo = new Image("Design/battlebombin1.png");
        backMoldova = new Image("Design/battlebombin1.png");
        
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67 ,164);
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        Music musicAlfredoB = new Music("Musica/rock_8bit.ogg", false);
        Music musicAlfredoH = new Music("Musica/rock_hall.ogg", false);
        
        spriteLudwigD = new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167);
        spriteLudwigI = new SpriteSheet("Design/BombinWalkSprite_V3.png", 71, 167);
        ludwigD = new Animation(spriteLudwigD,100);
        ludwigI = new Animation(spriteLudwigI,100);
        Music musicLudwigB = new Music("Musica/classic_battle.ogg", false);
        Music musicLudwigH = new Music("Musica/classic_hall.ogg", false);
        
        spriteMoldovaD = new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 67, 176);
        spriteMoldovaI = new SpriteSheet("Design/SaxGuyWalkSprite_V3.png", 67, 176);
        moldovaD = new Animation(spriteMoldovaD,100);
        moldovaI = new Animation(spriteMoldovaI,100);
        Music musicMoldovaB = new Music("Musica/classic_8bit.ogg", false);
        Music musicMoldovaH = new Music("Musica/jazz_hall.ogg", false);
        
        Ataque Guitarrazo = new Ataque(3000, 30, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 10, new Sound("Musica/Sonidos/fx_arock1.ogg"));
        Ataque Mama = new Ataque(65, 15, "Mama", "Inflingirá un daño brutal en los tímpanos del enemigo", 30, new Sound("Musica/Sonidos/fx_arock2.ogg"));
        Ataque DiscoPlatino = new Ataque(80, 7, "Disco de platino", "Lanzará uno de sus discos de platino a la yugular para causar un daño LETAL!!!", 90, new Sound("Musica/Sonidos/fx_arock3.ogg"));
        
        Ataque Saxofonazo = new Ataque(35, 30, "Saxofonazo", "Lanzará un saxofón para causar un daño leve", 10, new Sound("Musica/Sonidos/fx_ajazz1.ogg"));
        Ataque BaileSwing = new Ataque(50, 15, "Al ritmo del Swing", "Te romperá las caderas con solo mirarle bailar!!", 30, laser);
        Ataque SaxGuy = new Ataque(60, 7, "SaxGuy", "Usará la mítica canción de Eurovisión para causar un daño LETAL!!", 40, new Sound("Musica/Sonidos/fx_ajazz3.ogg"));
        
        Ataque Pianazo = new Ataque(15, 30, "Pianazo", "Lanzará un piano para causar un daño leve", 10, new Sound("Musica/Sonidos/fx_aclassic1.ogg"));
        Ataque MetricaExacta = new Ataque(20, 15, "Metrica Exacta", "Regañará al enemigo por no llevar el ritmo acorde e inflingirá daño por humillación", 30, new Sound("Musica/Sonidos/fx_aclassic2.ogg"));
        Ataque PelucoVictoriano = new Ataque(35, 7, "Peluco Victoriano", "Lanzará su tremenda peluca para destrozar los sueños capilares del enemigo, causando un daño LETAL!!!", 40, new Sound("Musica/Sonidos/fx_aclassic3.ogg"));
        
        LudwigvanMozart = new Personaje(400,"Ludwin van Mozart",new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167),ludwigD,ludwigI, musicLudwigB, musicLudwigH, hudMozart, backMozart);
        LudwigvanMozart.getAtaques().add(Pianazo);
        LudwigvanMozart.getAtaques().add(MetricaExacta);
        LudwigvanMozart.getAtaques().add(PelucoVictoriano);
       
        AlfredoMercurio = new Personaje(10000, "Alfredo Mercurio", new SpriteSheet("Design/FreddieStillBIG.png", 69, 164), alfredoD, alfredoI, musicAlfredoB, musicAlfredoH, hudAlfredo, backAlfredo);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        AlfredoMercurio.getAtaques().add(Mama);
        AlfredoMercurio.getAtaques().add(DiscoPlatino);
        
        MoldovaSax = new Personaje(300, "Moldova Sax", new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 70, 176), moldovaD, moldovaI, musicMoldovaB, musicMoldovaH, hudMoldova, backMoldova);
        MoldovaSax.getAtaques().add(Saxofonazo);
        MoldovaSax.getAtaques().add(BaileSwing);
        MoldovaSax.getAtaques().add(SaxGuy);
        ClaseEstatica.setAtaqueAcertado(true);
    }
        

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if(!ClaseEstatica.getMusicaMenu().playing())
            ClaseEstatica.getMusicaMenu().play();
        switch (indicador) {
            case 0:
                ALFREDO.draw();
                break;
            case 1:
                MOLDOVA.draw();
                break;
            case 2:
                LUDWIG.draw();
                break;
            default:
                break;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        
        
        if(entrada.isKeyPressed(Input.KEY_RIGHT)){
            if (indicador==0){
                indicador=1;
            }
            else if(indicador==1){
                indicador=2;
            }
        }else if(entrada.isKeyPressed(Input.KEY_LEFT)){
            if(indicador==1){
                indicador=0;
            }
            else if(indicador==2){
                indicador=1;
            }
            
        }
        else if(entrada.isKeyPressed(Input.KEY_ESCAPE)){
            game.enterState(0,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
            switch (indicador) {
                case 0:
                    ClaseEstatica.setPersonaje(AlfredoMercurio);
                    game.enterState(2,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    break;
                case 1:
                    ClaseEstatica.setPersonaje(MoldovaSax);
                    game.enterState(2,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    break;
                case 2:
                    ClaseEstatica.setPersonaje(LudwigvanMozart);
                    game.enterState(2,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    break;
                default:
                    break;
            }
        } 
    }
}
