package game.object;

import java.util.Objects;

public class Scissors extends AbstractWeapon {

    private static Scissors scissors;

    public static Scissors getInstance() {
        if (Objects.isNull(scissors)) {
            scissors = new Scissors();
        }

        return scissors;
    }

    private Scissors() {
        super("scissors");
    }


    @Override
    public boolean bittenBy(Weapon weapon) {
        if (weapon instanceof Rock) {
            return true;
        }
        return false;
    }

}
