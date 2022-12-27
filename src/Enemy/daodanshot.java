package Enemy;

import zidan.Zidan;
import zidan.daodan;

import java.util.Vector;

public class daodanshot implements ShotBehavior {

    @Override
    public void Bshot(Vector<Zidan> zidans, int x, int y, int direct) {
        Zidan z=null;
        switch (direct) {
            case 0:
                z = new daodan(x + 20, y, 0);
                break;
            case 1:
                z = new daodan(x + 60, y + 30, 1);
                break;
            case 2:
                z = new daodan(x + 20, y + 60, 2);
                break;
            case 3:
                z = new daodan(x, y + 30, 3);
                break;
        }
        zidans.add(z);
        new Thread(z).start();
    }
}
