package Persons;

import LocationAndMap.ras;

import java.util.ArrayList;
import GetID.GetRandomID;
import Order.Order;
import Order.*;

public class Deliver extends Person
{
    //TODO: Complete It!!!
    static ArrayList<Deliver> delivers=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//
    public ArrayList<Order> receivedOrders;
    public Order using=null;



//////////////////////////////////////////////////////////////////////
//constructors
public Deliver(String Name,String Password)
{
    ID= GetRandomID.getID();
    name=Name;
    password=Password;
    role = Role.Deliver;
    delivers.add(this);
    Person.people.add(this);

}

//////////////////////////////////////////////////////////////////////
//functions

    public void acceptOrder(int orderID)
    {
        if(Order.getOrder(orderID)!=null&&using==null)
        {
            using=Order.getOrder(orderID);
            Order.getOrder(orderID).setStatus(Status.InWay);
        }
    }
    public void deliverFood(int orderID)
    {
        if(using.equals(Order.getOrder(orderID)))
        {
            using=null;
            Order.getOrder(orderID).setStatus(Status.Delivered);
            receivedOrders.add(Order.getOrder(orderID));
        }
    }


    //////////////////////////////////////////////////////////////////////
//static functions
    public static Deliver getDeliver(int deliverID)
    {
        for (Deliver deliver:delivers)
        {
            if(deliver.ID==deliverID)
            {
                return deliver;
            }
        }
        return null;
    }
}
