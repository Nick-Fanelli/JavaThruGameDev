package nickfanelli.javathrugamedev.engine.state;

import nickfanelli.javathrugamedev.engine.graphics.GameApplication;
import nickfanelli.javathrugamedev.engine.io.InputContextAdapter;

public class StateApplicationAdapter {

    private final GameApplication gameApplication;
    private final InputContextAdapter inputContextAdapter;

    public StateApplicationAdapter(GameApplication application) {
        this.gameApplication = application;
        this.inputContextAdapter = new InputContextAdapter(this.gameApplication.getInputContext());
    }

    public <T extends GameState> void requestChangeGameStateTo(Class<T> stateClass) { this.gameApplication.setGameState(stateClass); }

    public InputContextAdapter getInputContextAdapter() { return this.inputContextAdapter; }


}
