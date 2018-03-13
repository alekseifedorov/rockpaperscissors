package game.console;

import game.object.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class holds game state
 */
public class GameModel {

    List<Weapon> weapons = new ArrayList<>();

    List<Command> commands = new ArrayList<>();

    String status;

    int defeatCounter;

    int winCounter;

    int drawCounter;

    private Random random;

    boolean isPlayerVsComputerMode;

    boolean stopGame;

    public GameModel(List<Weapon> weapons, List<Command> commands) {
        addWeapons(weapons);
        addCommands(commands);
        init();
    }

    private void init() {
        random = new Random();
        status = "";
        isPlayerVsComputerMode = true;
    }

    void addCommands(List<Command> commands) {
        this.commands.addAll(commands);
    }

    void addWeapons(List<Weapon> weapons) {
        if (weapons.size() != weapons.stream().distinct().count()) {
            throw new IllegalArgumentException("Duplicate names: " + weapons);
        }
        this.weapons.addAll(weapons);
    }

    void checkWhoWin(Weapon myWeapon, Weapon opponentWeapon) {
        if (myWeapon == opponentWeapon) {
            drawCounter++;
            status = "Draw";
        } else if (myWeapon.bittenBy(opponentWeapon)) {
            defeatCounter++;
            status = "You lost";
        } else {
            winCounter++;
            status = "You win";
        }
        status = String.format("%s vs %s. %s", myWeapon, opponentWeapon, status);
    }

    public Weapon randomWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }

    public void reset() {
        random = new Random();
        winCounter = 0;
        defeatCounter = 0;
        drawCounter = 0;
        status = "";
    }
}
