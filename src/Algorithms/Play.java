package Algorithms;

import Controller.mazeController;
import Controller.theEyeOfAgamotto;
import mazeData.maze;
import mazeData.xyPair;
import java.util.HashMap;
import java.util.Stack;

public class Play {
    maze maze;
    xyPair current;
   Stack<xyPair>map=new Stack<xyPair>();
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    public boolean finished=false;
    mazeController mc;
    private void finish(){

        while(!map.isEmpty()){xyPair xyPair=map.pop();
            e.waiter();e.waiter();
            maze.arr[xyPair.y][xyPair.x]=4;
        }
        maze.arr[maze.startY][maze.startX]=-1;
        maze.arr[maze.endY][maze.endX]=-1;
        mc.play=false;
    }

    public Play(mazeData.maze maze,mazeController mc) {
        this.maze = maze;
        if(maze.endY!=-1){
            current=new xyPair(maze.startX,maze.startY);
            maze.arr[current.y][current.x]=5;
        }
        this.mc=mc;
        maze.arr[maze.endY][maze.endX]=-1;
    }
    public void  checkStartEnd(){
        maze.arr[maze.startY][maze.startX]=-1;
        maze.arr[maze.endY][maze.endX]=-1;

    }
    public void goLeft(){checkStartEnd();
        if(finished)return;
        if(current.x-1>=0&&maze.arr[current.y][current.x-1]!=1){
            maze.arr[current.y][current.x]=3;
            current=new xyPair(current.x-1,current.y);
            map.add(current);
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goRight(){checkStartEnd();
        if(finished)return;

        if(current.x+1<maze.arr[0].length&&maze.arr[current.y][current.x+1]!=1){
            maze.arr[current.y][current.x]=3;
            current=new xyPair(current.x+1,current.y);
            map.add(current);
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goDown(){checkStartEnd();
        if(finished)return;

        if(current.y+1<maze.arr.length&&maze.arr[current.y+1][current.x]!=1){
            maze.arr[current.y][current.x]=3;
            current=new xyPair(current.x,current.y+1);
            map.add(current);
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }
    public void goUp(){checkStartEnd();
        if(finished)return;

        if(current.y-1>=0&&maze.arr[current.y-1][current.x]!=1){
            maze.arr[current.y][current.x]=3;
            current=new xyPair(current.x,current.y-1);
            map.add(current);
            maze.arr[current.y][current.x]=5;
            if(current.x==maze.endX&&current.y==maze.endY)finish();
        }
    }

}
