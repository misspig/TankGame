package Enemy;

public class BlackHawk extends Enemy{
    public BlackHawk(int x, int y, int direct) {
        super(x, y, direct);
        shotBehavior=new hawkshot();
        //ddd
    }
}
