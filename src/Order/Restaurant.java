package Order;
import GetID.GetRandomID;
import LocationAndMap.ras;

import java.util.ArrayList;

public class Restaurant
{
    public static ArrayList<Restaurant> restaurants=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//
    //  8-->
    private final int restaurantID;
    private final int ownerID;
    private String name;
    private String foodType;
    public ArrayList<Food> activeFoods =new ArrayList<>();
    public ArrayList<Food> deActiveFoods =new ArrayList<>();
    public ArrayList<Comment> receivedComments =new ArrayList<>();
    public ArrayList<Order> receivedOrders=new ArrayList<>();
    public ArrayList<Order> currentOrders=new ArrayList<>();
    public ArrayList<Order> finishedOrders=new ArrayList<>();
    private ras location;

    private ArrayList<Integer> rating=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//constructors
    public Restaurant(String Name, int OwnerID, String FoodType, ras Location)
    {
        name=Name;
        ownerID=OwnerID;
        foodType=FoodType;
        restaurantID= GetRandomID.getID();
        location=Location;
        restaurants.add(this);
    }

    //////////////////////////////////////////////////////////////////////
//functions
    public int getRestaurantID(){return restaurantID;}

    public ras getLocation(){return location;}
    public String getName(){return name;}

    public String getFoodType(){return foodType;}
    //ezafshose
    public ArrayList<String> getFood(){
        ArrayList<String> s=new ArrayList();
        for (int i = 0; i <activeFoods.size() ; i++) {
            s.add(activeFoods.get(i).toString());
        }
        return s;
    }

    public void changeFoodType(String FoodType)
    {
        foodType=FoodType;
        activeFoods =new ArrayList<>();
    }
    public void receiveAnOrder(int orderID) //look at the name
    {
        receivedOrders.add(Order.getOrder(orderID));
    }
    public void receiveAComment(int commentID){receivedComments.add(Comment.getComment(commentID));}
    public void acceptAnOrder(int orderID) // :) 8--->
    {
        receivedOrders.remove(Order.getOrder(orderID));
        currentOrders.add(Order.getOrder(orderID));
    }
    public void declineAnOrder(int orderID) //how mean!
    {
        receivedOrders.remove(Order.getOrder(orderID));
    }
    public void orderFinished(int orderID) //let a restaurant know that their order is finished
    {
        currentOrders.remove(Order.getOrder(orderID));
        finishedOrders.add(Order.getOrder(orderID));
    }

    public void addFood(String name,int price) //add food to restaurant
    {
        Food food=new Food(name,this.restaurantID,price);
        activeFoods.add(food);
    }
    public void deleteAFood(int FoodID) //how to deleteAFood
    {
        activeFoods.remove(Food.getFood(FoodID));
        Food.deleteFood(FoodID);
    }
    public void deActiveAFood(int foodID)  //you can deactive an active food
    {
        Food food=Food.getFood(foodID);
        food.deactivateFood();
        deActiveFoods.add(food);
        activeFoods.remove(food);
    }
    public void activeAFood(int foodID) //active a deactive food
    {
        Food food=Food.getFood(foodID);
        food.activateFood();
        deActiveFoods.remove(food);
        activeFoods.add(food);
    }
    public void editLocation(ras newLocation)
    {
        location=newLocation;
    }
    public void editFoodName(int foodID,String name)
    {
        if(isThisFoodForRestaurant(foodID))
        {
            Food.getFood(foodID).editFoodName(name);
        }
    }
    public void editFoodPrice(int foodID,int price)
    {
        if(isThisFoodForRestaurant(foodID))
        {
            Food.getFood(foodID).editFoodPrice(price);
        }
    }
    boolean isThisFoodForRestaurant(int foodID)
    {
        for(Food food:activeFoods)
        {
            if(food.getFoodID()==foodID)
            {
                return true;
            }
        }
        for(Food food:deActiveFoods)
        {
            if(food.getFoodID()==foodID)
            {
                return true;
            }
        }
        return false;
    }
    public void setFoodDiscount(int foodID, int percent)
    {
        if(isThisFoodForRestaurant(foodID))
        {
            Food.getFood(foodID).setDiscount(percent);
        }
    }
    public void setARespond(int orderID,String comment)
    {
        if(isItAFinishedOrder(orderID))
        {
            Order order=Order.getOrder(orderID);
            order.getComment().setRespond(comment);
        }
    }
    public boolean isItAFinishedOrder(int orderID)
    {
        for(Order order:finishedOrders)
        {
            if(order.getOrderID()==orderID)
            {
                return true;
            }
        }
        return false;
    }
    public Double getRate() {
        Double sum= Double.valueOf(0);
        for (int x:rating) {
            sum+=x;
        }
        return (Double) sum/rating.size();
    }
    public void rate(int Rating) //a user will use this to rate
    {
        rating.add(Rating);
    }

    //////////////////////////////////////////////////////////////////////
//static functions
    public static Restaurant getRestaurant(int restaurantID) //search restaurants using their IDs
    {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.restaurantID == restaurantID) {
                return restaurant;
            }
        }
        return null;
    }
    //ezaf shod
    @Override
    public String toString() {
        return "Restaurant : "+this.name+ " "+this.foodType+" "+this.restaurantID;
    }


}
