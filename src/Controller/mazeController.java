package Controller;

import Algorithms.*;
import Controller.theEyeOfAgamotto;
import mazeData.maze;
import mazeData.xyPair;
import org.newdawn.slick.*;


public class mazeController {
    mazeData.maze maze=new maze();
    mazeSolver b;
    mazeGenerator hak;
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    boolean solvedFlag=false,colrflag=false;
    public boolean play=false;
    Play playalgo;
    Image[] generatekillhunt={new Image("res/shk.png"),new Image("res/deacshk.png")},
            generaterbt={new Image("res/srbt.png"),new Image("res/deacsrbt.png")},
            solvedfs={new Image("res/sdfs.png"),new Image("res/deacsdfs.png")},
            solvebfs={new Image("res/sbfs.png"),new Image("res/deacsbfs.png")},
            solvea={new Image("res/sa.png"),new Image("res/deacsa.png")},
            solved={new Image("res/sd.png"),new Image("res/deacsd.png")},
            resetsol={new Image("res/reset solution.png"),new Image("res/deacreset solution.png")},
            playbut={new Image("res/play.png"),new Image("res/deacplay.png")};
    int backup[][]=new int[maze.arr.length][maze.arr[0].length];
    Image reset=new Image("res/reset.png"),title=new Image("res/title.png"),generateweight=new Image("res/button.png");
    long time;

    public mazeController() throws SlickException {
        time= System.currentTimeMillis();
    }

    public void Backup(){
        backup=new int[maze.arr.length][maze.arr[0].length];
        for(int i=0;i<60;i++){
            for(int j=0;j<126;j++){backup[i][j]=maze.arr[i][j];}
        }
    }

    private boolean solveactive(){
        if(!e.isGenerating && !e.isSolving && maze.endY != -1)return true;
        return false;
    }

