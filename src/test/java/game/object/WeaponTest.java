package game.object;


import org.testng.annotations.Test;

public class WeaponTest {

    @Test
    public void shouldFollowRules() {
        Rock.getInstance().bittenBy(Paper.getInstance());
        Paper.getInstance().bittenBy(Scissors.getInstance());
        Scissors.getInstance().bittenBy(Rock.getInstance());
    }
}