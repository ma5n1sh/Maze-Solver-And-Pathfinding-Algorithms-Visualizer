package gui;

//this class contains the time stone that controls the flow of time

public class theEyeOfAgamotto {
    public static long time=0;
    public void waiter(){time=System.currentTimeMillis();
        while(System.currentTimeMillis()-time<5){
            System.out.print("");
        }
        time=System.currentTimeMillis();;
    }

}
