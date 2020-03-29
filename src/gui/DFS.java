package gui;

import mazeData.maze;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {
        mazeData.maze maze;
        HashMap<String, pair> map=new HashMap<>();
        Stack<pair> q=new Stack<>();
        boolean Stop=false;
        theEyeOfAgamotto e=new theEyeOfAgamotto();


        public DFS(mazeData.maze maze) {
            this.maze = maze;
        }

        public void addToMap(pair tmp,pair point){//tmp the child point the parent
            if(!map.containsKey(tmp.toString()))map.put(tmp.toString(),point);
        }

        public void finish(pair pair){
            Stop=true;
            pair=map.get(pair.toString());
            while(pair.x!=maze.startX||pair.y!=maze.startY){e.waiter();
                maze.arr[pair.y][pair.x]=4;
                pair=map.get(pair.toString());
            }
            maze.arr[maze.startY][maze.startX]=-1;
        }

        private void addAdjacents(pair point){
            if(maze.arr[point.y][point.x]!=-1)maze.arr[point.y][point.x]=3;
            if(maze.arr[maze.startY][maze.startX]!=-1){maze.arr[maze.startY][maze.startX]=-1;}
            if(point.x+1<31&&(maze.arr[point.y][point.x+1]==0||maze.arr[point.y][point.x+1]==-1)){
                pair tmp=new pair(point.x+1,point.y);
                q.add(tmp);
                addToMap(tmp,point);
                if(point.y==maze.endY&&point.x+1==maze.endX){finish(tmp);}
                else maze.arr[point.y][point.x+1]=2;
                e.waiter();
            }
            if(point.x-1>=0&&(maze.arr[point.y][point.x-1]==0||maze.arr[point.y][point.x-1]==-1)){
                pair tmp=new pair(point.x-1,point.y);
                q.add(tmp);
                addToMap(tmp,point);
                if(point.y==maze.endY&&point.x-1==maze.endX){finish(tmp);}
                else maze.arr[point.y][point.x-1]=2;
                e.waiter();
            }
            if(point.y+1<15&&(maze.arr[point.y+1][point.x]==0||maze.arr[point.y+1][point.x]==-1)){
                pair tmp=new pair(point.x,point.y+1);
                q.add(tmp);
                addToMap(tmp,point);
                if(point.y+1==maze.endY&&point.x==maze.endX){finish(tmp);}
                else maze.arr[point.y+1][point.x]=2;
                e.waiter();
            }
            if(point.y-1>=0&&(maze.arr[point.y-1][point.x]==0||maze.arr[point.y-1][point.x]==-1)){
                pair tmp=new pair(point.x,point.y-1);
                q.add(tmp);
                addToMap(tmp,point);
                if(point.y-1==maze.endY&&point.x==maze.endX){finish(tmp);}
                else maze.arr[point.y-1][point.x]=2;
                e.waiter();
            }

        }

        public void find(){
            q.add(new pair(maze.startX,maze.startY));
            while(!q.isEmpty()&&!Stop){e.waiter();
                addAdjacents(q.pop());

            }
        }

    }


