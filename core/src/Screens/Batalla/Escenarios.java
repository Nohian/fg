package Screens.Batalla;


import java.security.Key;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import Entradas.Entradas;
import Entradas.direcciones;
import Online.server;
import Screens.Hud;
import Screens.HudBarra;
import Screens.TieneFondo;
import personajes.Astolfo;
import personajes.Atalante;
import personajes.Jeanne;
import personajes.Mordred;
import personajes.personajePrefab;
import personajes.personajePrefab.Estado;
import utiles.Config;
import utiles.Imagen;
import utiles.InputEvent;
import utiles.Render;
public class Escenarios implements Screen,TieneFondo,InputEvent{
   SpriteBatch b;

   protected Imagen fightstage;
   Hud hud;
   HudBarra hb;
    Pixmap pixmap = new Pixmap(128, 128, Pixmap.Format.RGBA8888);
      
   boolean animacion;
   int cont, cont2;
   float time, ts;
   float period= 0.9f;
   Mordred mordred;
   Astolfo astolfo;
   boolean a1, a2, a3, leftW = false;
   Entradas entradas = new Entradas(this);
   private String e;
   private int opc;
   float velocidad = 0f,velocidad2=0f;
   float  gravedad = 10f;
  private   personajePrefab p1;
  private  personajePrefab p2;
    public Escenarios(String escenario, personajePrefab p1, personajePrefab p2){
    this.e = escenario;
    this.p1=p1;
    this.p2=p2;
    Config.addListInput(this);
    setFondo();
    p1.setX(450);
    p1.setY(Config.HEIGHT/2);
    p2.setX(700);
    p2.setY(Config.HEIGHT/2);
    pixmap.setBlending(Pixmap.Blending.None);
    pixmap.setColor(Color.RED);
    
 }

    protected Escenarios(Imagen e2, personajePrefab p12, personajePrefab p22) {
    }
    @Override
    public void show() {

        b= Render.batch;
        hud= new Hud(b);
        hb= new HudBarra();

        Gdx.input.setInputProcessor(entradas);


    }
float a;
 @Override
    public void render(float delta) {
        time += delta;



        ts+=delta;
        Render.cleaner();
       b.begin();
       fightstage.dibujar();
        b.draw(new Texture((int)p1.getCollide().width,(int)p1.getCollide().height,Pixmap.Format.RGB565), p1.getCollide().getX(), p1.getCollide().getY());
        b.draw(new Texture((int)p2.getCollide().width,(int)p2.getCollide().height,Pixmap.Format.RGB565), p2.getCollide().getX(), p2.getCollide().getY());
        if (ts>.05f ) {
            colision(); 
            gestorEstados();
            ts=0;
                }
            
       

        b.end();

       
       hud.mostrarHud();
     hud.getCuentaAtras().setText(hud.getSec());

    //    if (hud.getSec()<=0) {
    //         Render.app.setScreen(new PeleaTerminada(this.fightstage,this.p1,this.p2));
    //         server.getHl().enviarAtodos(direcciones.PELEATERMINADA.getString());
    // }

     }
     public void gestorEstados (){
         switch (p1.getEstado()) {
            
            case ATAQUEF:
            System.out.println("voy a dibujar");
            pixmap.fillRectangle((int)p1.getCollide().getX(),(int) p1.getCollide().getY(), 55,48);
            b.draw(new Texture(pixmap), p1.getCollide().getX(), p1.getCollide().getY());
            
            
            break;
            case ATAQUEM:

          

            break;
            case ATAQUED:

           
        
            break;
            case AEREO1:
         

            break;
            case AEREO2:
          
            break;
            case AEREO3:
         
            break;
             default:
                 break;
         }

         switch (p2.getEstado()) {
           
            case ATAQUEF:
            pixmap.fillRectangle((int)p2.getCollide().getX(),(int) p2.getCollide().getY(), 55,48);
            b.draw(new Texture(pixmap), p2.getCollide().getX(), p2.getCollide().getY());
            
            
            
            
            break;
            case ATAQUEM:

          

            break;
            case ATAQUED:

           
        
            break;
            case AEREO1:
         

            break;
            case AEREO2:
          
            break;
            case AEREO3:
         
            break;
             default:
                 break;
         }
     }

