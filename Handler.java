import java.awt.Graphics;
import java.util.LinkedList;

public class Handler
{
   public LinkedList<GameObject> object = new LinkedList<GameObject>();
   
   private GameObject tempObject;
   
   public void tick()
   {
      //Call tick() for every object in the game...
      for(int i = 0; i < object.size(); i++)
      {
         tempObject = object.get(i);
         
         tempObject.tick(object);
      }
   }
   
   public void render(Graphics g)
   {
      //Call render for every object in the game...
      for(int i = 0; i < object.size(); i++)
      {
         tempObject = object.get(i);
         
         tempObject.render(g);
      }
   }
   
   public void addObject(GameObject object)
   {
      //Add object to the game list...
      this.object.add(object);
   }
   
   public void removeObject(GameObject object)
   {
      //Remove object to the game list...
      this.object.remove(object);
   }
   
   public void createLevel()
   {
      for (int xx = 0; xx < Game.WIDTH + 32 * 16; xx += 32)  addObject(new Block(xx, Game.HEIGHT - 32, ObjectId.Block));
         
      for (int yy = 0; yy < Game.HEIGHT; yy += 32) addObject(new Block(0, yy, ObjectId.Block));

      //for (int yy = 0; yy < Game.HEIGHT; yy += 32) addObject(new Block(Game.WIDTH - 32, yy, ObjectId.Block));
         
      for (int xx = 0; xx < 7 * 32; xx += 32) addObject(new Block((6 * 32) + xx, Game.WIDTH - 32 * 5, ObjectId.Block));
      
      for (int xx = 0; xx < 5 * 32; xx += 32) addObject(new Block((15 * 32) + xx, Game.WIDTH - 32 * 8, ObjectId.Block));
      
      for (int xx = 0; xx < 14 * 32; xx += 32) addObject(new Block((22 * 32) + xx, Game.WIDTH - 32 * 12, ObjectId.Block));
      
      ///////////////////////
      
      for (int xx = 0; xx < Game.WIDTH + 32 * 10; xx += 32)  addObject(new Block((45 * 32) + xx, Game.HEIGHT - 32, ObjectId.Block));

   }
}