    public void draw (Graphics g){
        if(System.currentTimeMillis()-time>500){time=System.currentTimeMillis();colrflag=!colrflag;}
        //background
        g.setColor(new Color(46,196,182));
        g.fillRect(0,0,1280,820);
        g.drawImage(title,0,0);
        //__________________________________________________________
        //buttons
        g.drawImage(!e.isGenerating?generatekillhunt[0]:generatekillhunt[1],1000,10);
        g.drawImage(!e.isGenerating?generaterbt[0]:generaterbt[1],650,10);
        g.drawImage(generateweight,1000,66);
        g.drawImage(solveactive()?solvedfs[0]:solvedfs[1],650,66);
        g.drawImage(solveactive()?solvebfs[0]:solvebfs[1],770,66);
        g.drawImage(solveactive()?solvea[0]:solvea[1],890,66);
        g.drawImage(reset,1182,66);
        g.drawImage(solveactive()?solved[0]:solved[1],755,120);
        g.drawImage(solveactive()?resetsol[0]:resetsol[1],1060,120);
        g.drawImage(solveactive()&&!play?playbut[0]:playbut[1],530,10);
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
                else if(maze.arr[i][j]==5){g.setColor(colrflag?Color.cyan:new Color(253,255,252));}
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
                if(maze.obs[i][j]>1&&maze.arr[i][j]==3) {
                    switch (maze.obs[i][j]){
                        case 2:g.setColor(new Color(255,255,102));break;
                        case 3:g.setColor(new Color(255,255,51));break;
                        case 4:g.setColor(new Color(255,255,0));break;
                        case 5:g.setColor(new Color(204,204,0));break;
                        case 6:g.setColor(new Color(153,153,0));break;
                        default:g.setColor(new Color(102,102,0));break;
                    }
                }
                g.fillRect(10+10*j,200+10*i,10,10);
            }




        }
    }

    public void initiatePlay(){
        resetSolu();
        playalgo=new Play(maze,this);
    }

  /*  private boolean[] surroundingArray(xyPair xy){
        boolean tmp[]={false,false,false,false};
        if(xy.y-1>=0&&maze.arr[xy.y-1][xy.x]==4)tmp[0]=true;
        if(xy.y+1<maze.arr.length&&maze.arr[xy.y+1][xy.x]==4)tmp[2]=true;
        if(xy.x+1<maze.arr[0].length&&maze.arr[xy.y][xy.x+1]==4)tmp[1]=true;
        if(xy.x-1>=0&&maze.arr[xy.y][xy.x-1]==4)tmp[3]=true;
        return tmp;

    }

    private void darwLine(Graphics g){
        g.setColor(Color.white);
        g.setLineWidth(2);
        for(int i=0;i<maze.arr.length;i++) {
            for (int j = 0; j < maze.arr[0].length; j++) {
                if(maze.arr[i][j]==4){
                    boolean tmp[]=surroundingArray(new xyPair(j,i));
                    if(boolean){}
                }
            }
        }
    }*/


    public void playup(){
        if(play){
            new Thread(new Runnable() {
                public void run() {
                    playalgo.goUp();
                }
            }).start();


        }
    }
    public void playdown(){
        if(play){
            new Thread(new Runnable() {
                public void run() {
                    playalgo.goDown();
                }
            }).start();


        }
    }
    public void playleft(){
        if(play){
            new Thread(new Runnable() {
                public void run() {
                    playalgo.goLeft();
                }
            }).start();


        }
    }
    public void playright(){
        if(play){
            new Thread(new Runnable() {
                public void run() {
                    playalgo.goRight();
                }
            }).start();

        }
    }

    public void generateweight(){
        for(int i=0;i<1000;i++) {
            maze.reversecell(10+(int)(Math.random()*1260),200+(int)(Math.random()*600));
        }
    }

    public void mouseLPressed(int xpos, int ypos){

        if(xpos>10&&xpos<1270&&ypos>200&&ypos<800){
            maze.reversecell(xpos,ypos);
            if(!solvedFlag)Backup();
        }
        else if( xpos>1000&&xpos<1000+generatekillhunt[0].getWidth()&&ypos>10&&ypos<56&&!e.isGenerating&&!e.isSolving){
            e.isGenerating=true;
            hak=new huntAndKillMazeGenerator(maze);

            new Thread(new Runnable() {
                public void run() {
                    hak.generateMaze();

                }
            }).start();

        }
        else if( xpos>530&&xpos<530+playbut[0].getWidth()&&ypos>10&&ypos<10+playbut[0].getHeight()&&!e.isGenerating&&!e.isSolving&&!solvedFlag&&!play){
           initiatePlay();
            play=true;
        }
        else if(xpos>650&&xpos<1000+generaterbt[0].getWidth()&&ypos>10&&ypos<56&&!e.isGenerating&&!e.isSolving){
            e.isGenerating=true;
            hak=new recursiveBackTrackerMazeGenerator(maze);
            new Thread(new Runnable() {
                public void run() {
                    hak.generateMaze();
                }
            }).start();
        }
        else if(xpos>650&&xpos<650+solvedfs[0].getWidth()&&ypos>66&&ypos<66+solvedfs[0].getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            resetSolu();
            new Thread(new Runnable() {
                public void run() {
                    b=new DFS(maze);
                    b.find();
                }
            }).start();
        }

        else if(xpos>770&&xpos<770+solvebfs[0].getWidth()&&ypos>66&&ypos<66+solvebfs[0].getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            resetSolu();
            solvedFlag=true;
            e.isSolving=true;
            new Thread(new Runnable() {
                public void run() {
                    solvedFlag=true;
                    b=new BFS(maze);
                    b.find();
                }
            }).start();
        }
        else if(xpos>890&&xpos<890+solvea[0].getWidth()&&ypos>66&&ypos<66+solvebfs[0].getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            resetSolu();
            solvedFlag=true;
            new Thread(new Runnable() {
                public void run() {
                    b=new AStar(maze);
                    b.find();
                }
            }).start();
        }

        else if(xpos>775&&xpos<775+solved[0].getWidth()&&ypos>120&&ypos<120+solved[0].getHeight()&&maze.endY!=-1&&!e.isGenerating&&!e.isSolving){
            e.isSolving=true;
            resetSolu();
            solvedFlag=true;
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
            this.maze=new maze();
            e.isGenerating=false;
            e.isSolving=false;
        }

        else if(xpos>1060&&xpos<1060+resetsol[0].getWidth()&&ypos>120&&ypos<120+resetsol[0].getHeight()&&!e.isSolving){
            resetSolu();
            if(playalgo!=null){play=false;}
        }




    }
    public void resetSolu(){
        maze.arr=backup;
        solvedFlag=false;
        Backup();
    }
    public void mouseRPressed(int xpos, int ypos){
        if(xpos>10&&xpos<1270&&ypos>200&&ypos<800){
            maze.startEnd(xpos,ypos);
            if(!solvedFlag)Backup();
        }


    }



}