package Screens;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
 
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Entradas.Entradas;
import personajes.Astolfo;
import personajes.Mordred;
import personajes.personajePrefab;
import utiles.Config;
import utiles.Imagen;
 
import utiles.Render;
public class Escenarios implements Screen,TieneFondo{
   SpriteBatch b;
   Rectangle player1Box;
   Rectangle player2Box;
   private Imagen fightstage;
   Hud hud;
   float ts;
   float period= 0.9f;
   Mordred mordred;
   Astolfo astolfo;
   Entradas entradas = new Entradas(this);
  private String e;
    public Escenarios(String escenario, personajePrefab p1, personajePrefab p2){
    this.e = escenario;
    System.out.println(p1);
    setFondo();
    
 }
    @Override
    public void show() {
        
        b= Render.batch;
        hud= new Hud(b);
        mordred = new Mordred();   
        astolfo = new Astolfo();                                  
        Gdx.input.setInputProcessor(entradas);
        player2Box = new Rectangle(astolfo.img.getX(), astolfo.img.getY(), astolfo.img.getWidth(), astolfo.img.getHeight());
       
    }

    @Override
    public void render(float delta) {
        inputSelec();
        mordred.setInput(opc);
        mordred.update(delta);
        
       // mordred.elapsedTime += delta;
        
        Render.cleaner();
       b.begin();
        fightstage.dibujar();
       astolfo.img.dibujar();
       
       b.draw(mordred.bruh.getKeyFrame(mordred.elapsedTime, true), 500, 500, 75, 100);
     

        movimiento();
         b.end(); 
       hud.mostrarHud();
     hud.getCuentaAtras().setText(hud.getSec());
        player2Box.setPosition(astolfo.img.getX(), astolfo.img.getY());
    }



     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    public void movimiento(){
        		
		if(entradas.isLeft()){ 	//entradas = input entrys
   
            
			}
		if(entradas.isRight()){
        
		
            
 
//     }
// }
     
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
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
    
}
