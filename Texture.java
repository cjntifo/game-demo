import java.awt.image.BufferedImage;

public class Texture
{
   SpriteSheet bs, ps, es, cs;
   private BufferedImage block_sheet = null;
   private BufferedImage player_sheet = null;
   private BufferedImage effect_sheet = null;
   private BufferedImage coin_sheet = null;
   
   public BufferedImage[] block = new BufferedImage[2];
   public BufferedImage[] effect = new BufferedImage[2];
   public BufferedImage[] player = new BufferedImage[7];
   public BufferedImage[] player_jump = new BufferedImage[7];
   public BufferedImage[] coin = new BufferedImage[8];
   
   public Texture()
   {
      BufferedImageLoader loader = new BufferedImageLoader();
      
      try
      {
         block_sheet = loader.loadImage("res/minesprite.png");
         player_sheet = loader.loadImage("res/player.png");
         effect_sheet = loader.loadImage("res/energy_effect.png");
         coin_sheet = loader.loadImage("res/coinTex.png");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      
      bs = new SpriteSheet(block_sheet);
      ps = new SpriteSheet(player_sheet);
      es = new SpriteSheet(effect_sheet);
      cs = new SpriteSheet(coin_sheet);
      
      getTextures();
      
      /*coin[0] = loader.loadImage("res/coins/coin_01.png");
      coin[1] = loader.loadImage("res/coins/coin_02.png");
      coin[2] = loader.loadImage("res/coins/coin_03.png");
      coin[3] = loader.loadImage("res/coins/coin_04.png");
      coin[4] = loader.loadImage("res/coins/coin_05.png");
      coin[5] = loader.loadImage("res/coins/coin_06.png");
      coin[6] = loader.loadImage("res/coins/coin_07.png");
      coin[7] = loader.loadImage("res/coins/coin_08.png");*/
   }
   
   private void getTextures()
   {
      //Dirt block          (col, row, width, height)
      block[0] = bs.grabImage(2, 1, 32, 32);
      //Grass block
      block[1] = bs.grabImage(2, 1, 80, 80);
      
      //Facing Right
      player[0] = ps.grabImage(1, 1, 32, 64);   //Idle frame
      player[1] = ps.grabImage(2, 1, 32, 64);   //Walking animation for player
      player[2] = ps.grabImage(3, 1, 32, 64);   //Walking animation for player
      player[3] = ps.grabImage(4, 1, 32, 64);   //Walking animation for player
      player[4] = ps.grabImage(5, 1, 32, 64);   //Walking animation for player
      player[5] = ps.grabImage(6, 1, 32, 64);   //Walking animation for player
      player[6] = ps.grabImage(7, 1, 32, 64);   //Walking animation for player
      
      player_jump[0] = ps.grabImage(8, 2, 32, 64);
      player_jump[1] = ps.grabImage(9, 2, 32, 64);
      player_jump[2] = ps.grabImage(10, 2, 32, 64);
      
      effect[0] = es.grabImage(1, 1, 30, 30);
      
      loadSingleImages(coin, "res/coins/coin_");
   }
   
   private BufferedImage[] loadSingleImages(BufferedImage[] images, String filePath)
   {
      BufferedImageLoader loader = new BufferedImageLoader();
      
      for(int i = 0; i < images.length; i++)
      {
         images[i] = loader.loadImage(filePath + "0" + (i + 1) + ".png");
      }
      
      return images;
   }
}