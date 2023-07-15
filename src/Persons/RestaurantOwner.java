package Persons;

import GetID.GetRandomID;
import LocationAndMap.ras;
import Order.Restaurant;

import java.util.ArrayList;

public class RestaurantOwner extends Person
{
    //TODO: Complete It!!!
    static ArrayList<RestaurantOwner> restaurantOwners=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//
    public ArrayList<Restaurant> ownedRestaurants=new ArrayList<>();

    //////////////////////////////////////////////////////////////////////
//constructors
    public RestaurantOwner(String Name,String Password)
    {
        ID= GetRandomID.getID();
        name=Name;
        password=Password;
        role = Role.RestaurantOwner;
        restaurantOwners.add(this);
        Person.people.add(this);
    }

//////////////////////////////////////////////////////////////////////
//functions

    public int getID(){return ID;}
    public String getName(){return name;}
    public String getPassword(){return password;}
    public boolean isItAnOwnedRestaurant(int restaurantID)
    {
        for(Restaurant restaurant:ownedRestaurants)
        {
            if(restaurant.getRestaurantID()==restaurantID)
            {
                return true;
            }
        }
        return false;
    }
    public ras getLocation(int restaurantID) // return location of an owned restaurant
    {
        if(isItAnOwnedRestaurant(restaurantID))
        {
            return Restaurant.getRestaurant(restaurantID).getLocation();
        }
        return null;
    }
    public void editLocation(int restaurantID,ras newLocation)
    {
        if(isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).editLocation(newLocation);
        }
    }
    public String getFoodType(int restaurantID) // return foodType of an owned restaurant
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            return Restaurant.getRestaurant(restaurantID).getFoodType();
        }
        return null;
    }
    public void editFoodType(int restaurantID,String foodType)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).changeFoodType(foodType);
        }
    }
    public void editAFoodName(int restaurantID,int foodID,String name)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).editFoodName(foodID,name);
        }
    }
    public void editAFoodPrice(int restaurantID,int foodID,int price)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).editFoodPrice(foodID,price);
        }
    }
    public void addFoodToMenu(int restaurantID,String foodName,int foodPrice)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).addFood(foodName,foodPrice);
        }
    }
    public void deleteFoodFromMenu(int restaurantID,int foodID)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).deleteAFood(foodID);
        }
    }
    public void deActiveFood(int restaurantID,int foodID)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).deActiveAFood(foodID);
        }
    }
    public void activeFood(int restaurantID,int foodID)
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).activeAFood(foodID);
        }
    }
    public void setADiscountOnAFood(int restaurantID,int foodID,int discount) //TODO: Set a time for this
    {
        if (isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).setFoodDiscount(foodID,discount);
        }
    }
    public void setARespondOnAnOrder(int restaurantID,int orderID,String respond)
    {
        if(isItAnOwnedRestaurant(restaurantID))
        {
            Restaurant.getRestaurant(restaurantID).setARespond(orderID,respond);
        }
    }


    //////////////////////////////////////////////////////////////////////
//static functions
    public static RestaurantOwner getRestaurantOwner(int ownerID)
    {
        for(RestaurantOwner restaurantOwner:restaurantOwners)
        {
            if(restaurantOwner.ID==ownerID)
            {
                return restaurantOwner;
            }
        }
        return null;
    }
    public static RestaurantOwner getRestaurantOwner(String name)
    {
        for (RestaurantOwner user:restaurantOwners) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

}
