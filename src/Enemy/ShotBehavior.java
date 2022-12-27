package Enemy;

import zidan.Zidan;

import java.util.Vector;

public interface ShotBehavior {
    void Bshot(Vector<Zidan> zidans,int x,int y,int direct);
}
