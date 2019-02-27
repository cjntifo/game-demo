import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.image.BufferedImage;

public class Handler
{
   public LinkedList<GameObject> object = new LinkedList<GameObject>();
   
   private GameObject tempObject;
   private Camera camera;
   
   public BufferedImage level = null, level2 = null, clouds = null;
   
   private int x_max = 0, y_max = 0;
   
   public Handler(Camera camera)
   {
      this.camera = camera;
      
      BufferedImageLoader loader = new BufferedImageLoader();
      level = loader.loadImage("res/game_level.png");
      level2 = loader.loadImage("res/level.png");
      clouds = loader.loadImage("res/cloud1.png");
   }
   
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
   
   public void loadImageLevel(BufferedImage image)
   {
      int w = image.getWidth();
      int h = image.getHeight(); 
      //System.out.println("Width " + w + " Height " + h);
      
      for(int xx = 0; xx < h; xx++)
      {
         for(int yy = 0; yy < w; yy++)
         {
            //Retrieve the current pixel
            int pixel = image.getRGB(xx, yy);
            
            //Extract RGB values from pixel
            // pixel : 0xA3 0x41 0x28 0x76
            // pixel :   A   R    G   B
            
            //int alpha = (pixel >> 24) & 0xff;
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;
            
            if(red == 255 && green == 255 && blue == 255)
            {
               //Must be a white pixel
               addObject(new Block(xx*32, yy*32, 1, ObjectId.Block)); 
            }
            else if(red == 128 && green == 128 && blue == 128)
            {
               //Must be a grey pixel
               addObject(new Block(xx*32, yy*32, 0, ObjectId.Block)); 
            }
            else if(red == 0 && green == 0 && blue == 255)
            {
               //Must be a blue pixel
               addObject(new Player(xx*32, yy*32, this, camera, ObjectId.Player)); 
            }
            else if(red == 255 && green == 255 && blue == 0)
            {
               //Must be a yellow pixel
               addObject(new Coin(xx*32, yy*32, ObjectId.Coin)); 
            }
            else if(red == 255 && green == 0 && blue == 255)
            {
               //Must be a purple pixel
               addObject(new Flag(xx*32, yy*32, ObjectId.Flag)); 
            }
            
            if(!(red == 0 && green == 0 && blue == 0))
            {
               if(y_max < yy)
                  y_max = yy;
                  
               if(x_max < xx)
                  x_max = xx;
            }
         }      
      }
      
      drawGameBounds();
   }
   
   private void drawGameBounds()
   {
      System.out.println("XMAX: " + x_max + " Y MAX: " + y_max);
      int offset = 1;
      
      //Create top layer of BoundingBlocks
      for(int xx = 0; xx <= (x_max + offset); xx++)
      {
         addObject(new BoundingBlock((xx - offset)*32, (-offset * 32), ObjectId.BoundingBlock));
      }
      
      //Create left layer of BoundingBlocks
      for(int yy = 0; yy <= (y_max + offset); yy++)
      {
         addObject(new BoundingBlock((-offset * 32), (yy - (offset - 1))*32, ObjectId.BoundingBlock));
      }
      
      //Create bottom layer of BoundingBlocks
      for(int xx = 0; xx <= (x_max + offset); xx++)
      {
         addObject(new BoundingBlock((xx + (offset - 1))*32, (y_max + offset)*32, ObjectId.BoundingBlock));
      }
      
      //Create right layer of BoundingBlocks
      for(int yy = 0; yy <= (y_max + offset); yy++)
      {
         addObject(new BoundingBlock((x_max + offset)*32, (yy - offset)*32, ObjectId.BoundingBlock));
      }
   }
   
   public void switchLevel()
   {
      clearLevel();
      camera.setX(0);
      
      switch(Game.LEVEL)
      {
         case 1:
            loadImageLevel(level2);
            Game.LEVEL++;
            break;
         default:
            loadImageLevel(level);
            break;
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
   
   public void clearLevel()
   {
      object.clear();
   }
   
   public void createLevel()
   {
      for (int xx = 0; xx < Game.WIDTH + 32 * 16; xx += 32)  addObject(new Block(xx, Game.HEIGHT - 32, 0, ObjectId.Block));
         
      for (int yy = 0; yy < Game.HEIGHT; yy += 32) addObject(new Block(0, yy, 0, ObjectId.Block));

      //for (int yy = 0; yy < Game.HEIGHT; yy += 32) addObject(new Block(Game.WIDTH - 32, yy, 0, ObjectId.Block));
         
      for (int xx = 0; xx < 7 * 32; xx += 32) addObject(new Block((6 * 32) + xx, Game.WIDTH - 32 * 5, 0, ObjectId.Block));
      
      for (int xx = 0; xx < 5 * 32; xx += 32) addObject(new Block((15 * 32) + xx, Game.WIDTH - 32 * 8, 0, ObjectId.Block));
      
      for (int xx = 0; xx < 14 * 32; xx += 32) addObject(new Block((22 * 32) + xx, Game.WIDTH - 32 * 12, 0, ObjectId.Block));
      
      ///////////////////////
      
      for (int xx = 0; xx < Game.WIDTH + 32 * 10; xx += 32)  addObject(new Block((45 * 32) + xx, Game.HEIGHT - 32, 0, ObjectId.Block));

   }
}