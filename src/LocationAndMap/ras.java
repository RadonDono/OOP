package LocationAndMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
    //braye hamsaye ha az hashmap mojaver estefade konid faghat
    public class ras {
        ArrayList<pair> p=new ArrayList<>();
        Map<ras,Integer> mojaver=new HashMap();
        public int number;
        ras(int x){
            number=x;
        }
        void add_yal(ras r,int vazn){
            p.add(new pair(r.number-1,vazn));
            mojaver.put(r,vazn);
        }

    }