     private void colision() {
        if (Intersector.overlaps(p1.getCollide(), p2.getCollide())) {
            System.out.println("se tocan");
            if (server.getHl().getDir().compareTo(direcciones.DERECHA)==0) {
                server.getHl().enviarAtodos(direcciones.POSX.getString()+",-15");
            Pjug().getCollide().setX(Pjug().getCollide().getX()+-15);
            
            }
            else if (server.getHl().getDir().compareTo(direcciones.IZQUIERDA)==0) {
                server.getHl().enviarAtodos(direcciones.POSX.getString()+",15");
            Pjug().getCollide().setX(Pjug().getCollide().getX()+15);
           
            }
            
            
        }

         p1.getCollide().setY(p1.getCollide().getY() + (velocidad -= gravedad));
         p2.getCollide().setY(p2.getCollide().getY() + (velocidad2 -= gravedad));
        if(p1.getCollide().getY() < Config.HEIGHT/2){
            p1.getCollide().setY(Config.HEIGHT/2);
            p1.setEstado(Estado.STANCE);
        }
        if(p2.getCollide().getY() <Config.HEIGHT/2){
            p2.getCollide().setY(Config.HEIGHT/2);
            p2.setEstado(Estado.STANCE);
        }

    }


public int inputSelec() {
    try {
        synchronized(entradas){
              entradas.wait(90);

        }

      } catch (InterruptedException e) {

          e.printStackTrace();
      }


        if (entradas.isDown()) {

            if (opc==0) {

                opc=3;

            }
            else{
                opc--;

            }
        }
        if (entradas.isUp()) {
            if(opc==3){
             opc=0;
            }
            else{
                opc++;

            }
        }

        return opc;
}




    @Override
    public void resize(int width, int height) {
        //

    }

    @Override
    public void pause() {
        //

    }

    @Override
    public void resume() {
        //

    }

    @Override
    public void hide() {
        //

    }

    @Override
    public void dispose() {

    Render.batch.dispose();
    hud.dispose();
    }
     @Override
    public void setFondo() {
        fightstage= new Imagen(e);
        fightstage.setSize(Config.tamanioDeAlgo(100, Config.WIDTH),Config.tamanioDeAlgo(100, Config.HEIGHT));
        fightstage.setPosition(Config.centrado(Config.WIDTH), Config.centrado(Config.HEIGHT));
    }
public personajePrefab Pjug(){
    if (server.getUsuario()==server.getUsuarios()[0]) {
         
        return p1;
        
    }

    else if(server.getUsuario()==server.getUsuarios()[1]){
       
         
        return p2;

    }
    return null;
}


    @Override
    public void handleInput() {


            switch (server.getHl().getDir()) {
                case IZQUIERDA:
                   server.getHl().enviarAtodos(direcciones.POSX.getString()+",-15");
                   Pjug().getCollide().setPosition(Pjug().getCollide().x-15, Pjug().getCollide().y);
                    
                    break;

                    case DERECHA:
   
                    server.getHl().enviarAtodos(direcciones.POSX.getString()+",15");
                    Pjug().getCollide().setPosition(Pjug().getCollide().x + 15, Pjug().getCollide().y);
                    break;
                    case ARRIBA:
                    server.getHl().getDir().POSY.setPOSY(50);
                    Pjug().getCollide().setPosition(Pjug().getCollide().x , Pjug().getCollide().y+100);
                    server.getHl().enviarAtodos(server.getHl().getDir().POSY.getString());
                    server.getHl().getDir().POSY.reposy();
                    
                    break;
                    case ENTER:


                    
                    break;
                    case ATAQUEF:
                    System.out.println("llego un ataque fuerte");
                    Pjug().setEstado(Estado.ATAQUEF);
                

                    break;
                    case ATAQUEM:

                    Pjug().setEstado(Estado.ATAQUEM);

                    break;
                    case ATAQUED:

                    Pjug().setEstado(Estado.ATAQUED);
                
                    break;
                    case AEREO1:
                    Pjug().setEstado(Estado.AEREO1);

                    break;
                    case AEREO2:
                    Pjug().setEstado(Estado.AEREO2);
                    break;
                    case AEREO3:
                    Pjug().setEstado(Estado.AEREO3);
                    break;

                default:
                    System.out.println("no me encuentro instruccion");
                    break;
            }


    }

}
