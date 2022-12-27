package Enemy;

import zidan.Zidan;
import Tank.Tank;

import java.util.Vector;

public abstract class Enemy extends Tank implements Runnable{

    private int speed=1;
    public Vector<Zidan>zidans=new Vector<>();
    public Enemy(int x, int y, int direct) {
        super(x, y, direct);
    }

    //抽象设计方法，所有敌人都能射击
    public  void shot(){
        shotBehavior.Bshot(zidans,this.getX(),this.getY(),this.getDirect());
    }
    public void run(){
        while(true){
            //根据坦克的方向继续移动
            switch(getDirect()){
                case 0:
                    for(int i=0;i<20;i++) {
                        if(getY()>0) {
                            moveUp();
                        }else{
                            setDirect((int)(Math.random()*4));
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for(int i=0;i<20;i++) {
                        if(getX()+60<800)
                        moveRight();
                        else{
                            setDirect((int)(Math.random()*4));
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<20;i++) {
                        if(getY()+60<550)
                        moveDown();
                        else{
                            setDirect((int)(Math.random()*4));
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for(int i=0;i<20;i++) {
                        if(getX()>0) {
                            moveLeft();
                        }else{
                            setDirect((int)(Math.random()*4));
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //然后随机的改变坦克的方向
            setDirect((int)(Math.random()*4));
            if(isLive()==false)
                break;
        }
    }
}
