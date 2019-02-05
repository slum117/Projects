public class circle extends GeometricObject {
   
   double radius;
   
   // constructors
   public circle() {
      super("white",false);
      radius = 1;
   }
   
   public circle(double radius, String color, boolean filled) {
      super(color,filled);
      this.radius = radius;
   }
   
   // accessors
   public double getRadius() {
      return radius;
   }
   
   public double getDiameter() {
      return radius*2;
   }
   
   public String toString() {
      String retStr = String.format("Shape: Circle Radius: %.2f Diameter: %.2f "
      , radius, radius*2);
      retStr += super.toString();
      return retStr;
   }
   
   public double getArea() {
      return Math.PI*radius*radius;
   }
   
   public double getPerimeter() {
      return 2*Math.PI*radius;
   }
   
   // mutators
   public void setRadius(double radius) {
      this.radius = radius;
   }
   
}