package Panel;

import Equipment.Equip;
import zidan.Zidan;
import Enemy.Enemy;
import Tank.Hero;
import Tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;
import Enemy.*;
import zidan.*;
import Equipment.*;
//为了使Pannel可以一直重绘子弹，所以也要使用线程
public class MyPanel extends JPanel implements KeyListener,Runnable{

    Hero hero=null;
    int enemySize=3;
    Enemy black=null;
    Enemy daodan=null;
    Enemy tesla=null;
    Equip equip1=null;
    Equip equip2=null;
    Equip equip3=null;
    public MyPanel(){
        hero = Hero.getInstance();//初始位置,初始化我的坦克
        hero.setX(400);
        hero.setY(300);
       // for(int i=0;i<enemySize;i++){
//            Enemy enemy=new Enemy((100*(i+1)),0,2);
             black=new BlackHawk(100,10,2);
             daodan=new DaodanCar(300,10,2);
             tesla=new TeslaCar(500,10,2);
            new Thread(black).start();
            new Thread(daodan).start();
            new Thread(tesla).start();
            //添加子弹
//            Zidan enemyzidan=new Zidan(enemy.getX()+20,enemy.getY()+60,enemy.getDirect());
//            enemy.zidans.add(enemyzidan);
            //启动子弹对象
            Enemys.add(black);
            Enemys.add(daodan);
            Enemys.add(tesla);
        }

