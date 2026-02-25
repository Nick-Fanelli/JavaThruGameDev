package nickfanelli.javathrugamedev.game;

import nickfanelli.javathrugamedev.engine.state.GameState;
import nickfanelli.javathrugamedev.engine.state.StateApplicationAdapter;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestState extends GameState {

    private boolean shouldPaintRed = false;

    public TestState(StateApplicationAdapter stateApplicationAdapter) {
        super(stateApplicationAdapter);
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        this.shouldPaintRed = super.input.isKey(KeyEvent.VK_SPACE);

    }

    @Override
    public void onRender(Graphics2D g) {
        g.setColor(shouldPaintRed ? Color.RED : Color.WHITE);
        g.fillRect(10, 10, 100, 100);
    }

    @Override
    public void onEnd() {

    }

}
