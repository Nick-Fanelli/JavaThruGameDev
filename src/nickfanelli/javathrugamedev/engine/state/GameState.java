package nickfanelli.javathrugamedev.engine.state;

import nickfanelli.javathrugamedev.engine.io.InputContextAdapter;

import java.awt.*;

public abstract class GameState {

    protected final StateApplicationAdapter applicationAdapter;
    protected final InputContextAdapter input;

    public GameState(StateApplicationAdapter applicationAdapter) {
        this.applicationAdapter = applicationAdapter;
        this.input = applicationAdapter.getInputContextAdapter();
    }

    public abstract void onBegin();

    public abstract void onUpdate(float deltaTime);
    public abstract void onRender(Graphics2D g);

    public abstract void onEnd();

}
