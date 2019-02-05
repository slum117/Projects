public class Triangle extends GeometricObject {
   double side1 = 1;
   double side2 = 1;
   double side3 = 1;
   
   
   
   // constructor
   public Triangle() {
      super("white",false);
      side1 = 1.0;
      side2 = 1.0;
      side3 = 1.0;
   }
   
   public Triangle(double side1, double side2, double side3) {
      super("white",false);
      this.side1 = side1;
      this.side2 = side2;
      this.side3 = side3;
   }
   
   public Triangle(double side1, double side2, double side3, String color, boolean filled) {
      super(color,filled);
      this.side1 = side1;
      this.side2 = side2;
      this.side3 = side3;
   }
   
   // accessors
   public double getSide1() {
      return side1;
   }
   
   public double getSide2() {
      return side2;
   }
   
   public double getSide3() {
      return side3;
   }
   
   public double getArea() {
      double p = getPerimeter()/2;
      double retVal = Math.sqrt((p)*(p-side1)*(p-side2)*(p-side3));
      return retVal;
   }
   public double getPerimeter() {
      return side1+side2+side3;
   }
   
   public String toString() {
      String retVal = String.format("Triangle: side1: %.2f side2: %.2f side3: %.2f "
            ,side1,side2,side3);
            retVal+=super.toString();
      return retVal;
   }
   
   public boolean equals(Triangle other) {
      boolean retVal = false;
      if(other instanceof Triangle) {
         if(getPerimeter() == other.getPerimeter())
            if(getArea() == other.getArea())
               retVal = true;
      } else {
         retVal = false;
      }
      return retVal;
   }
   // mutators
   public void setSide1(double side1) {
      this.side1 = side1;
   }
   public void setSide2(double side2) {
      this.side2 = side2;
   }
   public void setSide3(double side3) {
      this.side3 = side3;
   }
}