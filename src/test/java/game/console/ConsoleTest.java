package game.console;


import game.object.Paper;
import game.object.Rock;
import game.object.Scissors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {

    private Console console;

    @BeforeMethod
    public void setUp() throws Exception {
        console = Console.getConsole();
        console.gameModel.reset();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfWeaponDuplicates() {
        console.gameModel.addWeapons(Arrays.asList(Rock.getInstance(), Rock.getInstance(), Paper.getInstance(), Scissors.getInstance()));
    }

    @Test
    public void shouldHaveMandatoryWeapons() {
        assertThat(console.gameModel.weapons).contains(Paper.getInstance(), Rock.getInstance(), Scissors.getInstance());
    }

    @Test
    public void shouldReset() {
        Optional<Command> reset = console.gameModel.commands.stream().filter(c -> c instanceof ResetCommand).findAny();
        assertThat(reset).isNotEmpty();
        console.gameModel.defeatCounter = 10;
        reset.get().execute(console.gameModel, Collections.emptyList());
        assertThat(console.gameModel.defeatCounter).isEqualTo(0);
    }

    @Test
    public void shouldChangeMode() {
        Optional<Command> modeCommand = console.gameModel.commands.stream().filter(c -> c instanceof ModeCommand).findAny();
        assertThat(modeCommand).isNotEmpty();
        console.gameModel.isPlayerVsComputerMode = true;
        modeCommand.get().execute(console.gameModel, Arrays.asList("computerVsComputer"));
        assertThat(console.gameModel.isPlayerVsComputerMode).isFalse();
        modeCommand.get().execute(console.gameModel, Arrays.asList("playerVsComputer"));
        assertThat(console.gameModel.isPlayerVsComputerMode).isTrue();
    }

    @Test
    public void shouldQuit() {
        Optional<Command> quitCommand = console.gameModel.commands.stream().filter(c -> c instanceof QuitCommand).findAny();
        assertThat(quitCommand).isNotEmpty();
        assertThat(console.gameModel.stopGame).isFalse();
        quitCommand.get().execute(console.gameModel, Collections.emptyList());
        assertThat(console.gameModel.stopGame).isTrue();
    }
}
