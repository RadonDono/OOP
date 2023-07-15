package Persons;

import LocationAndMap.ras;

import java.util.ArrayList;
import GetID.GetRandomID;

public class Deliver extends Person
{
    //TODO: Complete It!!!
    static ArrayList<Deliver> delivers=new ArrayList<>();
    //////////////////////////////////////////////////////////////////////
//
    private ras location;



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
