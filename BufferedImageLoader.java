import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader
{
   private BufferedImage image;
   
   public BufferedImage loadImage(String path)
   {
      //Attempt to load image, else throw an error Exception...
      try
      {
         image = ImageIO.read(getClass().getResource(path));
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return image;
   }
}