package controller;
import GetID.GetRandomID;
import Order.Food;
import Order.Order;
import Order.Restaurant;
import Order.Comment;
import enums.Message;
import Persons.User;

import java.util.ArrayList;
import java.util.Random;

public class UserController {
    public User usercon=null;
    public int orderidres;
    public Restaurant restaurant_now=null;

    public UserController(User us) {
        usercon=us;
    }

    public ArrayList<String> searchrestaurant(String name) {
        ArrayList<String> x=new ArrayList<>();
        for (int i = 0; i < Restaurant.restaurants.size(); i++) {
            if (Restaurant.restaurants.get(i).getName().equals(name)){
                x.add(Restaurant.restaurants.get(i).toString());
            }
        }
        return x;
    }

    public Message selectrestaurant(String ID) {
        restaurant_now=Restaurant.getRestaurant(Integer.parseInt(ID));
        if(Restaurant.getRestaurant(Integer.parseInt(ID))==null){
            return Message.NoRestaurant;
        }
        else{
            return Message.SUCCESS;
        }
    }



    public ArrayList<String> searchfood(String username) {
        ArrayList<String> x=new ArrayList<>();
        for (int i = 0; i < Restaurant.restaurants.size(); i++) {
            if (restaurant_now.activeFoods.get(i).getName().equals(username)){
                x.add(Restaurant.restaurants.get(i).toString());
            }
        }
        return x;
    }

    public Message selectfood(String ID) {

        if(Food.getFood(Integer.parseInt(ID))==null){
            return Message.Nofood;
        }
        else{
            usercon.addFoodToOrder(Integer.parseInt(ID),usercon.getOrderIDbyRestaurant(orderidres));
            return Message.SUCCESS;
        }
    }

    public String showorder(int Id) {
        if (Order.getOrder(Id)==null){
            return "ID is incorrect";
        }
        else {
            return Order.getOrder(Id).toString();
        }
    }

    public Message charge(int money) {
        usercon.chargeAccount(money);
        return Message.SUCCESS;
    }

    public ArrayList<String> confirmorder() {
        ArrayList<String> s=new ArrayList<>();
        for (int i = 0; i < usercon.cart.size(); i++) {
            s.add(Order.getOrder(usercon.cart.get(i)).toString());
            usercon.sendAnOrderToTheRestaurant(usercon.cart.get(i));
        }
        
        return s;
    }

    public int showtime() {
        return GetRandomID.getID()%100;
    }

    public Message addrestaurantcomment(String com) {
        Comment comment=new Comment(usercon.getID(),com, GetRandomID.getID(),restaurant_now.getRestaurantID());
        return Message.commentadded;
    }

    public Message editrestaurantcomment(int id,String username) {
        Comment.editcomment(id,username);
        return Message.SUCCESS;
    }

    public ArrayList<Comment> getfoodcomment(int foodid) {
        return Food.getFood(foodid).getcomment();
    }

    public Double getrate() {
        return restaurant_now.getRate();
    }

    public Message addrate(int rate) {
        restaurant_now.rate(rate);
        return Message.SUCCESS;
    }

    public Message editrate(int rate) {
        return Message.SUCCESS;
    }

    public ArrayList<String> showcart() {
        ArrayList<String> s=new ArrayList<>();
        for (int x:usercon.cart) {
            s.add(Order.getOrder(x).toString());
        }
        return s;
    }

    public String addoredercomment(String orderId, String comment) {
        usercon.CommentAnOrder(Integer.parseInt(orderId),comment);
        return "comment successfully";
    }

    public String editoredercomment(String commentId, String comment) {
        Comment.editcomment(Integer.parseInt(commentId),comment);
        return "comment successfully";
    }

    public Message addrateorder(int rate,String OrderId) {
        Order.getOrder(Integer.parseInt(OrderId)).rate(usercon.getID(),rate);
        return Message.SUCCESS;
    }
}
