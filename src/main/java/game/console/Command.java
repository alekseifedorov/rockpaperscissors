package game.console;

import java.util.List;

public interface Command {

    void execute(GameModel model, List<String> parameters);

    String getName();
}
