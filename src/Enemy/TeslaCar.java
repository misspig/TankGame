package Enemy;

public class TeslaCar extends Enemy{

    public TeslaCar(int x, int y, int direct) {
        super(x, y, direct);
        shotBehavior=new teslashot();
    }

}
