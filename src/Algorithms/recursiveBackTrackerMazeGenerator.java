package Algorithms;

import Controller.theEyeOfAgamotto;
import mazeData.xyPair;

import java.util.ArrayList;

public class recursiveBackTrackerMazeGenerator implements mazeGenerator{
    mazeData.maze maze;
    boolean visited[][];
    theEyeOfAgamotto e=new theEyeOfAgamotto();

    public recursiveBackTrackerMazeGenerator(mazeData.maze maze) {
        this.maze = maze;
        visited= new boolean[maze.arr.length][maze.arr[0].length];
        for(int i=0;i<maze.arr.length;i++){
            for (int j=0;j<maze.arr[0].length;j++){
                maze.arr[i][j]=1;
                visited[i][j]=false;
            }
        }
    }

    private boolean isPossible(xyPair source, xyPair destination){
        if(destination.x>=maze.arr[0].length||destination.x<0)return false;
        if(destination.y>=maze.arr.length||destination.y<0)return false;
        if(visited[destination.y][destination.x]){
            return false;
        }
        e.waiterhalf();
        return true;

    }


    public void recursiveBackTracker(xyPair source){
        maze.arr[source.y][source.x]=3;
        e.waiterhalf();
        maze.arr[source.y][source.x]=0;
        visited[source.y][source.x]=true;
        xyPair destination;
        ArrayList<Integer> a=new ArrayList<Integer>();
        a.add(0);a.add(1);a.add(2);a.add(3);
        do{
            int direction=(int)(Math.random()*10000)%a.size();
            switch (a.get(direction)){
                case 0:destination=new xyPair(source.x,source.y-2);break;
                case 1:destination=new xyPair(source.x+2,source.y);break;
                case 2:destination=new xyPair(source.x,source.y+2);break;
                default:destination=new xyPair(source.x-2,source.y);break;
            }
            a.remove(direction);
        }while(!isPossible(source,destination)&&!a.isEmpty());

        if(!isPossible(source,destination)&&a.isEmpty())return ;
        if(isPossible(source,destination)){
            if(destination.x==source.x){
                maze.arr[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=0;
                visited[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=true;

            }
            else{maze.arr[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=0;
                visited[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=true;

            }
            recursiveBackTracker(destination);
        }
        recursiveBackTracker(source);

    }

    public void generateMaze(){
        xyPair start;
        start=new xyPair((int)(Math.random()*maze.arr[0].length),(int)(Math.random()*maze.arr.length));
        recursiveBackTracker(start);
        e.isGenerating=false;
    }

}
