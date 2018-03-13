package game.console;

import java.util.List;

public class ResetCommand extends AbstractCommand {

    public ResetCommand() {
        super("reset");
    }

    @Override
    public void execute(GameModel model, List<String> parameters) {
        model.reset();
    }

}
