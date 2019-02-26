import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;

public class Coin extends GameObject
{
   Texture texture = Game.getInstance();
   private Animation coinRotate;
   
   private float width = 32, height = 32;
   
   public Coin(float x, float y, ObjectId id)
   {
      super(x, y, id);
      
      coinRotate = new Animation(5, texture.coin[0], texture.coin[1], texture.coin[2], 
         texture.coin[3], texture.coin[4], texture.coin[5], texture.coin[6],texture.coin[7]);
   }
   
   public void tick(LinkedList<GameObject> object)
   {
      coinRotate.runAnimation();
   }
   
   public void render(Graphics g)
   {
      //g.setColor(Color.yellow);
      //g.fillRect((int)x, (int)y, 32, 32);
      
      coinRotate.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
   }
   
   public Rectangle getBounds()
   {
      //Return Rectangle for Collision dectection...
      return new Rectangle((int)x, (int)y, 32, 32);
   }
   
   public void setJumping(boolean jumping){} //Blocks cannot jump...
   public boolean isJumping(){return false;} //Blocks cannot jump...
   public void setFalling(boolean falling){} //Blocks cannot jump...
   public boolean isFalling(){return false;} //Blocks cannot jump...
   public void setFacing(int facing){}
   public int getFacing() {return facing;}
   
}