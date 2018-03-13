package game.object;

public abstract class AbstractWeapon implements Weapon {

    protected String name;

    public AbstractWeapon(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
