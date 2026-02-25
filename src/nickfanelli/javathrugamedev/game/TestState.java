package nickfanelli.javathrugamedev.game;

import nickfanelli.javathrugamedev.engine.state.GameState;

import java.awt.*;

public class TestState extends GameState {

    public TestState() {
        
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onUpdate(float deltaTime) {

    }

    @Override
    public void onRender(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(10, 10, 100, 100);
    }

    @Override
    public void onEnd() {

    }

}
