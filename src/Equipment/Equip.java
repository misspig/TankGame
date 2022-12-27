package Equipment;
import Tank.*;

import java.util.ArrayList;

public abstract class Equip extends Tank{
    private int x;
    private int y;
    public ArrayList<String> equips=new ArrayList<>();
    private boolean Live=true;
    public Equip(){}
    public Equip(int x,int y){
        this.x=x;
        this.y=y;
    }

    public ArrayList<String> getEquips() {
        return equips;
    }

    public void setEquips(ArrayList<String> equips) {
        this.equips = equips;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return Live;
    }

    public void setLive(boolean live) {
        Live = live;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }


}
