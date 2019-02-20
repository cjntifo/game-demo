import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends GameObject
{
   private float width = 32, height = 64;
   private float gravity = 0.35f;
   private boolean falling = true;
   private boolean jumping = false;
   
   private final float MAX_SPEED = 10;   
   private Handler handler;
   
   Texture texture = Game.getInstance();
   
   private Animation playerWalk;
   
   public Player(float x, float y, Handler handler, ObjectId id)
   {
      super(x, y, id);
      this.handler = handler;
      
      playerWalk = new Animation(5, texture.player[1], texture.player[2], 
         texture.player[3], texture.player[4], texture.player[5], texture.player[6]);
   }
   
   public void tick(LinkedList<GameObject> object)
   {
      //Move the player, according to the current velocity.
      x += velX;
      y += velY;
      
      if(falling || jumping)
      {
         velY += gravity;
         
         if(velY > MAX_SPEED)
            velY = MAX_SPEED;
      }
      
      //Check for any collisions...
      Collision(object);
      
      playerWalk.runAnimation();
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
            if(getBoundsTop().intersects(tempObject.getBounds()))
            {
               //If player has a top collision, stop upward velocity.
               y = tempObject.getY() + 32;
               velY = 0;
            }
            
            if(getBoundsBase().intersects(tempObject.getBounds()))
            {
               //If player has hit the ground, set falling/ jumping to false and stop velocity.
               falling = false;
               jumping = false;
               velY = 0;
               
               //Clip the player to ground in case Collision() called after ground is passed.
               y = tempObject.getY() - 64;
            }
            else
            {
               //If no top/bottom collision, set falling to true so gravity can act.
               falling = true;
            }
               
            if(getBoundsLeft().intersects(tempObject.getBounds()))
            {
               x = tempObject.getX() + 32;
            }
            
            if(getBoundsRight().intersects(tempObject.getBounds()))
            {
               x = tempObject.getX() - 32;
            }
         }
      }  
   }
   
   public void render(Graphics g)
   {
      if(velX != 0)
         playerWalk.drawAnimation(g, (int)x, (int)y, (int)width, (int)height);
      else
         g.drawImage(texture.player[0], (int)x, (int)y, (int)width, (int)height, null);
      
      //g.setColor(Color.blue);
      //g.fillRect((int)x, (int)y, (int)width, (int)height);
      
      //Render players collision bounds
      g.setColor(Color.RED);
      Graphics2D g2d = (Graphics2D) g;
      
      g2d.draw(getBoundsBase());
      g2d.draw(getBoundsTop());
      g2d.draw(getBoundsLeft());
      g2d.draw(getBoundsRight());
   }
   
   public Rectangle getBounds()
   {
      return new Rectangle((int)x,(int)y, (int)width/2, (int)height/2);
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