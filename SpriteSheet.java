import java.awt.image.BufferedImage;

public class SpriteSheet
{
   private BufferedImage image;
   
   public SpriteSheet(BufferedImage image)
   {
      this.image = image;
   }
   
   public BufferedImage grabImage(int col, int row, int width, int height)
   {
      //Get Subimage from SpriteSheet
      BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
      return img;
   }
   
   public BufferedImage grabSpecificImage(int x, int y, int width, int height)
   {
      //Get Subimage from SpriteSheet
      BufferedImage img = image.getSubimage(x, y, width, height);
      return img;
   }
}
