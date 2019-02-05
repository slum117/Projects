import java.util.*;

public class shapeDriver {

   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);

      start(console);
   }
   
   
   public static void start(Scanner console) {
      GeometricObject shapeList[] = fillArray(console);
      output(shapeList);
   }
   
   public static GeometricObject[] fillArray(Scanner console) {
      boolean moreShapes = true; // user wants to put in more shapes
      String shape;
      String cont;
      GeometricObject listOfShapes[] = new GeometricObject[12];
      boolean exitProg = true;
      // 12 loops max for max amount of objects
      for(int i = 0; i < 12 && moreShapes; i++) {
         boolean misSpell = true;
         // if the user mispells a word loops back
         while(misSpell && exitProg) {
         
            System.out.print("Please enter the shape you want:");
            shape = console.next(); 
            System.out.println();
            
         //program checks for the shape they want
            if(shape.equalsIgnoreCase("circle")) {
               listOfShapes[i] = circ(console);
               System.out.println();
               misSpell = false;
            } else if(shape.equalsIgnoreCase("rectangle")) {
               listOfShapes[i] = rect(console);
               System.out.println();
               misSpell = false;
            } else if(shape.equalsIgnoreCase("triangle")) {
               listOfShapes[i] = tria(console);
               System.out.println();
               misSpell = false;
            } else if(shape.equalsIgnoreCase("exit")) {
               exitProg = false;
            } else {
               System.out.println(shape + " is not on the list");
               System.out.println();
            }
         }
         
         if(exitProg) {
         //program asks if the user wants to quit or continue
            if(i<=10) {
               System.out.print("Do you want to put in more shapes? [Yes/No]");
               cont = console.next();
               moreShapes = goOn(cont,"yes");
               System.out.println();
            } else {
               System.out.println("The maximum amount of objects has been reached.");
               System.out.println();
            }
         }
      }
      return listOfShapes;
   }
   
   // if the user wants to create more shapes, return true, else return false
   public static boolean goOn(String userChoice, String shapeOrAnswer) {
      boolean retVal = true;
      if(userChoice.equalsIgnoreCase(shapeOrAnswer)) {
         retVal = true;
      } else
         retVal = false;
      return retVal; 
   }
   
   //creates a circle object and returns it
   public static circle circ(Scanner console) {
      
      System.out.print("Please enter the radius of the circle: ");
      verDou(console);
      double radius = console.nextDouble();
      
      boolean filled = isFilled(console);
      
      circle c = new circle(radius,col(console),filled);
      
      return c;
   }
   
   // creates a rectangle
   public static rectangle rect(Scanner console) {
      System.out.print("Please enter the width: ");
      verDou(console);
      double width = console.nextDouble();
      System.out.print("Please enter the height: ");
      verDou(console);
      double height = console.nextDouble();
      rectangle rec = new rectangle(width,height,col(console),isFilled(console));
      return rec;
   }
   
   // creates a triangle
   public static Triangle tria(Scanner console) {
      System.out.println("Please enter the 3 side lengths: ");
      double side1 = sides(console,1);
      double side2 = sides(console,2);
      double side3 = sides(console,3);
      
      Triangle triang = new Triangle(side1,side2,side3,col(console),isFilled(console));
      return triang;

   }
   //asks if the object is filled
   public static boolean isFilled(Scanner console) {
      System.out.print("Is the shape filled in? ");
      String filled = console.next();
      return goOn(filled,"yes");
   }
   //asks for the color of the object
   public static String col(Scanner console) {
      System.out.print("What is the color of the shape? ");
      console.nextLine();
      String color = console.nextLine();
      
      
      return color;
   }
   
   // verify's that the input is a double
   public static void verDou(Scanner console) {
      while(!console.hasNextDouble()){
         console.next();
      }
   }
   
   // creates sides of a triangle
   public static double sides(Scanner console,int side) {
      System.out.printf("Side%d: ",side);
         verDou(console);
      return console.nextDouble();
   }
   
   // gets area of all objects
   public static void getArea(GeometricObject[] other) {
      for(int i = 0; i < other.length; i++) {
         if(other[i] != null)
            if(other[i] instanceof circle) {
               circle circ = (circle)other[i];
               System.out.printf("A circle with a radius of %.2f has an area of %.2f.%n",circ.getRadius(),circ.getArea());
            } else if(other[i] instanceof rectangle) {
               rectangle rect = (rectangle)other[i];
               System.out.printf("A rectangle with a width and height of %.2f and %.2f has an area of %.2f.%n",rect.getWidth(),rect.getHeight(),rect.getArea());
            } else if(other[i] instanceof Triangle) {
               Triangle triang = (Triangle)other[i];
               System.out.printf("A triangle with sides %.2f, %.2f, and %.2f has an area of %.2f.%n", triang.getSide1(),triang.getSide2(),triang.getSide3(),triang.getArea());
            }
      }
   }
   
   // gets perimeter of all objects
   public static void getPerimeter(GeometricObject[] other) {
      for(int i = 0; i < other.length; i++) {
         if(other[i] != null)
            if(other[i] instanceof circle) {
               circle circ = (circle)other[i];
               System.out.printf("A circle with a radius of %.2f has an perimeter of %.2f.%n",circ.getRadius(),circ.getPerimeter());
            } else if(other[i] instanceof rectangle) {
               rectangle rect = (rectangle)other[i];
               System.out.printf("A rectangle with a width and height of %.2f and %.2f has an perimeter of %.2f.%n",rect.getWidth(),rect.getHeight(),rect.getPerimeter());
            } else if(other[i] instanceof Triangle) {
               Triangle triang = (Triangle)other[i];
               System.out.printf("A triangle with sides %.2f, %.2f, and %.2f has an perimeter of %.2f.%n", triang.getSide1(),triang.getSide2(),triang.getSide3(),triang.getPerimeter());
            }
      }
   }
   
   public static void basicDescription(GeometricObject[] other) {
   for(int i = 0; i < 12; i++) {
         if(other[i] != null)
            System.out.println(i+1 + ": " + other[i]);
      }

   }
   public static void output(GeometricObject[] shapeList) {
      System.out.println("Here is a list of your shapes");
      System.out.println();
      System.out.println("Basic Description:");
      basicDescription(shapeList);
      System.out.println();
      System.out.println("Area: ");
      getArea(shapeList);
      System.out.println();
      System.out.println("Perimeter: ");
      getPerimeter(shapeList);
   }
}