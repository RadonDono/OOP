package GetID;

import java.util.UUID;

public class GetRandomID
{
    //    this class gives you random unique IDs !!!
    public static int getID(){
        int id=UUID.randomUUID().hashCode();
        return absoluteValue(id);
    }
    private static int absoluteValue(int in)
    {
        if(in>=0){return in;}
        else {return -in;}
    }
}
