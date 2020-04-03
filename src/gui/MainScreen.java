package gui;

import Controller.mazeController;
import Controller.theEyeOfAgamotto;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MainScreen extends BasicGameState {
    boolean flag=false;
    theEyeOfAgamotto e=new theEyeOfAgamotto();
    Image cur;

    mazeController cont;

    public MainScreen(int state) throws SlickException {

    }

    public void init(GameContainer gc, StateBasedGame sbg )throws SlickException {
        cont=new mazeController();
        cur=new Image("res/cursor.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int xpos=Mouse.getX();
        int ypos=820-Mouse.getY();
        g.drawImage(cur,xpos-20,ypos-20);
        cont.draw(g);
        g.setColor(Color.black);
        g.drawString("MA5N1SH",1208,803);

    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int xpos=Mouse.getX();
        int ypos=820-Mouse.getY();
        if(Mouse.isButtonDown(0)){
            if(!flag){cont.mouseLPressed(xpos,ypos);flag=true;}
        }
        if(Mouse.isButtonDown(1)){
            if(!flag){cont.mouseRPressed(xpos,ypos);flag=true;}
        }
        if(!Mouse.isButtonDown(0)&&!Mouse.isButtonDown(1)){
            flag=false;
        }
        //e.time+=delta;
    }

    public void window(String window, int delta, StateBasedGame sbg) {}

    public int getID() {return 0;}

}
