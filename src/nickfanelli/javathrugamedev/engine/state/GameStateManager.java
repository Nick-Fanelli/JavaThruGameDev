package nickfanelli.javathrugamedev.engine.state;

import nickfanelli.javathrugamedev.engine.graphics.GameApplication;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameStateManager {

    private GameState activeState = null;

    public <T extends GameState> void setState(Class<T> stateClass, GameApplication requestingApplication) throws Exception {

        StateApplicationAdapter adapter = new StateApplicationAdapter(requestingApplication);
        T newState = stateClass.getDeclaredConstructor(StateApplicationAdapter.class).newInstance(adapter);

        // End current state
        if (this.activeState != null) {
            this.activeState.onEnd();
        }

        // Switch state
        this.activeState = newState;
        this.activeState.onBegin();
    }


    public void update(float deltaTime) {
        if(this.activeState != null) {
            this.activeState.onUpdate(deltaTime);
        }
    }

    public void render(Graphics2D g) {
        if(this.activeState != null) {
            this.activeState.onRender(g);
        }
    }

    public void end() {
        if(this.activeState != null) {
            this.activeState.onEnd();
            this.activeState = null;
        }
    }

}
