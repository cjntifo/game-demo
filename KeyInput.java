import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/*import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;*/


public class KeyInput extends KeyAdapter
{
   Handler handler;
   
   public KeyInput(Handler handler)
   {
      this.handler = handler;
   }
   
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      //When a key is pressed iterate to the player object...
      for(int i = 0; i < handler.object.size(); i++)
      {
         GameObject tempObject = handler.object.get(i);
         
         if(tempObject.getId() == ObjectId.Player)
         {
            //Move Right
            if(key == KeyEvent.VK_D) tempObject.setVelX(5);
            
            //Move Left
            if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
            
            //Jump (if not jumping already)
            if(key == KeyEvent.VK_SPACE && !tempObject.isJumping())
            {
               tempObject.setJumping(true);
               tempObject.setVelY(-10);
            }                   
         }
      }
      
      if(key == KeyEvent.VK_ESCAPE)
      {
         System.exit(0);
      }
   }
   
   public void keyReleased(KeyEvent e)
   {
      int key = e.getKeyCode();
      
      //When key is released iterate to the player object...
      for(int i = 0; i < handler.object.size(); i++)
      {
         GameObject tempObject = handler.object.get(i);
         
         if(tempObject.getId() == ObjectId.Player)
         {
            //Stop moving Right
            if(key == KeyEvent.VK_D) tempObject.setVelX(0);
            
            //Stop moving Left
            if(key == KeyEvent.VK_A) tempObject.setVelX(0);
         }
      }
   }
}