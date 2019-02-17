import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferStrategy;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class Game extends Canvas implements Runnable
{
   private static final long serialVersionUID = -6261436164361361187L;
   private boolean running = false;
   private Thread thread;
   
   public static int WIDTH, HEIGHT;
   
   Handler handler;   
   //Random rand = new Random();
   
   private void init()
   {
      WIDTH = getWidth();
      HEIGHT = getHeight();
       
      handler = new Handler();
      handler.addObject(new Player(100, 100, handler, ObjectId.Player));
      handler.createLevel();    
       
      /*addKeyListener(new KeyListener(){
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

       
      this.addKeyListener(new KeyInput(handler));  
      //this.setFocusable(true);
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
            System.out.println("FPS: " + frames + " TICKS: " + updates);
            /*fps = frames;
            ticks = updates; */
            frames = 0;
            updates = 0; 
         }
      }
   }
   
   private void tick()
   {
      handler.tick();
   }
   
   private void render()
   {
      BufferStrategy bs = this.getBufferStrategy();
      if(bs == null)
      {
         this.createBufferStrategy(3);
         return;
      }
      
      Graphics g = bs.getDrawGraphics();
      //////////////////////////////////
      
      //Draw
      
      g.setColor(Color.black);
      g.fillRect(0, 0, getWidth(), getHeight());
      
      handler.render(g);
      
      /////////////////////////////////
      
      g.dispose();
      bs.show();
   }
   
   public static void main(String[] args)
   {
      Window window = new Window(600, 600, "Carl's Magical New Engine", new Game());
   }
}  