package gui;

import mazeData.maze;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class mazeController {
    mazeData.maze maze=new maze();
    BFS b=new BFS(maze);
    huntAndKillMazeGenerator hak=new huntAndKillMazeGenerator(maze);
    theEyeOfAgamotto e=new theEyeOfAgamotto();

    public mazeController() {
        for(int i=0;i<10;i++){e.waiter();}
        new Thread(new Runnable() {
            public void run() {
                hak.generateMaze();
            }
        }).start();
    }

    public void draw (Graphics g){

        g.setColor(new Color(46,196,182));
        g.fillRect(0,0,1280,720);
        g.setColor(new Color(1,22,39));
        g.fillRect(5,95,1270,610);
        for(int i=0;i<maze.arr.length;i++){
            for(int j=0;j<maze.arr[0].length;j++){
                if(maze.arr[i][j]==0){g.setColor(new Color(253,255,252));}
                else if(maze.arr[i][j]==1){g.setColor(new Color(1,22,39));}
                else if(maze.arr[i][j]==2){g.setColor(new Color(255,87,51));}
                else if(maze.arr[i][j]==3){g.setColor(new Color(255,159,28));}
                else if(maze.arr[i][j]==4){g.setColor(new Color(231,29,54));}
                else{g.setColor(Color.green);}
                g.fillRect(10+10*j,100+10*i,10,10);
                if(maze.obs[i][j]>1) {
                    g.setLineWidth(1);
                    g.setColor(Color.black);
                    g.drawString(Integer.toString(maze.obs[i][j]),10+10*j,100+10*i);
                }
/*                g.setColor(new Color(0,0,0));
                g.drawRect(10+20*j,100+20*i,20,20);*/
            }
        }
    }

    void mouseLPressed(int xpos,int ypos){
        maze.reversecell(xpos,ypos);
    }
    void mouseRPressed(int xpos,int ypos){
        maze.startEnd(xpos,ypos);
        if(maze.endY!=-1){
            new Thread(new Runnable() {
                public void run() {
                    b.find();
                }
            }).start();
        }
    }


}