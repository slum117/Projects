public class rectangle extends GeometricObject {
   
   double width;
   double height;
   
   // constructors
   public rectangle() {
      super("white",false);
      height = 0;
      width = 0;
   }
   public rectangle(double width, double height) {
      super("white",false);
      this.width = width;
      this.height = height;
   }
   
   public rectangle(double width, double height, String color, boolean filled) {
      super(color,filled);
      this.width = width;
      this.height = height;
   }
   
   // accessors
   public double getWidth() {
      return width;
   }
   
   public double getHeight() {
      return height;
   }
   
   public String toString() {
      String retStr = String.format("Shape: Rectangle Height: %.2f Width: %.2f "
      , height, width);
      retStr += super.toString();
      return retStr;
   }
   
   public double getArea() {
      return width*height;
   }
   
   public double getPerimeter() {
      return (width*2)+(height*2);
   }
   
   // mutators
   public void setHeight(double height) {
      this.height = height;
   }
   
   public void setWidth(double width) {
      this.width = width;
   }
   
}