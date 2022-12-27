package Enemy;

import zidan.Zidan;
import zidan.hawk;

import java.util.Vector;

public class hawkshot implements ShotBehavior,Runnable{

    public void Bshot(Vector< Zidan > zidans,int x,int y,int direct){
            Zidan z=null;
            switch (direct) {
                case 0:
                    z = new hawk(x + 20, y, 0);
                    break;
                case 1:
                    z = new hawk(x + 60, y + 30, 1);
                    break;
                case 2:
                    z = new hawk(x + 20, y + 60, 2);
                    break;
                case 3:
                    z = new hawk(x, y + 30, 3);
                    break;
            }
            zidans.add(z);
            new Thread(z).start();
        }
        public void run(){

        }
}
