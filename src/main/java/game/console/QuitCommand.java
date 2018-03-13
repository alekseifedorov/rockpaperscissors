package game.console;

import java.util.List;

public class QuitCommand extends AbstractCommand {

    public QuitCommand() {
        super("quit");
    }

    @Override
    public void execute(GameModel model, List<String> parameters) {
        model.stopGame = true;
    }
}
