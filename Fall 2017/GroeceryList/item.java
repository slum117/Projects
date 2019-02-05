public class item {

   
   // instance fields 
   String name;
   int quantity;
   double pricePerUnit;
   
   
   // constructors
   public item(String name, int quantity, double pricePerUnit) {
      this.name = name;
      this.quantity = quantity;
      this.pricePerUnit = pricePerUnit;
   }

   public item() {
      name = null;
      quantity = 0;
      pricePerUnit = 0.00;
   }
   
   
   
   // mutators
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   
   public void setPricePerUnit(double newPrice) {
      this.pricePerUnit = newPrice;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   // accessors
   public String getName() {
      return name;
   }
   
   
   public int getQuantity() {
      return quantity;
   }
   
   
   public double getPricePerUnit() {
      return pricePerUnit;
   }
   
   
   public boolean equals(item other) {
      boolean isEqual = true;
      if(this.name != other.name)
         isEqual = false;
      else if(this.quantity != other.quantity) 
         isEqual = false;
      else if(this.pricePerUnit != other.pricePerUnit)
         isEqual = false;
      return isEqual;
   }
   
   
   public double getTotalCost() {
      return quantity*pricePerUnit;
   }
   
   public String toString() {
      String result = String.format("Name: %-9s Quantity: %2d Price Per Unit: %.2f Price: %.2f%n",
                        name,quantity,pricePerUnit,quantity*pricePerUnit);
      return result;
   }
}