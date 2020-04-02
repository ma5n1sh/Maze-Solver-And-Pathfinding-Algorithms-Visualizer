package gui;

import mazeData.maze;
import org.newdawn.slick.*;


public class mazeController {
    mazeData.maze maze=new maze();
    mazeSolver b;
    mazeGenerator hak;
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    Image generatekillhunt=new Image("res/shk.png"),generaterbt=new Image("res/srbt.png"),generateweight=new Image("res/button.png"),
    solvedfs=new Image("res/sdfs.png"),solvebfs=new Image("res/sbfs.png"),solvea=new Image("res/sa.png"),reset=new Image("res/reset.png"),
    solved=new Image("res/sd.png"),resetsol=new Image("res/reset solution.png"),title=new Image("res/title.png");
    int backup[][]=new int[maze.arr.length][maze.arr[0].length];

    public mazeController() throws SlickException {
        for(int i=0;i<10;i++){e.waiter();}

    }

    public void Backup(){
        backup=new int[maze.arr.length][maze.arr[0].length];
        for(int i=0;i<60;i++){
            for(int j=0;j<126;j++){backup[i][j]=maze.arr[i][j];}
        }
    }


    public void draw (Graphics g){
        //background
        g.setColor(new Color(46,196,182));
        g.fillRect(0,0,1280,820);
        g.drawImage(title,0,0);
        //__________________________________________________________
        //buttons
        g.drawImage(generatekillhunt,1000,10);
        g.drawImage(generaterbt,650,10);
        g.drawImage(generateweight,1000,66);
        g.drawImage(solvedfs,650,66);
        g.drawImage(solvebfs,770,66);
        g.drawImage(solvea,890,66);
        g.drawImage(reset,1182,66);
        g.drawImage(solved,755,120);
        g.drawImage(resetsol,1060,120);
        //_________________________________________________________
        //maze
        g.setColor(new Color(1,22,39));
        g.fillRect(5,195,1270,610);
        for(int i=0;i<maze.arr.length;i++){
            for(int j=0;j<maze.arr[0].length;j++){
                if(maze.arr[i][j]==0){g.setColor(new Color(253,255,252));}
                else if(maze.arr[i][j]==1){g.setColor(new Color(1,22,39));}
                else if(maze.arr[i][j]==2){g.setColor(new Color(255,87,51));}
                else if(maze.arr[i][j]==3){g.setColor(new Color(255,159,28));}
                else if(maze.arr[i][j]==4){g.setColor(new Color(231,29,54));}
                else{g.setColor(Color.green);}
                g.setLineWidth((float) 0.5);
                if(maze.obs[i][j]>1&&maze.arr[i][j]==0) {
                    switch (maze.obs[i][j]){
                        case 2:g.setColor(new Color(240,240,240));break;
                        case 3:g.setColor(new Color(210,210,210));break;
                        case 4:g.setColor(new Color(180,180,180));break;
                        case 5:g.setColor(new Color(150,150,150));break;
                        case 6:g.setColor(new Color(120,120,120));break;
                        default:g.setColor(new Color(47,47,79));break;
                    }
                }
                g.fillRect(10+10*j,200+10*i,10,10);
            }




        }
    }

    public void generateweight(){
        for(int i=0;i<maze.arr.length;i+=2) {
            for (int j = 0; j < maze.arr[0].length; j += 2) {
                if (maze.arr[i][j] == 0) {
                    maze.obs[i][j] = (int) (Math.random() * 8);
                }

            }
        }
    }

    void mouseLPressed(int xpos,int ypos){

        if(xpos>10&&xpos<1260&&ypos>200&&ypos<800){
            maze.reversecell(xpos,ypos);
        }
        else if( xpos>1000&&xpos<1000+generatekillhunt.getWidth()&&ypos>10&&ypos<56&&!e.isGenerating&&!e.isSolving){
            e.isGenerating=true;
            hak=new huntAndKillMazeGenerator(maze);

            new Thread(new Runnable() {
                public void run() {
                    hak.generateMaze();

                }
            }).start();

        }
        else if(xpos>650&&xpos<1000+generaterbt.getWidth()&&ypos>10&&ypos<56&&!e.isGenerating&&!e.isSolving){
            e.isGenerating=true;
            hak=new recursiveBackTrackerMazeGenerator(maze);

            new Thread(new Runnable() {
                public void run() {
                    hak.generateMaze();
                }
            }).start();
        }
        else if(xpos>650&&xpos<650+solvedfs.getWidth()&&ypos>66&&ypos<66+solvedfs.getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            Backup();
            new Thread(new Runnable() {
                public void run() {
                    b=new DFS(maze);
                    b.find();
                }
            }).start();
        }

        else if(xpos>770&&xpos<770+solvebfs.getWidth()&&ypos>66&&ypos<66+solvebfs.getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            Backup();
            e.isSolving=true;
            new Thread(new Runnable() {
                public void run() {
                    b=new BFS(maze);
                    b.find();
                }
            }).start();
        }
        else if(xpos>890&&xpos<890+solvea.getWidth()&&ypos>66&&ypos<66+solvebfs.getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            Backup();
            new Thread(new Runnable() {
                public void run() {
                    b=new AStar(maze);
                    b.find();
                }
            }).start();
        }

        else if(xpos>775&&xpos<775+solved.getWidth()&&ypos>120&&ypos<120+solved.getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            Backup();
            new Thread(new Runnable() {
                public void run() {
                    b=new Dijkstra(maze);
                    b.find();
                }
            }).start();
        }
        else if(xpos>1000&&xpos<1000+generateweight.getWidth()&&ypos>66&&ypos<66+reset.getHeight()){
            generateweight();
        }

        else if(xpos>1182&&xpos<1182+reset.getWidth()&&ypos>66&&ypos<66+reset.getHeight()){
            maze=new maze();
        }

        else if(xpos>1060&&xpos<1060+resetsol.getWidth()&&ypos>120&&ypos<120+resetsol.getHeight()){
            maze.arr=backup;
            Backup();
        }




    }
    void mouseRPressed(int xpos,int ypos){
        if(xpos>10&&xpos<1260&&ypos>200&&ypos<800){
            maze.startEnd(xpos,ypos);
        }


    }


}