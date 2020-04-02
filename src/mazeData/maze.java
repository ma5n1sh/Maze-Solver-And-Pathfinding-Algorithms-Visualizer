package mazeData;

import gui.xyPair;

import java.util.ArrayList;

public class maze {
    public int arr[][]=new int[60][126],obs[][]=new int[60][126];
    public int startX=-1,startY=-1,endX=-1,endY=-1;
    boolean flag=false;

    public maze() {
        for(int i=0;i<60;i++){
            for(int j=0;j<126;j++){arr[i][j]=0;obs[i][j]=1;}
        }
    }

    public void print(){
        for(int i=0;i<15;i++){
            for(int j=0;j<31;j++){
                System.out.print(" "+ arr[i][j]);
            }
            System.out.println("*"+i);
            System.out.println();
        }

    }

    private boolean containsOne(ArrayList<xyPair> a){
        if (a.isEmpty())return false;
        for(int i=0;i<a.size();i++){
            if(arr[a.get(i).y][a.get(i).x]==1)return true;
        }
        return false;
    }
    private void setObs(ArrayList<xyPair> a){
        int maxObs=0;
        for(int i=0;i<a.size();i++){
            if(obs[a.get(i).y][a.get(i).x]>maxObs){maxObs=obs[a.get(i).y][a.get(i).x];}
        }
        if(maxObs==7){
            for(int i=0;i<a.size();i++){
                obs[a.get(i).y][a.get(i).x]=0;
                arr[a.get(i).y][a.get(i).x]=1;
            }
        }
        else{
            for(int i=0;i<a.size();i++){
                obs[a.get(i).y][a.get(i).x]=maxObs+1;
            }

        }
    }
    public void setOnes(ArrayList<xyPair>a){
        for(int i=0;i<a.size();i++){
            obs[a.get(i).y][a.get(i).x]=0;
            arr[a.get(i).y][a.get(i).x]=0;
        }
    }

    public void reversecell(int xpos,int ypos){
        xpos=xpos-10;
        xpos=xpos/10;
        ypos=ypos-200;
        ypos=ypos/10;
        ArrayList<xyPair> a=new ArrayList<xyPair>();
        a.add(new xyPair(xpos,ypos));
        if((xpos-1>=0)&&((xpos-1!=startX)||(ypos!=startY))&&((xpos-1!=endX)||(ypos!=endY)))a.add(new xyPair(xpos-1,ypos));
        if((ypos-1>=0)&&((xpos!=startX)||(ypos-1!=startY))&&((xpos!=endX)||(ypos-1!=endY)))a.add(new xyPair(xpos ,ypos-1));
        if((ypos-1>=0)&&(xpos-1>=0)&&((xpos-1!=startX)||(ypos-1!=startY))&&((xpos-1!=endX)||(ypos-1!=endY)))a.add(new xyPair(xpos-1,ypos-1));
        if((xpos==startX&&ypos==startY)||(xpos==endX&&ypos==endY)){return;}
        /*if(this.arr[ypos][xpos]==0&&obs[ypos][xpos]<7){this.obs[ypos][xpos]++;}
        else if(this.arr[ypos][xpos]==1){this.arr[ypos][xpos]=0;this.obs[ypos][xpos]=1;}
        else{this.arr[ypos][xpos]=1;}*/
        if(containsOne(a)){setOnes(a);}
        else{setObs(a);}
    }

    public void startEnd(int xpos,int ypos){
        xpos=xpos-10;
        xpos=xpos/10;
        ypos=ypos-200;
        ypos=ypos/10;
        if(!flag){if(startY!=-1)arr[startY][startX]=0;
            startX=xpos;startY=ypos;
        }
        else{if(endY!=-1)arr[endY][endX]=0;
            endX=xpos;endY=ypos;
        }
        flag=!flag;
        arr[ypos][xpos]=-1;
        System.out.println(startX+" "+startY+" "+endX+" "+endY+" "+arr[ypos][xpos]);
    }


}
