import java.util.*;
public class GeometricObject {

   public String color = "";
   public boolean filled = false;
   public Date dateCreated;
   
   public GeometricObject(String color, boolean filled) {
      this.color = color;
      this.filled = filled;
      dateCreated = new Date();
   }
   
   //accessors
   public String getColor() {
      return color;
   }
   
   public boolean isFilled() {
      return filled;
   }
   
   public String toString() {
      String retVal = String.format("Color: %s Filled: %s",color, filled);
      return retVal;
   }
   //mutators
   public void setColor(String color) {
      this.color = color;
   }
   
   public void setFilled(boolean filled) {
      this.filled = filled;
   }
   
   public Date getDateCreated() {
      return dateCreated;
   }
   
}