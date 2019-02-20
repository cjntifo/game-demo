public class Camera
{
   private float x, y;
   
   Camera(float x, float y)
   {
      this.x = x;
      this.y = y;
   }
   
   public void tick(GameObject player)
   {
      //Clip the camera to the player's current position...
      x = -player.getX() + Game.WIDTH / 2;
      
      //x--;   //Move one pixel to the right every tick...
   }
   
   public float getX() {return x;}
   public float getY() {return y;}
   
   public void setX(float x)  {this.x = x;}
   public void setY(float y)  {this.y = y;}
}