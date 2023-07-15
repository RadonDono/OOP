package LocationAndMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
//baraye estefade az in class do tabe tarif shode
//show_time be shoma int zaman ra midahad
//show_path be shoma arraylist az LocationAndMap.ras haye masir ra midahad

public class path {
    ras mabda;
    int rasn;
    ras maghsad;
    map mapp=new map();
    int zaman;
    ArrayList<Integer> path = new ArrayList<>();
    ArrayList<ras> masir=new ArrayList<>();
    int max=1000000000;
    public path(ras mabd,ras maghs){
        mabda=mabd;
        maghsad=maghs;
        rasn= mapp.rasn;
    }
    void best_path()
    {
        int s = mabda.number-1;
        int[] dist = new int[rasn];
        Arrays.fill(dist, 100000000);
        dist[s] = 0;
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        int[] prev = new int[mapp.rasn];
        Arrays.fill(prev, -1);
        pq.add(new pair(s, 0));
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            if (p.w > dist[p.v]) {
                continue;
            }
            try {

                for (pair e : mapp.rases.get(p.v).p) {
                    if (dist[e.v] > dist[p.v] + e.w) {
                        prev[e.v] = p.v;
                        dist[e.v] = dist[p.v] + e.w;
                        pq.add(new pair(e.v, dist[e.v]));
                    }
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        for (int v = maghsad.number-1; v != -1; v = prev[v]) {
            path.add(v+1);
        }
        Collections.reverse(path);
        zaman=dist[maghsad.number-1];


    }
    int show_time(){
        return zaman;
    }
    ArrayList show_path(){
        for (int i=0;i<path.size();i++){
            masir.add(mapp.rases.get(path.get(i)-1));
        }
        return masir;
    }

}
