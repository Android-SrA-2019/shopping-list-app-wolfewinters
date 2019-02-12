package ca.nbcc.shoppinglist;

import java.security.InvalidParameterException;

public class GroceryItem {
    private String itemName;
    private int count;
    private GroceryItem(){}
    public String getItemName(){
        return itemName;
    }
    public int getCount(){
        return count;
    }
    public void addCount(){
        count++;
    }
    public GroceryItem(String itemName, int count){
        if(count < 0){
            throw new InvalidParameterException("count must be greater than zero");
        }
        this.count = count;
        this.itemName = itemName;
    }
    public void addAmount(int amount){
        if(count + amount < 0){
            throw new InvalidParameterException("amount must be greater than zero");
        }
        count += amount;
    }
}
