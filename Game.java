import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable
{
   private static final long serialVersionUID = -6261436164361361187L;
   private boolean running = false;
   private Thread thread;
   
   public static int LEVEL;
   public static int WIDTH, HEIGHT;
   
   public BufferedImage level = null, level2 = null, clouds = null;
   
   Handler handler;
   Camera camera;
   static Texture texture;   
   //Random rand = new Random();
   
   private void init()
   {
      LEVEL = 1;
      WIDTH = getWidth();
      HEIGHT = getHeight();
      
      texture = new Texture();
      
      //Loading the level..
      BufferedImageLoader loader = new BufferedImageLoader();
      level = loader.loadImage("res/game_level.png");
      clouds = loader.loadImage("res/cloud1.png");
      
      camera = new Camera(0, 0);
      handler = new Handler(camera);     
      
      handler.loadImageLevel(level);
      
      //handler.addObject(new Player(100, 100, handler, ObjectId.Player));
      //handler.createLevel();    
      
      this.addKeyListener(new KeyInput(handler));
      
      /*////////////////////////////////////////////////
      
      addKeyListener(new KeyListener(){
         @Override
         public void keyTyped(KeyEvent e){}
         @Override
         public void keyPressed(KeyEvent e)
         {  
            GameObject player;
            for(int i = 0; i < handler.object.size(); i++)
            {if(handler.object.get(i).getId() == ObjectId.Player){player = handler.object.get(i);player.keyPressed(e);}}
         }
         @Override
         public void keyReleased(KeyEvent e)
         {
            GameObject player;
            for(int i = 0; i < handler.object.size(); i++)
            {if(handler.object.get(i).getId() == ObjectId.Player){player = handler.object.get(i);player.keyReleased(e);}}
         }
      });*/ 
   }
   
   public synchronized void start()
   {
      if(running)
         return;
         
      running = true;
      thread = new Thread(this);
      thread.start();
   }
   
   public void run()
   {
      init();
      
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      long timer = System.currentTimeMillis();
      int updates = 0;
      int frames = 0;
      
      //Start game loop...
      while(running)
      {
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         while(delta >= 1)
         {
            tick();
            updates++;
            delta--;
         }
         render();
         frames++;
         
         if(System.currentTimeMillis() - timer > 1000)
         {
            timer += 1000;
            System.out.println("FPS: " + frames + " TICKS: " + updates);   /*fps = frames; ticks = updates;*/
            frames = 0;
            updates = 0; 
         }
      }
   }
   
   private void tick()
   {
      //Have handler class tick every object in the game...
      handler.tick();
      
      //Pass player object to camera class and have the camera object tick...
      for(int i = 0; i < handler.object.size(); i++)
      {
         if(handler.object.get(i).getId() == ObjectId.Player)
            camera.tick(handler.object.get(i));
      }
   }
   
   private void render()
   {
      //Setup screen buffer...
      BufferStrategy bs = this.getBufferStrategy();
      if(bs == null)
      {
         this.createBufferStrategy(3);
         return;
      }
      
      Graphics g = bs.getDrawGraphics();
      Graphics2D g2d = (Graphics2D) g;
      
      //////////////////////////////////

      ////////////  Draw  //////////////
      
      //Set's background screen...
      g.setColor(new Color(25, 191, 255));
      g.fillRect(0, 0, getWidth(), getHeight());
      
      /////////////////////////////////////
      
      g2d.translate(camera.getX(), camera.getY() + 100);
     
      for(int xx = 0; xx < clouds.getWidth() * 3; xx += clouds.getWidth())
         g.drawImage(clouds, xx, -150, this);
               
      //Have the handler class render every object in the program.
      handler.render(g);
      
      g2d.translate(-camera.getX(), -(camera.getY() + 100));
      
      /////////////////////////////////
      
      //Dispose of Graphics object
      g.dispose();
      //Switch screen buffer
      bs.show();
   }
   
   public static Texture getInstance()
   {
      return texture;
   }
   
   public static void main(String[] args)
   {
      //Create JFrame window and pass a new instance of the Game class.
      Window window = new Window(600, 600, "Carl's Magical New Engine", new Game());
   }
}  