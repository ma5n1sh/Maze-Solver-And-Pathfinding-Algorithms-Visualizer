package gui;

import mazeData.maze;
import java.util.*;
public class BFS {
    maze maze;
    HashMap<String, xyPair> map=new HashMap<>();
    Queue <xyPair>q=new LinkedList();
    boolean Stop=false;
    theEyeOfAgamotto e=new theEyeOfAgamotto();


    public BFS(mazeData.maze maze) {
        this.maze = maze;
    }

    public void addToMap(xyPair tmp, xyPair point){//tmp the child point the parent
        if(!map.containsKey(tmp.toString()))map.put(tmp.toString(),point);
    }

    public void finish(xyPair xyPair){
        Stop=true;
        xyPair =map.get(xyPair.toString());
        while(xyPair.x!=maze.startX|| xyPair.y!=maze.startY){e.waiter();
            maze.arr[xyPair.y][xyPair.x]=4;
            xyPair =map.get(xyPair.toString());
        }
        maze.arr[maze.startY][maze.startX]=-1;
    }
    private void addAdjacents(xyPair point){
        if(maze.arr[point.y][point.x]!=-1)maze.arr[point.y][point.x]=3;
        if(maze.arr[maze.startY][maze.startX]!=-1){maze.arr[maze.startY][maze.startX]=-1;}
        if(point.x+1<maze.arr[0].length&&(maze.arr[point.y][point.x+1]==0||maze.arr[point.y][point.x+1]==-1)){
            xyPair tmp=new xyPair(point.x+1,point.y);
            q.add(tmp);
            addToMap(tmp,point);
            if(point.y==maze.endY&&point.x+1==maze.endX){finish(tmp);}
            else maze.arr[point.y][point.x+1]=2;
            e.waiter();
        }
        if(point.x-1>=0&&(maze.arr[point.y][point.x-1]==0||maze.arr[point.y][point.x-1]==-1)){
            xyPair tmp=new xyPair(point.x-1,point.y);
            q.add(tmp);
            addToMap(tmp,point);
            if(point.y==maze.endY&&point.x-1==maze.endX){finish(tmp);}
            else maze.arr[point.y][point.x-1]=2;
            e.waiter();
        }
        if(point.y+1<maze.arr.length&&(maze.arr[point.y+1][point.x]==0||maze.arr[point.y+1][point.x]==-1)){
            xyPair tmp=new xyPair(point.x,point.y+1);
            q.add(tmp);
            addToMap(tmp,point);
            if(point.y+1==maze.endY&&point.x==maze.endX){finish(tmp);}
            else maze.arr[point.y+1][point.x]=2;
            e.waiter();
        }
        if(point.y-1>=0&&(maze.arr[point.y-1][point.x]==0||maze.arr[point.y-1][point.x]==-1)){
            xyPair tmp=new xyPair(point.x,point.y-1);
            q.add(tmp);
            addToMap(tmp,point);
            if(point.y-1==maze.endY&&point.x==maze.endX){finish(tmp);}
            else maze.arr[point.y-1][point.x]=2;
            e.waiter();
        }

    }

    public void find(){
        q.add(new xyPair(maze.startX,maze.startY));
        while(!q.isEmpty()&&!Stop){e.waiter();
            addAdjacents(q.poll());

        }
    }

}
