import java.util.*;

public class stock {

   //variables
   
   private int amount;
   private double price;
   private Date datePurchased;
   private Date dateSold;
   private String name;
   
   //constructor
   
   public stock(String name,int amount, double price) {
    this.amount = amount;
    this.name = name;
    this.price = price;
    datePurchased = new Date();
   } // set date to current date
   
   //Accessors
   
   public int getAmount() {
      return amount;
   }
   public String getName() {
      return name;
   }
   public double getPrice() {
      return price;
   }
   public double getProfit(double currentPrice) {
      if(currentPrice >= 0) 
         return (currentPrice - price)*amount;
      else
         return -1;
   }
   public Date getDatePurchased() {
      return datePurchased;
   }
   
   public boolean equals(Object other) {
      boolean equalValue = true;
      
      if(other instanceof stock) {
         stock o = (stock) other;
         if(o.name.equalsIgnoreCase(name)){
            if(o.amount == amount) {
               if(o.price == price) {
               }
               else
                  equalValue = false;
            }
            else
               equalValue = false;
         }
         else
            equalValue = false;  
      }
      else
         equalValue = false;
      return equalValue;
   }
   
   public String toString() {
      String retStr = String.format("Name: %7s Amount: %5d Original Price %5.2f Date Purchased: %s %n",name,amount,price, datePurchased);
      return retStr;
   }
   //Mutators
   
   public void setAmount(int amount) {
      if(amount >= 0)
      this.amount = amount;
   }
   public void setPrice(double price) {
      if(price>=0)
         this.price = price;
   }
   public void setName(String name) {
      this.name = name;
   }
   public void setDateSold() {
      dateSold = new Date();
   }
   /**
      @param count number of stocks to sell
      @param currentPrice current price of the stocks
      @return proft amount of money person made
   **/
   public double sell(int count, double currentPrice) {
      //if the amount is greater than current amount, sell all
      setDateSold();
      if(count>=amount) {
         count = amount;
      }
      
      double profit = -1;
      // current price < 0
      if(currentPrice >= 0) {
         profit =(currentPrice - price) * count;
         amount-=count;
      }
      return profit;
   }
   public double sellAll(double currentPrice) {
      setDateSold();
      double profit = 0;
      profit = (currentPrice - price)*amount;
      amount-=amount;
      return profit;
   }
}