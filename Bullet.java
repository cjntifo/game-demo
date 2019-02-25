import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;

public class Bullet extends GameObject
{
   private float width = 32, height = 32;
   private int facing;
   Texture texture = Game.getInstance();
   
   Bullet(float x, float y, int velX, ObjectId id)
   {
      super(x, y, id);
      
      this.velX = velX;
      this.facing = (velX > 0) ? 1 : -1;
   }
   
   public void tick(LinkedList<GameObject> object)
   {
      x += velX;
   }
   
   public void render(Graphics g)
   {
      //g.setColor(Color.red);
      //g.fillRect((int)x, (int)y, 16, 16);
      
      int positionCorrection = (facing == -1) ? (int)width : 0;
      g.drawImage(texture.effect[0], (int)x + positionCorrection, (int)y, (int)width * facing, (int)height, null);
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle((int)x, (int)y, (int)width, (int)height);
   }
   
   public void setJumping(boolean jumping){} //Blocks cannot jump...
   public boolean isJumping(){return false;} //Blocks cannot jump...
   public void setFalling(boolean falling){} //Blocks cannot jump...
   public boolean isFalling(){return false;} //Blocks cannot jump...
   public void setFacing(int facing){}
   public int getFacing() {return facing;}
}