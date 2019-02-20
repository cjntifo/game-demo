import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;

public class Block extends GameObject
{
   Texture texture = Game.getInstance();
   private int type;
   
   public Block(float x, float y, int type, ObjectId id)
   {
      super(x, y, id);
      this.type = type;
   }
   
   public void tick(LinkedList<GameObject> object)
   {
   
   }
   
   public void render(Graphics g)
   {
      if(type == 0)
      {
         g.drawImage(texture.block[0], (int)x, (int)y, 32, 32, null);
      }
      else if(type == 1)
      {
         g.drawImage(texture.block[1], (int)x, (int)y, 32, 32, null);
      }
      else
      {
         //Render block as a 32x32 white square...
         g.setColor(Color.white);
         g.drawRect((int)x, (int)y, 32, 32);
      }
   }
   
   public Rectangle getBounds()
   {
      //Return Rectangle for Collision dectection...
      return new Rectangle((int)x, (int)y, 32, 32);
   }
   
   public void setJumping(boolean jumping){} //Blocks cannot jump...
   public boolean isJumping(){return false;} //Blocks cannot jump...
}