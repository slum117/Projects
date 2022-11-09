public class Investor{

   private int count = 0;
   private static int id;
   private stock[] myStocks = new stock[1000];
   private String name;
   
   public Investor(String name) {
      this.name = name;
   }
   
   
   public void buy(String name, int amount, double price) {
      myStocks[count] = new stock(name,amount,price);
      count++;
   }
   
   public int find(String name) {
      int retVal = -1;
      for(int i = 0; i < myStocks.length; i++)
         if(myStocks[i] != null)
            if(myStocks[i].getName().equalsIgnoreCase(name))
               retVal = i;
      return retVal;
   }
   
   public double sellAll(String name, double currentPrice) {
      int beingSold = find(name);
      double profit = (currentPrice - myStocks[beingSold].getPrice())*myStocks[beingSold].getAmount(); 
      myStocks[beingSold].setAmount(0);
      return profit;
   }
   
   public double sellPartial(String name, double currentPrice, int amount) {
      int beingSold = find(name);
      double profit = (currentPrice - myStocks[beingSold].getPrice())*amount; 
      myStocks[beingSold].setAmount(myStocks[beingSold].getAmount()-amount);
      return profit;
   }
   
   public String toString() {
      String retVal = String.format("");
      for(int i = 0; i < myStocks.length;i++) {
         if(myStocks[i] != null)
         retVal += myStocks[i].toString();
      }
      return retVal;
   }
   
   // accessors
   public int getCount(){
      return count;
   }
   
   public stock[] getStock() {
      return myStocks;
   }
}