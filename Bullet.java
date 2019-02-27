import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;

public class Bullet extends GameObject
{
   private float width = 32, height = 32;
   private int facing;
   
   private Handler handler;
   Texture texture = Game.getInstance();
   
   Bullet(float x, float y, int velX, Handler handler, ObjectId id)
   {
      super(x, y, id);
      
      this.handler = handler;
      this.velX = velX;
      this.facing = (velX > 0) ? 1 : -1;
   }
   
   public void tick(LinkedList<GameObject> object)
   {
      x += velX;
      
      Collision(object);
   }
   
   private void Collision(LinkedList<GameObject> object)
   {
      for(int i = 0; i < handler.object.size(); i++)
      {
         GameObject tempObject = handler.object.get(i);
         
         //Checks collision against every block in the mesh...
         if(tempObject.getId() == ObjectId.Block)
         {
            //Collision Bounds
            if(getBounds().intersects(tempObject.getBounds()))
            {
               handler.removeObject(tempObject);
               handler.removeObject(this);
               //System.out.println("Number of objects: " + handler.object.size());
            }
         }
         else if(tempObject.getId() == ObjectId.BoundingBlock)
         {
            if(getBounds().intersects(tempObject.getBounds()))
            {
               handler.removeObject(this);
               //System.out.println("Number of objects: " + handler.object.size());
            }
         }
      }
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