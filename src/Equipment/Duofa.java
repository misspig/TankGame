package Equipment;

import Tank.*;

public class Duofa extends Equip{
    Equip hero;
    public static String name="Duofa";
    public Duofa(int x, int y) {
        super(x, y);
    }
    public Duofa(Equip hero){
        this.hero=hero;
    }
}
