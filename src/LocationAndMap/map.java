package LocationAndMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//show_map
//give location
//getlocation
//show LocationAndMap.path
public class map {
    //tedad LocationAndMap.ras ha va yal haye ma
    int rasn;
    int yaln;
    ArrayList<ras> rases=new ArrayList<>();
    File file =new File("src//graph.txt");
    public map(){
        try {
            Scanner scanmap=new Scanner(file);
            rasn=scanmap.nextInt();
            sakht_raas();
            yaln=scanmap.nextInt();
            for (int i = 0; i <yaln ; i++) {
                int ras1=scanmap.nextInt();
                int ras2=scanmap.nextInt();
                int vazn=scanmap.nextInt();
                rases.get(ras1-1).add_yal(rases.get(ras2-1),vazn);
                rases.get(ras2-1).add_yal(rases.get(ras1-1),vazn);
            }
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
    void sakht_raas(){
        for (int i = 0; i < rasn; i++) {
            rases.add(new ras(i+1));
        }
    }
    //in tabe be shoma location ra bar migardanad
    //jens an ra bayad az noe LocationAndMap.ras beszid ke yek noghte ast
    public ras get_location(int number){
        return rases.get(number-1);
    }
    //shoma LocationAndMap.ras(location) ra midahid shomare ra migirid

    int give_location(ras r){
        return r.number;
    }

    //in tabe be shoma tamam noghat+etasalat ra midahad

    ArrayList<ras> show_map(){
        return rases;
    }

    //vorodi in do tabe do LocationAndMap.ras ya haman location mored nazar shomast
    //in tabe be shoma yek kelas masir midahad
    path show_pass(ras mabda,ras maghsad){
        path p=new path(mabda,maghsad);
        p.best_path();
        p.show_path();
        return p;
    }
}