package Algorithms;

import Controller.theEyeOfAgamotto;
import mazeData.maze;
import mazeData.xyPair;
import java.util.HashMap;

public class Play {
    maze maze;
    xyPair current;
    HashMap<String, xyPair> map=new HashMap<>();
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    boolean finished=false;
    private void finish(){
        xyPair xyPair =map.get(current.toString());
        while(xyPair.x!=maze.startX|| xyPair.y!=maze.startY){e.waiter();e.waiter();
            maze.arr[xyPair.y][xyPair.x]=4;
            xyPair =map.get(xyPair.toString());
        }
        maze.arr[maze.startY][maze.startX]=-1;
        maze.arr[maze.endY][maze.endX]=-1;
    }

    public Play(mazeData.maze maze) {
        this.maze = maze;
        if(maze.endY!=-1){
            current=new xyPair(maze.startX,maze.startY);
        }

    }
    public void goLeft(){
        if(finished)return;
        if(current.x-1>=0&&maze.arr[current.y][current.x-1]!=1){
            maze.arr[current.y][current.x]=3;
            current.x=current.x-1;
            map.put(current.toString(),new xyPair(current.x+1,current.y));
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goRight(){
        if(finished)return;
        maze.arr[current.y][current.x]=3;
        if(current.x+1<maze.arr[0].length&&maze.arr[current.y][current.x+1]!=1){
            current.x=current.x+1;
            map.put(current.toString(),new xyPair(current.x-1,current.y));
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goDown(){
        if(finished)return;
        maze.arr[current.y][current.x]=3;
        if(current.y+1<maze.arr.length&&maze.arr[current.y+1][current.x]!=1){
            current.y=current.y+1;
            map.put(current.toString(),new xyPair(current.x,current.y-1));
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goUp(){
        if(finished)return;
        maze.arr[current.y][current.x]=3;
        if(current.y-1>=0&&maze.arr[current.y-1][current.x]!=1){
            current.y=current.y-1;
            map.put(current.toString(),new xyPair(current.x,current.y+1));
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }

}
