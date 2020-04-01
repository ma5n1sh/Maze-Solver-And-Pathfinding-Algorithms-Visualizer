package gui;

import java.util.ArrayList;

public class huntAndKillMazeGenerator {
    mazeData.maze maze;
    boolean visited[][];
    theEyeOfAgamotto e=new theEyeOfAgamotto();

    public huntAndKillMazeGenerator(mazeData.maze maze) {
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
        return true;
    }
    private boolean kill(xyPair source){
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

        if(!isPossible(source,destination)&&a.isEmpty())return false;
        if(destination.x==source.x){
            maze.arr[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=0;
            visited[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=true;
            maze.arr[destination.y][destination.x]=0;
            visited[destination.y][destination.x]=true;
        }
        else{maze.arr[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=0;
            visited[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=true;
            maze.arr[destination.y][destination.x]=0;
            visited[destination.y][destination.x]=true;
        }
        e.waiter();
        source.x=destination.x;
        source.y=destination.y;
        return true;
    }

    private xyPair check4adj(xyPair pair){
        if(isPossible(pair,new xyPair(pair.x,pair.y-2))) return new xyPair(pair.x,pair.y-2);
        if(isPossible(pair,new xyPair(pair.x,pair.y+2))) return new xyPair(pair.x,pair.y+2);
        if(isPossible(pair,new xyPair(pair.x-2,pair.y))) return new xyPair(pair.x-2,pair.y);
        if(isPossible(pair,new xyPair(pair.x+2,pair.y))) return new xyPair(pair.x+2,pair.y);
        return new xyPair(-1,-1);
    }

    private xyPair hunt(){
        for(int i=0;i<maze.arr.length;i+=2){
            for (int j=0;j<maze.arr[0].length;j+=2){
                if(visited[i][j]){
                    xyPair source=new xyPair(j,i);
                    xyPair destination=check4adj(source);
                    if(destination.x!=-1){
                        if(destination.x==source.x){
                            maze.arr[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=0;
                            visited[(int) (source.y+Math.signum(destination.y-source.y))][source.x]=true;
                            maze.arr[destination.y][destination.x]=0;
                            visited[destination.y][destination.x]=true;
                        }
                        else{maze.arr[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=0;
                            visited[source.y][(int) (source.x+Math.signum(destination.x-source.x))]=true;
                            maze.arr[destination.y][destination.x]=0;
                            visited[destination.y][destination.x]=true;
                        }

                        return destination;
                    }
                }
            }
        }
        e.waiter();
        return new xyPair(-1,-1);
    }

    public void generateMaze(){
        xyPair start;
        boolean flag;
        int temp=(int)Math.random()*2;
        if(temp==0){start=new xyPair(0,0);}
        else{start=new xyPair(1,1);}
        maze.arr[start.y][start.x]=0;
        visited[start.y][start.x]=true;
        while(start.x!=-1) {
            do {
                flag = kill(start);
            } while (flag);
            start=hunt();
        }
        int count =10;
        while(count>0){
            if(maze.arr[(int)(Math.random()*maze.arr.length)][(int)(Math.random()*maze.arr[0].length)]==1){
                maze.arr[(int)(Math.random()*maze.arr.length)][(int)(Math.random()*maze.arr[0].length)]=0;
                count--;
                e.waiter();
                System.out.println(count);
            }
        }
        for(int i=0;i<maze.arr.length;i+=2){
            for (int j=0;j<maze.arr[0].length;j+=2){
                if(maze.arr[i][j]==0){e.waiter();
                    maze.obs[i][j]=(int)(Math.random()*8);
                }
            }
        }

    }


}
