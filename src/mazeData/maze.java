package mazeData;

public class maze {
    public int arr[][]=new int[15][31];
    public int startX=-1,startY=-1,endX=-1,endY=-1;
    boolean flag=false;

    public maze() {
        for(int i=0;i<15;i++){
            for(int j=0;j<31;j++){arr[i][j]=0;}

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
        xpos=xpos/40;
        ypos=ypos-100;
        ypos=ypos/40;
        if((xpos==startX&&ypos==startY)||(xpos==endX&&ypos==endY)){return;}
        if(this.arr[ypos][xpos]==1){this.arr[ypos][xpos]=0;}
        else{this.arr[ypos][xpos]=1;}
    }

    public void startEnd(int xpos,int ypos){
        xpos=xpos-10;
        xpos=xpos/40;
        ypos=ypos-100;
        ypos=ypos/40;
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
