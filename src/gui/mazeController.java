package gui;

import mazeData.maze;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class mazeController {
    mazeData.maze maze=new maze();
    AStar b=new AStar(maze);
    huntAndKillMazeGenerator hak=new huntAndKillMazeGenerator(maze);

    public mazeController() {
        new Thread(new Runnable() {
            public void run() {
                hak.generateMaze();
            }
        }).start();
    }

    public void draw (Graphics g){
        g.setColor(new Color(153,225,217));
        g.fillRect(0,0,1280,720);
        for(int i=0;i<30;i++){
            for(int j=0;j<62;j++){
                if(maze.arr[i][j]==0){g.setColor(new Color(50,41,47));}
                else if(maze.arr[i][j]==1){g.setColor(new Color(112,93,86));}
                else if(maze.arr[i][j]==2){g.setColor(Color.pink);}
                else if(maze.arr[i][j]==3){g.setColor(Color.orange);}
                else if(maze.arr[i][j]==4){g.setColor(Color.red);}
                else{g.setColor(Color.green);}
                g.fillRect(15+20*j,100+20*i,20,20);
                if(maze.obs[i][j]>1) {
                    g.setLineWidth(1);
                    g.setColor(Color.black);
                    g.drawString(Integer.toString(maze.obs[i][j]),15+20*j+5,100+20*i+5);
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