import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends GameObject
{
   private float width = 32, height = 64;
   private float gravity = 0.5f;
   private boolean falling = true;
   private boolean jumping = false;
   
   private Handler handler;
   
   private final float MAX_SPEED = 10;
   
   public Player(float x, float y, Handler handler, ObjectId id)
   {
      super(x, y, id);
      this.handler = handler;
   }
   
   public void tick(LinkedList<GameObject> object)
   {
      x += velX;
      y += velY;
      
      if(falling || jumping)
      {
         velY += gravity;
         
         if(velY > MAX_SPEED)
            velY = MAX_SPEED;
      }
      
      Collision(object);
   }
   
   private void Collision(LinkedList<GameObject> object)
   {
      for(int i = 0; i < handler.object.size(); i++)
      {
         GameObject tempObject = handler.object.get(i);
         
         if(tempObject.getId() == ObjectId.Block)
         {
            if(getBoundsBase().intersects(tempObject.getBounds()))
            {
               falling = false;
               jumping = false;
               y = tempObject.getY() - 64;
               velY = 0;
            }
            else
            {
               falling = true;
            }
         }
      }  
   }
   
   public void render(Graphics g)
   {
      g.setColor(Color.blue);
      g.fillRect((int)x, (int)y, (int)width, (int)height);
      
      g.setColor(Color.RED);
      Graphics2D g2d = (Graphics2D) g;
      
      
      g2d.draw(getBoundsBase());
      g2d.draw(getBoundsTop());
      g2d.draw(getBoundsLeft());
      g2d.draw(getBoundsRight());
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle((int)x,(int)y, (int)width, (int)height);
   }
   
   public Rectangle getBoundsBase()
   {
      return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), (int)( (int)y + (height/2) ), (int)width/2, (int)height/2);
   }
   
   public Rectangle getBoundsTop()
   {
      return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
   }
   
   public Rectangle getBoundsRight()
   {
      return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
   }
   
   public Rectangle getBoundsLeft()
   {
      return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
   }
   
   public boolean isJumping() {return jumping;}
   public boolean isfalling() {return falling;}
   
   public void setJumping(boolean jumping) {this.jumping = jumping;}
   public void setfalling(boolean falling) {this.falling = falling;}
   
   /*public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_D) setVelX(5);
      if(key == KeyEvent.VK_A) setVelX(-5);
      if(key == KeyEvent.VK_SPACE) setVelY(-10);
   }
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_D) setVelX(0);
      if(key == KeyEvent.VK_A) setVelX(0);
   }*/
}