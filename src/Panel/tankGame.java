package Panel;


import javax.swing.*;

public class tankGame extends JFrame {
    //定义MyPanel
    MyPanel mp=null;
    public static void main(String[]args){

        tankGame tg=new tankGame();
    }

    public tankGame(){
        mp=new MyPanel();
        Thread thread=new Thread(mp);
        thread.start();
        this.add(mp);//把面板放入窗口中
        this.setSize(800,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
    }
}
