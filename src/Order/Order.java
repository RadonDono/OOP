package Order;

import GetID.GetRandomID;
import LocationAndMap.path;
import Persons.User;

import java.util.ArrayList;

public class Order
{

    static ArrayList<Order> orders=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//
    private final int orderID;
    private ArrayList<Integer> foodIDs=new ArrayList<>();
    private final int restaurantID;
    private int deliverID;
    private final int userID;
    private Status status;
    private path masir;
    private Comment comment;
    //////////////////////////////////////////////////////////////////////
//constructors
    public Order(int UserID,int RestaurantID)
    {
        orderID= GetRandomID.getID();
        restaurantID=RestaurantID;
        Restaurant.getRestaurant(RestaurantID).receiveAnOrder(orderID);
        userID=UserID;
        status=Status.InCart;
        orders.add(this);
        masir=new path(Restaurant.getRestaurant(RestaurantID).getLocation(), User.getUser(userID).getLocation());
    }

    //////////////////////////////////////////////////////////////////////
//functions
    public int getOrderID(){return orderID;}
    public Status getStatus(){return status;}
    public int getRestaurantID(){return restaurantID;}
    public Comment getComment(){return comment;}
    public void deliverFond(int DeliverID)
    {
        deliverID=DeliverID;
        this.setStatus(Status.InWay);
    }
    public path getMasir(){return masir;}
    public ArrayList<Integer> getFoodIDs(){return foodIDs;}
    public int price() //calculate price of the order
    {
        int pr=0;
        for(int ID : foodIDs)
        {
            pr+=Food.getFood(ID).getPrice();
        }
        return pr;
    }
    public void addFood(int foodID)//add food to order
    {
        if(Food.getFood(foodID).isFoodActive()&&status.equals(Status.InCart))
        {
            foodIDs.add(foodID);
        }

    }

    public void removeFood(int foodID) //remove food from your order
    {
        for(int i=0;i<foodIDs.size();i++)
        {
            if(foodIDs.get(i)==foodID&&status.equals(Status.InCart))
            {
                foodIDs.remove(i);
            }
        }
    }

    public void setStatus(Status status) //leveling up the status!!
    {
        //todo: complete each case with what user ,deliver and restaurant needs to know
        if(status.equals(Status.InCart))
        {
            Restaurant.getRestaurant(restaurantID).receiveAnOrder(orderID);
        }
        else if(status.equals(Status.NeedRestaurantAccept))
        {
            this.status=Status.NeedRestaurantAccept;
        }
        else if (status.equals(Status.NeedDeliverAccept))
        {
            this.status=Status.NeedDeliverAccept;
        }
        else if (status.equals(Status.InWay))
        {
            this.status=Status.InWay;
        }
        else if (status.equals(Status.Delivered))
        {
            this.status=Status.Delivered;
            Restaurant.getRestaurant(restaurantID).orderFinished(orderID);
        }
        else if (status.equals(Status.Finished))
        {
            this.status=Status.Finished;

        }
    }

    public void cancelOrder() //you know what it does!
    {
        status=Status.Canceled;
    }

    public void rateAndComment(int userID,int rating,String comment) //the user set a single rating and comment for all off the foods in an order
    {
        if(userID==this.userID)
        {
            for(int i=0;i<foodIDs.size();i++)
            {
                this.comment=new Comment(foodIDs.get(i),userID,comment);
                Food.getFood(foodIDs.get(i)).rate(rating);
            }
        }
    }
    public void AddComment(int userID,String comment) //the user set a single rating and comment for all off the foods in an order
    {
        if(userID==this.userID)
        {
            for(int i=0;i<foodIDs.size();i++)
            {
                this.comment=new Comment(foodIDs.get(i),userID,comment);
            }
        }
    }
    public void rate(int userID,int rating)
    {
        if(userID==this.userID)
        {
            for(int i=0;i<foodIDs.size();i++)
            {
                Food.getFood(foodIDs.get(i)).rate(rating);
            }
        }
    }

    public void userChangeTheComment(int UserID,String NewBody) //user can change the comment before receiving respond
    {
        if(UserID==this.userID&&!Comment.getComment(comment.getCommentID()).getRespond())
        {
            for(int i=0;i<foodIDs.size();i++)
            {
                this.comment=new Comment(foodIDs.get(i),userID,NewBody);
            }
        }
    }

    public void restaurantSetRespond(int restaurantID,String respond) //a restaurant owner can set respond
    {
        if(this.restaurantID==restaurantID)
        {
            comment.setRespond(respond);
        }
    }


    //////////////////////////////////////////////////////////////////////
//static functions
    public static Order getOrder(int orderID) //searching order using their IDs
    {
        for (Order order: orders)
        {
            if (order.orderID==orderID)
            {
                return order;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        String x= "Order : "+this.orderID+ " ";
        for (int id:foodIDs) {
            x+=Food.getFood(id).toString()+"\n";
        }
        x+=+this.price();
        return x;
    }
}