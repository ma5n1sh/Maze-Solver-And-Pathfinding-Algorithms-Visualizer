package gui;

import java.util.ArrayList;

public class arraylist {
    public ArrayList<difCoorPair> storage=new ArrayList<difCoorPair>();
    public void add(difCoorPair a){
        storage.add(a);
        if(contains(a.pair)==-1){storage.add(a);}
        else{
            storage.get(contains(a.pair)).difficulty=a.difficulty;
        }
        storage.sort(difCoorPair::compareTo);
    }
    public int contains(xyPair xy){
        for (int i=0;i<storage.size();i++){
            if(storage.get(i).pair.x==xy.x&&storage.get(i).pair.y==xy.y){
                return i;
            }
        }
        return -1;
    }
    public difCoorPair getMin(){
        difCoorPair tmp=storage.get(0);
        storage.remove(0);
        return tmp;
    }

}
