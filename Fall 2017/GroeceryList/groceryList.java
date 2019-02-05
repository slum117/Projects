public class groceryList {
      
   // assumes that the maximum items a person can buy will be 50
   item items[];
   
   // tracking how many items they buy
   private static int count = 0;   
   
   // constructor
   public groceryList() {
      count = 0;
      items = new item[50];
   }
   
   // mutators
   public void add(item newItem) {
      items[count] = newItem;
      count++;
   }
   
   public boolean remove(item oldItem,int index) {
      boolean retVal = false;
      for(int i = 0; i < count; i++) {
         if(items[i].getName().equalsIgnoreCase(oldItem.getName())) {
            retVal = true; 
            items[index].setName(null);
         }
      }
      return retVal;
   }
      
   // accessors
   public String getList()  {
      String retVal = String.format("List of items ordered: %n");
      for(int i = 0; i < count; i++) {
         if(items[i].getName() != null)
            retVal+= items[i].toString();  
      }
      return retVal;
   }
      
   public double getTotalCost() {
      double totalCost = 0;
      for(int i = 0; i < count; i++) 
         if(items[i].getName() != null)
         totalCost += items[i].getTotalCost();
      return totalCost;
   }
   
   public int find(String name) {
      int retVal = -1;
      boolean itemHasBeenFound = false;
      for(int i = 0; i < count; i++) {
         if(items[i].getName() != null)
         if(items[i].getName().equalsIgnoreCase(name)) {
            retVal = i;
         }
      }
      return retVal;
   }
   
   public String toString() {
      String retVal = String.format("");
      for(int i = 0; i < count; i++) {
         if(items[i].getName() != null) {
            retVal += items[i].toString();
         }
      }
      return retVal;
   }
   
      
}