package Enemy;

public class DaodanCar extends Enemy{


    public DaodanCar(int x, int y, int direct) {
        super(x, y, direct);
        shotBehavior=new daodanshot();
    }
}
