package Equipment;

import Tank.*;

public class upSpeed extends Equip{
    Equip hero;
    public static String name="upSpeed";
    public upSpeed(int x, int y) {
        super(x, y);
    }
    public upSpeed(Equip hero){
        this.hero=hero;
        hero=(Hero)hero;
        ((Hero) hero).setSpeed(10);
    }

}