  //  }
    Vector<Enemy> Enemys=new Vector<>();

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(94,162,94));
        g.fillRect(0,0,800,550);//绘制矩形
        drawWay(g);
        if(hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
            System.out.println(hero.getEquips());
            if(hero.getEquips().contains("upSpeed")){
//                drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
                Image i=Toolkit.getDefaultToolkit().getImage("src/xiong.png");
                g.drawImage(i,hero.getX()-25,hero.getY()-10,20,20, this);
            }
            if(hero.getEquips().contains("Duofa")){
                Image i=Toolkit.getDefaultToolkit().getImage("src/dao.png");
                g.drawImage(i,hero.getX()-25,hero.getY()+30,20,20, this);
            }
            if(hero.getEquips().contains("Dianci")){
                Image i=Toolkit.getDefaultToolkit().getImage("src/buji.png");
                g.drawImage(i,hero.getX()+45,hero.getY()-10,20,20, this);
            }
        }
        //死亡掉落装备
        if(black.isLive()==false){
            if(equip1==null)
             equip1=new upSpeed(black.getX(),black.getY());
             else if(equip1.isLive()==true) {
                 drawEquip(black.getX(), black.getY(), g, 0);
             }
        }
        if(daodan.isLive()==false){
            if(equip2==null)
            equip2=new Duofa(daodan.getX(),daodan.getY());
            else if(equip2.isLive()==true) {
                drawEquip(daodan.getX(), daodan.getY(), g, 1);
            }
        }
        if(tesla.isLive()==false){
            if(equip3==null)
            equip3=new Dianci(tesla.getX(),tesla.getY());
            else if(equip3.isLive()==true) {
                drawEquip(tesla.getX(), tesla.getY(), g, 2);
            }
        }
        //绘制hero的子弹
        if(hero.isLive()) {
            for (int i = 0; i < hero.zidans.size(); i++) {
                Zidan z = hero.zidans.get(i);
                if (z != null && z.isLive() == true) {
                    int x = z.getX();
                    int y = z.getY();
                    int direct = z.getDirect();
                   drawZidan(z,g);
                } else {
                    hero.zidans.remove(i);
                }
            }
        }
        for(int i=0;i<Enemys.size();i++) {
            if (Enemys.get(i).isLive()) {
                Enemy e = Enemys.get(i);
                int x = e.getX();
                int y = e.getY();
                int direct=e.getDirect();
                //黑熊战车为1，导弹车为2，电磁炮为3
                if(e instanceof BlackHawk)
                    drawTank(x, y, g, direct, 1);
                else if(e instanceof DaodanCar)
                    drawTank(x, y, g, direct, 2);
                else if(e instanceof TeslaCar)
                    drawTank(x, y, g, direct, 3);
                //画出敌人子弹
                for (int j = 0; j < e.zidans.size(); j++) {
                    Zidan zidan = e.zidans.get(j);
                    //绘制
                    if (zidan.isLive()) {
                        //drawZidan(zidan.getX(),zidan.getY(),zidan.getDirect(),g);
                        drawZidan(zidan,g);
                        //g.draw3DRect(zidan.getX(), zidan.getY(), 1, 1, false);
                    } else {
                        e.zidans.remove(j);
                    }
                }
            }
        }
    }

    //绘制掉落的装备
    public void drawEquip(int x,int y,Graphics g,int type){
        switch(type){
            case 0:
                Image i=Toolkit.getDefaultToolkit().getImage("src/xiong.png");
                g.drawImage(i,x,y,20,20, this);
                break;
            case 1:
                Image i2=Toolkit.getDefaultToolkit().getImage("src/dao.png");
                g.drawImage(i2,x,y,20,20, this);
                break;
            case 2:
                Image i3=Toolkit.getDefaultToolkit().getImage("src/buji.png");
                g.drawImage(i3,x,y,20,20, this);
                break;
        }
    }
    //绘制子弹：
    public void drawZidan(Zidan z,Graphics g){
        int x=z.getX();
        int y=z.getY();
        int direct=z.getDirect();
        if(z instanceof hawk){
            g.draw3DRect(x,y,15,15,true);
        }else if(z instanceof tesla){
            g.drawOval(x,y,18,18);
        }else if(z instanceof daodan){
            g.draw3DRect(x,y,8,8,false);
        }else if(z instanceof herozi){
            if(hero.getEquips().contains("Duofa")) {
                g.draw3DRect(x-10, y, 1, 1, true);
                g.draw3DRect(x, y, 1, 1, true);
            }else
                g.draw3DRect(x, y, 1, 1, true);
        }
    }
    //画坦克
    //x,y表示坦克左上角的坐标
    //g 画笔
    //direct 坦克方向
    //type 坦克类型
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch(type){
            case 0://0表示自己的坦克
                g.setColor(Color.RED);
                switch(direct){
                    case 0://0表示向上
                        g.fill3DRect(x,y,10,60,false);//左边的轮子
                        g.fill3DRect(x+10,y+10,20,40,false);//身体
                        g.fill3DRect(x+30,y,10,60,false);//右边的轮子
                        g.fillOval(x+10,y+20,20,20);//
                        g.fill3DRect(x+19,y,2,20,false);
                        break;
                    case 1:
                        g.fill3DRect(x-10,y+10,60,10,false);
                        g.fill3DRect(x,y+20,40,20,false);
                        g.fill3DRect(x-10,y+40,60,10,false);
                        g.fillOval(x+10,y+20,20,20);
                        g.fill3DRect(x+30,y+29,20,2,false);
                        break;
                    case 2:
                        g.fill3DRect(x,y,10,60,false);//左边的轮子
                        g.fill3DRect(x+10,y+10,20,40,false);//身体
                        g.fill3DRect(x+30,y,10,60,false);//右边的轮子
                        g.fillOval(x+10,y+20,20,20);//
                        g.fill3DRect(x+19,y+40,2,20,false);
                        break;
                    case 3:
                        g.fill3DRect(x-10,y+10,60,10,false);
                        g.fill3DRect(x,y+20,40,20,false);
                        g.fill3DRect(x-10,y+40,60,10,false);
                        g.fillOval(x+10,y+20,20,20);
                        g.fill3DRect(x-10,y+29,20,2,false);
                        break;
                }
                break;
            case 1://敌人坦克
                g.setColor(Color.black);
                switch(direct){
                    case 0://0表示向上
                        g.fill3DRect(x, y, 10, 10, false);//左边的轮子1
                        g.fill3DRect(x, y + 30, 10, 10, false);//左边的轮子2
                        g.fill3DRect(x + 10, y + 10, 20, 20, false);//身体
                        g.fillOval(x + 10, y , 20, 20);//黑熊的头
                        g.fill3DRect(x + 30, y, 10, 10, false);//右边的轮子
                        g.fill3DRect(x + 30, y + 30, 10, 10, false);
                        break;
                    case 1:
                        g.fill3DRect(x, y, 10, 10, false);//左边的轮子1
                        g.fill3DRect(x, y + 30, 10, 10, false);//左边的轮子2
                        g.fill3DRect(x + 10, y + 10, 20, 20, false);//身体
                        g.fillOval(x + 20, y + 10, 20, 20);//黑熊的头
                        g.fill3DRect(x + 30, y, 10, 10, false);//右边的轮子
                        g.fill3DRect(x + 30, y + 30, 10, 10, false);
                        break;
                    case 2:
                        g.fill3DRect(x, y, 10, 10, false);//左边的轮子1
                        g.fill3DRect(x, y + 30, 10, 10, false);//左边的轮子2
                        g.fill3DRect(x + 10, y + 10, 20, 20, false);//身体
                        g.fillOval(x + 10, y + 20, 20, 20);//黑熊的头
                        g.fill3DRect(x + 30, y, 10, 10, false);//右边的轮子
                        g.fill3DRect(x + 30, y + 30, 10, 10, false);
                        break;
                    case 3:
                        g.fill3DRect(x, y, 10, 10, false);//左边的轮子1
                        g.fill3DRect(x, y + 30, 10, 10, false);//左边的轮子2
                        g.fill3DRect(x + 10, y + 10, 20, 20, false);//身体
                        g.fillOval(x , y + 10, 20, 20);//黑熊的头
                        g.fill3DRect(x + 30, y, 10, 10, false);//右边的轮子
                        g.fill3DRect(x + 30, y + 30, 10, 10, false);
                        break;
                }
                break;
            case 2:
                g.setColor(Color.blue);
                switch(direct){
                    case 0://0表示向上
                        g.fillOval(x,y,10,10);
                        g.fillOval(x+30,y,10,10);
                        g.fillOval(x,y+30,10,10);
                        g.fillOval(x+30,y+30,10,10);

                        g.fillOval(x+10,y-25,20,75);
                        break;
                    case 1:
                        g.fillOval(x,y,10,10);
                        g.fillOval(x+30,y,10,10);
                        g.fillOval(x,y+30,10,10);
                        g.fillOval(x+30,y+30,10,10);

                        g.fillOval(x-10,y+10,75,20);
                        break;
                    case 2:
                        g.fillOval(x,y,10,10);
                        g.fillOval(x+30,y,10,10);
                        g.fillOval(x,y+30,10,10);
                        g.fillOval(x+30,y+30,10,10);

                        g.fillOval(x+10,y-10,20,75);
                        break;
                    case 3:
                        g.fillOval(x,y,10,10);
                        g.fillOval(x+30,y,10,10);
                        g.fillOval(x,y+30,10,10);
                        g.fillOval(x+30,y+30,10,10);

                        g.fillOval(x-25,y+10,75,20);
                        break;
                }
                break;
            case 3:
                g.setColor(Color.white);
                switch(direct){
                    case 0://0表示向上
                        g.fill3DRect(x,y,10,10,false);
                        g.fill3DRect(x-3,y+10,16,16,false);
                        g.fill3DRect(x-7,y+20,24,24,false);
                        break;
                    case 1:
                        g.fill3DRect(x,y,24,24,false);
                        g.fill3DRect(x+24,y+4,16,16,false);
                        g.fill3DRect(x+40,y+7,10,10,false);
                        break;
                    case 2:
                        g.fill3DRect(x,y,24,24,false);
                        g.fill3DRect(x+4,y+24,16,16,false);
                        g.fill3DRect(x+7,y+40,10,10,false);
                        break;
                    case 3:
                        g.fill3DRect(x,y,10,10,false);
                        g.fill3DRect(x+10,y-3,16,16,false);
                        g.fill3DRect(x+26,y-7,24,24,false);
                        break;
                }
                break;
        }
    }

    //判断是否吃到了掉落的装备
    public void eatEquip(Equip hero,Equip equip){
        if(hero.getX()>equip.getX()-30&&hero.getX()<equip.getX()+30&&hero.getY()>equip.getY()-30&&hero.getY()<equip.getY()+30){
            equip.setLive(false);
            System.out.println("吃到装备了");
            System.out.println(equip.isLive());
            if(equip instanceof upSpeed){
                hero.getEquips().add(upSpeed.name);//先加入在转型！
                hero=new upSpeed(hero);//装饰者模式
                System.out.println(hero.getEquips());
            }else if(equip instanceof Duofa){
                hero.getEquips().add(Duofa.name);//先加入在转型！
                hero=new Duofa(hero);//装饰者模式
                System.out.println(hero.getEquips());
            }else if(equip instanceof Dianci){
                hero.getEquips().add(Dianci.name);//先加入在转型！
                hero=new Dianci(hero);//装饰者模式
                System.out.println(hero.getEquips());
            }
        }
    }
    //判断我方子弹是否几中敌方坦克
    public void hitTank(Zidan z, Tank e){
        if(e instanceof Enemy) {
            switch (e.getDirect()) {
                case 0:
                case 2:
                    if (z.getX() > e.getX() && z.getX() < e.getX() + 40
                            && z.getY() > e.getY() && z.getY() < e.getY() + 60) {
                        z.setLive(false);
                        e.setLive(false);
                        Enemys.remove(e);
                    }
                    break;
                case 1:
                case 3:
                    if (z.getX() > e.getX() && z.getX() < e.getX() + 60
                            && z.getY() > e.getY() && z.getY() < e.getY() + 40) {
                        z.setLive(false);
                        e.setLive(false);
                        Enemys.remove(e);
                    }
                    break;
            }
        }
        else if(e instanceof Hero){
            switch (e.getDirect()) {
                case 0:
                case 2:
                    if (z.getX() > e.getX() && z.getX() < e.getX() + 40
                            && z.getY() > e.getY() && z.getY() < e.getY() + 60) {
                        z.setLive(false);
                        e.setLive(false);
                    }
                    break;
                case 1:
                case 3:
                    if (z.getX() > e.getX() && z.getX() < e.getX() + 60
                            && z.getY() > e.getY() && z.getY() < e.getY() + 40) {
                        z.setLive(false);
                        e.setLive(false);
                    }
                    break;
            }
        }
    }
    //有字符输出时，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //玩法绘制
    public void drawWay(Graphics g){
        g.setColor(Color.black);
        g.drawString("w:上 a:左 s:下 d:右",600,30);
        g.drawString("j攻击,打败他们可以通过1 2 3来切换攻击效果",500,50);
        if(hero.isLive()==false){
            g.setColor(Color.RED);
            g.drawString("失败了",400,250);
        }
    }
    //当某个键按下，会触发
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_W) {
            hero.setDirect(0);
            if(hero.getY()>0) {
                hero.moveUp();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_S) {
            hero.setDirect(2);
            if(hero.getY()+60<550) {
                hero.moveDown();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_A) {
            hero.setDirect(3);
            if(hero.getX()>0) {
                hero.moveLeft();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_D) {
            hero.setDirect(1);
            if(hero.getX()+60<800) {
                hero.moveRight();
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_J){
            if(hero.isLive())
            hero.shot();
        }
        else if(e.getKeyCode()==KeyEvent.VK_1&&black.isLive()==false){
            hero.shotBehavior=new hawkshot();
        }
        else if(e.getKeyCode()==KeyEvent.VK_2&&daodan.isLive()==false){
            hero.shotBehavior=new daodanshot();
        }
        else if(e.getKeyCode()==KeyEvent.VK_3&&tesla.isLive()==false){
            hero.shotBehavior=new teslashot();
        }
//        System.out.println((char)e.getKeyCode()+"被按下了");
       // this.repaint();
    }

    //当某个键释放，会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void run() {//每隔100毫秒,重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (hero.isLive()) {
                if (equip1 != null && equip1.isLive() == true) {
                    eatEquip(hero, equip1);
                }
                if (equip2 != null && equip2.isLive() == true) {
                    eatEquip(hero, equip2);
                }
                if (equip3 != null && equip3.isLive() == true) {
                        eatEquip(hero, equip3);
                    }
                    for (int i = 0; i < Enemys.size(); i++) {
                        Enemy enemy = Enemys.get(i);
                        if (enemy.zidans.size() <= 3) {
                            switch (enemy.getDirect()) {
                                case 0:
                                    if (hero.getX() >= enemy.getX() - 40 && hero.getX() <= enemy.getX() + 40 && hero.getY() < enemy.getY()) {
                                        enemy.shot();
                                    }
                                    break;
                                case 1:
                                    if (hero.getY() >= enemy.getY() - 40 && hero.getY() <= enemy.getY() + 40 && hero.getX() > enemy.getX()) {
                                        enemy.shot();
                                    }
                                    break;
                                case 2:
                                    if (hero.getX() >= enemy.getX() - 40 && hero.getX() <= enemy.getX() + 40 && hero.getY() > enemy.getY()) {
                                        enemy.shot();
                                    }
                                    break;
                                case 3:
                                    if (hero.getY() >= enemy.getY() - 40 && hero.getY() <= enemy.getY() + 40 && hero.getX() < enemy.getX()) {
                                        enemy.shot();
                                    }
                                    break;
                            }
                        }
                    }
                }

                //判断是否击中敌方坦克
                for (int j = 0; j < hero.zidans.size(); j++) {
                    Zidan zidan = hero.zidans.get(j);
                    if (zidan != null && zidan.isLive()) {//我的子弹还存活
                        // 遍历敌人所有坦克
                        for (int i = 0; i < Enemys.size(); i++) {
                            Enemy e = Enemys.get(i);
                            hitTank(zidan, e);
                        }
                    }
                }
                //判断是否被敌人击中
                for (int i = 0; i < Enemys.size(); i++) {
                    Enemy e = Enemys.get(i);
                    for (int j = 0; j < e.zidans.size(); j++) {
                        Zidan z = e.zidans.get(j);
                        hitTank(z, hero);
                    }
                }
                this.repaint();
            }
        }
    }
