package personajes;
 
 
 
import javax.swing.Action;
import javax.swing.Timer;
import javax.tools.Diagnostic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;

import utiles.Imagen;
 

 

public abstract class personajePrefab implements Action{
    protected TextureAtlas textureAtlas;
   protected float cargasuper;
   protected TextureRegion texRegion;
  protected float x, y, texHeight;
int texWidth;
Imagen img;
  protected int vidamax=100;
  protected int opc;
  private Imagen i;
  private  int vidaActual=100;
  public boolean a1, a2, a3, leftW = false;
  public enum Estado{CORRER, CORRERL, SALTO, ANIMACION, STUN, STANCE, AGACHADO, ATAQUED, ATAQUEM ,ATAQUEF, AEREO1, AEREO2, AEREO3 ,ESPECIAL1, ESPECIAL2, ESPECIAL3}
  protected Estado estadoActual, estadoAnterior;
  protected float statetimer=0;
  protected static Imagen spriteImagen;   
  public Rectangle box1;
  public TextureRegion currentFrame, previusFrame;
  public Animation<TextureRegion> intro;
  public Animation<TextureRegion> stance;
  public Animation<TextureRegion> win;
  public Animation<TextureRegion> walk;
  public Animation<TextureRegion> walkLeft;
  public Animation<TextureRegion> ataque1;
  public Animation<TextureRegion> ataque2;
  public Animation<TextureRegion> ataque3;
  public Animation<TextureRegion> ataque4;
  public Animation<TextureRegion> jump;
  public Animation<TextureRegion> crouch;
  public Animation<TextureRegion> fall;
  public Animation<TextureRegion> guard;
  public Animation<TextureRegion> dash;
  public Animation<TextureRegion> dmgTaken;
  public Animation<TextureRegion> air1;
  public Animation<TextureRegion> air2;
  public Animation<TextureRegion> air3;
  public Animation<TextureRegion> air4;
  public Animation<TextureRegion> special1;
  public Animation<TextureRegion> special2;
  public Animation<TextureRegion> special3;
  public Animation<TextureRegion> special4;
  public Animation<TextureRegion> special5;
  public Animation<TextureRegion> special6;
  public Animation<TextureRegion> noblePhantasm;
  public Animation<TextureRegion> vfx1;
  public Animation<TextureRegion> vfx2;
  public Animation<TextureRegion> vfx3;
  public Animation<TextureRegion> vfx4;
  private Timer tiempo;
  
 

  public void sethitbox(){
    img = new Imagen("Astolfo/Stance1.png");
    img.setPosition((Gdx.graphics.getWidth()/2), Gdx.graphics.getHeight()/2);
      box1 = new Rectangle(img.getX(), img.getY(), img.getAncho(), img.getAlto());
  }
    public personajePrefab(){
         tiempo= new Timer(200, this);
      
    } 
    
    protected void iniciar(){
        tiempo.start();
        
    }

    public void update(float dt){

    }
     public void setAnims() {
        
    }
    public Imagen getImagen() {
        return i;
    }
    public void setImagen(Imagen i){

        this.i = i;
        
    }
    public String getNombre(){
        return null;
        
    }
    public void setInput(int opc){
        this.opc=opc;
    }
    public void setX(float x){
        this.x = x;

    }
	public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }
	public float getY(){
        return y;
    }

    public float getW() {
        return img.getAncho();
    }
	public float getH() {
        return img.getAlto();
    }
    public int getVidaActual() {
        return vidaActual;
    }
	public int getVidamax() {
        return vidamax;
    }
 public void setVidaActual(int vidaActual) {
     this.vidaActual = vidaActual;
 }
 public void setVidamax(int vidamax) {
     this.vidamax = vidamax;
 }
 
 public float getCargasuper() {
     return cargasuper;
 }
	public Estado getEstado(){
        return estadoActual;
    }
    public void setEstado(Estado estado){
        this.estadoActual = estado;
    }
    public Estado getEstadoAnterior(){
        return estadoAnterior;
    }
    public void setEstadoAnterior(Estado estado){
        this.estadoAnterior = estado;
    }
  
    

}
