package game.console;

import java.util.List;

public class ModeCommand extends AbstractCommand {

    public ModeCommand() {
        super("mode");
    }

    @Override
    public void execute(GameModel model, List<String> parameters) {
        if (parameters.size() == 0) {
            return;
        }

        switch (parameters.get(0)) {
            case "playerVsComputer":
                if (!model.isPlayerVsComputerMode) {
                    model.reset();
                }
                model.isPlayerVsComputerMode = true;
                break;
            case "computerVsComputer":
                if (model.isPlayerVsComputerMode) {
                    model.reset();
                }
                model.isPlayerVsComputerMode = false;
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + parameters.get(0));
        }
    }

    @Override
    public String toString() {
        return "mode <playerVsComputer or computerVsComputer>";
    }

}
