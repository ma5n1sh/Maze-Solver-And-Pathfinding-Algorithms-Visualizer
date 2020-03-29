package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState {
    boolean flag=false;
    theEyeOfAgamotto e=new theEyeOfAgamotto();

    mazeController cont=new mazeController();

    public Menu(int state) throws SlickException {
    }

    public void init(GameContainer gc, StateBasedGame sbg )throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setLineWidth(2);
        cont.draw(g);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int xpos=Mouse.getX();
        int ypos=720-Mouse.getY();
        if(Mouse.isButtonDown(0)){
            if(!flag){cont.mouseLPressed(xpos,ypos);flag=true;}
        }
        if(Mouse.isButtonDown(1)){
            if(!flag){cont.mouseRPressed(xpos,ypos);flag=true;}
        }
        if(!Mouse.isButtonDown(0)&&!Mouse.isButtonDown(1)){
            flag=false;
        }
        e.time+=delta;
    }

    public void window(String window, int delta, StateBasedGame sbg) {}

    public int getID() {return 0;}

}
