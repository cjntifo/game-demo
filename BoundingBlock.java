import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

import java.util.LinkedList;

public class BoundingBlock extends GameObject
{
   public BoundingBlock(float x, float y, ObjectId id)
   {
      super(x, y, id);
   } 
   
   
   public void tick(LinkedList<GameObject> object)
   {
   
   }
   
   public void render(Graphics g)
   {
      g.setColor(Color.RED);
      g.drawRect((int) x, (int) y, 32, 32);
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle((int) x, (int) y, 32, 32);
   }
   
   public void setJumping(boolean jumping){} //Blocks cannot jump...
   public boolean isJumping(){return false;} //Blocks cannot jump...
   public void setFalling(boolean falling){} //Blocks cannot jump...
   public boolean isFalling(){return false;} //Blocks cannot jump...
   public void setFacing(int facing){}
   public int getFacing() {return facing;}
}

