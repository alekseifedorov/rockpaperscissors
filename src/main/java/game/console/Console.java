package game.console;

import game.object.Paper;
import game.object.Rock;
import game.object.Scissors;
import game.object.Weapon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class prints a current game state and gets user input
 */
public class Console {

    private static Console console;

    GameModel gameModel;

    public static Console getConsole() {
        if (Objects.isNull(console)) {
            console = new Console();
        }

        return console;
    }

    private Console() {
        gameModel = new GameModel(Arrays.asList(Rock.getInstance(), Paper.getInstance(), Scissors.getInstance()),
                Arrays.asList(new QuitCommand(), new ResetCommand(), new ModeCommand()));
    }

    public void start() {
        while (!gameModel.stopGame) {
            try {
                nextStep();
            } catch (Exception e) {
                gameModel.status = e.toString();
            }
        }
    }

    public void nextStep() throws IOException {
        clearScreen();
        System.out.println(String.format("Status: %s", gameModel.status));
        System.out.println(String.format("Available weapons: %s", gameModel.weapons));
        System.out.println(String.format("Available commands: %s", gameModel.commands));
        System.out.println(String.format("Mode: %s", gameModel.isPlayerVsComputerMode ? "HumanVsComputer" : "ComputerVsComputer"));
        System.out.println(String.format("Wins: %s; Defeats: %s; Draws: %s", gameModel.winCounter, gameModel.defeatCounter, gameModel.drawCounter));
        System.out.print(String.format("Type the command or the name of a weapon > "));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        executeCommand(reader.readLine());
    }

    public void executeCommand(String cmd) {
        String[] splitCommand = cmd.split(" ");

        Optional<Weapon> myWeapon = gameModel.weapons.stream()
                                                     .filter(w -> w.getName().equals(splitCommand[0].toLowerCase()))
                                                     .findAny();



        Optional<Command> command = gameModel.commands.stream()
                          .filter(c -> c.getName().equals(splitCommand[0].toLowerCase()))
                          .findAny();
        command.ifPresent( c -> c.execute(gameModel, splitCommand.length > 1 ? Stream.of(splitCommand).skip(1).collect(Collectors.toList()) : Collections.emptyList()));

        if (!gameModel.isPlayerVsComputerMode) {
            myWeapon = Optional.of(gameModel.randomWeapon());
        }

        if (myWeapon.isPresent()) {
            Weapon opponentWeapon = gameModel.randomWeapon();
            gameModel.checkWhoWin(myWeapon.get(), opponentWeapon);
            return;
        }

        if(!command.isPresent() && !myWeapon.isPresent()) {
            throw new IllegalArgumentException("Unknown command or weapon: " + splitCommand[0]);
        }

    }

    public void clearScreen() {
        IntStream.range(0, 100).forEach(i -> System.out.println());
    }
}