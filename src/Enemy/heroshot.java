package Enemy;

import zidan.Zidan;
import zidan.herozi;

import java.util.Vector;

public class heroshot implements ShotBehavior {

    @Override
    public void Bshot(Vector<Zidan> zidans, int x, int y, int direct) {
        Zidan zidan=null;
        switch(direct){
            case 0:
                zidan=new herozi(x+19,y,direct);
                break;
            case 1:
                zidan=new herozi(x+49,y+30,direct);
                break;
            case 2:
                zidan=new herozi(x+19,y+60,direct);
                break;
            case 3:
                zidan=new herozi(x-10,y+29,direct);
                break;
        }
        //把新建的zidan放入到vector中
        zidans.add(zidan);
        new Thread(zidan).start();
    }
}
