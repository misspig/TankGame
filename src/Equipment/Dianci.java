package Equipment;

public class Dianci extends Equip{
    Equip hero;
    public static String name="Dianci";
    public Dianci(int x, int y) {
        super(x, y);
    }
    public Dianci(Equip hero){
        this.hero=hero;
    }
}
