package Tank;

import Enemy.ShotBehavior;
import Enemy.SkillBehavior;

public abstract class Tank {
    private int x;
    private int y;//坦克的初始位置
    private int direct;
    private int speed=0;
    private boolean isLive=true;
    public ShotBehavior shotBehavior;
    public SkillBehavior skillBehavior;
    public Tank(){}
    public Tank(int x, int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct=direct;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void moveUp(){
        y-=3;
    }
    public void moveRight(){
        x+=3;
    }
    public void moveDown(){
        y+=3;
    }
    public void moveLeft(){
        x-=3;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
