package game.object;

import java.util.Objects;

public class Paper extends AbstractWeapon {

    private static Paper paper;

    public static Paper getInstance() {
        if (Objects.isNull(paper)) {
            paper = new Paper();
        }

        return paper;
    }

    private Paper() {
        super("paper");
    }

    @Override
    public boolean bittenBy(Weapon weapon) {
        if (weapon instanceof Scissors) {
            return true;
        }
        return false;
    }

}
