package game.object;

import java.util.Objects;

public class Rock extends AbstractWeapon {

    private static Rock rock;

    public static Rock getInstance() {
        if (Objects.isNull(rock)) {
            rock = new Rock();
        }

        return rock;
    }

    private Rock() {
        super("rock");
    }

    @Override
    public boolean bittenBy(Weapon weapon) {
        if (weapon instanceof Paper) {
            return true;
        }
        return false;
    }

}
