package Algorithms;

import mazeData.customPriorityQueue;
import mazeData.difCoorPair;
import Controller.theEyeOfAgamotto;
import mazeData.xyPair;

import java.util.*;

public class Dijkstra implements mazeSolver{
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    mazeData.maze maze;
    public int[][] arr;
    HashMap<String, xyPair> map=new HashMap<>();
    customPriorityQueue pq=new customPriorityQueue();
    boolean stop=false;


    public Dijkstra(mazeData.maze maze) {
        this.maze = maze;
        arr=new int[maze.arr.length][maze.arr[0].length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){if(i==maze.startY&&j==maze.startX){arr[i][j]=0;continue;}
                arr[i][j]=-1;
            }
        }
    }

    public void addToMap(xyPair tmp, xyPair point){//tmp the child point the parent
        map.put(tmp.toString(),point);
    }

    public void finish(xyPair xyPair){
        stop=true;
        xyPair =map.get(xyPair.toString());
        while(xyPair.x!=maze.startX|| xyPair.y!=maze.startY){e.waiter();e.waiter();
            maze.arr[xyPair.y][xyPair.x]=4;
            xyPair =map.get(xyPair.toString());
        }
        while(!pq.isEmpty()){
            maze.arr[pq.peek().y][pq.getMin().pair.x]=3;
            e.waiterhalf();
        }
        maze.arr[maze.startY][maze.startX]=-1;
        maze.arr[maze.endY][maze.endX]=-1;
    }

    private void addAdjacents(xyPair point){
        if(maze.arr[point.y][point.x]!=-1) maze.arr[point.y][point.x]=3;
        if(maze.arr[maze.startY][maze.startX]!=-1){maze.arr[maze.startY][maze.startX]=-1;}
        if(point.x+1<maze.arr[0].length&&(maze.arr[point.y][point.x+1]==0||maze.arr[point.y][point.x+1]==-1||maze.arr[point.y][point.x+1]==2)&&!(maze.startY==point.y&&maze.startX==point.x+1)){
            xyPair tmp=new xyPair(point.x+1,point.y);
            if(arr[point.y][point.x+1]==-1){
                arr[point.y][point.x+1]=arr[point.y][point.x]+maze.obs[point.y][point.x+1];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y][point.x+1],tmp));
                addToMap(tmp,point);
            }
            else if(arr[point.y][point.x+1]>arr[point.y][point.x]+maze.obs[point.y][point.x+1]){
                arr[point.y][point.x+1]=arr[point.y][point.x]+maze.obs[point.y][point.x+1];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y][point.x+1],tmp));
                addToMap(tmp,point);
            }
            if(point.y==maze.endY&&point.x+1==maze.endX){finish(tmp);return;}
            maze.arr[point.y][point.x+1]=2;
            e.waiterhalf();
        }
        if(point.x-1>=0&&(maze.arr[point.y][point.x-1]==0||maze.arr[point.y][point.x-1]==-1||maze.arr[point.y][point.x-1]==2)&&!(maze.startY==point.y&&maze.startX==point.x-1)){
            xyPair tmp=new xyPair(point.x-1,point.y);
            if(arr[point.y][point.x-1]==-1){
                arr[point.y][point.x-1]=arr[point.y][point.x]+maze.obs[point.y][point.x-1];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y][point.x-1],tmp));
                addToMap(tmp,point);
            }
            else if(arr[point.y][point.x+1]>arr[point.y][point.x]+maze.obs[point.y][point.x+1]){
                arr[point.y][point.x-1]=arr[point.y][point.x]+maze.obs[point.y][point.x-1];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y][point.x+1],tmp));
                addToMap(tmp,point);
            }
            if(point.y==maze.endY&&point.x-1==maze.endX){finish(tmp);return;}
            maze.arr[point.y][point.x-1]=2;
            e.waiterhalf();
        }
        if(point.y+1<maze.arr.length&&(maze.arr[point.y+1][point.x]==0||maze.arr[point.y+1][point.x]==-1||maze.arr[point.y+1][point.x]==2)&&!(maze.startY==point.y+1&&maze.startX==point.x)){
            xyPair tmp=new xyPair(point.x,point.y+1);
            if(arr[point.y+1][point.x]==-1){
                arr[point.y+1][point.x]=arr[point.y][point.x]+maze.obs[point.y+1][point.x];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y+1][point.x],tmp));
                addToMap(tmp,point);
            }
            else if(arr[point.y+1][point.x]>arr[point.y][point.x]+maze.obs[point.y+1][point.x]){
                arr[point.y+1][point.x]=arr[point.y][point.x]+maze.obs[point.y+1][point.x];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y+1][point.x],tmp));
                addToMap(tmp,point);
            }
            if(point.y+1==maze.endY&&point.x==maze.endX){finish(tmp);return;}
            maze.arr[point.y+1][point.x]=2;
            e.waiterhalf();
        }
        if(point.y-1>=0&&(maze.arr[point.y-1][point.x]==0||maze.arr[point.y-1][point.x]==-1||maze.arr[point.y-1][point.x]==2)&&!(maze.startY==point.y-1&&maze.startX==point.x)){
            xyPair tmp=new xyPair(point.x,point.y-1);
            if(arr[point.y-1][point.x]==-1){
                arr[point.y-1][point.x]=arr[point.y][point.x]+maze.obs[point.y-1][point.x];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y-1][point.x],tmp));
                addToMap(tmp,point);
            }
            else if(arr[point.y-1][point.x]>arr[point.y][point.x]+maze.obs[point.y-1][point.x]){
                arr[point.y-1][point.x]=arr[point.y][point.x]+maze.obs[point.y-1][point.x];
                pq.add(new difCoorPair(arr[point.y][point.x]+maze.obs[point.y-1][point.x],tmp));
                addToMap(tmp,point);
            }
            if(point.y-1==maze.endY&&point.x==maze.endX){finish(tmp);return;}
            maze.arr[point.y-1][point.x]=2;
            e.waiterhalf();
        }

    }

    public void find () {
        addAdjacents(new xyPair(maze.startX,maze.startY));
        while(!stop&&!pq.storage.isEmpty()){
            addAdjacents(pq.getMin().pair);
            e.waiterhalf();
        }
        for(int i=0;i<maze.arr.length;i++){
            for(int j=0;j<maze.arr[0].length;j++){if(maze.arr[i][j]==2)maze.arr[i][j]=3;}
        }
        e.isSolving=false;
    }
}
