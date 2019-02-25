import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;

public class Flag extends GameObject
{
   Texture texture = Game.getInstance();
   
   public Flag(float x, float y, ObjectId id)
   {
      super(x, y, id);
   }
   
   public void tick(LinkedList<GameObject> object)
   {
   
   }
   
   public void render(Graphics g)
   {
      //g.drawImage(texture.block[0], (int)x, (int)y, 32, 32, null);
      //Render block as a 32x32 white square...
      g.setColor(Color.white);
      g.drawRect((int)x, (int)y, 32, 32);
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