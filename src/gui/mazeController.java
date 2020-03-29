package gui;

import mazeData.maze;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class mazeController {
    mazeData.maze maze=new maze();
    BFS b=new BFS(maze);

    public void draw (Graphics g){
        for(int i=0;i<15;i++){
            for(int j=0;j<31;j++){
                if(maze.arr[i][j]==0){g.setColor(Color.gray);}
                else if(maze.arr[i][j]==1){g.setColor(Color.red);}
                else if(maze.arr[i][j]==2){g.setColor(Color.pink);}
                else if(maze.arr[i][j]==3){g.setColor(Color.orange);}
                else if(maze.arr[i][j]==4){g.setColor(Color.cyan);}
                else{g.setColor(Color.green);}
                g.fillRect(10+40*j,100+40*i,39,39);
                g.setColor(Color.white);
                g.drawRect(10+40*j,100+40*i,40,40);
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
