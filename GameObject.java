import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.event.KeyEvent;

public abstract class GameObject
{
   protected float x, y;
   protected ObjectId id;  
   protected float velX, velY;
   protected boolean jumping, falling;
   
   GameObject(float x, float y, ObjectId id)
   {
      this.x = x;
      this.y = y;
      this.id = id;
   }
   
   public abstract void tick(LinkedList<GameObject> object);
   public abstract void render(Graphics g);
   public abstract Rectangle getBounds();
   
   public float getX() {return x;}
   public float getY() {return y;}
   public void setX(float x) {this.x = x;}
   public void setY(float y) {this.y = y;}
   
   public float getVelX() {return velX;}
   public float getVelY() {return velY;}
   public void setVelX(float velX) {this.velX = velX;}
   public void setVelY(float velY) {this.velY  = velY;}
   
   public ObjectId getId() {return id;}
   //public abstract void keyPressed(KeyEvent e);
   //public abstract void keyReleased(KeyEvent e);
   
   public abstract boolean isJumping();
   public abstract void setJumping(boolean jumpings);
}
            