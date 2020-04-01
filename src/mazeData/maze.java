package mazeData;

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
    public void reversecell(int xpos,int ypos){
        xpos=xpos-10;
        xpos=xpos/10;
        ypos=ypos-100;
        ypos=ypos/10;
        if((xpos==startX&&ypos==startY)||(xpos==endX&&ypos==endY)){return;}
        if(this.arr[ypos][xpos]==0&&obs[ypos][xpos]<7){this.obs[ypos][xpos]++;}
        else if(this.arr[ypos][xpos]==1){this.arr[ypos][xpos]=0;this.obs[ypos][xpos]=1;}
        else{this.arr[ypos][xpos]=1;}
    }

    public void startEnd(int xpos,int ypos){
        xpos=xpos-10;
        xpos=xpos/10;
        ypos=ypos-100;
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
