package Tank;

import Equipment.Equip;
import zidan.Zidan;
import Enemy.*;

import java.util.ArrayList;
import java.util.Vector;

public class Hero extends Equip {

    public int speed=6;
    private volatile static Hero hero;
    public Vector<Zidan>zidans=new Vector<>();
    private Hero() {
         shotBehavior=new heroshot();
    }

    public static Hero getInstance(){
        if(hero==null){
            synchronized (Hero.class){
                if(hero==null){
                    hero=new Hero();
                }
            }
        }
        return hero;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveUp(){
        setY(getY()-speed);
    }
    public void moveRight(){
        setX(getX()+speed);
    }
    public void moveDown(){
        setY(getY()+speed);
    }
    public void moveLeft(){
       setX(getX()-speed);
    }
    //射击
    public void shot(){
        shotBehavior.Bshot(zidans,getX(),getY(),getDirect());
    }
}
